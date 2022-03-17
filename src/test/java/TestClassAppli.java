

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.RunnerOptions;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestClassAppli {
static WebDriver wd;
/*
    public TestClassAppli(WebDriver wd) {
        super(wd);
    }*/


   // public static void main(String[] args) {

    @Test
    public void someTest(){
        // Create a new chrome web driver
        // WebDriver webDriver = new ChromeDriver();
        wd = new ChromeDriver();
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Create a runner with concurrency of 1
        VisualGridRunner runner = new VisualGridRunner(new RunnerOptions().testConcurrency(5));

        // Create Eyes object with the runner, meaning it'll be a Visual Grid eyes.
        Eyes eyes = new Eyes(runner);

        setUp(eyes);

        try {
            // Note to see visual bugs, run the test using the above URL for the 1st run.
            // but then change the above URL to https://demo.applitools.com/index_v2.html
            // (for the 2nd run)
            ultraFastTest(wd, eyes);

        } finally {
            tearDown(wd, runner);
        }

    }

    public static void setUp(Eyes eyes) {

        // Initialize eyes Configuration
        Configuration config = eyes.getConfiguration();

        // You can get your api key from the Applitools dashboard
        config.setApiKey("ylfTBcOE0E107fPVsf3m9101OcEgPoJWZyrx2CNMRtqXmPw110");

        // create a new batch info instance and set it to the configuration
        config.setBatch(new BatchInfo("TRELLO"));

        // Add browsers with different viewports
        config.addBrowser(800, 600, BrowserType.CHROME);
        config.addBrowser(700, 500, BrowserType.FIREFOX);
        config.addBrowser(1600, 1200, BrowserType.IE_11);
        //System.setProperty("webdriver.edge.driver","C:\\TOOLS\\msedgedriver.exe");
        config.addBrowser(1024, 768, BrowserType.EDGE_CHROMIUM);
        config.addBrowser(800, 600, BrowserType.SAFARI);

        // Add mobile emulation devices in Portrait mode
        config.addDeviceEmulation(DeviceName.iPhone_X, ScreenOrientation.PORTRAIT);
        config.addDeviceEmulation(DeviceName.Pixel_2, ScreenOrientation.PORTRAIT);

        // Set the configuration object to eyes
        eyes.setConfiguration(config);

    }

    public static void ultraFastTest(WebDriver wd, Eyes eyes) {
//TetsBase tb = new TetsBase();
        try {

            // Navigate to the url we want to test
            //webDriver.get("https://demo.applitools.com");
            // webDriver.get("https://demo.applitools.com/index_v2.html");
            wd.get("https://trello.com/");

            // Call Open on eyes to initialize a test session
            eyes.open(wd, "Demo App", "Trello testing", new RectangleSize(800, 600));

            // check the login page with fluent api, see more info here
            // https://applitools.com/docs/topics/sdk/the-eyes-sdk-check-fluent-api.html
            eyes.check(Target.window().fully().withName("Home page"));

            initLogin();
            Thread.sleep(3000);
            eyes.check(Target.window().fully().withName("Login page"));
            Thread.sleep(3000);
            fillLoginForm("2022jamesbond2022@gmail.com", "Credentials1988!~");
            Thread.sleep(3000);
            eyes.check(Target.window().fully().withName("Login form"));
            Thread.sleep(3000);
            submitLogin();
            Thread.sleep(3000);
            eyes.check(Target.window().fully().withName("Account page"));
            // webDriver.findElement(By.id("log-in")).click();
            // Check the app page
            Thread.sleep(3000);

            // Call Close on eyes to let the server know it should display the results
            eyes.closeAsync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eyes.abortAsync();
        }

    }

    private static void tearDown(WebDriver webDriver, VisualGridRunner runner) {
        // Close the browser
        //webDriver.quit();

        // we pass false to this method to suppress the exception that is thrown if we
        // find visual differences
        TestResultsSummary allTestResults = runner.getAllTestResults(false);
        System.out.println(allTestResults);
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
