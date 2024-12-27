package PavanProject.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PavanProject.Abstractcomponent.AbstractComponent;

public class OrdersPage extends AbstractComponent {
	WebDriver driver;
	public OrdersPage(WebDriver driver) {
		
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	//Pagefactor
	//WebElement userEmail = driver.findElement(By.id("userEmail")).sendKeys("pavansoppa@gmail.com");
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> PrudctsOrder ;
public Boolean varifyDisplOrderdisplayed(String Productname) {
	Boolean match = PrudctsOrder.stream().anyMatch(Product->Product.getText().equalsIgnoreCase(Productname));
	
	return match;
	}

}
