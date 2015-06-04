package com.taobao.protobuf.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import _util.Util;
import cn.javaplus.plugins.console.ConsoleFactory;
import cn.javaplus.plugins.console.Debuger;

import com.taobao.protobuf.Activator;
import com.taobao.protobuf.editors.D;
import com.taobao.protobuf.popmenu.Location;

public class Generator {

	private final String	MARKER_ID	= Activator.PLUGIN_ID + ".pbmarker";

	/**
	 * @param protocDir
	 *            protoc.exe 的目录, 如果要生成as代码, 那么必须保证 "protoc-gen-as3.bat" 和
	 *            "protoc-gen-as3.jar" 在这个目录下, 否则不会生成成功
	 * @param f
	 *            被生成的文件
	 * @param type
	 *            这个参数来自 D.LanguageArg.*
	 * @param plugin
	 *            如果是AS代码的话, 需要传入插件生成参数
	 * @param generateDst
	 *            生成代码的目标文件夹
	 */
	public void generateFile(String protocDir, IFile f, String type, String plugin, String generateDst) {

		IContainer container = f.getParent();
		String parent = container.getLocation().toOSString();

		String name = parent + File.separator + f.getName();
		String executeCmd = protocDir + File.separator + D.FileName.PROTOC + " --proto_path=" + parent + File.separator + " " + plugin + type + "=" + generateDst + File.separator + " " + name;

		try {

			Process process = Runtime.getRuntime().exec(executeCmd, null, new File(protocDir));
			List<Location> list = processStream(process.getErrorStream(), f);
			if (list != null && list.size() > 0) {
				createMarkers(list, f);
			} else {
				try {
					f.deleteMarkers(MARKER_ID, true, IResource.DEPTH_INFINITE);
				} catch (CoreException e1) {
					throw Util.toRuntimeException(e1);
				}
			}
			process.destroy();

			Debuger.debug(type + " generate success ! dest path:" + generateDst);
			Debuger.debug("command:" + executeCmd);

		} catch (Exception e) {
			throw Util.toRuntimeException(e);
		}
	}

	private void createMarkers(List<Location> list, IFile file) {
		try {
			file.deleteMarkers(MARKER_ID, true, IResource.DEPTH_INFINITE);
		} catch (CoreException e1) {
			throw Util.toRuntimeException(e1);
		}
		for (Location location : list) {
			Debuger.debug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX:");
			Debuger.debug(location.getMsg());
			Debuger.debug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			Debuger.debug("");

			IFile f = location.getFile();
			try {
				IMarker marker = f.createMarker(MARKER_ID);
				marker.setAttribute(IMarker.LINE_NUMBER, location.getLine());
				marker.setAttribute(IMarker.MESSAGE, location.getMsg());
				marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
			} catch (CoreException e) {
				e.printStackTrace(ConsoleFactory.getOut());
			}
		}
	}

	private List<Location> processStream(InputStream stream, IFile f) {
		InputStreamReader isreader = new InputStreamReader(stream);
		BufferedReader reader = new BufferedReader(isreader);
		String line = null;
		List<String> lines = new ArrayList<String>();
		try {
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			throw Util.toRuntimeException(e);
		}
		List<Location> locations = new ArrayList<Location>();
		for (String temp : lines) {
			if (temp.matches("[^:]*:[0-9]*:[0-9]*:.*")) {
				String[] ar = temp.split(":");
				Location location = new Location();
				location.setFile(f);
				try {
					location.setLine(Integer.parseInt(ar[1]));
				} catch (NumberFormatException e) {
					e.printStackTrace(ConsoleFactory.getOut());
				}
				location.setMsg(ar[3]);
				locations.add(location);
			} else if (temp.matches("[^:]*:[^:]*")) {
				String[] ar = temp.split(":");
				Location location = new Location();
				location.setFile(f);
				location.setMsg(ar[1]);
				locations.add(location);
			} else {
				Location location = new Location();
				location.setFile(f);
				location.setMsg(temp);
				locations.add(location);
			}
		}
		return locations;
	}
}
