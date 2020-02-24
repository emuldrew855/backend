package com.ebay.queens.responses;
import java.util.HashMap;
import java.util.Map;
import com.ebay.queens.responses.paypalgetcharityresponse.CauseArea;
import com.ebay.queens.responses.paypalgetcharityresponse.GetCharityResult;

public class CharityCache {
	private Map<Integer, GetCharityResult> currentCharityResponses;
	private Map<Integer, GetCharityResult> _tempCharityResponse;
	private Map<Integer, String> currentCauseAreas;
	private Map<Integer, String> _tempCurrentCauseAreas;
	int charityKey = 0;
	int causeAreaKey = 0;
	
	public CharityCache() {
		Map<Integer, GetCharityResult> currentCharityResponses = new HashMap<>();
		this.currentCharityResponses = currentCharityResponses;
		Map<Integer, String> currentCauseArea = new HashMap<>();
		this.currentCauseAreas = currentCauseArea;
	}

	// Methods
	public void addCharity(GetCharityResult paypalCharity) {
		currentCharityResponses.put(Integer.parseInt(paypalCharity.getNonprofit_id()), paypalCharity);
	}
	
	public void addCharityCause(String causeArea) {
		charityKey = charityKey + 1;
		currentCauseAreas.put(charityKey, causeArea);
	}
	
	// Setters
	public Map<Integer, GetCharityResult> getCurrentCharityResponses() {
		return currentCharityResponses;
	}

	public Map<Integer, GetCharityResult> get_tempCharityResponse() {
		return _tempCharityResponse;
	}

	public Map<Integer, String> getCurrentCauseAreas() {
		return currentCauseAreas;
	}

	public Map<Integer, String> get_tempCurrentCauseAreas() {
		return _tempCurrentCauseAreas;
	}

	public int getCharityKey() {
		return charityKey;
	}

	public int getCauseAreaKey() {
		return causeAreaKey;
	}
	
	// Setters
	public void setCurrentCharityResponses(Map<Integer, GetCharityResult> currentCharityResponses) {
		this.currentCharityResponses = currentCharityResponses;
	}

	public void set_tempCharityResponse(Map<Integer, GetCharityResult> _tempCharityResponse) {
		this._tempCharityResponse = _tempCharityResponse;
	}

	public void setCurrentCauseAreas(Map<Integer, String> currentCauseAreas) {
		this.currentCauseAreas = currentCauseAreas;
	}

	public void set_tempCurrentCauseAreas(Map<Integer, String> _tempCurrentCauseAreas) {
		this._tempCurrentCauseAreas = _tempCurrentCauseAreas;
	}

	public void setCharityKey(int charityKey) {
		this.charityKey = charityKey;
	}

	public void setCauseAreaKey(int causeAreaKey) {
		this.causeAreaKey = causeAreaKey;
	}
	
}

