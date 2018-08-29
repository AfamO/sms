package com.longbridge.sams.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Class")
public class SClass extends AbstractSchoolEntity {

    private String name;
    private String description;


    @ManyToOne
    private Level level;

    @JsonIgnore
    @OneToMany
    private List<Student> students;
    
    @JsonIgnore
    @OneToMany
    private List<ClassTeacher> teachers;

    @Embedded
    private Period period;


    public SClass(){

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}


	public List<ClassTeacher> getTeachers() {
		return teachers;
	}


	public void setTeachers(List<ClassTeacher> teachers) {
		this.teachers = teachers;
	}

}
