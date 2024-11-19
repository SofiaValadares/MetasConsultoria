package com.metasconsultoria.entities;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client  {
    private User user;
    private City city;
}
