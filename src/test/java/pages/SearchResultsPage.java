package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class SearchResultsPage {

    private static final Logger LOGGER = LogManager.getLogger(SearchResultsPage.class);

    private static final String PROPERTY_TYPE_HOTELS_CHECKBOX = ("//div[@id='popular-filters-go-here']//preceding-sibling::div[@data-testid='filters-sidebar']//div[@data-filters-group='ht_id']//div[text()='%s']");
    private static final String PROPERTY_RATING_CHECKBOX = ("//div[@id='popular-filters-go-here']//preceding-sibling::div[@data-testid='filters-sidebar']//div[@data-filters-group='class']//div[text()='%s']");
    private static final String RATE_LABEL = ("//label[@data-testid='filter:class=%s']");
    private static final String DROPDOWN_SORTING_LIST = ("//button[@data-testid='sorters-dropdown-trigger']");
    private static final String DROPDOWN_SORTING_LIST_PROPERTY_RATING_LOW_TO_HIGH_OPTION = ("//div/span[text()='Property rating (low to high)']");
    private static final String PROPERTY_CARD_RATE_VALUE = ("//div[@data-testid='property-card'][%s]//div[@aria-label]");
    private static final String PROPERTY_CARD = ("//div[@data-testid='property-card'][%s]");
    private static final String SCORE_RATE_CHECKBOX = ("//div[@id='popular-filters-go-here']//preceding-sibling::div[@data-testid='filters-sidebar']//div[@data-filters-group='review_score']//div[text()='%s']");
    private static final String SCORE_RATE_LABEL = ("//label[@data-testid='filter:%s']");
    private static final String PROPERTY_CARD_IMAGE = ("//div[@data-testid='property-card'][%s]//img");

    public void selectPropertyTypeCheckbox(String propertyType) {

        SelenideElement propertyTypeHotelsCheckbox = $x(String.format(PROPERTY_TYPE_HOTELS_CHECKBOX, propertyType)).shouldBe(Condition.visible).hover();
        propertyTypeHotelsCheckbox.shouldBe(Condition.clickable).click();

        LOGGER.trace("Select property type checkbox is performed, locator: //div[@id='popular-filters-go-here']//preceding-sibling::div[@data-testid='filters-sidebar']//div[@data-filters-group='ht_id']//div[text()='%s']");
    }

    public void selectPropertyRatingCheckbox(String propertyRateInStars) {
        $x(String.format(PROPERTY_RATING_CHECKBOX, propertyRateInStars)).click();

        LOGGER.trace("Select property rate checkbox is performed, locator: //div[@id='popular-filters-go-here']//preceding-sibling::div[@data-testid='filters-sidebar']//div[@data-filters-group='class']//div[text()='%s']");
    }

    public void explicitWaitOfDisplayedLabelOfRate(int propertyRate) {
        $x(String.format(RATE_LABEL, propertyRate)).shouldBe(Condition.visible);

        LOGGER.trace("Explicit wait of displayed rate label is performed, locator: //label[@data-testid='filter:class=%s']");
    }

    public void clickToDropdownSortingList() {
        $x(DROPDOWN_SORTING_LIST).click();

        LOGGER.trace("Click to dropdown sorting list button is performed, locator: //button[@data-testid='sorters-dropdown-trigger']");
    }

    public void clickToPropertyRatingLowToHighOptionOfDropdownSortingList() {
        $x(DROPDOWN_SORTING_LIST_PROPERTY_RATING_LOW_TO_HIGH_OPTION).click();

        LOGGER.trace("Click to property rating low tp high option of dropdown sorting list is performed, locator: //div/span[text()='Property rating (low to high)']");
    }

    public String getRateFromPropertyCard(int propertyCardOrderNumber) {
        LOGGER.trace("Get rate from property card will be performed, locator: //div[@data-testid='property-card'][%s]//div[@aria-label]");

        return $x(String.format(PROPERTY_CARD_RATE_VALUE, propertyCardOrderNumber)).getAttribute("aria-label");
    }

    public void scrollToPropertyCard(int propertyCardOrderNumber) {
        $x(String.format(PROPERTY_CARD, propertyCardOrderNumber)).scrollTo();

        LOGGER.trace("Scroll to property card is performed, locator: //div[@data-testid='property-card'][%s]");
    }

    public void changePropertyCardBackgroundColor(int propertyCardOrderNumber) {
        WebElement propertyCard = $x(String.format(PROPERTY_CARD, propertyCardOrderNumber));
        executeJavaScript("arguments[0].style.backgroundColor = 'green'", propertyCard);
        executeJavaScript("arguments[0].style.color = 'red'", propertyCard);
        executeJavaScript("arguments[0].click()", propertyCard);

        LOGGER.trace("Change background of property card is performed, locator: //div[@data-testid='property-card'][%s]");
    }

    public void takeScreenshot(String directoryForScreenshots, String screenshotName) throws IOException {
        File asfile = screenshot(OutputType.FILE);

        FileUtils.copyFile(asfile, new File(directoryForScreenshots, screenshotName));

        LOGGER.trace("Take screenshot is performed");
    }

    public void selectScoreRateCheckbox(String scoreRateText) {

        WebElement scoreRateCheckbox = $x(String.format(SCORE_RATE_CHECKBOX, scoreRateText)).hover();
        scoreRateCheckbox.click();

        LOGGER.trace("Select score rate checkbox is performed, locator: //div[@id='popular-filters-go-here']//preceding-sibling::div[@data-testid='filters-sidebar']//div[@data-filters-group='review_score']//div[text()='%s']");
    }

    public void explicitWaitOfDisplayedLabelOfScoreRate(String propertyScoreRate) {

        $x(String.format(SCORE_RATE_LABEL, propertyScoreRate)).shouldBe(Condition.clickable);

        LOGGER.trace("Explicit wait of displayed label of score rate is performed, locator: //label[@data-testid='filter:%s']");
    }

    public void clickToPropertyCardImage(int propertyCardNumber) {
        $x(String.format(PROPERTY_CARD_IMAGE, propertyCardNumber)).click();

        LOGGER.trace("Click to property card image is performed, locator: //div[@data-testid='property-card'][%s]//img");
    }

    public void switchToTab(int orderTabNumber) {
        List<String> tabsList = new ArrayList<>(WebDriverRunner.getWebDriver().getWindowHandles());

        switchTo().window(tabsList.get(orderTabNumber));

        LOGGER.trace("Switch tab is performed");
    }
}
