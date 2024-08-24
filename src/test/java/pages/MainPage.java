package pages;

import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage extends BasePage{

    private static final Logger LOGGER = LogManager.getLogger(MainPage.class);

    private static final String BUTTON_CLOSE_SIGN_IN_POPUP = ("//button[@aria-label='Dismiss sign-in info.']");
    private static final String SEARCH_FIELD = ("//div[@data-testid='searchbox-layout-wide']//input");
    private static final String FIRST_SEARCH_OPTION = ("//li[@id='autocomplete-result-0']//div[text()='%s']");
    private static final String QUANTITY_HUMANS_AND_ROOMS_BUTTON = ("//button[@data-testid='occupancy-config']");
    private static final String PLUS_ADULTS_BUTTON = ("//button[ancestor::div/input[@id='group_adults']][2]");
    private static final String ADULTS_VALUE_FIELD = ("//input[@id='group_adults']");
    private static final String PLUS_ROOMS_BUTTON = ("//button[ancestor::div/input[@id='no_rooms']][2]");
    private static final String ROOMS_VALUE_FIELD = ("//input[@id='no_rooms']");
    private static final String SEARCH_BUTTON = ("//button/span[text()='Search']");
    private static final String TOOLTIP = ("//button[@data-testid= 'header-%s-picker-trigger']");


    public void closeSignInWindow() {
            WebElement buttonCLoseSignInPopUp = $x(BUTTON_CLOSE_SIGN_IN_POPUP).shouldBe(Condition.visible);
            buttonCLoseSignInPopUp.click();

        LOGGER.trace("Close Sign-in window is performed, locator: //button[@aria-label='Dismiss sign-in info.']");
    }

    public void setSearchCityField(String city) {
        WebElement searchField = $x(SEARCH_FIELD);
        searchField.clear();
        searchField.sendKeys(city);
        $x(String.format(FIRST_SEARCH_OPTION, city)).click();

        LOGGER.trace("Set city value to city field is performed, locators: //div[@data-testid='searchbox-layout-wide']//input; //li[@id='autocomplete-result-0']//div[text()='%s']");
    }

    public void setTimeRangeField(int plusDaysFromFirstDate, int plusDaysFromSecondDate) {

        LocalDateTime ldt1 = LocalDateTime.now().plusDays(plusDaysFromFirstDate);
        LocalDateTime ldt2 = LocalDateTime.now().plusDays(plusDaysFromSecondDate);

        String firstDay = ldt1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String secondDay = ldt2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        $x(String.format("//span[@data-date='%s']", firstDay)).click();
        $x(String.format("//span[@data-date='%s']", secondDay)).click();

        LOGGER.trace("Set time range value is performed, locator: //span[@data-date='%s']");
    }

    public void setQuantityParametersOfPeopleAndRooms(String quantityOfAdults, String quantityOfRooms) {

        $x(QUANTITY_HUMANS_AND_ROOMS_BUTTON).click();

        WebElement plusAdultsButton = $x(PLUS_ADULTS_BUTTON);
        while (!$x(ADULTS_VALUE_FIELD).getAttribute("value").equals(quantityOfAdults)) {
            plusAdultsButton.click();
        }

        WebElement plusRoomsButton = $x(PLUS_ROOMS_BUTTON);
        while (!$x(ROOMS_VALUE_FIELD).getAttribute("value").equals(quantityOfRooms)) {
            plusRoomsButton.click();
        }

        LOGGER.trace("Set quantity parameters of adults and rooms are performed, locators: //button[ancestor::div/input[@id='group_adults']][2], //button[ancestor::div/input[@id='no_rooms']][2]");
    }

    public void clickSearchButton() {
        $x(SEARCH_BUTTON).click();

        LOGGER.trace("Click to search button is performed, locator: //button/span[text()='Search']");
    }

    public void navigateToIndicator(String indicatorType) {

        $x(String.format(TOOLTIP, indicatorType)).hover();

        LOGGER.trace("Navigate to tooltip is performed, locator: //button[@data-testid= 'header-%s-picker-trigger']");
    }

    public String getTooltipValue(String tooltipType) {
        LOGGER.trace("Get tooltip value will be performed, locator: //div[contains(text(), '%s')]");

        return $x(String.format("//div[contains(text(), '%s')]", tooltipType)).getText();
    }
}
