package com.metasconsultoria.entities;

import java.util.ArrayList;
import java.util.List;

public class Collaborator extends User {
    private String city;
    private String neighborhood;
    private String street;
    private int number;
    private String complement;
    private List<String> phoneNumber =  new ArrayList();
}
