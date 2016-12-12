package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.User_info;
import model.Users;
import dao.CourseDAO;
import dao.User_infoDAO;
import dao.UsersDAO;


/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadAvatarServlet")
public class UploadAvatarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "Images/hocvien/avatar";
 
    // upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        // checks if the request actually contains upload file
        if (!ServletFileUpload.isMultipartContent(request)) {
            // if not, we stop here
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }
 
        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk 
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
 
        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);
 
        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);
 
        // constructs the directory path to store upload file
        // this path is relative to application's directory
        String uploadPath = getServletContext().getRealPath("")
                + File.separator + UPLOAD_DIRECTORY;
 
        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
 
        try {
            
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                    	
                    	User_info ui = new User_info();
                    	String fileName = ui.getTen();
                    	
                    	//String fileName = new File(item.getName()).getName();
                    	
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
 
                        // saves the file on disk
                        item.write(storeFile);
                        request.setAttribute("msg", UPLOAD_DIRECTORY + "/" + fileName);
                        
                        request.setAttribute("testpdf", UPLOAD_DIRECTORY + "/" + fileName);
                        
                        request.setAttribute("message",
                                "Upload has been done successfully >>" + UPLOAD_DIRECTORY + "/" + fileName);
                        
                        
                        
                        
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message",
                    "There was an error: " + ex.getMessage());
        }
        // redirects client to message page
        getServletContext().getRequestDispatcher("/message.jsp").forward(
                request, response);
    }
    public static void main(String[] args) throws SQLException{
    	
    	/*Course c = new Course(Long.parseLong("1479832606700"),"aaaaaa","1947-02-16","1945-01-16",2,2,4,"asdsadads","sadsadsad");
    	dao.update(c);*/
    	
    	//User_info ui = new User_info();
    	//ui.getTen();
    	//System.out.println(ui.getTen());
    	
    	//teach = dao.getteacher(Long.parseLong("1479732694852"));
    	//System.out.println(teach.getTen());
    }

}
