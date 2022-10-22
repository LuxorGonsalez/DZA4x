package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryNegativeTest {
    private String generateDate(int addDays) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldReturnErrorMessageIfCityEmpty() {

        $("[data-test-id='city'] [placeholder='Город']").setValue("");
        String currentDate = generateDate(3);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").setValue(currentDate);
        $("[data-test-id='name'] [type=text]").setValue("Евгений Перчаткин");
        $("[data-test-id='phone'] [type=tel]").setValue("+79877775432");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldReturnErrorMessageIfCityInvalid() {

        $("[data-test-id='city'] [placeholder='Город']").setValue("Стамбул");
        String currentDate = generateDate(3);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").setValue(currentDate);
        $("[data-test-id='name'] [type=text]").setValue("Евгений Перчаткин");
        $("[data-test-id='phone'] [type=tel]").setValue("+79877775432");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    public void shouldReturnErrorMessageIfDateIsEmpty() {

        $("[data-test-id='city'] [placeholder='Город']").setValue("Казань");
        String currentDate = generateDate(3);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").setValue("");
        $("[data-test-id='name'] [type=text]").setValue("Евгений Перчаткин");
        $("[data-test-id='phone'] [type=tel]").setValue("+79877775432");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='date'] .input__sub").shouldBe(visible).
                shouldHave(exactText("Неверно введена дата"));
    }

    @Test
    public void shouldReturnErrorMessageIfSurnameAndNameIsEmpty() {

        $("[data-test-id='city'] [placeholder='Город']").setValue("Казань");
        String currentDate = generateDate(3);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").setValue(currentDate);
        $("[data-test-id='name'] [type=text]").setValue("");
        $("[data-test-id='phone'] [type=tel]").setValue("+79877775432");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldReturnErrorMessageIfInvalidSurnameAndName() {

        $("[data-test-id='city'] [placeholder='Город']").setValue("Казань");
        String currentDate = generateDate(3);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").setValue(currentDate);
        $("[data-test-id='name'] [type=text]").setValue("Evgeny Perchatkin");
        $("[data-test-id='phone'] [type=tel]").setValue("+79877775432");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldReturnErrorMessageIfSurnameAndNameConsistsOfNumbers() {

        $("[data-test-id='city'] [placeholder='Город']").setValue("Казань");
        String currentDate = generateDate(3);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").setValue(currentDate);
        $("[data-test-id='name'] [type=text]").setValue("12345");
        $("[data-test-id='phone'] [type=tel]").setValue("+79877775432");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldReturnErrorMessageIfSurnameAndNameConsistsOfSpecialCharacters() {

        $("[data-test-id='city'] [placeholder='Город']").setValue("Казань");
        String currentDate = generateDate(3);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").setValue(currentDate);
        $("[data-test-id='name'] [type=text]").setValue("!?%%;");
        $("[data-test-id='phone'] [type=tel]").setValue("+79877775432");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldReturnSuccessIfFieldsAreFilledInCorrectly() {

        $("[data-test-id=city] [placeholder='Город']").setValue("Казань");
        String currentDate = generateDate(3);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(currentDate);
        $("[data-test-id=name] [type=text]").setValue("Евгений Перчаткин");
        $("[data-test-id=phone] [type=tel]").setValue("");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldReturnErrorMessageIfPhoneWithLetters() {

        $("[data-test-id=city] [placeholder='Город']").setValue("Казань");
        String currentDate = generateDate(3);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(currentDate);
        $("[data-test-id=name] [type=text]").setValue("Евгений Перчаткин");
        $("[data-test-id=phone] [type=tel]").setValue("АБВ");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldReturnErrorMessageIfPhoneHasSpecialCharacters() {

        $("[data-test-id=city] [placeholder='Город']").setValue("Казань");
        String currentDate = generateDate(3);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(currentDate);
        $("[data-test-id=name] [type=text]").setValue("Евгений Перчаткин");
        $("[data-test-id=phone] [type=tel]").setValue("!?%;");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldReturnErrorMessageIfPhoneIsWrong() {

        $("[data-test-id=city] [placeholder='Город']").setValue("Казань");
        String currentDate = generateDate(3);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(currentDate);
        $("[data-test-id=name] [type=text]").setValue("Евгений Перчаткин");
        $("[data-test-id=phone] [type=tel]").setValue("79877775432");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldReturnErrorMessageIfPhoneWrong() {

        $("[data-test-id=city] [placeholder='Город']").setValue("Казань");
        String currentDate = generateDate(3);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(currentDate);
        $("[data-test-id=name] [type=text]").setValue("Евгений Перчаткин");
        $("[data-test-id=phone] [type=tel]").setValue("+7987777543");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldReturnErrorMessageIfDoNotTick() {
        $("[data-test-id=city] [placeholder='Город']").setValue("Казань");
        String currentDate = generateDate(3);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(currentDate);
        $("[data-test-id=name] [type=text]").setValue("Евгений Перчаткин");
        $("[data-test-id=phone] [type=tel]").setValue("+79877775432");
        $("[role=button] .button__content").click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text").shouldBe(visible)
                .shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }
}