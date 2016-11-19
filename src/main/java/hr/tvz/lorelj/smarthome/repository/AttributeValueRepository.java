package hr.tvz.lorelj.smarthome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import hr.tvz.lorelj.smarthome.model.AttributeValue;


public interface AttributeValueRepository extends CrudRepository<AttributeValue, Long> {
	
    @Query(value= "SELECT at.ATTR_NAME name, at_val.ATTR_VALUE value "
    		+ "from ATTRIBUTE_VALUE at_val "
    		+ "join ATTRIBUTE at on at.ATTR_ID = at_val.ATTR_ID "
    		+ "join (select ATTR_NAME, max(ATTR_VALUE_TIMESTAMP) as max_timestamp "
    				+ "from ATTRIBUTE_VALUE at_val2 "
    				+ "join ATTRIBUTE at2 on at_val2.ATTR_ID = at2.ATTR_ID "
    				+ "group by ATTR_NAME ) LAV on at.ATTR_NAME = LAV.ATTR_NAME and at_val.ATTR_VALUE_TIMESTAMP = LAV.max_timestamp",nativeQuery = true)
	List<Object[]> fetchLatestAttributeValues();
}
