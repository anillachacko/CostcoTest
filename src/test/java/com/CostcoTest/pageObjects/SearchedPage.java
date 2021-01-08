package com.CostcoTest.pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchedPage {
	//1. Define WebDriver Globally
		public WebDriver driver;
		
		
		//2.  Create a constructor
		public SearchedPage(WebDriver driver) {
			
			this.driver = driver;   // to assign the driver passed from the test cases to this local webdriver instance variable
			PageFactory.initElements(driver, this);
		}
		
		//3  Locators for all the elements in the Page
		
		@FindBy(xpath="//input[@id='add-to-cart-btn']")
		@CacheLookup
		WebElement btnAddToCart;
		



		public WebElement getBtnAddToCart() {
			return btnAddToCart;
		}


		
		
}
