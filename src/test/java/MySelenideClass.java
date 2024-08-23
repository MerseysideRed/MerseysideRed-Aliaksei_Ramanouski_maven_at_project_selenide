import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MySelenideClass {
    public static void main(String[] args) {

        Selenide.open("https://booking.com");

        $x("//div[@role='dialog']//button").click();

        WebElement city = $(By.name("ss"));
        city.clear();
        city.sendKeys("Paris");
        $x("//div[text()='Paris']").click();


        //dates
        LocalDate dateFrom = LocalDate.now(ZoneId.systemDefault());
        LocalDate dateTo = dateFrom.plusDays(7);
        $x(String.format("//*[@data-date='%s']", dateFrom)).click();
        $x(String.format("//*[@data-date='%s']", dateTo)).click();

        // guests
        $(By.id("xp__guests__toggle")).click();
        WebElement adult = $x("//span[@id='group_adults_desc']/preceding-sibling::button[1]");
        //input[@id='group_adults']/following-sibling::div/button[2]
        adult.click();
        adult.click();
        $().
        /*WebElement rooms = driver.findElement(By.xpath("//span[@id='no_rooms_desc']/preceding-sibling::button[1]"));
        rooms.click();
        driver.findElement(By.cssSelector(".sb-searchbox__button ")).click();

        //search page
        //driver.findElement(By.xpath("//a[@data-type='price']")).click();
        driver.findElement(By.cssSelector("button[data-testid='sorters-dropdown-trigger']")).click();
        driver.findElement(By.cssSelector("button[data-id='price']")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(5))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@data-testid='overlay-spinner']")));

        WebElement filterPrice = driver.findElement(By.xpath("//div[@data-filters-group='pri']//div[contains(text(), '+')]"));
        int expectedMaxPrice = Integer.parseInt(filterPrice.getText().replaceAll("\\D+", ""));
        filterPrice.click();

        new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofMillis(5))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@data-testid='overlay-spinner']")));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        String actualMaxPriceText = driver
                .findElements(By.xpath("//div[@id='search_results_table']//div[@data-testid='property-card'][1]//div[contains(@data-testid, 'price')]/span"))
                .stream().reduce((x,y) -> y).get().getText();



        int actualMaxPrice = Integer.parseInt(actualMaxPriceText.replaceAll("\\D+", ""));

        System.out.println("Expected price: " + expectedMaxPrice);
        System.out.println("Actual price: " + actualMaxPrice / 7);

        //Assert.assertTrue("Expected hotel prise is lower than expected!", actualMaxPrice / 7 >= expectedMaxPrice);

        driver.quit();
        */
    }
}
