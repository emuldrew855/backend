package com.ebay.queens.responses;
import java.util.Map;
import com.ebay.queens.responses.paypalgetcharityresponse.CauseArea;
import com.ebay.queens.responses.paypalgetcharityresponse.GetCharityResult;

public class CharityCache {
	private Map<Integer, GetCharityResult> currentCharityResponses;
	private Map<Integer, GetCharityResult> _tempCharityResponse;
	private Map<Integer, CauseArea> currentCauseAreas;
	private Map<Integer, CauseArea> _tempCurrentCauseAreas;
	int charityKey = 0;
	int causeAreaKey = 0;


	// Methods
	public void addCharity(GetCharityResult paypalCharity) {
		charityKey = charityKey + 1;
		currentCharityResponses.put(charityKey, paypalCharity);
	}
	
	public void addCharityCause(CauseArea causeArea) {
		charityKey = charityKey + 1;
		currentCauseAreas.put(charityKey, causeArea);
	}

	

}

