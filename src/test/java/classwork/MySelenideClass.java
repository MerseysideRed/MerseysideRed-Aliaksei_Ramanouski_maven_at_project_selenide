package classwork;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;


import org.openqa.selenium.WebElement;



import java.time.LocalDate;
import java.time.ZoneId;


import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MySelenideClass {
    public static void main(String[] args) {

        Configuration.browserSize = "1920x1080";

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
        $x("//button[@data-testid='occupancy-config']").click();
        WebElement adult = $x("//input[@id='group_adults']/following-sibling::div/button[2]");
        //input[@id='group_adults']/following-sibling::div/button[2]
        adult.click();
        adult.click();
        WebElement rooms = $x("//button[ancestor::div/input[@id='no_rooms']][2]");
        rooms.click();
        $(".sb-searchbox__button ").click();

        //search page
        //driver.findElement(By.xpath("//a[@data-type='price']")).click();
        $("button[data-testid='sorters-dropdown-trigger']").click();
        $("button[data-id='price']").click();

        $x("//div[@data-testid='overlay-spinner']").shouldBe(visible);

        WebElement filterPrice = $x("//div[@data-filters-group='pri']//div[contains(text(), '+')]");
        int expectedMaxPrice = Integer.parseInt(filterPrice.getText().replaceAll("\\D+", ""));
        filterPrice.click();

        $x("//div[@data-testid='overlay-spinner']").shouldBe(not(visible));

        String actualMaxPriceText = $$x("//div[@id='search_results_table']//div[@data-testid='property-card'][1]//div[contains(@data-testid, 'price')]/span")
                .last().getText();

        int actualMaxPrice = Integer.parseInt(actualMaxPriceText.replaceAll("\\D+", ""));

        System.out.println("Expected price: " + expectedMaxPrice);
        System.out.println("Actual price: " + actualMaxPrice / 7);

        //Assert.assertTrue("Expected hotel prise is lower than expected!", actualMaxPrice / 7 >= expectedMaxPrice);
    }
}
