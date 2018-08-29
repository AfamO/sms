package com.longbridge.sams.model;



import javax.persistence.Entity;

@Entity
public class Address extends AbstractEntity {
    
    private String name;
    private String stateOfOrigin;
    private String localGovt;
    private String country;
    private String state;
    private String city;


    public Address() {
    }


    public String getName () {
        return name;
    }

    public void setName (String name ) {
        this.name = name;
    }

    public String getStateOfOrigin() {
        return stateOfOrigin;
    }

    public void setStateOfOrigin(String stateOfOrigin) {
        this.stateOfOrigin = stateOfOrigin;
    }

    public String getLocalGovt() {
        return localGovt;
    }

    public void setLocalGovt(String localGovt) {
        this.localGovt = localGovt;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getState () {
        return state;
    }
    
    public void setState ( String state ) {
        this.state = state;
    }
    
    public String getCity () {
        return city;
    }
    
    public void setCity ( String city ) {
        this.city = city;
    }


	@Override
	public String toString() {
		return "Address [name=" + name + ", stateOfOrigin=" + stateOfOrigin + ", localGovt=" + localGovt + ", country="
				+ country + ", state=" + state + ", city=" + city + "]";
	}
    
    
    
}
