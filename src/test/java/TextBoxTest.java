import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTest {

    @Test
    void succesfulFillFormTest(){

        open("https://demoqa.com/text-box");
        System.out.println("Страница открыта");

        $("#userName").setValue("Pavel Yatmanov");
        $("#userEmail").setValue("pavelya@gmail.com");
        $("#currentAddress").setValue("Avlukova street");
        $("#permanentAddress").setValue("Avlukova street");
        $("#submit").click();

        $("#output").shouldHave(Condition.text("Pavel Yatmanov"));
        $("#output").shouldHave(Condition.text("pavelya@gmail.com"));
        $("#output").shouldHave(Condition.text("Avlukova street"));
        $("#outpфыаut").shouldHave(Condition.text("Avlukova street"));





    }
}
