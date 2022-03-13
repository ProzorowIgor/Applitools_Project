import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverClass extends TetsBase{
    public DriverClass(WebDriver wd) {
        super(wd);
    }

    public void test(){
        wd = new ChromeDriver();



    }

}
