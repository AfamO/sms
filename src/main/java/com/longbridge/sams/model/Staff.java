package com.longbridge.sams.model;



import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Staff extends AbstractSchoolEntity{

    private String staffId;
    private String title;
    private String firstName;
    private String middleName;
    private String lastName;

    private String religion;
    private String gender;
    private String maritalStatus;
    private String nationality;
    private String phoneNumber;
    private String emailAddress;
    
    private String jobPosition;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "${date.format}")
    private Date dateOfBirth;


    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "${date.format}")
    private Date dateOfEmployment;
    
    private String nextOfKin;
    
    private String nokPhoneNumber;

    private int yearOfExperience;
    
    private String venueAssigned;

    @OneToOne
    private User user;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Address address;

    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Qualification> qualifications;

   

    public Staff(){

    }


    public String getStaffId() {
        return staffId;
    }



	public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(Date dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public String getNextOfKin() {
        return nextOfKin;
    }

    public void setNextOfKin(String nextOfKin) {
        this.nextOfKin = nextOfKin;
    }

    public String getNokPhoneNumber() {
        return nokPhoneNumber;
    }

    public void setNokPhoneNumber(String nokPhoneNumber) {
        this.nokPhoneNumber = nokPhoneNumber;
    }

    public int getYearOfExperience() {
        return yearOfExperience;
    }

    public void setYearOfExperience(int yearOfExperience) {
        this.yearOfExperience = yearOfExperience;
    }

    public String getVenueAssigned () {
        return venueAssigned;
    }

    public void setVenueAssigned (String venueAssigned ) {
        this.venueAssigned = venueAssigned;
    }
    

    public List<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<Qualification> qualifications) {
        this.qualifications = qualifications;
    }
    
    
    public Address getAddress () {
        return address;
    }
    
    public void setAddress ( Address address ) {
        this.address = address;
    }


	


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", title=" + title + ", firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + ", gender=" + gender + ", phoneNumber=" + phoneNumber
				+ ", emailAddress=" + emailAddress + ", jobPosition=" + jobPosition + "]";
	}
    
    
}



