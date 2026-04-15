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
        $("#genterWrapper").$(byText("Male")).click();

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1992");
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__month").$(byText("19")).doubleClick();

        $(".subjects-auto-complete__input").setValue("A")
                .pressEnter();

        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#hobbiesWrapper").$(byText("Reading")).click();

        $("#uploadPicture").uploadFromClasspath("doppp.jpg");

        $("#currentAddress").setValue("Ablukova street");
        $(".col-md-4.col-sm-12").click();
        $(byText("Haryana")).click();
        $("#city").click();
        $(byText("Karnal")).click();

        $("#submit").click();

        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Pavel Yatmanov"));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("pavelqa41@qa.com"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("9012311110"));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Sports, Reading"));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("19 January,1992"));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Maths"));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("doppp.jpg"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text("Ablukova street"));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("Haryana Karnal"));


    }

    @Test
    public void successfulFormSubmissionWithRequiredFieldsTestHardForm() {
        open("/automation-practice-form");
        $("#firstName").setValue("Pavel");
        $("#lastName").setValue("Yatmanov");
        $("#userEmail").setValue("pavelqa41@qa.com");
        $("#userNumber").setValue("9012311110");
        $("#genterWrapper").$(byText("Male")).click();
        $("#submit").click();

        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Pavel Yatmanov"));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("pavelqa41@qa.com"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("9012311110"));
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
        $("#genterWrapper").$(byText("Male")).click();
        $("#submit").click();
        $("#userForm").shouldHave(cssClass("was-validated"));
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
        $("#genterWrapper").$(byText("Male")).click();
        $("#submit").click();
        $("#userForm").shouldNotHave(cssClass("table-dark"));
    }

    @Test
    public void shouldShowValidationErrorWhenInvalidEmailIsEnteredTestEasyForm() {
        open("/text-box");
        $("#userEmail").setValue("pavelqaa");
        $("#submit").click();
        $("#userEmail").shouldHave(cssClass("field-error"))
                .shouldHave(cssClass("form-control"));

    }


}
