package controller;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name  = request.getParameter("filename");
		StringBuffer sb = new StringBuffer("whatever string you like");
		InputStream in = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename=upload/"+name);
		byte[] outputByte = new byte[4096];
		//copy binary contect to output stream
		while(in.read(outputByte, 0, 4096) != -1)
		{
			out.write(outputByte, 0, 4096);
		}
		in.close();
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				//tell browser program going to return an application file
		        //instead of html page
		        
				String name  = request.getParameter("filename");
				StringBuffer sb = new StringBuffer("whatever string you like");
				InputStream in = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
				ServletOutputStream out = response.getOutputStream();
				response.setContentType("application/octet-stream");
		        response.setHeader("Content-Disposition","attachment;filename=upload/"+name);
				byte[] outputByte = new byte[4096];
				//copy binary contect to output stream
				while(in.read(outputByte, 0, 4096) != -1)
				{
					out.write(outputByte, 0, 4096);
				}
				in.close();
				out.flush();
				out.close();
		
	}

}
