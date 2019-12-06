package com.ebay.queens.demo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void testGetItem() throws IOException, IOException {
		Http httpClass = new Http();
		String requestBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + 
    			"<GetItemRequest xmlns=\"urn:ebay:apis:eBLBaseComponents\">\r\n" + 
    			"  <RequesterCredentials>\r\n" + 
    			"    <eBayAuthToken>AgAAAA**AQAAAA**aAAAAA**9enaXQ**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6AGmICpDZmDpwWdj6x9nY+seQ**OSoGAA**AAMAAA**mc4q0e94Onut08iyCeBQDC6u1QQokVs4kukRMxe2PSdQ4CsKUIxSGhgFHFraUV0VJ1fLgYmKg6kXDQh0idHTgKjcHFPcuPDmtwT5TSH+SAKHw6TaZkAgVLgOMBprmwgLAJNiTESHarSfuuJxUdILw3lB/rrGC3tQ3sFz8l3SxpCx6NXvuOQ0++3aZ2gB/EPeiT70bR/z87bVkZYoK/OAG/vRCBVch5xo2PiJWJl5xLYpG3iz6aauTiJur3TKC3cPriMLWDkLAOqhXZol9jp2cknPesRfDypOBzDnvECNP81F18t0/3u/Lgn9BWxYdefCm95Rkw3XjZwQTG1GVLqBHoWBpG3s8eLuQhChlbH52ecF7sFb3aSXdvTcOCSwVHMA0GRjPhcoNt91WOfI22tUaJJ7/H72IqosJw235lvgvqQ5UQSzh5BE/Wp0u9bGzpUHgODeRtfO45miC+5itGBs0r1KKjN0CEzQ8WsDzWK2eGmmnznW8f2osEa83C9sa41/dEC5U1Cy8vpMgp/nz+qjKf+wQ3OUsSEgOKrjvC3tZcWUivhyu/GPEhAHEF6XBOTOyMnspoZKWNL4RMxGxfpeG3ANoer4vmdizPK7C6h3eLyTTYfL0jcML9Ld+rFKMD7hVx8ATu32nQVt3GmXa9m8cp2rSNPdgRV36LMWRxW2aXMq+MksRZkNhhm4WxSGUykR3N8K8jFiD9LPrz0pEux8UXEfF8ZWEKlxn2jCn9Tjy1WLMQ5ljotGHt+eMsfkaWoC</eBayAuthToken>\r\n" + 
    			"  </RequesterCredentials>\r\n" + 
    			"  <ItemID>"+ "323986487909" + "</ItemID>\r\n" + 
    			"</GetItemRequest>";
		String response = httpClass.genericSendPOST("https://api.ebay.com/ws/api.dll", requestBody, "getItem");
		System.out.print("Response" + response);
		String actualResponse = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
				"<GetItemResponse xmlns=\"urn:ebay:apis:eBLBaseComponents\">\n" + 
				"    <Timestamp>2019-12-05T20:00:01.347Z</Timestamp>\n" + 
				"    <Ack>Success</Ack>\n" + 
				"    <Version>1125</Version>\n" + 
				"    <Build>E1125_INTL_API_19070421_R1</Build>\n" + 
				"    <Item>\n" + 
				"        <AutoPay>false</AutoPay>\n" + 
				"        <BuyerProtection>ItemIneligible</BuyerProtection>\n" + 
				"        <BuyItNowPrice currencyID=\"GBP\">0.0</BuyItNowPrice>\n" + 
				"        <Charity>\n" + 
				"            <CharityName>Children with Cancer</CharityName>\n" + 
				"            <CharityNumber>13221</CharityNumber>\n" + 
				"            <DonationPercent>10.0</DonationPercent>\n" + 
				"            <CharityID>18539</CharityID>\n" + 
				"            <Mission>Children with Cancer UK, Reg Charity No 298405 is the leading national children&apos;s charity dedicated to the fight against childhood cancer. Our aims are to determine the causes, find cures and provide care for children with cancer.</Mission>\n" + 
				"            <LogoURL>https://i.ebayimg.com/00/s/NjM4WDE2MDA=/z/DVMAAOSwfdhd6Meu/$_1.PNG?set_id=8800005007</LogoURL>\n" + 
				"            <Status>Valid</Status>\n" + 
				"        </Charity>\n" + 
				"        <Country>GB</Country>\n" + 
				"        <Currency>GBP</Currency>\n" + 
				"        <Description>&lt;font rwr=&apos;1&apos; size=&apos;4&apos; style=&apos;font-family:Arial&apos;&gt;A wonderful selection of&amp;nbsp; quality pure wood spatulas ..Each one is hand pyrographed and painted in a lovely variety of designs ,including ..blue nose teddy xmas ,winnie pooh ,snoopy and much more..All are 100% unique and 1 offs ,each&amp;nbsp; has an RRP of £5-£7 each ,so min total of £60 worth ..14 designs in total ,great for markets,craft fairs,schools, online .. This item will not be repeated ,all come bagged with hanging ribbon.&lt;/font&gt;</Description>\n" + 
				"        <GiftIcon>0</GiftIcon>\n" + 
				"        <HitCounter>NoHitCounter</HitCounter>\n" + 
				"        <ItemID>323986487909</ItemID>\n" + 
				"        <ListingDetails>\n" + 
				"            <Adult>false</Adult>\n" + 
				"            <BindingAuction>false</BindingAuction>\n" + 
				"            <CheckoutEnabled>true</CheckoutEnabled>\n" + 
				"            <ConvertedBuyItNowPrice currencyID=\"GBP\">0.0</ConvertedBuyItNowPrice>\n" + 
				"            <ConvertedStartPrice currencyID=\"GBP\">0.99</ConvertedStartPrice>\n" + 
				"            <HasReservePrice>false</HasReservePrice>\n" + 
				"            <StartTime>2019-11-20T13:21:31.000Z</StartTime>\n" + 
				"            <EndTime>2019-11-23T13:21:31.000Z</EndTime>\n" + 
				"            <ViewItemURL>https://www.ebay.co.uk/itm/HANDCRAFTED-PURE-WOOD-SPATULAS-X-14-/323986487909</ViewItemURL>\n" + 
				"            <HasUnansweredQuestions>false</HasUnansweredQuestions>\n" + 
				"            <HasPublicMessages>false</HasPublicMessages>\n" + 
				"            <ViewItemURLForNaturalSearch>https://www.ebay.co.uk/itm/HANDCRAFTED-PURE-WOOD-SPATULAS-X-14-/323986487909</ViewItemURLForNaturalSearch>\n" + 
				"        </ListingDetails>\n" + 
				"        <ListingDesigner>\n" + 
				"            <LayoutID>310000</LayoutID>\n" + 
				"            <ThemeID>310</ThemeID>\n" + 
				"        </ListingDesigner>\n" + 
				"        <ListingDuration>Days_3</ListingDuration>\n" + 
				"        <ListingType>Chinese</ListingType>\n" + 
				"        <Location>Stevenage, Hertfordshire</Location>\n" + 
				"        <PaymentMethods>PayPal</PaymentMethods>\n" + 
				"        <PrimaryCategory>\n" + 
				"            <CategoryID>45077</CategoryID>\n" + 
				"            <CategoryName>Wholesale &amp; Job Lots:Crafts</CategoryName>\n" + 
				"        </PrimaryCategory>\n" + 
				"        <PrivateListing>false</PrivateListing>\n" + 
				"        <ProductListingDetails>\n" + 
				"            <EAN>Does not apply</EAN>\n" + 
				"            <BrandMPN>\n" + 
				"                <Brand>Handmade</Brand>\n" + 
				"            </BrandMPN>\n" + 
				"            <IncludeeBayProductDetails>true</IncludeeBayProductDetails>\n" + 
				"        </ProductListingDetails>\n" + 
				"        <Quantity>1</Quantity>\n" + 
				"        <ReviseStatus>\n" + 
				"            <ItemRevised>false</ItemRevised>\n" + 
				"        </ReviseStatus>\n" + 
				"        <Seller>\n" + 
				"            <AboutMePage>false</AboutMePage>\n" + 
				"            <Email>Invalid Request</Email>\n" + 
				"            <FeedbackScore>646</FeedbackScore>\n" + 
				"            <PositiveFeedbackPercent>100.0</PositiveFeedbackPercent>\n" + 
				"            <FeedbackPrivate>false</FeedbackPrivate>\n" + 
				"            <FeedbackRatingStar>Purple</FeedbackRatingStar>\n" + 
				"            <IDVerified>false</IDVerified>\n" + 
				"            <eBayGoodStanding>true</eBayGoodStanding>\n" + 
				"            <NewUser>false</NewUser>\n" + 
				"            <RegistrationDate>2011-05-21T11:01:55.000Z</RegistrationDate>\n" + 
				"            <Site>UK</Site>\n" + 
				"            <Status>Confirmed</Status>\n" + 
				"            <UserID>uneek2day</UserID>\n" + 
				"            <UserIDChanged>false</UserIDChanged>\n" + 
				"            <UserIDLastChanged>2014-12-11T13:07:22.000Z</UserIDLastChanged>\n" + 
				"            <VATStatus>VATTax</VATStatus>\n" + 
				"            <SellerInfo>\n" + 
				"                <AllowPaymentEdit>true</AllowPaymentEdit>\n" + 
				"                <CheckoutEnabled>true</CheckoutEnabled>\n" + 
				"                <CIPBankAccountStored>false</CIPBankAccountStored>\n" + 
				"                <GoodStanding>true</GoodStanding>\n" + 
				"                <LiveAuctionAuthorized>false</LiveAuctionAuthorized>\n" + 
				"                <MerchandizingPref>OptIn</MerchandizingPref>\n" + 
				"                <QualifiesForB2BVAT>false</QualifiesForB2BVAT>\n" + 
				"                <StoreOwner>false</StoreOwner>\n" + 
				"                <SellerBusinessType>Commercial</SellerBusinessType>\n" + 
				"                <SafePaymentExempt>false</SafePaymentExempt>\n" + 
				"                <TopRatedSeller>true</TopRatedSeller>\n" + 
				"            </SellerInfo>\n" + 
				"            <MotorsDealer>false</MotorsDealer>\n" + 
				"        </Seller>\n" + 
				"        <SellingStatus>\n" + 
				"            <BidCount>6</BidCount>\n" + 
				"            <BidIncrement currencyID=\"GBP\">0.2</BidIncrement>\n" + 
				"            <ConvertedCurrentPrice currencyID=\"GBP\">4.2</ConvertedCurrentPrice>\n" + 
				"            <CurrentPrice currencyID=\"GBP\">4.2</CurrentPrice>\n" + 
				"            <HighBidder>\n" + 
				"                <AboutMePage>false</AboutMePage>\n" + 
				"                <FeedbackScore>651</FeedbackScore>\n" + 
				"                <PositiveFeedbackPercent>100.0</PositiveFeedbackPercent>\n" + 
				"                <FeedbackRatingStar>Purple</FeedbackRatingStar>\n" + 
				"                <IDVerified>false</IDVerified>\n" + 
				"                <eBayGoodStanding>true</eBayGoodStanding>\n" + 
				"                <NewUser>false</NewUser>\n" + 
				"                <Status>Confirmed</Status>\n" + 
				"                <UserID>u***s</UserID>\n" + 
				"                <VATStatus>VATTax</VATStatus>\n" + 
				"                <UserAnonymized>true</UserAnonymized>\n" + 
				"            </HighBidder>\n" + 
				"            <MinimumToBid currencyID=\"GBP\">4.4</MinimumToBid>\n" + 
				"            <QuantitySold>1</QuantitySold>\n" + 
				"            <ReserveMet>true</ReserveMet>\n" + 
				"            <SecondChanceEligible>true</SecondChanceEligible>\n" + 
				"            <ListingStatus>Completed</ListingStatus>\n" + 
				"            <QuantitySoldByPickupInStore>0</QuantitySoldByPickupInStore>\n" + 
				"        </SellingStatus>\n" + 
				"        <ShippingDetails>\n" + 
				"            <ApplyShippingDiscount>false</ApplyShippingDiscount>\n" + 
				"            <GlobalShipping>true</GlobalShipping>\n" + 
				"            <CalculatedShippingRate>\n" + 
				"                <WeightMajor measurementSystem=\"Metric\" unit=\"kg\">0</WeightMajor>\n" + 
				"                <WeightMinor measurementSystem=\"Metric\" unit=\"gm\">0</WeightMinor>\n" + 
				"            </CalculatedShippingRate>\n" + 
				"            <SalesTax>\n" + 
				"                <SalesTaxPercent>0.0</SalesTaxPercent>\n" + 
				"                <ShippingIncludedInTax>false</ShippingIncludedInTax>\n" + 
				"            </SalesTax>\n" + 
				"            <ShippingServiceOptions>\n" + 
				"                <ShippingService>UK_myHermesDoorToDoorService</ShippingService>\n" + 
				"                <ShippingServiceCost currencyID=\"GBP\">4.99</ShippingServiceCost>\n" + 
				"                <ShippingServicePriority>1</ShippingServicePriority>\n" + 
				"                <ExpeditedService>false</ExpeditedService>\n" + 
				"                <ShippingTimeMin>2</ShippingTimeMin>\n" + 
				"                <ShippingTimeMax>3</ShippingTimeMax>\n" + 
				"            </ShippingServiceOptions>\n" + 
				"            <ShippingType>Flat</ShippingType>\n" + 
				"            <ThirdPartyCheckout>false</ThirdPartyCheckout>\n" + 
				"            <ShippingDiscountProfileID>0</ShippingDiscountProfileID>\n" + 
				"            <InternationalShippingDiscountProfileID>0</InternationalShippingDiscountProfileID>\n" + 
				"            <SellerExcludeShipToLocationsPreference>true</SellerExcludeShipToLocationsPreference>\n" + 
				"        </ShippingDetails>\n" + 
				"        <ShipToLocations>GB</ShipToLocations>\n" + 
				"        <ShipToLocations>AG</ShipToLocations>\n" + 
				"        <ShipToLocations>AT</ShipToLocations>\n" + 
				"        <ShipToLocations>BE</ShipToLocations>\n" + 
				"        <ShipToLocations>BG</ShipToLocations>\n" + 
				"        <ShipToLocations>HR</ShipToLocations>\n" + 
				"        <ShipToLocations>CY</ShipToLocations>\n" + 
				"        <ShipToLocations>CZ</ShipToLocations>\n" + 
				"        <ShipToLocations>DK</ShipToLocations>\n" + 
				"        <ShipToLocations>EE</ShipToLocations>\n" + 
				"        <ShipToLocations>FI</ShipToLocations>\n" + 
				"        <ShipToLocations>FR</ShipToLocations>\n" + 
				"        <ShipToLocations>DE</ShipToLocations>\n" + 
				"        <ShipToLocations>GR</ShipToLocations>\n" + 
				"        <ShipToLocations>HU</ShipToLocations>\n" + 
				"        <ShipToLocations>IE</ShipToLocations>\n" + 
				"        <ShipToLocations>IT</ShipToLocations>\n" + 
				"        <ShipToLocations>LV</ShipToLocations>\n" + 
				"        <ShipToLocations>LT</ShipToLocations>\n" + 
				"        <ShipToLocations>LU</ShipToLocations>\n" + 
				"        <ShipToLocations>MT</ShipToLocations>\n" + 
				"        <ShipToLocations>NL</ShipToLocations>\n" + 
				"        <ShipToLocations>PL</ShipToLocations>\n" + 
				"        <ShipToLocations>PT</ShipToLocations>\n" + 
				"        <ShipToLocations>RO</ShipToLocations>\n" + 
				"        <ShipToLocations>SK</ShipToLocations>\n" + 
				"        <ShipToLocations>SI</ShipToLocations>\n" + 
				"        <ShipToLocations>ES</ShipToLocations>\n" + 
				"        <ShipToLocations>SE</ShipToLocations>\n" + 
				"        <ShipToLocations>AU</ShipToLocations>\n" + 
				"        <ShipToLocations>US</ShipToLocations>\n" + 
				"        <ShipToLocations>BH</ShipToLocations>\n" + 
				"        <ShipToLocations>CA</ShipToLocations>\n" + 
				"        <ShipToLocations>BR</ShipToLocations>\n" + 
				"        <ShipToLocations>JP</ShipToLocations>\n" + 
				"        <ShipToLocations>NZ</ShipToLocations>\n" + 
				"        <ShipToLocations>CN</ShipToLocations>\n" + 
				"        <ShipToLocations>IL</ShipToLocations>\n" + 
				"        <ShipToLocations>HK</ShipToLocations>\n" + 
				"        <ShipToLocations>NO</ShipToLocations>\n" + 
				"        <ShipToLocations>ID</ShipToLocations>\n" + 
				"        <ShipToLocations>MY</ShipToLocations>\n" + 
				"        <ShipToLocations>MX</ShipToLocations>\n" + 
				"        <ShipToLocations>SG</ShipToLocations>\n" + 
				"        <ShipToLocations>KR</ShipToLocations>\n" + 
				"        <ShipToLocations>TW</ShipToLocations>\n" + 
				"        <ShipToLocations>TH</ShipToLocations>\n" + 
				"        <ShipToLocations>BD</ShipToLocations>\n" + 
				"        <ShipToLocations>BZ</ShipToLocations>\n" + 
				"        <ShipToLocations>BM</ShipToLocations>\n" + 
				"        <ShipToLocations>BO</ShipToLocations>\n" + 
				"        <ShipToLocations>BB</ShipToLocations>\n" + 
				"        <ShipToLocations>BN</ShipToLocations>\n" + 
				"        <ShipToLocations>KY</ShipToLocations>\n" + 
				"        <ShipToLocations>DM</ShipToLocations>\n" + 
				"        <ShipToLocations>EC</ShipToLocations>\n" + 
				"        <ShipToLocations>EG</ShipToLocations>\n" + 
				"        <ShipToLocations>GG</ShipToLocations>\n" + 
				"        <ShipToLocations>GI</ShipToLocations>\n" + 
				"        <ShipToLocations>GP</ShipToLocations>\n" + 
				"        <ShipToLocations>GD</ShipToLocations>\n" + 
				"        <ShipToLocations>GF</ShipToLocations>\n" + 
				"        <ShipToLocations>IS</ShipToLocations>\n" + 
				"        <ShipToLocations>JE</ShipToLocations>\n" + 
				"        <ShipToLocations>JO</ShipToLocations>\n" + 
				"        <ShipToLocations>KH</ShipToLocations>\n" + 
				"        <ShipToLocations>KN</ShipToLocations>\n" + 
				"        <ShipToLocations>LC</ShipToLocations>\n" + 
				"        <ShipToLocations>LI</ShipToLocations>\n" + 
				"        <ShipToLocations>LK</ShipToLocations>\n" + 
				"        <ShipToLocations>MO</ShipToLocations>\n" + 
				"        <ShipToLocations>MC</ShipToLocations>\n" + 
				"        <ShipToLocations>MV</ShipToLocations>\n" + 
				"        <ShipToLocations>MS</ShipToLocations>\n" + 
				"        <ShipToLocations>MQ</ShipToLocations>\n" + 
				"        <ShipToLocations>NI</ShipToLocations>\n" + 
				"        <ShipToLocations>OM</ShipToLocations>\n" + 
				"        <ShipToLocations>PK</ShipToLocations>\n" + 
				"        <ShipToLocations>PE</ShipToLocations>\n" + 
				"        <ShipToLocations>PY</ShipToLocations>\n" + 
				"        <ShipToLocations>RE</ShipToLocations>\n" + 
				"        <ShipToLocations>TC</ShipToLocations>\n" + 
				"        <ShipToLocations>AW</ShipToLocations>\n" + 
				"        <ShipToLocations>SA</ShipToLocations>\n" + 
				"        <ShipToLocations>ZA</ShipToLocations>\n" + 
				"        <ShipToLocations>AE</ShipToLocations>\n" + 
				"        <ShipToLocations>UA</ShipToLocations>\n" + 
				"        <ShipToLocations>CL</ShipToLocations>\n" + 
				"        <ShipToLocations>BS</ShipToLocations>\n" + 
				"        <ShipToLocations>CO</ShipToLocations>\n" + 
				"        <ShipToLocations>CR</ShipToLocations>\n" + 
				"        <ShipToLocations>GT</ShipToLocations>\n" + 
				"        <ShipToLocations>HN</ShipToLocations>\n" + 
				"        <ShipToLocations>JM</ShipToLocations>\n" + 
				"        <ShipToLocations>KW</ShipToLocations>\n" + 
				"        <ShipToLocations>PA</ShipToLocations>\n" + 
				"        <ShipToLocations>QA</ShipToLocations>\n" + 
				"        <ShipToLocations>SV</ShipToLocations>\n" + 
				"        <ShipToLocations>TT</ShipToLocations>\n" + 
				"        <ShipToLocations>UY</ShipToLocations>\n" + 
				"        <ShipToLocations>VN</ShipToLocations>\n" + 
				"        <Site>UK</Site>\n" + 
				"        <StartPrice currencyID=\"GBP\">0.99</StartPrice>\n" + 
				"        <TimeLeft>PT0S</TimeLeft>\n" + 
				"        <Title>HANDCRAFTED PURE WOOD SPATULAS X 14 </Title>\n" + 
				"        <HitCount>42</HitCount>\n" + 
				"        <BestOfferDetails>\n" + 
				"            <BestOfferCount>0</BestOfferCount>\n" + 
				"            <BestOfferEnabled>true</BestOfferEnabled>\n" + 
				"            <NewBestOffer>false</NewBestOffer>\n" + 
				"        </BestOfferDetails>\n" + 
				"        <GetItFast>false</GetItFast>\n" + 
				"        <PostalCode>SG28US</PostalCode>\n" + 
				"        <PictureDetails>\n" + 
				"            <GalleryType>Gallery</GalleryType>\n" + 
				"            <GalleryURL>https://i.ebayimg.com/00/s/MTIwMFgxNjAw/z/f90AAOSwPGBd1SfG/$_57.JPG?set_id=8800005007</GalleryURL>\n" + 
				"            <PhotoDisplay>PicturePack</PhotoDisplay>\n" + 
				"            <PictureURL>https://i.ebayimg.com/00/s/MTIwMFgxNjAw/z/f90AAOSwPGBd1SfG/$_57.JPG?set_id=8800005007</PictureURL>\n" + 
				"            <PictureURL>https://i.ebayimg.com/00/s/MTIwMFgxNjAw/z/4J8AAOSwdnNd1SfP/$_57.JPG?set_id=8800005007</PictureURL>\n" + 
				"            <PictureURL>https://i.ebayimg.com/00/s/MTIwMFgxNjAw/z/EdUAAOSw-s5d1SfX/$_57.JPG?set_id=8800005007</PictureURL>\n" + 
				"            <PictureURL>https://i.ebayimg.com/00/s/MTIwMFgxNjAw/z/u8UAAOSwFuJd1Sfl/$_57.JPG?set_id=8800005007</PictureURL>\n" + 
				"            <PictureURL>https://i.ebayimg.com/00/s/MTIwMFgxNjAw/z/M1YAAOSwmnFd1Sfv/$_57.JPG?set_id=8800005007</PictureURL>\n" + 
				"            <PictureURL>https://i.ebayimg.com/00/s/MTIwMFgxNjAw/z/wlsAAOSwwUJd1Sgj/$_57.JPG?set_id=8800005007</PictureURL>\n" + 
				"            <PictureSource>EPS</PictureSource>\n" + 
				"        </PictureDetails>\n" + 
				"        <DispatchTimeMax>2</DispatchTimeMax>\n" + 
				"        <ProxyItem>false</ProxyItem>\n" + 
				"        <BusinessSellerDetails>\n" + 
				"            <Address>\n" + 
				"                <Street1>12 Cotney Croft</Street1>\n" + 
				"                <CityName>Stevenage</CityName>\n" + 
				"                <StateOrProvince>Hertfordshire</StateOrProvince>\n" + 
				"                <CountryName>United Kingdom</CountryName>\n" + 
				"                <Phone>01438|230481</Phone>\n" + 
				"                <PostalCode>SG29PT</PostalCode>\n" + 
				"                <CompanyName>greetinz</CompanyName>\n" + 
				"                <FirstName>Sharon</FirstName>\n" + 
				"                <LastName>Mole</LastName>\n" + 
				"            </Address>\n" + 
				"            <Email>greetinz@gmail.com</Email>\n" + 
				"            <LegalInvoice>false</LegalInvoice>\n" + 
				"        </BusinessSellerDetails>\n" + 
				"        <BuyerGuaranteePrice currencyID=\"GBP\">20000.0</BuyerGuaranteePrice>\n" + 
				"        <ReturnPolicy>\n" + 
				"            <ReturnsWithinOption>Days_14</ReturnsWithinOption>\n" + 
				"            <ReturnsWithin>14 days</ReturnsWithin>\n" + 
				"            <ReturnsAcceptedOption>ReturnsAccepted</ReturnsAcceptedOption>\n" + 
				"            <ReturnsAccepted>Returns Accepted</ReturnsAccepted>\n" + 
				"            <ShippingCostPaidByOption>Buyer</ShippingCostPaidByOption>\n" + 
				"            <ShippingCostPaidBy>Buyer</ShippingCostPaidBy>\n" + 
				"        </ReturnPolicy>\n" + 
				"        <ConditionDescription>Brand new</ConditionDescription>\n" + 
				"        <PostCheckoutExperienceEnabled>false</PostCheckoutExperienceEnabled>\n" + 
				"        <ShippingPackageDetails>\n" + 
				"            <ShippingIrregular>false</ShippingIrregular>\n" + 
				"            <ShippingPackage>PackageThickEnvelope</ShippingPackage>\n" + 
				"            <WeightMajor measurementSystem=\"Metric\" unit=\"kg\">0</WeightMajor>\n" + 
				"            <WeightMinor measurementSystem=\"Metric\" unit=\"gm\">0</WeightMinor>\n" + 
				"        </ShippingPackageDetails>\n" + 
				"        <HideFromSearch>false</HideFromSearch>\n" + 
				"        <eBayPlus>false</eBayPlus>\n" + 
				"        <eBayPlusEligible>false</eBayPlusEligible>\n" + 
				"        <IsSecureDescription>true</IsSecureDescription>\n" + 
				"    </Item>\n" + 
				"</GetItemResponse>"; 
		assertEquals(actualResponse.toLowerCase().toString(), response.toLowerCase().toString());
	}
	
	@Test
	public void findNonProfit() throws IOException, IOException {
		Http httpClass = new Http();
		String requestBody = 
    			"<findNonprofitRequest xmlns=\"http://www.ebay.com/marketplace/fundraising/v1/services\">\r\n" + 
    			"    <searchFilter>\r\n" + 
    			"        <externalId>"+ "10484"+"</externalId>\r\n" + 
    			"    </searchFilter>\r\n" + 
    			"    <outputSelector>Mission</outputSelector>\r\n" + 
    			"    <outputSelector>Address</outputSelector>\r\n" + 
    			"    <outputSelector>LargeLogoURL</outputSelector>\r\n" + 
    			"    <outputSelector>PopularityIndex</outputSelector>\r\n" + 
    			"    <outputSelector>EIN</outputSelector>\r\n" + 
    			"    <outputSelector>Description</outputSelector>\r\n" + 
    			"    <paginationInput>\r\n" + 
    			"        <pageNumber>1</pageNumber>\r\n" + 
    			"        <pageSize>25</pageSize>\r\n" + 
    			"    </paginationInput>\r\n" + 
    			"</findNonprofitRequest>";
		String response = httpClass.genericSendPOST("http://svcs.ebay.com/services/fundraising/FundRaisingFindingService/v1", requestBody, "nonProfit");
		System.out.print("Response" + response);
		String actualResponse = "<?xml version='1.0' encoding='UTF-8'?>\n" + 
				"<findNonprofitResponse xmlns=\"http://www.ebay.com/marketplace/fundraising/v1/services\">\n" + 
				"    <ack>Success</ack>\n" + 
				"    <version>1.0.0</version>\n" + 
				"    <timestamp>2019-12-04T15:45:58.534Z</timestamp>\n" + 
				"    <nonprofit>\n" + 
				"        <name>RSPCA</name>\n" + 
				"        <mission>The RSPCA is the leading UK animal welfare charity. We promote kindness and aim to prevent cruelty. \n" + 
				"\n" + 
				"We rescue, rehabilitate and rehome animals in need. We stand up to those who deliberately harm animals - we will not tolerate animal abuse..</mission>\n" + 
				"        <logoURL>https://i.ebayimg.com/00/s/ODQ2WDEzOTY=/z/IAwAAOSwhktdXlni/$_1.JPG?set_id=8800005007</logoURL>\n" + 
				"        <nonprofitId>16187</nonprofitId>\n" + 
				"        <description>Donate £2 to help rescue, rehabilitate and rehome animals in need</description>\n" + 
				"        <externalId>10484</externalId>\n" + 
				"        <homePageURL>http://www.ebay.co.uk/charity/charity.jsp?NP_ID=10484</homePageURL>\n" + 
				"        <address>\n" + 
				"            <latitude>51.037</latitude>\n" + 
				"            <longitude>-0.3441</longitude>\n" + 
				"            <zipCode>RH13 9RS</zipCode>\n" + 
				"            <addressLine1>Wilberforce Way</addressLine1>\n" + 
				"            <city>Horsham</city>\n" + 
				"            <state>West Sussex</state>\n" + 
				"        </address>\n" + 
				"        <ein>219099</ein>\n" + 
				"        <largeLogoURL>https://i.ebayimg.com/00/s/ODQ2WDEzOTY=/z/IAwAAOSwhktdXlni/$_1.JPG?set_id=8800005007</largeLogoURL>\n" + 
				"    </nonprofit>\n" + 
				"    <paginationOutput>\n" + 
				"        <pageNumber>1</pageNumber>\n" + 
				"        <pageSize>25</pageSize>\n" + 
				"        <totalEntries>1</totalEntries>\n" + 
				"        <totalPages>1</totalPages>\n" + 
				"    </paginationOutput>\n" + 
				"</findNonprofitResponse>";
		assertEquals(actualResponse, response);
	}
}


