package fr.cszw.td_devwebavance.controllers;

import fr.cszw.td_devwebavance.exceptions.DBException;
import fr.cszw.td_devwebavance.exceptions.NotFoundException;
import fr.cszw.td_devwebavance.models.Device;
import fr.cszw.td_devwebavance.services.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
@Slf4j
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping
    public ResponseEntity<List<Device>> getDevices() {
        return new ResponseEntity<>(this.deviceService.getAllDevices(), HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<Device>> getDevicesUser(@PathVariable String userId) {
        return new ResponseEntity<>(this.deviceService.getDevicesUserId(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Device> postDevice(@RequestBody Device deviceSent) {
        try {
            log.info("Creating Device ...");
            return deviceSent.getId() == null ?
                    new ResponseEntity<>(this.deviceService.updateDevice(deviceSent), HttpStatus.CREATED) :
                    new ResponseEntity<>(this.deviceService.updateDevice(deviceSent), HttpStatus.ACCEPTED);
        } catch (DBException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        if (id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            this.deviceService.deleteDevice(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DBException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}