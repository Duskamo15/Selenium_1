package org.openqa.selenium.example;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GoogleSuggest {
    public static void main(String[] args) throws Exception {
        // The Firefox driver supports javascript 
        WebDriver driver = new FirefoxDriver();
        
        // Go to the Google Suggest home page
        driver.get("http://www.google.com/webhp?complete=1&hl=en");
        
        // Enter the query string "Cheese"
        WebElement query = driver.findElement(By.name("q"));
        query.sendKeys("Cheese");

        // Sleep until the div we want is visible or 5 seconds is over
        List<WebElement> resultsDiv = null;
        long end = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < end) {          
            try {
	        	if (resultsDiv.get(0) != null) 
	                break;
        	} catch (Exception e) {
        		resultsDiv = driver.findElements(By.className("sbqs_c"));       		
        	}
        }
        
        for (WebElement suggestion : resultsDiv) {
            System.out.println(suggestion.getText());
        }

        driver.quit();
    }
}