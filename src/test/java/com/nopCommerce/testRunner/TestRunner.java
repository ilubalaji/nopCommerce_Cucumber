package com.nopCommerce.testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions
	(
		features=".//Features",
		glue="com.nopCommerce.stepDefinitions",
		dryRun=false,
		monochrome=true,
		tags="@Sanity",
	//	plugin= {"pretty","html:Reports"},
		plugin= {"pretty","html:test-output"}
	)

public class TestRunner {

}
