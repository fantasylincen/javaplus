package cn.mxz.base.telnet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import cn.javaplus.file.IProperties;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;
import cn.mxz.base.server.Server;
import cn.mxz.events.EventDispatcher2Impl;
import cn.mxz.events.ServerStartEvent;


public class TelnetServer extends EventDispatcher2Impl implements Server, Runnable {

    private Thread	thread;

    private boolean	isRunning = true;

    private String	configPath;

    private SpringFactory	springFactory;

    public TelnetServer(String configPath) {

        this.configPath = configPath;
    }

    
    
    public boolean isRunning() {
		return isRunning;
	}



	@Override
    public void run() {

        ServerSocket ss = null;

        IProperties properties = Util.Property.getProperties("config/config.properties");

		int socketPort = properties.getInt("telnetServerPortStart");

        while(isRunning) {

            try {

                ss = new ServerSocket(socketPort);

                Socket socket = ss.accept();

                new CommandProcesser(this, socket, configPath, springFactory).start();

            } catch (java.net.BindException e) {

            	Logger.getLogger("cn.mxz.systemlog").error(/*e.getMessage() + */"  TelnetServer 端口已经绑定, 更换端口为:" + ++socketPort);

            } catch (java.net.SocketException e) {

            	Logger.getLogger("cn.mxz.systemlog").error(/*e.getMessage() + */"  TelnetServer 端口已经绑定, 更换端口为:" + ++socketPort);

            } catch (IOException e) {

                e.printStackTrace();

                cn.javaplus.util.Util.Thread.sleep(300);

            } finally {

                Closer.close(ss);
            }

        }
    }

    @Override
    public void start() {

        thread = new Thread(this);

        thread.setDaemon(true);

        thread.start();

        dispatchEvent(new ServerStartEvent(this));
    }


    @Override
    public void stop() {

        isRunning = false;

    }

    public void setSpringFactory(SpringFactory springFactory) {
        this.springFactory = springFactory;
    }
}
