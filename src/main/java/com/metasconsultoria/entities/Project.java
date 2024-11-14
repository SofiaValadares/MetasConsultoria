package com.metasconsultoria.entities;

import java.util.Date;

public class Project {
    private int idProject;
    private String name;
    private String description;
    private boolean publicProject;
    private Date date;
    private int idCity;

    public Project() {}

    public Project(String name, String description, boolean publicProject, Date date, int idCity) {
        this.name = name;
        this.description = description;
        this.publicProject = publicProject;
        this.date = date;
        this.idCity = idCity;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublicProject() {
        return publicProject;
    }

    public void setPublicProject(boolean publicProject) {
        this.publicProject = publicProject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

}
