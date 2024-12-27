package PavanProject.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PavanProject.Abstractcomponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By Results=  By.cssSelector(".ta-results");
	@FindBy(css="[placeholder*='Select Country']")
	private WebElement Country ;
	
	@FindBy(css=".btnn.action__submit.ng-star-inserted")
	private WebElement Submit ;
	
	@FindBy(css=".ta-item:last-of-type")
	private WebElement SelectCountry ;
	
public void SelectCountry(String CountyName) {
	
	Actions a =new Actions(driver);
	   a.sendKeys(Country, CountyName).build().perform();
	   waitForElementAppear(Results);
	   SelectCountry.click();
	   
}
public ConfirmationPage SubmitOrder() {
	
	Submit.click();
	return new ConfirmationPage(driver);
	   
}
}
