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
	int causeAreaKey = 18;
	
	public CharityCache() {
		Map<Integer, GetCharityResult> currentCharityResponses = new HashMap<>();
		this.currentCharityResponses = currentCharityResponses;
		Map<Integer, String> currentCauseArea = new HashMap<>();
		Map<Integer, String> causeAreas  = new HashMap<Integer, String>() {{
		    put(1, "Animals");
		    put(2, "Arts and Culture");
		    put(3, "Children and Youth Development");
		    put(4, "Crime Prevention and Justice");
		    put(5, "Disaster Relief");
		    put(6, "Environment");
		    put(7, "Employment and Professional Associations");
		    put(8, "Health and Medicine");
		    put(9, "Human Services");
		    put(10, "Housing, Homelessness and Hunger");
		    put(11, "International");
		    put(12, "Military and Veterans");
		    put(13, "Philanthropy, Grants, Other");
		    put(14, "Religion and Spirituality");
		    put(15, "Schools and Education");
		    put(16, "Science and Research");
		    put(17, "Schools and Education");
		    put(18, "Sports and Recreation");
		}};
		currentCauseArea.putAll(causeAreas);
		this.currentCauseAreas = currentCauseArea;
	}

	// Methods
	public void addCharity(GetCharityResult paypalCharity) {
		currentCharityResponses.put(Integer.parseInt(paypalCharity.getNonprofit_id()), paypalCharity);
	}
	
	public void addCharityCause(String causeArea) {
		if(!this.currentCauseAreas.containsValue(causeArea)) {			
			charityKey = charityKey + 1;
			currentCauseAreas.put(charityKey, causeArea);
		}
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

