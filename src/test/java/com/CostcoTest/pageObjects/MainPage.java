package com.CostcoTest.pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
	//1. Define WebDriver Globally
		public WebDriver driver;
		
		
		//2.  Create a constructor
		public MainPage(WebDriver driver) {
			
			this.driver = driver;   // to assign the driver passed from the test cases to this local webdriver instance variable
			PageFactory.initElements(driver, this);
		}
		
		//3  Locators for all the elements in the Page
		
		@FindBy(id="costcoModalTitle")
		@CacheLookup
		WebElement costcoModal;
			
		@FindBy(xpath="//input[@name='region' and @value='ON']")
		@CacheLookup
		WebElement radioBtnON;
		
		@FindBy(id="language-region-set")
		@CacheLookup
		WebElement btnSetRegion;
		
		@FindBy(id="search-field")
		@CacheLookup
		WebElement txtSearchField;
		
		@FindBy(xpath="//button[contains(@class,'btn search-ico-button') and @type='button']")
		@CacheLookup
		WebElement btnSearchItem;
		
		@FindBy(id="rsltCntMsg")
		@CacheLookup
		WebElement labelMessage;
		
		@FindBy(xpath="//a[contains(text(),'Casio PX-760')]")
		@CacheLookup
		WebElement linkPX760;
				
			
		@FindBy(xpath="//a[contains(text(),'Casio PX-780')]")
		@CacheLookup
		WebElement linkPX780;
		
		public WebElement getCostcoModalElement() {
			return costcoModal;
		}
		public WebElement getLabelMessage() {
			return labelMessage;
		}
		
		public WebElement getLinkPX760() {
			return linkPX760;
		}
		public WebElement getLinkPX780() {
			return linkPX780;
		}

		public void setTxtSearchField(String searchString) {
			txtSearchField.clear();
			txtSearchField.sendKeys(searchString);
		}
		
		public void clickSearchButton() {
			btnSearchItem.click();
		}

		public void setRegion() {

				radioBtnON.click();
				btnSetRegion.click();			
		}
		

}
