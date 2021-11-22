package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testBase.PageInitializer;

public class LoginPageElements extends PageInitializer {



    @FindBy(id = "login-account-name")
    public WebElement userNameTextBox;

    @FindBy(id = "login-account-password")
    public WebElement passwordTextBox;

    @FindBy(xpath = "//*[@class=\"btn btn-large btn-primary btn btn-icon-text ember-view\"]")
    public WebElement loginButton;

    @FindBy(xpath = "//*[@id=\"modal-alert\"]")
    public WebElement allertMessage;

    @FindBy(id = "forgot-password-link")
    public WebElement forgotPasswordButton;

    @FindBy(xpath = "//input[@id='username-or-email']")
    public WebElement forgotPasswordTextBox;

    @FindBy(xpath = "//button[@class='btn-primary forgot-password-reset btn btn-text ember-view']")
    public WebElement resetPasswordButton;

    @FindBy(xpath = "//div[@id='modal-alert']")
    public WebElement forgotPasswordMessage;







    public LoginPageElements(){
    PageFactory.initElements(driver , this);
}


}
