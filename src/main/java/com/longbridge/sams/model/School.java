package com.longbridge.sams.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@Entity @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class School extends AbstractEntity {

	@NotBlank
	private String code;

	@NotBlank
	private String name;

	private String phoneNumber;

	private String motto;

	private String emailAddress;

	private String status;

	private String history;

	private String website;

	@OneToOne
	@JsonIgnore
	private Image logo;

	@OneToOne
	@JsonIgnore
	private Image banner;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Address address;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMotto() {
		return motto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Image getLogo() {
		return logo;
	}

	public void setLogo(Image logo) {
		this.logo = logo;
	}

	public Image getBanner() {
		return banner;
	}

	public void setBanner(Image banner) {
		this.banner = banner;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "School [code=" + code + ", name=" + name + ", phoneNumber=" + phoneNumber + ", motto=" + motto
				+ ", emailAddress=" + emailAddress + ", status=" + status + ", website=" + website + "]";
	}

}
