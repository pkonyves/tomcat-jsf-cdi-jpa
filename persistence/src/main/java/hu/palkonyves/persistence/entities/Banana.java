package hu.palkonyves.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Banana {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;
    Integer yellowness;
    Integer browness;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Integer getYellowness() {
	return yellowness;
    }

    public void setYellowness(Integer yellowness) {
	this.yellowness = yellowness;
    }

    public Integer getBrowness() {
	return browness;
    }

    public void setBrowness(Integer browness) {
	this.browness = browness;
    }

    public Long getId() {
	return id;
    }
}
