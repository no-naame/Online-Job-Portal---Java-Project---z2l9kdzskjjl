package myPackage;

import java.util.List;

public class Job {
    public Integer id;
    public String title;
    public String company;
    public String location;
    public String description;
    public String typeOfJob;
    public List<String> requirements;

    public Job(Integer id, String title, String company, String location, String description, String typeOfJob, List<String> requirements) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.location = location;
        this.description = description;
        this.typeOfJob = typeOfJob;
        this.requirements = requirements;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getTypeOfJob() {
        return typeOfJob;
    }
    
    public int getId() {
        return this.id;
    }

    public List<String> getRequirements() {
        return requirements;
    }
}