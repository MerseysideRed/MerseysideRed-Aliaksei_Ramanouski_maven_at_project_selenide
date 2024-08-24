package pages;

import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Selenide.$x;

public class PropertyPage {

    private static final Logger LOGGER = LogManager.getLogger(PropertyPage.class);

    private static final String WEB_ITEM_WITH_SCORE_RATE = ("//div[contains(text(), 'Scored') and ancestor::div[@class='hp-sidebar--right']]");

    public float getPropertyRate() {
        LOGGER.trace("Get score rate value will be performed, locator: //div[contains(text(), 'Scored') and ancestor::div[@class='hp-sidebar--right']]");

        String rawActualResultString = $x(WEB_ITEM_WITH_SCORE_RATE).shouldBe(Condition.clickable).getText();
        String actualResultString = rawActualResultString.replace("Scored ", "");
        return Float.parseFloat(actualResultString);
    }
}
