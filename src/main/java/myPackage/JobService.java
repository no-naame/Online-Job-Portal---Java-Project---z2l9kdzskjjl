package myPackage;

import jakarta.servlet.ServletContext;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class JobService {
    private ServletContext servletContext;

    public JobService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public List<Job> getAllJobs() throws IOException {
        Gson gson = new Gson();
        InputStream is = servletContext.getResourceAsStream("/jobs.json");
        if (is == null) {
            throw new FileNotFoundException("Cannot find 'jobs.json'");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        JobsContainer jobsContainer = gson.fromJson(reader, JobsContainer.class);
        reader.close();
        is.close();
        return jobsContainer.getJobs();
    }

    public List<Job> getFilteredJobs(String jobCategory) throws IOException {
        List<Job> allJobs = getAllJobs();
        if ("all".equalsIgnoreCase(jobCategory)) {
            return allJobs;
        } else {
            return allJobs.stream()
                    .filter(job -> jobCategory.equalsIgnoreCase(job.getTypeOfJob()))
                    .collect(Collectors.toList());
        }
    }

    public Job getJobById(String jobId) throws IOException {
        for (Job job : getAllJobs()) {
            if (jobId.equals(String.valueOf(job.getId()))) {
                return job;
            }
        }
        return null;
    }
}