package PavanProject.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PavanProject.Abstractcomponent.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;
	public CartPage(WebDriver driver) {
		
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//Pagefactor
	//WebElement userEmail = driver.findElement(By.id("userEmail")).sendKeys("pavansoppa@gmail.com");
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProd ;
	
	@FindBy(css=".totalRow button")
	WebElement checkout ;
	
public Boolean varifyProdDisplayed(String Productname) {
	Boolean match = cartProd.stream().anyMatch(cartProd->cartProd.getText().equalsIgnoreCase(Productname));
	
	return match;
	}

public CheckoutPage CheckOut() {
	
	checkout.click();
	return new CheckoutPage(driver);
}

	
	
	
}
