import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class SignUpTest {
    private WebDriver driver;
    private SignUpPage page;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver","C:\\Users\\dmitry.kunitsyn\\IdeaProjects\\Testselenium\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.spotify.com/signup");
    }

    @Test
    public void typeInvalidYear(){
        page = new SignUpPage(driver);
        page.setMonth("Dezember")
                .typeDay("20")
                .typeYear("85")
                .setShare(true).clickSignUpButton();

        Assert.assertTrue(page.isErrorVisible("Bitte gib ein gültiges Jahr an."));
        Assert.assertFalse(page.isErrorVisible("When were you born"));
    }

    @Test
    public void typeInvalidEmail(){
        page = new SignUpPage(driver);
        page.typeEmail("test@mail.test")
                .typeConfirmEmail("wrong@mail.test")
                .typeName("testName")
                .clickSignUpButton();
        Assert.assertTrue(page.isErrorVisible("Die E-Mail-Adresse stimmt nicht."));
    }

    @Test
    public void signUpWithEmptyPassword(){
        page = new SignUpPage(driver);
        page.typeEmail("test@mail.test")
                .typeConfirmEmail("test@mail.test")
                .typeName("testName")
                .clickSignUpButton();
        Assert.assertTrue(page.isErrorVisible("Gib ein Passwort ein, um fortzufahren."));
    }

    @Test
    public void typeInvalidValues(){
        page = new SignUpPage(driver);
        page.typeEmail("testmail")
                .typeConfirmEmail("wrong@mail.test")
                .typePassword("qwerty!1234")
                .typeName("Name")
                .setSex("Weiblich")
                .setShare(false)
                .clickSignUpButton();
        Assert.assertEquals(10, page.getErrors().size());
        Assert.assertEquals("Bitte gib einen gültigen Tag des Monats an.", page.getErrorsByNumber(5));
        }

    @After
    public void tearDown(){
        driver.quit();
    }
}