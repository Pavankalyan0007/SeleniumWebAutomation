package cocumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cocumber",glue="Pavan.StepDefinition",
monochrome=true,plugin= {"html :target/cucumber.html"})
public class TestNgTestRunner extends AbstractTestNGCucumberTests {

	
	
}
