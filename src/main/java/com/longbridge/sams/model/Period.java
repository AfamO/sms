package com.longbridge.sams.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Embeddable
public class Period {

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "/mm/dd")
    private Date start;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "${date.format}")
    private Date end;

    @Column(name="period_name")
    private  String name;

    private String type;

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
    
}
