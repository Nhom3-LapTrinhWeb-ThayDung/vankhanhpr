package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
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

import dao.ExerciseDAO;
import dao.Exercise_UserDAO;
import model.Exercise;
import model.Exercise_User;
import model.Resources;
import model.Section;
import model.User_info;

/**
 * Servlet implementation class SubmitExerciseServlet
 */
@WebServlet("/SubmitExerciseServlet")
public class SubmitExerciseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Exercise_UserDAO exuDAO = new Exercise_UserDAO();
	ExerciseDAO eDAO = new ExerciseDAO();
	// location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "upload";
 
    // upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitExerciseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "";
    	boolean f;
    	int i=0;
    	String command ="";
    	User_info uf = new User_info();
		HttpSession session = request.getSession();
		uf = (User_info) session.getAttribute("user_info");
		String srcname="";
		String exercise_id="";
		String course_id="";
		String file="";
		String description="";
		Exercise_User exu1= new Exercise_User();
		Exercise_User exu = new Exercise_User();
		Exercise e = new Exercise();
		Section section = new Section();
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
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
 
                        // saves the file on disk
                        item.write(storeFile);
                        request.setAttribute("msg", UPLOAD_DIRECTORY + "/" + fileName);
                        request.setAttribute("message",
                                "Upload has been done successfully >>" + UPLOAD_DIRECTORY + "/" + fileName);
                        uf.setAnhdaidien(UPLOAD_DIRECTORY + "/" + fileName);
                        srcname = fileName;
                    }
                    else{
                    	i++;
                    	if(i==1)
                    		exercise_id=item.getString();
                    	if(i==2)
                    		course_id=item.getString();
                    	if(i==3)
                    		file = item.getString();
                    	if(i==4)
                    		description = item.getString();
                    		
                    }
                    
                   } 
            }
            
        } catch (Exception ex) {
            request.setAttribute("message",
                    "There was an error: " + ex.getMessage());
        }
        if(srcname=="")
        	srcname=file;
        exu1 = exuDAO.timbaidanop(Long.parseLong(exercise_id), uf.getId());
        if(exu1==null)
        {
        	command= "nopbai";
        }
        else
        	command= "suabai";
        switch(command){
        case "nopbai":
        	section = exuDAO.getSection(Long.parseLong(exercise_id));
        	e = eDAO.getExercise(Long.parseLong(exercise_id));
        	exu.setResult_id(new Date().getTime());
        	exu.setExercise_id(Long.parseLong(exercise_id));
        	exu.setUser_id(uf.getId());
        	exu.setUser_name(uf.getTen());
        	exu.setCourse_id(Long.parseLong(course_id));
        	exu.setSection_name(section.getSection_name());
        	exu.setExercise_name(e.getExercise_name());
        	exu.setFilesubmit(srcname);
        	exu.setTimesubmit(new Timestamp(new Date().getTime()));
        	exu.setDescription(description);
        	f = exuDAO.nopbai(exu);
        	//url="/Chi-Tiet-Bai-Tap.jsp?course_id="+course_id+"&exercise_id="+exercise_id;
        	break;
        case "suabai":
        	exu.setResult_id(exu1.getResult_id());
        	exu.setExercise_id(exu1.getExercise_id());
        	exu.setUser_id(exu1.getUser_id());
        	exu.setUser_name(exu1.getUser_name());
        	exu.setCourse_id(exu1.getCourse_id());
        	exu.setSection_name(exu1.getSection_name());
        	exu.setExercise_name(exu1.getExercise_name());
        	exu.setFilesubmit(srcname);
        	exu.setTimesubmit(new Timestamp(new Date().getTime()));
        	exu.setDescription(description);
        	exu.setReview(exu1.getReview());
        	exu.setScore(exu1.getScore());
        	f = exuDAO.suabai(exu);
        	break;
        }
        // redirects client to message page
        request.setAttribute("message",i+"-"+ srcname+"-"+file+"-"+exercise_id+"-"+course_id+"-"+description+"-"+command+"-"+uf.getId());
        response.sendRedirect(url);
	}

}
