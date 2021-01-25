package resources.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:resources/features", glue = { "resources.StepDefinitions" }, plugin="json:target/jsonReports/cucumber-report.json")
public class TestRunner {
	
}