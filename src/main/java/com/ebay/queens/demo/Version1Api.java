package com.ebay.queens.demo;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ebay.queens.requests.findnonprofit.PaginationInput;
import com.ebay.queens.requests.charityitems.CharityItemRequest;
import com.ebay.queens.requests.charityitems.Constraints;
import com.ebay.queens.requests.charityitems.GlobalAspect;
import com.ebay.queens.requests.charityitems.SearchRequest;
import com.ebay.queens.requests.findnonprofit.*;
import com.ebay.queens.requests.findnonprofit.FindNonProfitRequest;
import com.ebay.queens.requests.getitem.*;
import com.ebay.queens.responses.charityitemresponse.CharityItemResponse;
import com.ebay.queens.responses.findnonprofitresponse.FindNonProfitResponse;
import com.ebay.queens.responses.getitemresponse.*;
import com.ebay.queens.responses.searchitemresponse.SearchItemResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Represents a class to access and hit all of the eBays api's
 */
@Component
@Path("/v1")
public class Version1Api implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(Version1Api.class);

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("Testing");
		//this.getItem("333460893922");
		 //this.findNonProfit("10484");
		this.findCharityItems("10484");
		//this.searchItem("drone");
	}

	@Autowired
	private Http httpClass;

	public static void main(String[] args) throws IOException {
		
	}

	/**
	 * Represents an api to search for the nonprofit id which is then used to bring
	 * back the charity items
	 * 
	 * @param charityId
	 *            - charityId is used to get information on the charity to obtain
	 *            the external id to return a list of products
	 * @throws JAXBException 
	 */
	@GET
	@Path("/advancedfindcharityItems")
	@Produces(MediaType.APPLICATION_JSON)
	public String advancedFindCharityItems(@QueryParam("charityId") String charityId) throws IOException, JAXBException {
		FindNonProfitResponse response = findNonProfit(charityId);
		String nonProfitId = response.getNonProfit();
		LOGGER.info("Non Profit Id: " + nonProfitId);
		String response2 = findCharityItems(nonProfitId);
		LOGGER.info("Response: " + response2);
		return response2;
	}

	/**
	 * Represents an api to return a list of products matching the search term
	 * 
	 * @param searchTerm
	 *            - uses the search term to return a list of products matching the
	 *            search term
	 */
	@GET
	@Path("/searchItem")
	@Produces(MediaType.APPLICATION_JSON)
	public SearchItemResponse searchItem(@QueryParam("searchTerm") String searchTerm) throws IOException {
		LOGGER.info("Search Item: " + searchTerm);
		String url = "https://api.ebay.com/buy/browse/v1/item_summary/search?q=" + searchTerm;
		String response = httpClass.genericSendGET(url, "searchItem");
		final ObjectMapper mapper = new ObjectMapper(); 
		final SearchItemResponse searchItemResponse = mapper.readValue(response, SearchItemResponse.class);
		System.out.println("Deserialized JSON String --> Object");
		System.out.println(searchItemResponse.getHref());
		System.out.println(searchItemResponse.getHref());
		System.out.println(searchItemResponse.getTotal());
		return searchItemResponse;
	}

	/**
	 * Represents an api to return products from a specific charity with a specific
	 * listing type
	 * 
	 * @param charityItemId
	 *            - uses the charity item id to return list of products/inventory
	 *            for that specific charity
	 */
	@GET
	@Path("/advancedcharityItems")
	@Produces(MediaType.APPLICATION_JSON)
	public String advancedCharitySearch(@QueryParam("charityItemId") String charityItemId,
			@QueryParam("listingType") String listingType) throws IOException {
		LOGGER.info("Advanced Charity Search");
		LOGGER.info("Charity Item Id: " + charityItemId + " Listing Type: " + listingType);
		String requestBody = "{\r\n" + "    \"searchRequest\": {\r\n" + "        \"sortOrder\": \"BestMatch\",\r\n"
				+ "        \"paginationInput\": {\r\n" + "            \"pageNumber\": 1,\r\n"
				+ "            \"entriesPerPage\": 5\r\n" + "        },\r\n" + "        \"keyword\": \"phone\",\r\n"
				+ "        \"constraints\": {\r\n" + "            \"globalAspect\": [\r\n" + "                {\r\n"
				+ "                    \"constraintType\": \"CharityIds\",\r\n" + "                    \"value\": ["
				+ charityItemId + "]\r\n" + "                },\r\n" + "                {\r\n"
				+ "                    \"constraintType\": \"CharityOnly\",\r\n"
				+ "                    \"value\": [\"true\"]\r\n" + "                },\r\n" + "                {\r\n"
				+ "                    \"constraintType\": \"ListingType\",\r\n" + "                    \"value\": [\""
				+ listingType + "\"],\r\n" + "                    \"paramNameValue\": [\r\n"
				+ "                        {\r\n" + "                            \"name\": \"operator\",\r\n"
				+ "                            \"value\": \"exclusive\"\r\n" + "                        }\r\n"
				+ "                    ]\r\n" + "                }\r\n" + "            ]\r\n" + "        }\r\n"
				+ "    }\r\n" + "}";
		LOGGER.info(requestBody);
		String url = "https://api.ebay.com/buying/search/v2";
		String response = httpClass.genericJSONSendPOST(url, requestBody, "charityItem");
		return response;
	}

	/**
	 * Represents an api to return products from a specific charity
	 * 
	 * @param charityItemId
	 *            - uses the charity item id to return list of products/inventory
	 *            for that specific charity
	 */
	@GET
	@Path("/findcharityItems")
	@Produces(MediaType.APPLICATION_JSON)
	public CharityItemResponse findCharityItems(@QueryParam("charityItemId") String charityItemId) throws IOException {
		LOGGER.info("Find Charity Items");
		PaginationInput paginationInput = new PaginationInput("1", "25");
		String[] valueIds = {"88"};
		String[] charityOnly = {"true"};
		GlobalAspect globalAspect1 = new GlobalAspect("CharityIds", valueIds);
		GlobalAspect globalAspect2 = new GlobalAspect("CharityOnly", charityOnly);
		GlobalAspect globalAspectList[] = new GlobalAspect[2]; 
		globalAspectList[0] = globalAspect1;
		globalAspectList[1] = globalAspect2;
		Constraints constraints = new Constraints(globalAspectList);
		SearchRequest searchRequest = new SearchRequest("StartTimeNewest", paginationInput, constraints); 
		CharityItemRequest charityItemRequest = new CharityItemRequest(searchRequest);
		String response = httpClass.genericJSONSendPOST("https://api.ebay.com/buying/search/v2", charityItemRequest, "charityItem");
		LOGGER.info(response);
		final ObjectMapper mapper = new ObjectMapper();
		final CharityItemResponse charityItemResponse = mapper.readValue(response, CharityItemResponse.class);
		System.out.println("Deserialized JSON String --> Object");
		System.out.println( charityItemResponse.getItems());
		System.out.println("---------------------------------");
		return charityItemResponse;
	}

	/**
	 * Represents an api to return specific product information
	 * 
	 * @param input
	 *            - uses the input to search for a specific product in the itemId
	 *            field
	 * @throws JAXBException
	 */
	@GET
	@Path("/getItem")
	@Produces(MediaType.APPLICATION_XML)
	public GetItemResponse getItem(@QueryParam("input") String input) throws IOException, JAXBException {
		LOGGER.info("Get Item Method");
		GetItemResponse getItemResponse = null;
		RequesterCredentials reqCred = new RequesterCredentials(
				"v^1.1#i^1#r^0#f^0#I^3#p^3#t^H4sIAAAAAAAAAOVYa2wUVRTu9oWlFOMTRDDrUDAIs3vnsY8Z2Y1LdwtL2+3aLQQI0tydudMOnZ1ZZu62LKgslUCBBND+MEhIqklDfEUkEANGg4lUUk1MJZgiMUGCyg8wKhqUqHFm+2BbIvRBTBP3z2bOPa/vO+fcuXNBprjkye3Ltl8vs03J78yATL7NRpWCkuKihdML8mcV5YEcBVtnpjxT2FZwebEBE0qSr0NGUlMNZN+YUFSDzwp9REpXeQ0assGrMIEMHgt8LFBTzdMOwCd1DWuCphD2cNBHII83HgdI4CQv9LhoyZSqgz7rNR/BUgxH0ZzECiLLuAVkrhtGCoVVA0MV+wga0IAEFMmAeorlaYZngIMFzBrCvhLphqyppooDEP5sunzWVs/J9fapQsNAOjadEP5woDJWGwgHQ5H6xc4cX/4BHmIY4pQx/KlCE5F9JVRS6PZhjKw2H0sJAjIMwunvjzDcKR8YTGYc6fdTDRlEuwUoSDQrQoG5K1RWanoC4tvnYUlkkZSyqjxSsYzTd2LUZCO+Hgl44CliuggH7dbfMymoyJKMdB8RWhJYvSIWqiPssWhU11pkEYkWUor20l4XR7k5wp9IKaLekGqmAcUOxOl3NsDyiEAVmirKFmeGPaLhJchMGo2khsqhxlSqVWv1gISthHL13AMUMhy3xqppfxFTuEm1yooSJg/27OOdCzDYETd74G71hCcOvcjFiEzcTUuCy3trT1izPva+8FulCUSjTisXFIdpMgH1ZoSTChQQKZj0phJIl0WecUk045UQKbo5iWQ5SSLjLtFNUhJCAKF4XOC8/6P2wFiX4ymMhlpk5EIWoznLJqW8DCUea81IrU8nETFSM7vxDPTFRsNHNGGc5J3O1tZWRyvj0PRGJw0A5VxVUx0TmlACEkO68p2VSTnbIdZ+bOrz2EzAR2w0G9AMrjYS/rpQZV0otqyhvrYqFBls3mGZ+UdK/wVpDAk6wpMLHYWjzhVOigJ1AW15jI2ml2shuSq6CaysX1LTAisqGFqo2LR0oVTL+iYGXtCSKKopspD+LxiwZn30LDC6GIU6TseQopiCCQE1LKCTq8iWvWE6gEnZYY2bQ9ASTg2aO7YlashmbB+NktMwCXL073+mZ4eOoKipSno8xmOwkdUWc//Q9PR4Ag4Zj8EGCoKWUvF4wg2YjsFCSimSrCjWFjmegDnmY0lThUoay4IxrpCyanWbMQaTJExnAYqykbRmZVSWpsx8tQrIYb7usketoWSHzaI162Od0kAyGU4kUhjGFRQWJ9e4soAFtGtCm5AFb5KhComtUBdrUmRFE9TNWkbJaF2QdDFeDnjctMs8MHFc3OueGO6aRnmSwaY4N825PG4vCwAzIWxB1DLZairRFBRpt4dENEuRLBIB6eVYhvTQlAQ5EBckBk4Ic4Uim5Ofcygs3PrTJMG+TDMwEkeLboQg51B8y+eQc/h1hD8v+6PabMdAm+29fJsNOME8ai54vLhgRWHBtFmGjM0dEkoOQ25Uza9sHTmaUToJZT2/2Ca//OWOszkXIJ3PgplDVyAlBVRpzn0ImH1zpYi6d0aZRQhjfk3QDAPWgLk3VwuphwsfjBz++J3M4t4jfyx6e2poZ0P7oZfWZkDZkJLNVpRX2GbLazoSYc/Rs8/vqeO3fRXrTVR9933bfQc+3Lvp0LXDB8HmPrrKtaD5xOJ333puVXlZ9aufz5/+5m9Fp/Ufn+/oeW1Vw+55pQueet8de6Vj7WXfG3OPCl1/df0AHyhbf6mjBJ+8vnnDNHpbKq6WHwqiyJme6nOxG9cfyky5f93FOaWRR0991jdjdd6B41/vaz/Tw134vf3P/J+veY73Xf2242DnzK2nfl2/98brBbvOly89c3Hz8t1b1h594pN1XY8tQj2ny3r3XNrNfNAYnvPilQ2J9h22s/v2/nJs6paWruA3V164WvnFxb9PdJ/curP6I77jcHdw/v6eT9tx94V79l+mZjJ9macfOdu7rruwYReW+8v3DwhYVRmaEgAA");
		GetItemRequest getItemRequest = new GetItemRequest(reqCred, "333460893922", "ReturnAll");
		String result = httpClass.genericXMLSendPOST("https://api.ebay.com/ws/api.dll", getItemRequest, "getItem");
		System.out.println("Result: " + result);
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(GetItemResponse.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			getItemResponse = (GetItemResponse) unmarshaller.unmarshal(new StringReader(result));
			System.out.println("Deserialized XML String --> Object");
			System.out.println(getItemResponse.getAck());
			System.out.println(getItemResponse.getBuild());
			System.out.println(getItemResponse.getTimestamp());
			System.out.println(getItemResponse.getItem());
			System.out.println("---------------------------------");
		} catch (JAXBException e) {
			LOGGER.error("Failed to deserialize XML.", e);
		}
		return getItemResponse;
	}

	/**
	 * Represents an api to bring information on non profits
	 * 
	 * @param nonProfitInput
	 *            - uses the externalId to bring up information on nonprofit
	 * @throws JAXBException
	 */
	@GET
	@Path("/findnonProfit")
	@Produces(MediaType.APPLICATION_XML)
	public FindNonProfitResponse findNonProfit(@QueryParam("nonProfitInput") String nonProfitInput)
			throws IOException, JAXBException {
		LOGGER.info("Find Non Profit Method");
		SearchFilter searchFilter = new SearchFilter(nonProfitInput);
		PaginationInput paginationInput = new PaginationInput("25","1");
		FindNonProfitRequest findNonProfitRequest = new FindNonProfitRequest(searchFilter, paginationInput);
		String response = httpClass.genericXMLSendPOST("http://svcs.ebay.com/services/fundraising/FundRaisingFindingService/v1", findNonProfitRequest, "nonProfit");
		FindNonProfitResponse findNonProfitResponse = new FindNonProfitResponse();
		response = "<findNonprofitResponse><ack>Success</ack><version>1.0.0</version><timestamp>2020-01-30T13:37:25.250Z</timestamp><nonprofit><name> We Share Foundation </name><logoURL>https://i.ebayimg.com/00/s/ODI1WDU3MA==/z/hx4AAOSwe4hdxS~T/$_1.JPG?set_id=8800005007</logoURL><nonprofitId>136705</nonprofitId><externalId>1999180</externalId><homePageURL>http://www.quotainternationalfortcollins.com</homePageURL></nonprofit><nonprofit><name> inc.</name><logoURL>https://i.ebayimg.com/00/s/MjcxWDQzMg==/z/dBIAAOSwAGxdxoCs/$_1.PNG?set_id=8800005007</logoURL><nonprofitId>133980</nonprofitId><externalId>1276598</externalId><homePageURL>http://charity.ebay.com/charity/charity.jsp?NP_ID=1276598</homePageURL></nonprofit><nonprofit><name>His PlaceContact Center Inc.</name><logoURL>https://i.ebayimg.com/00/s/NzA4WDcxNA==/z/vUEAAOSwzWFd6rhu/$_1.JPG?set_id=8800005007</logoURL><nonprofitId>144403</nonprofitId><externalId>1659362</externalId><homePageURL>http://www.hisplacecc.org</homePageURL></nonprofit><nonprofit><name> School Inc</name><logoURL>https://i.ebayimg.com/00/s/NjQ4WDM2NQ==/z/wjEAAOSwZzVd3OrU/$_1.PNG?set_id=8800005007</logoURL><nonprofitId>176155</nonprofitId><externalId>1299915</externalId><homePageURL>http://www.iamschool.org</homePageURL></nonprofit><nonprofit><name>#bettereveryday</name><logoURL>https://i.ebayimg.com/00/s/OTU5WDk1OQ==/z/C1YAAOSwWXddVeJ4/$_1.JPG?set_id=8800005007</logoURL><nonprofitId>139338</nonprofitId><externalId>2770598</externalId><homePageURL>https://www.bettereverydayweb.com/</homePageURL></nonprofit><nonprofit><name>#Projectmack</name><logoURL>https://i.ebayimg.com/00/s/MTYwMFgxNjAw/z/WpcAAOSwy9VdVeJf/$_1.JPG?set_id=8800005007</logoURL><nonprofitId>150828</nonprofitId><externalId>2838133</externalId><homePageURL>http://www.projectmack.com</homePageURL></nonprofit><nonprofit><name>#SchylerStrong Foundation, Inc.</name><logoURL>https://i.ebayimg.com/00/s/MTMzWDE1MA==/z/qNwAAOSw8gpdjc~d/$_1.PNG?set_id=8800004005</logoURL><nonprofitId>184643</nonprofitId><externalId>3643025</externalId><homePageURL>https://www.schylerstrong.com</homePageURL></nonprofit><nonprofit><name>#teamferal Cat Rescue and Adoptions</name><logoURL>https://i.ebayimg.com/00/s/MjcwWDUxMg==/z/mZUAAOSweHtdvUYp/$_1.PNG?set_id=8800005007</logoURL><nonprofitId>171613</nonprofitId><externalId>1208059</externalId><homePageURL>http://charity.ebay.com/charity/charity.jsp?NP_ID=1208059</homePageURL></nonprofit><nonprofit><name>#TNM The New Music Movement of Love Inc.</name><logoURL>https://i.ebayimg.com/00/s/ODAwWDgwMA==/z/uEkAAOSw8ltdVePk/$_1.JPG?set_id=8800005007</logoURL><nonprofitId>145490</nonprofitId><externalId>1487968</externalId><homePageURL>http://charity.ebay.com/charity/charity.jsp?NP_ID=1487968</homePageURL></nonprofit><nonprofit><name>#WeAreKY!, Inc.</name><logoURL>https://i.ebayimg.com/00/s/MTYwMFgxNjAw/z/1mEAAOSwoOddVeYQ/$_1.JPG?set_id=8800005007</logoURL><nonprofitId>138661</nonprofitId><externalId>1614314</externalId><homePageURL>http://charity.ebay.com/charity/charity.jsp?NP_ID=1614314</homePageURL></nonprofit><nonprofit><name>'New' Caring &amp; Sharing</name><logoURL>https://i.ebayimg.com/00/s/Mzg4WDM4Ng==/z/s5oAAOSwFQVd90gK/$_1.JPG?set_id=8800005007</logoURL><nonprofitId>189343</nonprofitId><externalId>1397870</externalId><homePageURL>http://charity.ebay.com/charity/charity.jsp?NP_ID=1397870</homePageURL></nonprofit><nonprofit><name>'North Shore Alliance of Gay, Lesbian, Bisexual, and Transgende</name><logoURL>https://i.ebayimg.com/00/s/MjE1WDQyNQ==/z/7vkAAOSwf4ldVeOK/$_1.JPG?set_id=8800005007</logoURL><nonprofitId>36444</nonprofitId><externalId>29938</externalId><homePageURL>http://www.nagly.org</homePageURL></nonprofit><nonprofit><name>'Ohana Komputer</name><logoURL>https://i.ebayimg.com/00/s/MzAzWDM5Mg==/z/BnIAAOSwYThdVedA/$_1.PNG?set_id=8800005007</logoURL><nonprofitId>107209</nonprofitId><externalId>2293402</externalId><homePageURL>http://charity.ebay.com/charity/charity.jsp?NP_ID=2293402</homePageURL></nonprofit><nonprofit><name>'Spark Theater Company, Inc.</name><logoURL>https://i.ebayimg.com/00/s/Mzg2WDgzMw==/z/l8cAAOSwipddYk45/$_1.PNG?set_id=8800005007</logoURL><nonprofitId>176601</nonprofitId><externalId>2179993</externalId><homePageURL>http://sparktheatercompany.com</homePageURL></nonprofit><nonprofit><name>'WEEN DREAM</name><logoURL>https://i.ebayimg.com/00/s/NDIwWDUxNQ==/z/-7wAAOSwkTZdxoCn/$_1.PNG?set_id=8800005007</logoURL><nonprofitId>84377</nonprofitId><externalId>69244</externalId><homePageURL>http://charity.ebay.com/charity/charity.jsp?NP_ID=69244</homePageURL></nonprofit><nonprofit><name>(914) Cares, Inc</name><logoURL>https://i.ebayimg.com/00/s/MTE0NFgxMzU4/z/LmQAAOSwctddVeXh/$_1.JPG?set_id=8800005007</logoURL><nonprofitId>162945</nonprofitId><externalId>1527840</externalId><homePageURL>http://www.914cares.org</homePageURL></nonprofit><nonprofit><name>(Charleston) Pro Bono Legal Services, Inc.</name><logoURL>https://i.ebayimg.com/00/s/NTU5WDE2MDA=/z/4oEAAOSwEhNdVeMb/$_1.JPG?set_id=8800005007</logoURL><nonprofitId>149716</nonprofitId><externalId>1398397</externalId><homePageURL>http://www.CharlestonProBono.org</homePageURL></nonprofit><nonprofit><name>(FMWC) Fathers and Mothers Who Care</name><logoURL>http://i.ebayimg.com/00/s/MjEzWDE0MQ==/z/NKgAAOSwA3dYI8pW/$_1.JPG?set_id=8800004005</logoURL><nonprofitId>103952</nonprofitId><externalId>117101</externalId><homePageURL>http://www.fmwc.org</homePageURL></nonprofit><nonprofit><name>(ISC)² Huntsville Chapter</name><logoURL>http://i.ebayimg.com/images/g/2Z4AAOSwdGxXImrM/s-l1600.jpg</logoURL><nonprofitId>151893</nonprofitId><externalId>3493667</externalId><homePageURL>https://www.isc2-huntsvillechapter.org</homePageURL></nonprofit><nonprofit><name>(RED)</name><logoURL>https://i.ebayimg.com/00/s/NTY2WDgwMA==/z/MeMAAOSwm8ZeMptf/$_1.JPG?set_id=8800005007</logoURL><nonprofitId>90330</nonprofitId><externalId>114552</externalId><homePageURL>https://www.red.org</homePageURL></nonprofit><nonprofit><name>(Un)Fur-gotten Paws</name><logoURL>https://i.ebayimg.com/00/s/OTNYNTg5/z/UQ8AAOSwCHddVeSp/$_1.PNG?set_id=8800005007</logoURL><nonprofitId>172654</nonprofitId><externalId>3700975</externalId><homePageURL>http://Unfurgottenpaws.org</homePageURL></nonprofit><nonprofit><name>...ARTiSTiCALLY ME! An Art Education Program</name><logoURL>https://i.ebayimg.com/00/s/MTIzNlgxNjAw/z/M1oAAOSwUOJd81Os/$_1.PNG?set_id=8800005007</logoURL><nonprofitId>189219</nonprofitId><externalId>2000614</externalId><homePageURL>http:/www.Artisticallyme.og</homePageURL></nonprofit><nonprofit><name>1 Boy 4 Change</name><logoURL>https://i.ebayimg.com/00/s/MzM2WDQwNw==/z/CXoAAOSwhMhdVeUm/$_1.JPG?set_id=8800005007</logoURL><nonprofitId>33088</nonprofitId><externalId>27435</externalId><homePageURL>http://www.1Boy4Change.org</homePageURL></nonprofit><nonprofit><name>1 Horse At A Time Draft Horse Rescue</name><logoURL>https://i.ebayimg.com/00/s/NjQwWDYwMQ==/z/kkgAAOSwC4FdVeMH/$_1.JPG?set_id=8800005007</logoURL><nonprofitId>153158</nonprofitId><externalId>3284949</externalId><homePageURL>http://www.1horseatatime.com</homePageURL></nonprofit><nonprofit><name>1 in 3 foundation</name><logoURL>https://i.ebayimg.com/00/s/MzE4WDQ1MA==/z/S2IAAOSwLbJdVeVF/$_1.JPG?set_id=8800005007</logoURL><nonprofitId>106875</nonprofitId><externalId>2013863</externalId><homePageURL>http://charity.ebay.com/charity/charity.jsp?NP_ID=2013863</homePageURL></nonprofit><paginationOutput><pageNumber>1</pageNumber><pageSize>25</pageSize><totalEntries>74177</totalEntries><totalPages>2968</totalPages></paginationOutput></findNonprofitResponse>";
		LOGGER.info(response.toString());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(FindNonProfitResponse.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			findNonProfitResponse = (FindNonProfitResponse) unmarshaller.unmarshal(new StringReader(response));
			System.out.println("Deserialized XML String --> Object");
			System.out.println(findNonProfitResponse.getAck());
			System.out.println(findNonProfitResponse.getTimestamp());
			System.out.println(findNonProfitResponse.getVersion());
			System.out.println(findNonProfitResponse.getNonProfit());
			System.out.println("---------------------------------");
		} catch (JAXBException e) {
			LOGGER.error("Failed to deserialize XML.", e);
		}
		return findNonProfitResponse;
	}
}