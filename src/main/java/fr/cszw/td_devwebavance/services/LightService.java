package fr.cszw.td_devwebavance.services;

import fr.cszw.td_devwebavance.exceptions.DBException;
import fr.cszw.td_devwebavance.models.Light;
import fr.cszw.td_devwebavance.repositories.LightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LightService {

    private final LightRepository lightRepository;

    public List<Light> getAllLights() {
        return this.lightRepository.findAll();
    }

    public Light updateLight(Light light) throws DBException {
        try {
            Light lightCreated = this.lightRepository.save(light);
            return lightCreated;
        } catch (Exception e) {
            throw new DBException("Could not create light");
        }
    }
}
