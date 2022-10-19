package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryPositiveTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldReturnSuccessIfFieldsAreFilledInCorrectly() {

        LocalDate deliveryDateCard = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateText = deliveryDateCard.format(formatter);

        $("[data-test-id=city] [placeholder='Город']").setValue("Казань");
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(dateText);
        $("[data-test-id=name] [type=text]").setValue("Евгений Перчаткин");
        $("[data-test-id=phone] [type=tel]").setValue("+79877775432");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + dateText));
    }

    @Test
    public void shouldReturnSuccessfullyIfSurnameWithHyphen() {
        LocalDate deliveryDateCard = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateText = deliveryDateCard.format(formatter);

        $("[data-test-id=city] [placeholder='Город']").setValue("Казань");
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(dateText);
        $("[data-test-id=name] [type=text]").setValue("Евгений Перчаткин-Гонсалез");
        $("[data-test-id=phone] [type=tel]").setValue("+79877775432");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + dateText));
    }

    @Test
    public void shouldReturnSuccessfullyIfCityWithHyphen() {
        LocalDate deliveryDateCard = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateText = deliveryDateCard.format(formatter);

        $("[data-test-id=city] [placeholder='Город']").setValue("Санкт-Петербург");
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(dateText);
        $("[data-test-id=name] [type=text]").setValue("Евгений Перчаткин-Гонсалез");
        $("[data-test-id=phone] [type=tel]").setValue("+79877775432");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + dateText));
    }
}