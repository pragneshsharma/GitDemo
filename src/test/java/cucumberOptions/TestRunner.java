package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",plugin = {"json:target/jsonReports/cucumber.json"}, glue={"stepDefinations"}) //leave tags empty if want to run all cases.,tags= {""}
public class TestRunner {

}
//"html:target/cucumber-html-reports",