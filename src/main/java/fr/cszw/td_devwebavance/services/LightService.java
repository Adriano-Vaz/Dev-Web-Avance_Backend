package fr.cszw.td_devwebavance.services;

import fr.cszw.td_devwebavance.exceptions.DBException;
import fr.cszw.td_devwebavance.exceptions.NotFoundException;
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

    public Light updateLight(Light light) throws DBException, NotFoundException {
        Light existing;

        if (light.getId() != null) {
            // On veut modifier une lampe
            existing = this.lightRepository.findById(light.getId()).orElse(null);
            if (existing == null) throw new NotFoundException("Could not find light with id : " + light.getId());
        } else {
            existing = new Light();
        }

        existing.setToggled(light.getToggled());
        existing.setColor(light.getColor());
        existing.setTitle(light.getTitle());

        try {
            Light lightCreated = this.lightRepository.save(existing);
            return lightCreated;
        } catch (Exception e) {
            throw new DBException("Could not create light");
        }
    }
}
