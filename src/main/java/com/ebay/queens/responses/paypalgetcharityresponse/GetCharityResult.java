/**
 * 
 */
package com.ebay.queens.responses.paypalgetcharityresponse;

import com.ebay.queens.responses.paypalcharitysearchresponse.Links;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({"cause_area", "address","mission_area"})
public class GetCharityResult {
	private String nonprofit_id;
	private String name; 
	private String description; 
	private String ein;
	private String giving_fund_status;
	private String registration_date;
	private String last_modified_date;
	private String website_url;
	private String keywords;
	private String slogan;
	private String logo_url;
	private Links links[];
/*	private CauseArea cause_area;
	private Address address;
	private MissionArea mission_area*/
	
	// Getters 
	public String getNonprofit_id() {
		return nonprofit_id;
	}
	public String getName() {
		return name;
	}
	public String getEin() {
		return ein;
	}
	public String getGiving_fund_status() {
		return giving_fund_status;
	}
	public String getRegistration_date() {
		return registration_date;
	}
	public String getLast_modified_date() {
		return last_modified_date;
	}
	public String getWebsite_url() {
		return website_url;
	}
	public String getDescription() {
		return description;
	}
	public String getKeywords() {
		return keywords;
	}
	public String getSlogan() {
		return slogan;
	}
	public String getLogo_url() {
		return logo_url;
	}
	public Links[] getLinks() {
		return links;
	}
	// Setters
	public void setNonprofit_id(String nonprofit_id) {
		this.nonprofit_id = nonprofit_id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEin(String ein) {
		this.ein = ein;
	}
	public void setGiving_fund_status(String giving_fund_status) {
		this.giving_fund_status = giving_fund_status;
	}
	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}
	public void setLast_modified_date(String last_modified_date) {
		this.last_modified_date = last_modified_date;
	}
	public void setWebsite_url(String website_url) {
		this.website_url = website_url;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}
	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}
	public void setLinks(Links links[]) {
		this.links = links;
	}

}
