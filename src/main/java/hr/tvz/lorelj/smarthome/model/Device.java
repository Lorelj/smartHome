package hr.tvz.lorelj.smarthome.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DEVICE")
public class Device{
   
	@Id
	@Column(name = "DEV_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
	@Column(name = "DEV_NAME")
    private String name;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="DEV_ID")
	private Set<Attribute> attributes;
	
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

	
}
