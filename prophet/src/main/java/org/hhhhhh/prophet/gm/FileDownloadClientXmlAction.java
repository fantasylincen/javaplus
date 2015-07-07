package org.hhhhhh.prophet.gm;  
  
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hhhhhh.prophet.Server;

import cn.javaplus.compressor.Zip;

import com.opensymphony.xwork2.ActionSupport;
  
//文件下载  
public class FileDownloadClientXmlAction extends ActionSupport{  
      
    /**
	 * 
	 */
	private static final long serialVersionUID = 4257083460855721752L;

	protected HttpServletResponse response;
	protected HttpServletRequest request;
	protected HttpSession session;

		
    private String projectId;
    private String zoneId;
  
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
		Zone zone = Server.getProjectManager().getZone(projectId, zoneId);
    	byte[] data = zone.getClientXml().getData();
		return data;
	}
    
    
      
    @Override  
    public String execute() throws Exception {  
          
        return SUCCESS;  
    }

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

}  