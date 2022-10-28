package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.DataGenerator.*;

public class CardDeliveryOrderDateChangeTest {

    private final String name = getName();
    private final String phone = getPhoneNumber();
    private final String city = getCity();
    private final String date = getNewDate(4);
    private final String otherDate = getNewDate(7);
    private final String invalidDate = getIncorrectDate();


    @AfterEach
    void close() {
        closeWebDriver();
    }

    @Test
    void registration() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(city);
        $x("//input[@placeholder='Дата встречи']").click();
        $x("//input[@name='name']").setValue(name);
        $("[name='phone']").setValue(phone);
        $("[data-test-id='agreement']").click();
        $("[class='button__text']").click();
        $("[data-test-id='success-notification']").should(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldGivenNewDateForRegistration() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(city);
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $x("//input[@placeholder='Дата встречи']").setValue(date);
        $x("//input[@name='name']").setValue(name);
        $("[name='phone']").setValue(phone);
        $("[data-test-id='agreement']").click();
        $("[class='button__text']").click();
        $("[data-test-id='success-notification']").should(visible, Duration.ofSeconds(15));
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $x("//input[@placeholder='Дата встречи']").setValue(otherDate);
        $("[class='button__text']").click();
        $x("//span[contains(text(),'Перепланировать')]").click();
        $("[data-test-id='success-notification']").should(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldNoGivenNewDateForRegistration() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(city);
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $x("//input[@placeholder='Дата встречи']").setValue(date);
        $x("//input[@name='name']").setValue(name);
        $("[name='phone']").setValue(phone);
        $("[data-test-id='agreement']").click();
        $("[class='button__text']").click();
        $("[data-test-id='success-notification']").should(visible, Duration.ofSeconds(15));
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $x("//input[@placeholder='Дата встречи']").setValue(invalidDate);
        $("[class='button__text']").click();
        $(byText("Заказ на выбранную дату невозможен")).should(visible, Duration.ofSeconds(15));
    }
}
