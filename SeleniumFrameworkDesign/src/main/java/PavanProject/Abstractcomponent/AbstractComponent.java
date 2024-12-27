package PavanProject.Abstractcomponent;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PavanProject.pageobjects.CartPage;
import PavanProject.pageobjects.OrdersPage;

public class AbstractComponent {
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[routerlink*='cart']")
	WebElement CartPages ;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement OrderPageHeader ;
	
	public void waitForElementAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForWebElementAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
public CartPage GetCartPage()  {
	CartPages.click();
		CartPage cartpage= new CartPage(driver);
	return cartpage;
	}
public OrdersPage GetOrderPage()  {
	OrderPageHeader.click();
	OrdersPage orderpages= new OrdersPage(driver);
	return orderpages;
	}
	public void waitForElementToDisappear(WebElement ele) throws Exception {
		
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
}
