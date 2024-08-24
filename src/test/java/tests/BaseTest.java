package tests;

import com.codeborne.selenide.WebDriverRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void initDriver() {

        LOGGER.info("Test is started");
    }

    @AfterMethod
    public void closeDriver() {

        WebDriverRunner.closeWebDriver();
    }
}
