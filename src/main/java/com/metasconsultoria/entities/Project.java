package com.metasconsultoria.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {
    private int idProject;
    private String name;
    private String description;
    private boolean publicProject;
    private Date date;
    private int idCity;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublicProject(boolean publicProject) {
        this.publicProject = publicProject;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

}
