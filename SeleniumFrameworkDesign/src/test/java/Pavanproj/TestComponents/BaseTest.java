package Pavanproj.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PavanProject.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver intailizeBrowser() throws IOException {
		
	Properties prop = new Properties();
		FileInputStream FilePath = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\PavanProject\\resourses\\GlobalData.properties");
		prop.load(FilePath);
		//is use to run in the different browsers in from cmd it will return in java termenary
		String browserName = 	System.getProperty("browser")!= null ? System.getProperty("browser"):prop.getProperty("browser");
		//prop.getProperty("browser");
		//mvn test -PRegression -Dbrowser=Edge diving these command in cmd it will open in edge/firefox by defaault it will take chrome
		
		if(browserName.contains("chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();

			if(browserName.contains("headless")) {
			options.addArguments("headless");
			}
				 driver = new ChromeDriver(options); 
			driver.manage().window().setSize(new Dimension(1440,900)); //Full Screen
		}
		else if (browserName.equalsIgnoreCase("edge")){
			
			WebDriverManager.edgedriver().setup();
			 driver = new EdgeDriver();
		}
else if (browserName.equalsIgnoreCase("FireFox")){
			
			WebDriverManager.firefoxdriver().setup();
			 driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
public List<HashMap<String, String>> getDataToMap(String FilePath) throws Exception {
		
		//Json to String
	String JsonData = FileUtils.readFileToString(new File(FilePath),
			StandardCharsets.UTF_8);
//String to HashMap Jackson DataBind
	
	ObjectMapper mapper = new ObjectMapper();
	
//	List<HashMap<String,String>> data = mapper.readValue(JsonData, new TypeReference<List<HashMap><String,String>> );
	 List<HashMap<String, String>> data = mapper.readValue(JsonData, new TypeReference<List<HashMap<String, String>>>() {
		 
	 });
	
	return data;
	
}
public String GetScreenShot(String testCaseName, WebDriver driver) throws Exception {
	
	TakesScreenshot ts = (TakesScreenshot) driver;
	File Source = ts.getScreenshotAs(OutputType.FILE);
	File file = new File(System.getProperty("user+dir")+ "//reports//"+ testCaseName + ".png");
	FileUtils.copyFile(Source, file);
	return System.getProperty("user+dir")+ "//reports//"+ testCaseName + ".png";
}
	@BeforeMethod (alwaysRun = true)
	public LandingPage lauchApplication() throws IOException {
		
		WebDriver driver= intailizeBrowser();
	    landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	
@AfterMethod (alwaysRun = true)
public void Close()  {
		
		driver.close();
	}
	

}
