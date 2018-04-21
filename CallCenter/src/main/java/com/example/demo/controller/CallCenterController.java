package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.request.CallRequest;
import com.example.demo.service.CallCalculationService;
import com.example.demo.vo.CallResponse;

@RestController
@CrossOrigin
public class CallCenterController {

	@Autowired
	private CallCalculationService callCalculationService;
	
	
	@RequestMapping(value = "/process/call",  method=RequestMethod.POST,consumes={"application/json"}, produces={"application/json"})
	public ResponseEntity<? extends Object> manageCall(@RequestBody CallRequest callReq)
	{
		ResponseEntity<? extends Object> res=null;
	
		try
		{
			CallResponse callRes1=callCalculationService.calculateRequest(callReq);
			res= new ResponseEntity<CallResponse>(callRes1,HttpStatus.OK);
		}
		catch(Exception e)
		{
			res= new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return res;
	}
	
	@RequestMapping(path = "/download1", method = RequestMethod.GET)
	public ResponseEntity<Resource> download(HttpServletRequest request,String param) throws IOException {

	    // ...

		String fullPath = "C:\\Users\\bhuban\\Desktop\\zookeeper_tutorial.pdf";    
        
		ServletContext context = request.getServletContext();  
        File file = new File(fullPath);
        String mimeType = context.getMimeType(fullPath);
		 HttpHeaders headers = new HttpHeaders();
	      headers.set("Content-Type", mimeType);
	      headers.set("Content-Disposition", "attachment; filename=\"" + file.getName());
	    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

	    return ResponseEntity.ok()
	            .headers(headers)
	            .contentLength(file.length())
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .body(resource);
	}
/*	@RequestMapping(value = "/jar",  method=RequestMethod.GET, produces="application/octet-stream")
	public Response downloadFile() throws FileNotFoundException 
	{
		 File file = new File("C:\\Users\\bhuban\\Desktop\\menu.jar");
	    FileInputStream inputStream = new FileInputStream(file);
	    ResponseBuilder response = Response.ok((Object) file);
	    response.header("Content-Disposition", "attachment;filename=menu.jar");
	    return response.build();
	}*/
	

	 @RequestMapping(method = RequestMethod.GET,value = "/downLoad")
	    public void doDownload(HttpServletRequest request,
	            HttpServletResponse response) throws IOException {
	 
	        // get absolute path of the application
	        ServletContext context = request.getServletContext();
	        String appPath = context.getRealPath("");
	        System.out.println("appPath = " + appPath);
	 
	        // construct the complete absolute path of the file
	        String fullPath = "C:\\Users\\bhuban\\Desktop\\zookeeper_tutorial.pdf";    
	        
	       
	        File downloadFile = new File(fullPath);
	        FileInputStream inputStream = new FileInputStream(downloadFile);
	         
	        // get MIME type of the file
	        String mimeType = context.getMimeType(fullPath);
	        if (mimeType == null) {
	            // set to binary type if MIME mapping not found
	            mimeType = "application/octet-stream";
	        }
	        System.out.println("MIME type: " + mimeType);
	 
	        // set content attributes for the response
	        response.setContentType(mimeType);
	        response.setContentLength((int) downloadFile.length());
	 
	        // set headers for the response
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"",
	                downloadFile.getName());
	        response.setHeader(headerKey, headerValue);
	 
	        // get output stream of the response
	        OutputStream outStream = response.getOutputStream();
	 
	        byte[] buffer = new byte[1024];
	        int bytesRead = -1;
	 
	        // write bytes read from the input stream into the output stream
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, bytesRead);
	        }
	 
	        inputStream.close();
	        outStream.close();
	 
	    }
}
