package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GeneratedPage {

	WebDriver driver;
	
	public GeneratedPage(WebDriver wdriver) {
		driver = wdriver;
	}

	public int getActualGeneratedCount(String type) {
		if (type.equals("paragraphs")){
			return driver.findElements(By.cssSelector("#lipsum p")).size();
		} else if (type.equals("words")){
			return wordcount(driver.findElement(By.cssSelector("#lipsum")).getText());
		} else if (type.equals("bytes")){
			return driver.findElement(By.cssSelector("#lipsum")).getText().length();
		} else {
			return driver.findElements(By.cssSelector("#lipsum ul")).size();
		}
	}

	public String getReport(String type) {
		if (type.equals("paragraphs")){
			String amount = driver.findElement(By.id("generated")).getText();
			return amount.split("Generated ")[1].split(" paragraphs")[0];
		} else if(type.equals("words")) {
			return driver.findElement(By.id("generated")).getText().split("paragraphs, ")[1].split(" words")[0];
		} else {
			return driver.findElement(By.id("generated")).getText().split("words, ")[1].split(" bytes")[0];
		}
	}
	
	public String getCompleteReport(){
		return driver.findElement(By.id("generated")).getText();
	}
	
	public int wordcount(String word) { 
        String[] words = word.split("\\s+"); // match one or more spaces
        return words.length;
	}

}
