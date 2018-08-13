package com.longbridge.sams.model;

/*
   Created by: Muhammad Habib
 */

import javax.persistence.*;
import java.util.List;

@Entity
public class Parent extends AbstractSchoolEntity{

    private String firstName;
    private  String middleName;
    private  String lastName;
    private String phoneNumber;
    private String email;
    private String religion;
    private String gender;
    private  String maritalStatus;

    
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;


    @ManyToMany
    private List<Student> students;

    @OneToOne
    private User user;

    public Parent() {
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   

    public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }



}
