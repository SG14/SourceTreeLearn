package scenario_Component;

import generic_Component.BaseClass;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;



import pageObject_Component.PageObjectSearch;


public class ScenarioSearch extends BaseClass {
	
	//Logger is for Log4j report
	public static Logger log = Logger.getLogger(ScenarioSearch.class);
	
	PageObjectSearch searchpageObject1 = new PageObjectSearch(driver);

	
	//Method for Buying the item
		@Test(dataProvider="dp_validSearch",dataProviderClass=dataProvider_Component.DataProviderSearch.class)
		public void testValidSearch(Map search) throws InterruptedException, IOException
		{
			
			//Collecting the data from the Map
			String testCaseId = search.get("TC_ID").toString();
			String Search_Item = search.get("Search_Item").toString();
			String Exp_Result = search.get("Exp_Result").toString();
			//start the appium server
			//Start_server();
			log.info("Executing The TestCase" + testCaseId);
			Init_App();
			
			
			System.out.println("Search for the shoes");
			String shoecount = searchShoe(Search_Item);
			validateShoeCount(shoecount,Exp_Result,testCaseId);
		
		//Stop_server();
		
		
	}
	
	public String searchShoe(String Search_Item)
	{    

		PageObjectSearch searchpageObject = new PageObjectSearch(driver);
		
		
		//click on the search text box in eBay
		searchpageObject.clicksearch();      					
		ExplicitWait(searchpageObject.ebaySearchtxtbox, 25);
		 
		
		//enter the search vale and click on search button
		searchpageObject.enterSearchvalue(Search_Item);         
		ExplicitWait(searchpageObject.result, 25);
		
		//Returning the search result count to validate
		return searchpageObject.getsearchresultcount();	
		
	}
	
	public void validateShoeCount(String actualCount, String expectedCount, String testCaseId) throws IOException
	{
		//Actual result is checked with Expected result from Excel sheet
				if(actualCount.equals(expectedCount))
				{
					log.info("Expected result = "+ expectedCount +" and Actual result =" + actualCount +", Hence Pass");
					snapshot1(testCaseId);
				}
				else
				{
					log.info("Expected result = "+ expectedCount +" and Actual result =" + actualCount +", Hence Fail");
					
					snapshot1(testCaseId);
				}
				
	}
	
	
//	public void testInvalidSearch()
//	{
//		
//	}
	
	
	
	
	
}
