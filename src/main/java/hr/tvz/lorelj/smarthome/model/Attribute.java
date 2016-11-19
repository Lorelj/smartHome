package hr.tvz.lorelj.smarthome.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ATTRIBUTE")
public class Attribute{
   
	@Id
	@Column(name = "ATTR_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
	@Column(name = "ATTR_NAME")
    private String name;
	
	@Column(name = "ATTR_VALUE_TYPE")
	private String valueType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	
}
