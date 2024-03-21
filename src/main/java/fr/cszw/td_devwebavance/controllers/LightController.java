package fr.cszw.td_devwebavance.controllers;

import fr.cszw.td_devwebavance.models.Light;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lights")
public class LightController {

    @GetMapping
    public ResponseEntity<Light> getLight() {
        Light light = new Light();
        light.setId(0L);
        light.setColor("#FFFFFF");
        light.setTitle("Test");
        light.setToggled(false);

        return new ResponseEntity<>(light, HttpStatus.OK);

    }

}
