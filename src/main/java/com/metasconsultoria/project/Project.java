package com.metasconsultoria.project;

import com.metasconsultoria.place.City;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private int idProject;
    private String name;
    private String description;
    private List<String> nextSteps = new ArrayList<>();
    private boolean publicProject;
    private List<Report> reports = new ArrayList<>();
    private City location;
}
