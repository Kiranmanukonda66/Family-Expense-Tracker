<%@page import="com.rs.fer.service.impl.FERServiceImpl"%>
<%@ page import="org.apache.commons.fileupload.FileItem"%>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page import="org.apache.commons.fileupload.FileUploadException"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.io.FileOutputStream"%>
<%@ page import="java.util.Iterator"%>
<%@page import="com.rs.fer.service.FERService"%>

<%!FERService ferService = new FERServiceImpl();%>

<%
    // Check if the request contains a file upload (multipart/form-data)
    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
    
    if (isMultipart) {
    	
    	//created to handle temporary file storage.
        DiskFileItemFactory factory = new DiskFileItemFactory();
    	
    	// created to process the file upload.
        ServletFileUpload upload = new ServletFileUpload(factory);
        
        // Define where to save the uploaded file
        String uploadPath = application.getRealPath("/") + "uploads/"+session.getAttribute("userId");  // Local folder 'uploads' in your project
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Create directory if it doesn't exist
        }
        
        try {
        	
            //returns all items, including both form fields and files
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iter = items.iterator();
            
            while (iter.hasNext()) {
            	
                FileItem item = iter.next();
                
                // Check if the item is a form field or a file
                if (!item.isFormField()) {
                	
                    	String fileName = new File(item.getName()).getName();
                    
                        // Define the file path to save the uploaded file
                        String filePath = uploadPath + File.separator + fileName;
                        File uploadedFile = new File(filePath);
                        
                        // Use InputStream and FileOutputStream to save the file
                        InputStream fileContent = item.getInputStream();  // to read the file
                        FileOutputStream fos = new FileOutputStream(uploadedFile);  // Write to the target location
                        
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = fileContent.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                        }
                        fos.close();  // Close the output stream
                        fileContent.close();  // Close the input stream
                        
                        int userId = Integer.parseInt(session.getAttribute("userId").toString());
                        
                        boolean isUploaded = ferService.uploadImage(fileName,userId);
                        
                        if (isUploaded) {
                         session.setAttribute("status", "File uploaded successfully!");
                         session.setAttribute("uploadedImage", fileName);
                        } else {
                         session.setAttribute("status", "No file selected!");
                        }
                }
            }
        } catch (FileUploadException e) {
            session.setAttribute("status", "File upload failed: " + e.getMessage());
        } catch (Exception e) {
            session.setAttribute("status", "Error: " + e.getMessage());
        }
    } else {
        session.setAttribute("status", "The request is not multipart!");
    }
%>

<!-- Include your dashboard page to display the status -->
<jsp:include page="DashBoard.jsp" />
