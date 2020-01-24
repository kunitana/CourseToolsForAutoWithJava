import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SignUpPage {
    private WebDriver webdriver;

    public SignUpPage(WebDriver webdriver) {
        this.webdriver = webdriver;
    }

    private By emailField = By.cssSelector("input#register-email");
    private By confirmEmailField = By.cssSelector("input#register-confirm-email");
    private By passwordField = By.cssSelector("input#register-password");
    private By nameField = By.cssSelector("input#register-displayname");
    private By monthDropDown = By.cssSelector("select#register-dob-month");
    private String monthDropDownOption = "//select[@id = 'register-dob-month']/option[text() = '%s']";
    private By dayField = By.cssSelector("input#register-dob-day");
    private By yearField = By.cssSelector("input#register-dob-year");
    private String sexRadioButton = "//li[@class = 'gender']/label[normalize-space() = '%s']/input";
    private By shareCheckBox = By.cssSelector("input#register-thirdparty");
    private By registerButton = By.cssSelector("a#register-button-email-submit");
    private By errorLabel = By.xpath("//label[@class='has-error']");
    private String errorByText = "//label[@class = \"has-error\" and text() = \"%s\"]";

    public SignUpPage typeEmail(String email){
        webdriver.findElement(emailField).sendKeys(email);
        return this;
    }

    public SignUpPage typeConfirmEmail(String email){
        webdriver.findElement(confirmEmailField).sendKeys(email);
        return this;
    }

    public SignUpPage typePassword(String password){
        webdriver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public SignUpPage typeName(String name){
        webdriver.findElement(nameField).sendKeys(name);
        return this;
    }

    public SignUpPage setMonth(String month){
        webdriver.findElement(monthDropDown).click();
        new WebDriverWait(webdriver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(monthDropDownOption, month)))).click();
        return this;
    }


    public SignUpPage typeDay (String day){
        webdriver.findElement(dayField).sendKeys(day);
        return this;
    }

    public SignUpPage typeYear (String year){
        webdriver.findElement(yearField).sendKeys(year);
        return this;
    }

    public SignUpPage setSex (String value){
        webdriver.findElement(By.xpath(String.format(sexRadioButton, value))).click();
        return this;
    }


    public SignUpPage setShare (boolean value){
        WebElement checkBox = webdriver.findElement(shareCheckBox);
        if (!checkBox.isSelected() == value){checkBox.click();}
        return this;
    }

    public void clickSignUpButton(){
        webdriver.findElement(registerButton).click();
    }

    public List<WebElement> getErrors(){
        return webdriver.findElements(errorLabel);
    }

    public String getErrorsByNumber(int number){
        return  getErrors().get(number-1).getText();
    }

    public boolean isErrorVisible(String message){
        return webdriver.findElements(By.xpath(String.format(errorByText, message))).size()>0 && webdriver.findElements(By.xpath(String.format(errorByText, message))).get(0).isDisplayed();
    }
}
