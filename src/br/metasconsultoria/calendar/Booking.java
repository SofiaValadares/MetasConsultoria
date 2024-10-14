package br.metasconsultoria.calendar;


import br.metasconsultoria.projects.Project;
import br.metasconsultoria.user.Collaborator;
import br.metasconsultoria.user.User;

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
