package fr.cszw.td_devwebavance.repositories;

import fr.cszw.td_devwebavance.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByUserId(String userId);
}
