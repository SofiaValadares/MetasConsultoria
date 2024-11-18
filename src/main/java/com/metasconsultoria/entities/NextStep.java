package com.metasconsultoria.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NextStep {
    private int idNextStep;
    private String nextStep;
    private int fkProject;


}
