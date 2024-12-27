package Pavanproj.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PavanProject.pageobjects.CartPage;
import PavanProject.pageobjects.CheckoutPage;
import PavanProject.pageobjects.ConfirmationPage;
import PavanProject.pageobjects.LandingPage;
import PavanProject.pageobjects.OrdersPage;
import PavanProject.pageobjects.ProductCatalouge;
import Pavanproj.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTestPageObject extends BaseTest {
	public String Productname = "ADIDAS ORIGINAL";
	@Test(dataProvider="getData",groups = { "Purchase" },retryAnalyzer=Pavanproj.TestComponents.Retry.class)
	public void SubmitOrder(HashMap<String, String>input ) throws Exception {
		
        ProductCatalouge productCatalouge = landingPage.loginApplication(input.get("email"), input.get("password"));
        List<WebElement> Products = productCatalouge.getProducts();
        productCatalouge.AddProductToCart(input.get("Productname"));
		CartPage cartpage = productCatalouge.GetCartPage();

		Boolean match = cartpage.varifyProdDisplayed(input.get("Productname"));
		Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartpage.CheckOut();
		checkoutPage.SelectCountry("India");
		ConfirmationPage confirmationpage = checkoutPage.SubmitOrder();
		String Confirmedmessage = confirmationpage.Confirmed();
		Assert.assertTrue(Confirmedmessage.equalsIgnoreCase("Thankyou for the order."));

	}
	@Test(dependsOnMethods={"SubmitOrder"}) 
	public void OrdersList(){
        ProductCatalouge productCatalouge = landingPage.loginApplication("pavansoppa@gmail.com", "Pavan@2000");
       OrdersPage orderpage=  productCatalouge.GetOrderPage();
      Assert.assertTrue(orderpage.varifyDisplOrderdisplayed(Productname));
		}
	
	@DataProvider
	public Object[][] getData() throws Exception {
//		
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "pavansoppa@gmail.com");
//		map.put("password", "Pavan@2000");
//		map.put("Productname", "ADIDAS ORIGINAL");
//		
//		HashMap<String, String> map2 = new HashMap<String, String>();
//		map2.put("email", "pavansoppa2000@gmail.com");
//		map2.put("password", "Elakya@2001");
//		map2.put("Productname", "ZARA COAT 3");
		List<HashMap<String, String>> Deatils = getDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\PavanKalyan\\data\\PurchaseOrder.json"
);
		
		return new Object[][] {{Deatils.get(0)},{Deatils.get(1)}};
	}


}
