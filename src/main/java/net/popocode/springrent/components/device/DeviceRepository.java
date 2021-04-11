package net.popocode.springrent.components.device;

import net.popocode.springrent.components.device.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device,Long> {
    List<Device> findAllByNameContainingIgnoreCase(String name);
}
