package hr.tvz.lorelj.smarthome.repository;

import org.springframework.data.repository.CrudRepository;

import hr.tvz.lorelj.smarthome.model.Device;

public interface DeviceRepository extends CrudRepository<Device, Long> {
	Device findByName(String name);
}
