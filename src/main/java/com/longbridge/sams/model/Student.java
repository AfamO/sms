package com.longbridge.sams.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Longbridge on 11/07/2018.
 */
@Entity
public class Student extends AbstractSchoolEntity {

    private String admissionNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private  String gender;
    private  String religion;
    @OneToOne
    private Image passport;
    private String email;
    private String sportHouse;
    private String phoneNum;
    private String department;


    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "${date.format}")
    private Date dateOfBirth;

    @OneToOne
    private User user;

    @ManyToMany (cascade = CascadeType.ALL)
    private List<Parent> parent;

    @ManyToMany (cascade = CascadeType.ALL)
    private List<Guardian> guardians;


    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public Student() {
    }

    public String getAdmissionNumber() {
		return admissionNumber;
	}

	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSportHouse() {
        return sportHouse;
    }

    public void setSportHouse(String sportHouse) {
        this.sportHouse = sportHouse;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Guardian> getGuardians() {
		return guardians;
	}

	public void setGuardians(List<Guardian> guardians) {
		this.guardians = guardians;
	}

	public List<Parent> getParent() {
        return parent;
    }

    public void setParent(List<Parent> parent) {
        this.parent = parent;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }



    public Image getPassport() {
        return passport;
    }

    public void setPassport(Image passport) {
        this.passport = passport;
    }




	@Override
	public String toString() {
		return "Student [admissionNumber=" + admissionNumber + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", gender=" + gender + ", email=" + email + ", sportHouse=" + sportHouse
				+ ", phoneNum=" + phoneNum + ", department=" + department  + "]";
	}

}
