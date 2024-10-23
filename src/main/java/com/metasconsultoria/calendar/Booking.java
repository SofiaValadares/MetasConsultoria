package com.metasconsultoria.calendar;

import com.metasconsultoria.projects.Project;
import com.metasconsultoria.user.Collaborator;
import com.metasconsultoria.user.User;

import java.sql.Time;
import java.util.Date;

public class Booking {
    private int idBooking;
    private Date date;
    private Time time;
    private Collaborator collaborator;
    private User whoBooked;
    private Project project;
}
