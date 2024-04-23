package myPackage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;



import com.google.gson.Gson;

/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jobCategory = request.getParameter("jobCategory");
//		String typeOfJob = jobCategory;
//	    
//		//Get Data of all Jobs
//	    System.out.println(jobCategory);
//	    
//	    
////        JobsContainer jobsContainer = gson.fromJson(new FileReader("jobs.json"), JobsContainer.class);
//
//	    InputStream is = getServletContext().getResourceAsStream("/jobs.json");
//        if (is == null) {
//            throw new FileNotFoundException("Cannot find 'jobs.json'");
//        }
//
//        // Read the file from the InputStream
//        JobsContainer jobsContainer = gson.fromJson(new InputStreamReader(is), JobsContainer.class);
//
//        // Close the InputStream
//        is.close();
//        // Filter the jobs based on typeOfJob, if parameter is provided
//        List<Job> filteredJobs = jobsContainer.getJobs();
//        if (typeOfJob != null && !typeOfJob.isEmpty()) {
//            filteredJobs = filteredJobs.stream()
//                                       .filter(job -> typeOfJob.equalsIgnoreCase(job.getTypeOfJob()))
//                                       .collect(Collectors.toList());
//        	
//        	
//        }
		
		JobService jobService = new JobService(getServletContext());
        List<Job> filteredJobs = jobService.getFilteredJobs(jobCategory);

      Gson gson = new Gson();
      String jsonJobs = gson.toJson(filteredJobs);

     // Set the JSON string as a request attribute
     request.setAttribute("jobsData", jsonJobs);
     System.out.println(jsonJobs);
     // Forward the request to index.jsp
     RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
     dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
