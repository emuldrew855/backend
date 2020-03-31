/**
 * 
 */
package com.ebay.queens.responses.paypalgetcharityresponse;

import com.ebay.queens.responses.paypalcharitysearchresponse.Links;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"address", "mission_area" })
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
	private CauseArea cause_area[];

	public CauseArea[] getCause_area() {
		return cause_area;
	}

	public String getDescription() {
		return description;
	}

	public String getEin() {
		return ein;
	}

	public String getGiving_fund_status() {
		return giving_fund_status;
	}

	public String getKeywords() {
		return keywords;
	}

	public String getLast_modified_date() {
		return last_modified_date;
	}

	public Links[] getLinks() {
		return links;
	}

	public String getLogo_url() {
		return logo_url;
	}

	public String getName() {
		return name;
	}

	// Getters
	public String getNonprofit_id() {
		return nonprofit_id;
	}

	public String getRegistration_date() {
		return registration_date;
	}

	public String getSlogan() {
		return slogan;
	}
	
	public String getWebsite_url() {
		return website_url;
	}


	public void setCause_area(CauseArea cause_area[]) {
		this.cause_area = cause_area;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEin(String ein) {
		this.ein = ein;
	}

	public void setGiving_fund_status(String giving_fund_status) {
		this.giving_fund_status = giving_fund_status;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setLast_modified_date(String last_modified_date) {
		this.last_modified_date = last_modified_date;
	}

	public void setLinks(Links links[]) {
		this.links = links;
	}

	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Setters
	public void setNonprofit_id(String nonprofit_id) {
		this.nonprofit_id = nonprofit_id;
	}

	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public void setWebsite_url(String website_url) {
		this.website_url = website_url;
	}

}
