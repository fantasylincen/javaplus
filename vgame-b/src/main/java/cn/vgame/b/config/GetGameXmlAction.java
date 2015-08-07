package cn.vgame.b.config;  
  
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.vgame.b.Server;
import cn.vgame.share.KeyValue;

import com.opensymphony.xwork2.ActionSupport;
  
public class GetGameXmlAction extends ActionSupport{  
      
    /**
	 * 
	 */
	private static final long serialVersionUID = -8898632916045561032L;
	/**
	 * 
	 */

	protected HttpServletResponse response;
	protected HttpServletRequest request;
	protected HttpSession session;

    //返回一个输入流，作为一个客户端来说是一个输入流，但对于服务器端是一个 输出流  
    public InputStream getDownloadFile() throws Exception  
    {  
    	response = ServletActionContext.getResponse();
		request = ServletActionContext.getRequest();
		session = request.getSession();
		
    	byte[] data = getData();
    	return new ByteArrayInputStream(data);
    }
    
    private byte[] getData() {
    	KeyValue kv = Server.getKeyValueForever();
    	String gameXml = kv.getString("GAME_XML");
    	if(gameXml == null)
    		return new byte[] {};
    	byte[] bytes = gameXml.getBytes();
		return bytes;
    }

	@Override  
    public String execute() throws Exception {  
        return SUCCESS;  
    }


}  