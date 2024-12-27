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
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import PavanProject.pageobjects.CartPage;
import PavanProject.pageobjects.CheckoutPage;
import PavanProject.pageobjects.ConfirmationPage;
import PavanProject.pageobjects.LandingPage;
import PavanProject.pageobjects.ProductCatalouge;
import Pavanproj.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
public class ErrorValidation extends BaseTest {

	@Test (groups = { "ErrorHandling" } , retryAnalyzer=Pavanproj.TestComponents.Retry.class)
	public void LoginValidation() throws Exception{
		String Productname ="ADIDAS ORIGINAL";
		ProductCatalouge productCatalouge = landingPage.loginApplication("pavansoppa@gmail.com", "Pavan@2011");
	     Assert.assertEquals("Incorrect email or password." , landingPage.getErrormsg());
	}
	@Test
	public void ProductErrorValidalition() throws Exception {
		String Productname = "ADIDAS ORIGINAL";
        ProductCatalouge productCatalouge = landingPage.loginApplication("pavansoppa@gmail.com", "Pavan@2000");
        List<WebElement> Products = productCatalouge.getProducts();
        productCatalouge.AddProductToCart(Productname);
		CartPage cartpage = productCatalouge.GetCartPage();
        Boolean match = cartpage.varifyProdDisplayed("ADIDAS ORIGINAL");
		Assert.assertTrue(match);
		
	}

}
