package hr.tvz.lorelj.smarthome.repository;

import org.springframework.data.repository.CrudRepository;

import hr.tvz.lorelj.smarthome.model.Attribute;

public interface AttributeRepository extends CrudRepository<Attribute, Long>{
	Attribute findByName(String name);

}
