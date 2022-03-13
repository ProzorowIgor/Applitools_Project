import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TetsBase {
  static WebDriver wd;

    public TetsBase(WebDriver wd) {
        this.wd = wd;
    }

    public static void initLogin() {
        click(By.cssSelector("[href='/login']"));
    }

    public static void fillLoginForm(String email, String password) throws InterruptedException {
        User user = new User().withUser(email).withPassword(password);
        type(By.cssSelector("#user"),user.getUser());

        Thread.sleep(2000);
        click(By.id("login"));
        type(By.name("password"), user.getPassword());
    }

    public static void click(By locator) {
        wd.findElement(locator).click();

    }

    public static void type(By locator, String text) {
        if (text != null) {
            WebElement element = wd.findElement(locator);
            element.click();
            element.clear();
            element.sendKeys(text);
        }
    }

    public static void submitLogin() {
        click(By.id("login-submit"));
    }


}
