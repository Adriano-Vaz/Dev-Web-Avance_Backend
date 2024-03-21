package fr.cszw.td_devwebavance.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Light {

    private Long id;
    private Boolean toggled;
    private String title;
    private String color;



}
