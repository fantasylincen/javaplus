package com.taobao.protobuf.perference;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Image;

import com.taobao.protobuf.Activator;

public class PBPluginImages {

	public static final String			CLZ_IMAGE		= "class_obj.gif";

	public static final String			OPTION_IMAGE	= "option.gif";

	public static final String			OTHER_IMAGE		= "other.gif";

	public static final String			TYPES_IMAGE		= "types.gif";

	public static final String			DES_IMAGE		= "public_co.gif";

	public static Map<String, Image>	caches			= new HashMap<String, Image>();

	public static Image getImage(String imagename) {
		if (caches.get(imagename) == null) {
			Image image = Activator.getImageDescriptor("icons/" + imagename).createImage();
			caches.put(imagename, image);
			return image;
		}
		return caches.get(imagename);
	}

}
