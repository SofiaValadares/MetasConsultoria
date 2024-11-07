package com.metasconsultoria.entities;

import java.sql.Connection;
import java.sql.ResultSet;

public class NextStep {
    private int idNextStep;
    private String nextStep;

    public NextStep() {

    }

    public int getIdNextStep() {
        return idNextStep;
    }

    public void setIdNextStep(int idNextStep) {
        this.idNextStep = idNextStep;
    }

    public String getNextStep() {
        return nextStep;
    }

    public void setNextStep(String nextStep) {
        this.nextStep = nextStep;
    }

    public static NextStep getNextStepById(Connection conn, int id) {
        NextStep nextStep = null;

        return nextStep;
    }
}
