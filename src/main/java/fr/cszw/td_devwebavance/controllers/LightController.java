package fr.cszw.td_devwebavance.controllers;

import fr.cszw.td_devwebavance.exceptions.DBException;
import fr.cszw.td_devwebavance.models.Light;
import fr.cszw.td_devwebavance.repositories.LightRepository;
import fr.cszw.td_devwebavance.services.LightService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lights")
@RequiredArgsConstructor
@Slf4j
public class LightController {

    private final LightService lightService;

    @GetMapping
    public ResponseEntity<List<Light>> getLights() {
        return new ResponseEntity<>(this.lightService.getAllLights(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Light> postLight(@RequestBody Light lightSent) {
        try {
            log.info("Creating light ...");
            return new ResponseEntity<>(this.lightService.updateLight(lightSent), HttpStatus.CREATED);
        } catch (DBException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
