package com.metasconsultoria.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Collaborator  {
    private int idUser;
    private String city;
    private String neighborhood;
    private String street;
    private int number;
    private String complement;
    private String phoneNumber1;
    private String phoneNumber2;
    private int supervisedBy;

}
