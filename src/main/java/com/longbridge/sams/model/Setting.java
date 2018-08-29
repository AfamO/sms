package com.longbridge.sams.model;


import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

@Entity
public class Setting extends AbstractEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 426968016089779439L;
	private String name;
    private String type;
    private String description;
    private String value;
    private String enabled ;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@Override
	public List<String> getDefaultSearchFields() {
		return Arrays.asList("description", "name","type","value");
	}


	
}
