package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.DataGenerator.*;

public class CardDeliveryOrderDateChangeTest {

    private final String name = getName();
    private final String phone = getPhoneNumber();
    private final String city = getCity();

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
    void newDateForRegistration() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(city);
        $x("//input[@placeholder='Дата встречи']").click();
        $x("//input[@name='name']").setValue(name);
        $("[name='phone']").setValue(phone);
        $("[data-test-id='agreement']").click();
        $("[class='button__text']").click();
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $x("//input[@placeholder='Дата встречи']").setValue(getNewDate());
        $("[class='button__text']").click();
        $x("//span[contains(text(),'Перепланировать')]").click();
        $("[data-test-id='success-notification']").should(visible, Duration.ofSeconds(15));
    }
}
