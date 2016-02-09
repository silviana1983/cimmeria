package tests;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pageObjects.GeneratedPage;
import pageObjects.HomePage;

public class LorumIpsumDefaulTest {
	
	static WebDriver driver = new FirefoxDriver();

	@Test 
	public void homePageTitleTest() {

		//Creating instance of homePage 
		HomePage homePage = new HomePage(driver);
		homePage.OpenPage();
		
		//Fetch title 
		String title = homePage.getTitle();
		
		//Assert title content 
		Assert.assertTrue(title.equals("Lorem Ipsum - All the facts - Lipsum generator"));
	}
	
	@Test 
	public void homePageHeaderTest() {

		// Creating instance of homePage 
		HomePage homePage = new HomePage(driver);
		homePage.OpenPage();

		// Validate page header 
		String header = homePage.getHeader();
		Assert.assertTrue(header.equals(""));

		// Fetch latin quote: 
		String latinQuote = homePage.getQuote("latin");
		Assert.assertEquals("\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"", latinQuote);

		// Fetch english quote, which is: 
		String englishQuote = homePage.getQuote("english"); 
		Assert.assertEquals("\"There is no one who loves pain itself, who seeks after it and wants to have it, simply because it is pain...\"",englishQuote); 
	}
	
	@Test 
	public void homePageParagraphTest() {

		// Creating instance of homePage 
		HomePage homePage = new HomePage(driver);
		homePage.OpenPage();

		// Check paragraph count 
		int paragraphCount = homePage.getParagraphCount(); 
		Assert.assertTrue(paragraphCount == 5);

		// Validate "What is Lorum Ipsum?" paragraph 
		List<String> what = homePage.getParagraph("what");
		Assert.assertEquals("What is Lorem Ipsum?", what.get(0)); 
		Assert.assertTrue(what.get(1).startsWith("Lorem Ipsum is simply dummy text of the printing and typesetting industry"));

		// Validate Why do we use it? paragraph 
		List<String> why = homePage.getParagraph("why"); 
		Assert.assertEquals("Why do we use it?", why.get(0));
		Assert.assertTrue(why.get(1).startsWith("It is a long established fact "));

		// Validate Where does it come from? paragraph
		List<String> where = homePage.getParagraph("where"); 
		Assert.assertEquals("Where does it come from?",where.get(0));
		Assert.assertTrue(where.get(1).startsWith("Contrary to popular belief, Lorem Ipsum is not simply random text."));

		// Validate Where can I get some? paragraph 
		List<String> getSome = homePage.getParagraph("getSome"); 
		Assert.assertEquals("Where can I get some?", getSome.get(0));
		Assert.assertTrue(getSome.get(1).startsWith("There are many variations of passages of Lorem Ipsum available,"));
	}
	
	@Test 
	public void generateParagraphsTest() {

		// Creating instance of homePage 
		HomePage homePage = new HomePage(driver);
		homePage.OpenPage();

		// select 'paragraphs' radio button 
		homePage.selectContentType("paragraphs");

		// enter '10' in the count field 
		homePage.inputCount("10");

		// click "Generate Lorum Ipsum" button
		homePage.generateLorumIpsum();
		GeneratedPage generatedPage = new GeneratedPage(driver);

		// validate the number of text paragraphs generated 
		int paragraphCount = generatedPage.getActualGeneratedCount("paragraphs");
		Assert.assertTrue(paragraphCount == 10);

		// validate the report text // ex: "Generated 10 paragraphs, 1106 words, 7426 bytes of Lorem Ipsum"
		Assert.assertEquals("Generated " + generatedPage.getReport("paragraphs") + 
				" paragraphs, " + generatedPage.getReport("words") + 
				" words, " + generatedPage.getReport("bytes") + 
				" bytes of Lorem Ipsum", generatedPage.getCompleteReport());
	}
	
	@Test 
	public void generateParagraphsTest_NegativeTest() {

		// Creating instance of homePage 
		HomePage homePage = new HomePage(driver);
		homePage.OpenPage();

		// select 'paragraphs' radio button 
		homePage.selectContentType("paragraphs");

		// enter '0' in the count field 
		homePage.inputCount("0");

		// click "Generate Lorum Ipsum" button
		homePage.generateLorumIpsum();
		GeneratedPage generatedPage = new GeneratedPage(driver);

		// validate the number of text paragraphs generated 
		int paragraphCount = generatedPage.getActualGeneratedCount("paragraphs");
		Assert.assertTrue("Incorrect reported paragraph count with 0 input",paragraphCount == 0);
	}
	
	@Test 
	public void generateWordsTest() {

		// Creating instance of homePage 
		HomePage homePage = new HomePage(driver);
		homePage.OpenPage();

		// select 'words' radio button 
		homePage.selectContentType("words");

		// enter '100' in the count field 
		homePage.inputCount("1000");

		// click "Generate Lorum Ipsum" button
		homePage.generateLorumIpsum();
		GeneratedPage generatedPage = new GeneratedPage(driver);

		// validate the number of words generated 
		int wordCount = generatedPage.getActualGeneratedCount("words");
		Assert.assertTrue(wordCount == 1000);

		// validate the report text // ex: "Generated 10 paragraphs, 1106 words, 7426 bytes of Lorem Ipsum" 
		Assert.assertEquals("Generated " + generatedPage.getReport("paragraphs") + 
				" paragraphs, " + generatedPage.getReport("words") + 
				" words, " + generatedPage.getReport("bytes") + 
				" bytes of Lorem Ipsum", generatedPage.getCompleteReport());

	}
	
	@Test 
	public void generateWordsTest_NegativeTest() {

		// Creating instance of homePage 
		HomePage homePage = new HomePage(driver);
		homePage.OpenPage();

		// select 'words' radio button 
		homePage.selectContentType("words");

		// enter 'abcd' in the count field 
		homePage.inputCount("abcd");

		// click "Generate Lorum Ipsum" button
		homePage.generateLorumIpsum();
		GeneratedPage generatedPage = new GeneratedPage(driver);

		// validate the number of words generated 
		int wordCount = generatedPage.getActualGeneratedCount("words");
		Assert.assertTrue("Incorrect reported words count with abcd input", wordCount == 0);
	}

	
	@Test 
	public void generateBytesTest() {

		// Creating instance of homePage 
		HomePage homePage = new HomePage(driver);
		homePage.OpenPage();

		// select 'bytes' radio button 
		homePage.selectContentType("bytes");

		// enter '3000' in the count field 
		homePage.inputCount("3000");

		// click "Generate Lorum Ipsum" button
		homePage.generateLorumIpsum();
		GeneratedPage generatedPage = new GeneratedPage(driver);

		// validate the number of bytes generated 
		int byteCount = generatedPage.getActualGeneratedCount("bytes");
		Assert.assertTrue("Incorrect reported byte count" ,byteCount == 3000);

		// validate the report text // ex: "Generated 10 paragraphs, 1106
		Assert.assertEquals("Generated " + generatedPage.getReport("paragraphs") + 
				" paragraphs, " + generatedPage.getReport("words") + 
				" words, " + generatedPage.getReport("bytes") + 
				" bytes of Lorem Ipsum", generatedPage.getCompleteReport()); 
	}
	
	@Test 
	public void generateBytesTest_NegativeTest() {

		// Creating instance of homePage 
		HomePage homePage = new HomePage(driver);
		homePage.OpenPage();

		// select 'bytes' radio button 
		homePage.selectContentType("bytes");

		// enter '-300' in the count field 
		homePage.inputCount("-300");

		// click "Generate Lorum Ipsum" button
		homePage.generateLorumIpsum();
		GeneratedPage generatedPage = new GeneratedPage(driver);

		// validate the number of bytes generated 
		int byteCount = generatedPage.getActualGeneratedCount("bytes");
		Assert.assertTrue("Incorrect reported byte count with -300 input" ,byteCount == 0);
	}
	
	@Test 
	public void generateListsTest() {

		// Creating instance of homePage 
		HomePage homePage = new HomePage(driver);
		homePage.OpenPage();

		// select 'lists' radio button 
		homePage.selectContentType("lists");

		// enter '9' in the count field 
		homePage.inputCount("9");

		// click "Generate Lorum Ipsum" button
		homePage.generateLorumIpsum();
		GeneratedPage generatedPage = new GeneratedPage(driver);

		// validate the number of lists generated 
		int listCount = generatedPage.getActualGeneratedCount("lists");
		Assert.assertTrue("Incorrect list count",listCount == 9);

		// validate the report text // ex: "Generated 10 paragraphs, 1106 words, 7426 bytes of Lorem Ipsum" 
		Assert.assertEquals("Generated " + generatedPage.getReport("paragraphs") + 
				" paragraphs, " + generatedPage.getReport("words") + 
				" words, " + generatedPage.getReport("bytes") + 
				" bytes of Lorem Ipsum", generatedPage.getCompleteReport()); 

	}
	
	@Test 
	public void generateListsTest_NegativeTest() {

		// Creating instance of homePage 
		HomePage homePage = new HomePage(driver);
		homePage.OpenPage();

		// select 'lists' radio button 
		homePage.selectContentType("lists");

		// enter 'abcd' in the count field 
		homePage.inputCount("abcd");

		// click "Generate Lorum Ipsum" button
		homePage.generateLorumIpsum();
		GeneratedPage generatedPage = new GeneratedPage(driver);

		// validate the number of lists generated 
		int listCount = generatedPage.getActualGeneratedCount("lists");
		Assert.assertTrue("Incorrect list count with abcd input",listCount == 0);
	}
	
	@AfterClass
	public static void closePage(){
		driver.quit();
	}

}
