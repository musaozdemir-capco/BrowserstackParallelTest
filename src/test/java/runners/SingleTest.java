package runners;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

public class SingleTest extends BrowserStackJUnitTest {

  @Test
  public void test() throws Exception {
    driver.get("https://community.backbase.com/login");

    driver.findElement(By.xpath("//*[@class=\"widget-button btn btn-primary btn-small login-button btn-icon-text\"]")).click();
    driver.findElement(By.id("login-account-name")).sendKeys("username");
    driver.findElement(By.id("login-account-password")).sendKeys("PASSWORD");
    driver.findElement(By.xpath("//*[@class=\"btn btn-large btn-primary btn btn-icon-text ember-view\"]")).click();



  }

}