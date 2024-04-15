package fr.cszw.td_devwebavance.repositories;

import fr.cszw.td_devwebavance.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
