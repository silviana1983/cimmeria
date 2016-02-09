package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class HomePage {

	WebDriver driver;
	JavascriptExecutor jsx;
	
	public HomePage(WebDriver wdriver) {
		driver = wdriver;
		jsx = (JavascriptExecutor) driver;
	}
	
	public void OpenPage(){
		driver.get("http://www.lipsum.com/");
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getHeader() {
		return driver.findElement(By.cssSelector("h1")).getText();
	}

	public String getQuote(String languageText) {
		if (languageText == "latin"){
			return driver.findElement(By.cssSelector("h4")).getText();
		} else {
			return driver.findElement(By.cssSelector("h5")).getText();
		}
	}

	public int getParagraphCount() {
		return Integer.parseInt(driver.findElement(By.id("amount")).getAttribute("value"));
	}

	public List<String> getParagraph(String text) {
		List<String> list = new ArrayList<String>();
		
		if (text == "what"){
			String innerText = (String) ((JavascriptExecutor) driver).executeScript("return document.querySelector('.what span').innerHTML");
			list.add(innerText);
			list.add(driver.findElements(By.cssSelector(".lc p")).get(0).getText());
			return list;
		} else if (text == "where") {
			String innerText = (String) ((JavascriptExecutor) driver).executeScript("return document.querySelector('.where span').innerHTML");
			list.add(innerText);
			list.add(driver.findElements(By.cssSelector(".lc p")).get(1).getText());
			return list;
		} else if (text == "why") {
			String innerText = (String) ((JavascriptExecutor) driver).executeScript("return document.querySelector('.why span').innerHTML");
			list.add(innerText);
			list.add(driver.findElements(By.cssSelector(".rc p")).get(0).getText());
			return list;
		} else {
			String innerText = (String) ((JavascriptExecutor) driver).executeScript("return document.querySelector('.getsome span').innerHTML");
			list.add(innerText);
			list.add(driver.findElements(By.cssSelector(".rc p")).get(1).getText());
			return list;
		}
	}

	public void selectContentType(String type) {
		if (type.equals("paragraphs")){
			driver.findElement(By.id("paras")).click();
		} else if (type.equals("words")){
			driver.findElement(By.id("words")).click();
		} else if (type.equals("bytes")){
			driver.findElement(By.id("bytes")).click();
		} else if (type.equals("lists")){
			driver.findElement(By.id("lists")).click();
		}
	}

	public void inputCount(String input) {
		driver.findElement(By.id("amount")).clear();
		driver.findElement(By.id("amount")).sendKeys(input);
	}

	public void generateLorumIpsum() {
		driver.findElement(By.id("generate")).click();
	}
}