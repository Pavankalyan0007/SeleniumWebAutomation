package Pavan.StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import PavanProject.pageobjects.CartPage;
import PavanProject.pageobjects.CheckoutPage;
import PavanProject.pageobjects.ConfirmationPage;
import PavanProject.pageobjects.LandingPage;
import PavanProject.pageobjects.ProductCatalouge;
import Pavanproj.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseTest{
    

    public LandingPage landingPage;
    public ProductCatalouge productCatalouge;
    public ConfirmationPage confirmationpage;
    @Given("I landed on Ecommerace page")
    public void I_landed_on_Ecommerace_page() throws IOException {
        landingPage = lauchApplication();  // assuming you meant 'launchApplication' instead of 'lauchApplication'
    }
    @Given("^Logged in with username (.+) and password (.+)$")
    public void Logged_in_with_username_and_password(String username, String password) {
    	ProductCatalouge productCatalouge = landingPage.loginApplication(username, password);
    }
    @When("^I add product  (.+) to cart$")
    public void I_add_product_to_cart(String Product) throws Exception {
    	List<WebElement> Products = productCatalouge.getProducts();
        productCatalouge.AddProductToCart(Product);
    }
    
    @When("^Checkout (.+) and submit the order$")
    public void checkout_submit_the_order(String Productname) {
    	
    	CartPage cartpage = productCatalouge.GetCartPage();

		Boolean match = cartpage.varifyProdDisplayed(Productname);
		Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartpage.CheckOut();
		checkoutPage.SelectCountry("India");
		 confirmationpage = checkoutPage.SubmitOrder();
    }
   
    @Then("{String} message is displayed in confirmation page")
    public void message_is_displayed_confirmation_page(String string) {
    	String Confirmedmessage = confirmationpage.Confirmed();
    	Assert.assertTrue(Confirmedmessage.equalsIgnoreCase(string));
    }
    
}
