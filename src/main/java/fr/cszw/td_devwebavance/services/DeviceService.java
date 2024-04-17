package fr.cszw.td_devwebavance.services;

import fr.cszw.td_devwebavance.exceptions.DBException;
import fr.cszw.td_devwebavance.exceptions.NotFoundException;
import fr.cszw.td_devwebavance.models.Device;
import fr.cszw.td_devwebavance.repositories.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public List<Device> getAllDevices() {
        return this.deviceRepository.findAll();
    }

    public List<Device> getDevicesUserId(String userId) {
        return this.deviceRepository.findByUserId(userId);
    }

    public Device updateDevice(Device device) throws DBException, NotFoundException {
        Device existing;

        if (device.getId() != null) {
            existing = this.deviceRepository.findById(device.getId()).orElse(null);
            if (existing == null) throw new NotFoundException("Could not find network with id : " + device.getId());
        } else {
            existing = new Device();
        }
        existing.setName(device.getName());
        existing.setType(device.getType());
        existing.setUserId(device.getUserId());
        existing.setImage(device.getImage());
        existing.setBrand(device.getBrand());
        existing.setPorts(device.getPorts());
        existing.setNetwork(device.getNetwork());
        existing.setIp(device.getIp());

        try {
            return this.deviceRepository.save(existing);
        } catch (Exception e) {
            throw new DBException("Could not create network");
        }
    }

    public void deleteDevice(Long id) throws NotFoundException, DBException {
        Device existing = this.deviceRepository.findById(id).orElse(null);
        if (existing == null) throw new NotFoundException("Can't find network with id :" + id);
        try {
            this.deviceRepository.delete(existing);
        } catch (Exception e) {
            throw new DBException("Error with DB");
        }
    }
}
