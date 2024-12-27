package Pavanproj.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import PavanProject.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		String Productname ="ADIDAS ORIGINAL";
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://rahulshettyacademy.com/client");
	LandingPage landingPage = new LandingPage(driver);
	driver.findElement(By.id("userEmail")).sendKeys("pavansoppa@gmail.com");
	driver.findElement(By.id("userPassword")).sendKeys("Pavan@2000");
	driver.findElement(By.id("login")).click();
	
	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
	List<WebElement>Products = driver.findElements(By.cssSelector(".col-lg-4"));
	
	  WebElement Prod =Products.stream().filter(Product->Product.findElement(By.cssSelector("b"))
			.getText().equals(Productname)).findFirst().orElse(null);
	  
	  Prod.findElement(By.cssSelector(".w-10")).click();
//	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating"))); - it taking time to load cart
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	
	driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	
	List<WebElement> cartProds =  driver.findElements(By.cssSelector(".cartSection h3"));
	
	Boolean match = cartProds.stream().anyMatch(cartProd->cartProd.getText().equalsIgnoreCase(Productname));
	
	Assert.assertTrue(match);
	
	driver.findElement(By.cssSelector(".totalRow button")).click();
	
	//driver.findElement(By.cssSelector("[placeholder*='Select Country']")).sendKeys("india");
	Actions a =new Actions(driver);
   a.sendKeys(driver.findElement(By.cssSelector("[placeholder*='Select Country']")), "india").build().perform();
   wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
   
   driver.findElement(By.cssSelector(".ta-item:last-of-type")).click();
 
   driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
   
   String ConfirmedMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
  Assert.assertTrue(ConfirmedMsg.equalsIgnoreCase("Thankyou for the order."));
  driver.close();
   
	}

}
