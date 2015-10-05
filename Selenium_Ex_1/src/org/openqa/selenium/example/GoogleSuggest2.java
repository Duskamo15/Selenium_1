package org.openqa.selenium.example;

import java.util.List;
import java.util.Scanner;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSuggest2 {
	// The Firefox driver supports javascript 
	private static WebDriver driver = new FirefoxDriver();
	
    public static void main(String[] args) throws Exception {        
        // Go to the Google Suggest home page
        driver.get("http://www.google.com/webhp?complete=1&hl=en");
        
        // Enter and submit the query string "Cheese" to view the following page
        WebElement query = driver.findElement(By.name("q"));
        query.sendKeys("Cheese");
        query.submit();
        
        // Go to the queried page
        driver.get(driver.getCurrentUrl());
        
        // *****************************************************************
        List<WebElement> resultsDiv = null;
        long end = System.currentTimeMillis() + 5000;
        int page = 0;
        Scanner in = new Scanner(System.in);
        
        do {
        	resultsDiv = null;
            end = System.currentTimeMillis() + 5000;
            while (System.currentTimeMillis() < end) {
            	try {
    	        	if (resultsDiv.get(0) != null) 
    	                break;
            	} catch (Exception e) {
            		resultsDiv = driver.findElements(By.xpath(".//*[@id='rso']/div[.]/div[.]/div/h3/a"));   // dont copy & paste xpath    		
            	}
            }
            
        	// List all suggestions
        	int i;
	        for (i = 0; i < resultsDiv.size(); i++) {
	            System.out.println(i + " " + resultsDiv.get(i).getText());
	        }
	        
			page = in.nextInt();  
			
			driver.findElement(By.linkText(resultsDiv.get(page).getText())).click();
			
			System.out.println("1 Go Back!");  
			while (page != 1) 
				page = in.nextInt();
				driver.navigate().back();
				
        } while (page != -1);
        
        driver.quit();
    }
    
    public void clickAnElementByLinkText(String linkText) {
    	WebDriverWait wait = new WebDriverWait(driver,5);
    	
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkText)));
        driver.findElement(By.linkText(linkText)).click();
    }    
}