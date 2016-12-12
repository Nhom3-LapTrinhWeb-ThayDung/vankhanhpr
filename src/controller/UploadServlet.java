package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.oreilly.servlet.MultipartRequest;

import dao.CourseDAO;
import dao.SectionDAO;
import dao.User_infoDAO;
import model.Outline;
import model.Resources;
import model.User_info;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User_infoDAO user_infoDAO = new User_infoDAO();
	SectionDAO sectionDAO = new SectionDAO();
	CourseDAO courseDAO = new CourseDAO();
    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "upload";
 
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
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
    	String url = "";
    	boolean f;
    	int i=0;
    	String command ="";
    	User_info uf = new User_info();
		HttpSession session = request.getSession();
		uf = (User_info) session.getAttribute("user_info");
		String srcname="";
		String srcsectionid="";
		String course_id="";
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
                    	i++;
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
 
                        // saves the file on disk
                        item.write(storeFile);
                        request.setAttribute("msg", UPLOAD_DIRECTORY + "/" + fileName);
                        request.setAttribute("message",
                                "Upload has been done successfully >>" + UPLOAD_DIRECTORY + "/" + fileName);
                        uf.setAnhdaidien(fileName);
                        srcname = fileName;
                    }
                    else{
                    	i++;
                    	if(i==1)
                    		command = item.getString();
                    	if(i==2)
                    		srcsectionid=item.getString();
                    	if(i==3)
                    		course_id=item.getString();
                    }
                    
                   } 
            }
            
        } catch (Exception ex) {
            request.setAttribute("message",
                    "There was an error: " + ex.getMessage());
        }
        switch(command){
        case "user":
        	f = user_infoDAO.updateUser_info(uf);
        	session.setAttribute("user_info", uf);
        	if(uf.getQuyen()==1)
        		url="canhangiangvien.jsp";
        	else
        		url="hocvien.jsp";
        	break;
        case "file":
        	Resources src = new Resources();
        	src.setResources_id(new java.util.Date().getTime());
        	src.setResources_name(srcname);
        	src.setSection_id(Long.parseLong(srcsectionid));
        	src.setResources_type(srcname.substring(srcname.length()-3));
        	f = sectionDAO.insertresources(src);
        	url="khoahoc2.jsp?course_id="+course_id;
        	break;
        case "outline":
        	Outline o = new Outline();
        	f= courseDAO.checkOutline(course_id);
        	if(!f)
        	{
	        	o.setOutline(srcname);
	        	o.setCourse_id(Long.parseLong(course_id));
	        	o.setType(srcname.substring(srcname.length()-3));
	        	f = courseDAO.insertOutline(o);
        	}
        	else
        	{

	        	o.setOutline(srcname);
	        	o.setCourse_id(Long.parseLong(course_id));
	        	o.setType(srcname.substring(srcname.length()-3));
	        	f = courseDAO.updateOutline(o);
        	}
        	url="khoahoc2.jsp?course_id="+course_id;
        	break;
        }
        // redirects client to message page
        response.sendRedirect(url);
    }

}
