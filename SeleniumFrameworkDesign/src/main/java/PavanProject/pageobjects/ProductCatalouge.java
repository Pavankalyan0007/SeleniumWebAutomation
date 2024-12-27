package PavanProject.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PavanProject.Abstractcomponent.AbstractComponent;

public class ProductCatalouge  extends AbstractComponent {
	WebDriver driver;
	public ProductCatalouge(WebDriver driver) {
		
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//Pagefactor
	//List<WebElement>Products = driver.findElements(By.cssSelector(".col-lg-4"));
	
	@FindBy(css=".mb-3")
	List<WebElement> Products ;
	
	@FindBy(css=".ng-animating")
	WebElement spinner ;
	
	By ProductsBy=  By.cssSelector(".mb-3");
	By AddProd=By.cssSelector(".w-10");
	By toaster =By.cssSelector("#toast-container");
	public List<WebElement> getProducts() {
		waitForElementAppear(ProductsBy);
		return Products;
	}
	
	public WebElement getProductsbyName(String Productname ){
		
		WebElement Prod =Products.stream().filter(Product->Product.findElement(By.cssSelector("b"))
				.getText().equals(Productname)).findFirst().orElse(null);
		return Prod;
	}
	
public void AddProductToCart(String Productname ) throws Exception{
	WebElement Prod = getProductsbyName(Productname);
	  Prod.findElement(AddProd).click();
	  waitForElementAppear(toaster);
	  waitForElementToDisappear(spinner);
	
	}


}
