package PavanProject.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PavanProject.Abstractcomponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//Pagefactor
	//WebElement userEmail = driver.findElement(By.id("userEmail")).sendKeys("pavansoppa@gmail.com");
	
	@FindBy(id="userEmail")
	WebElement userEmail ;
	
	@FindBy(id="userPassword")
	WebElement PasswordELE ;
	
	@FindBy(xpath="//input[@class=\"btn btn-block login-btn\"]")
	WebElement Submit ;
	
	@FindBy(css="[class*='flyOut']")
	WebElement ErrorMessage ;
	
public ProductCatalouge loginApplication(String username, String password) {
		
	userEmail.sendKeys(username);
	PasswordELE.sendKeys(password);
	Submit.click();
	ProductCatalouge productCatalouge = new ProductCatalouge(driver);
	return productCatalouge;
	}
	
	public String getErrormsg() {
		waitForWebElementAppear(ErrorMessage);
		return ErrorMessage.getText();
		
		
	}

public void goTo() {
	
	driver.get("https://rahulshettyacademy.com/client");
}
	
	
	
}
