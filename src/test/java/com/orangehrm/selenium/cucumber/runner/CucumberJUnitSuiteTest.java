package com.orangehrm.selenium.cucumber.runner;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
//@IncludeTags("EmptyLoginCredentials") 
@Execution(ExecutionMode.CONCURRENT)
public class CucumberJUnitSuiteTest {

}
