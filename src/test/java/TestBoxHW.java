import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestBoxHW extends tests.TestBase {


    @Test
    public void successfulFormSubmissionWithAllFieldsTestHardForm() {
        open("/automation-practice-form");

        $("#firstName").setValue("Pavel");
        $("#lastName").setValue("Yatmanov");
        $("#userEmail").setValue("pavelqa41@qa.com");
        $("#userNumber").setValue("9012311110");
        $(".form-check #gender-radio-1").click();

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1992");
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__month").$(byText("19")).doubleClick();

        $(".subjects-auto-complete__input").setValue("A")
                .pressEnter();
        $(".col-md-9 #hobbies-checkbox-1").click();
        $(".col-md-9 #hobbies-checkbox-2").click();
        $("#uploadPicture").uploadFromClasspath("doppp.jpg");

        $("#currentAddress").setValue("Ablukova street");
        $(".col-md-4.col-sm-12").click();
        $(byText("Haryana")).click();
        $("#city").click();
        $(byText("Karnal")).click();

        $("#submit").click();

        $(".table-dark")
                .$$("tr")
                .findBy(text("Student Name"))
                .shouldHave(text("Pavel"));
        $(".table-dark")
                .$$("tr")
                .findBy(text("Student Email"))
                .shouldHave(text("pavelqa41@qa.com"));
        $(".table-dark")
                .$$("tr")
                .findBy(text("Gender"))
                .shouldHave(text("Male"));
        $(".table-dark")
                .$$("tr")
                .findBy(text("Mobile"))
                .shouldHave(text("9012311110"));
        $(".table-dark")
                .$$("tr")
                .findBy(text("Hobbies"))
                .shouldHave(text("Sports, Reading"));
        $(".table-dark")
                .$$("tr")
                .findBy(text("Date of Birth"))
                .shouldHave(text("19 January,1992"));
        $(".table-dark")
                .$$("tr")
                .findBy(text("Subjects"))
                .shouldHave(text("Maths"));
        $(".table-dark")
                .$$("tr")
                .findBy(text("Picture"))
                .shouldHave(text("doppp.jpg"));
        $(".table-dark")
                .$$("tr")
                .findBy(text("Address"))
                .shouldHave(text("Ablukova street"));
        $(".table-dark")
                .$$("tr")
                .findBy(text("State and City"))
                .shouldHave(text("Haryana Karnal"));

    }

    @Test
    public void successfulFormSubmissionWithRequiredFieldsTestHardForm() {
        open("/automation-practice-form");
        $("#firstName").setValue("Pavel");
        $("#lastName").setValue("Yatmanov");
        $("#userEmail").setValue("pavelqa41@qa.com");
        $("#userNumber").setValue("9012311110");
        $(".form-check #gender-radio-1").click();
        $("#submit").click();

        $(".table-dark")
                .$$("tr")
                .findBy(text("Student Name"))
                .shouldHave(text("Pavel"));
        $(".table-dark")
                .$$("tr")
                .findBy(text("Student Email"))
                .shouldHave(text("pavelqa41@qa.com"));
        $(".table-dark")
                .$$("tr")
                .findBy(text("Gender"))
                .shouldHave(text("Male"));
        $(".table-dark")
                .$$("tr")
                .findBy(text("Mobile"))
                .shouldHave(text("9012311110"));
    }

    @Test
    public void successfulFormSubmissionWithAllFieldsTestEasyForm() {
        open("/text-box");
        $("#userName").setValue("Pavel Yatmanov");
        $("#userEmail").setValue("pavelqa41@qa.com");

        $("#submit").click();

        $("#output").shouldHave(Condition.text("Pavel Yatmanov"));
        $("#output").shouldHave(Condition.text("pavelqa41@qa.com"));


    }


    //НЕГАТИВНЫЕ ТЕСТЫ


    @Test
    public void shouldShowValidationErrorsWhenAllRequiredFieldsAreEmptyTestHardForm() {
        open("/automation-practice-form");
        $(".form-check #gender-radio-1").click();
        $("#submit").click();
    }

    @Test
    public void shouldShowValidationErrorsWhenFirstNameAreEmptyTestHardForm() {
        open("/automation-practice-form");
        $("#lastName").setValue("Yatmanov");
        $("#userEmail").setValue("pavelqa41@qa.com");
        $("#userNumber").setValue("9012311110");
        $(".form-check #gender-radio-1").click();
        $("#submit").click();
        $("#userForm").shouldHave(cssClass("was-validated"));
    }

    @Test
    public void shouldNotDisplayResultTableWhenFormSubmissionIsInvalidTestHardForm() {
        open("/automation-practice-form");
        $("#lastName").setValue("Yatmanov");
        $("#userEmail").setValue("pavelqa41@qa.com");
        $("#userNumber").setValue("9012311110");
        $(".form-check #gender-radio-1").click();
        $("#submit").click();
        $("#userForm").shouldNotHave(cssClass("table-dark"));
    }

    @Test
    public void shouldShowValidationErrorWhenInvalidEmailIsEnteredTestEasyForm() {
        open("/text-box");
        $("#userEmail").setValue("pavelqa");
        $("#submit").click();
        $("#userEmail").shouldHave(cssClass("field-error"))
                .shouldHave(cssClass("form-control"));

    }


}
