package fr.cszw.td_devwebavance.controllers;

import fr.cszw.td_devwebavance.models.Light;
import fr.cszw.td_devwebavance.repositories.LightRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lights")
@RequiredArgsConstructor
public class LightController {

    private final LightRepository lightRepository;

    @GetMapping
    public ResponseEntity<List<Light>> getLights() {
        List<Light> lights = this.lightRepository.findAll();
        return new ResponseEntity<>(lights, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Light> postLight() {
        Light light = new Light();
        light.setColor("#FFFFFF");
        light.setTitle("Test");
        light.setToggled(true);

        try {
            Light lightCreated = this.lightRepository.save(light);
            return new ResponseEntity<>(lightCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
