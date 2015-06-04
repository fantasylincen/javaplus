package cn.javaplus.build.ssh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SCPClientListener;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import cn.javaplus.log.Log;
import cn.javaplus.util.Closer;

import com.google.common.collect.Lists;

public class SSHClient {

	private String hostname;
	private String username;
	private String password;
	private Connection conn;
	private SCPClient scp;

	public SSHClient(String hostname, String username, String password) {
		this.hostname = hostname;
		this.username = username;
		this.password = password;
		connect();
	}

	public void addListener(SCPClientListener l) {
		scp.addListener(l);
	}

	public String getHostname() {
		return hostname;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void upload(String localFile, String remoteTargetDirectory) {
		try {
			scp.put(localFile, remoteTargetDirectory);
		} catch (IOException e) {
			close();
			throw new RuntimeException(e);
		}
	}

	public void download(String remoteFile, String localTargetDirectory) {
		try {
			scp.get(remoteFile, localTargetDirectory);
		} catch (IOException e) {
			close();
			throw new RuntimeException(e);
		}
	}
	
	

	private void connect() {

		conn = new Connection(hostname);

		try {

			conn.connect();

			boolean isAuthenticated = conn.authenticateWithPassword(username,
					password);

			if (isAuthenticated == false)
				throw new IOException("Authentication failed.");

			scp = new SCPClient(conn);
		} catch (RuntimeException e) {
			close();
			throw e;
		} catch (IOException e) {
			close();
			throw new RuntimeException(e);
		}
	}

	public void close() {
		conn.close();
		Log.d("close connection");
	}

	public List<String> exec(String cmd) {

		Session sess = null;
		BufferedReader out = null;
		BufferedReader err = null;
		ArrayList<String> outBack = Lists.newArrayList();
		ArrayList<String> errBack = Lists.newArrayList();
		try {

			sess = conn.openSession();
			Log.d(cmd);
			sess.execCommand(cmd);

			InputStream stdout = new StreamGobbler(sess.getStdout());
			InputStream stderr = new StreamGobbler(sess.getStderr());

			out = new BufferedReader(new InputStreamReader(stdout));
			err = new BufferedReader(new InputStreamReader(stderr));

			read(out, outBack);
			read(err, errBack);

			Integer status = sess.getExitStatus();

			if (status != null && !status.equals(0)) {
				throw new SSHStatusException(status);
			}

			if (!errBack.isEmpty()) {
				throw new SSHExecException(errBack);
			}

			return outBack;

		} catch (IOException e) {
			close();
			throw new RuntimeException(e);
		} finally {
			Closer.close(out);
			Closer.close(err);
			close(sess);
		}
	}

	private void read(BufferedReader reader, List<String> back)
			throws IOException {
		while (true) {
			String line = reader.readLine();
			if (line == null)
				break;
			back.add(line);
			Log.d(line);
		}
	}

	private void close(Session sess) {
		try {
			sess.close();
		} catch (Exception e) {
		}
	}
}
