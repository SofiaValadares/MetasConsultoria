package com.metasconsultoria.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {
    private int idBooking;
    private Date date;
    private Time timeStart;
    private Time timeEnd;
    private int idCollaborator;
    private int idClient;

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTimeStart(Time timeStart) {
        this.timeStart = timeStart;
    }

    public void setTimeEnd(Time timeEnd) {
        this.timeEnd = timeEnd;
    }

    public void setIdCollaborator(int idCollaborator) {
        this.idCollaborator = idCollaborator;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
}
