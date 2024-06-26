import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.List;

public class FirstTest {

    private AppiumDriver<?> driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "13.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "org.wikipedia.main.MainActivity");
        capabilities.setCapability("app", "/Users/vadim/Desktop/javaAppiumAutomation/JavaAutomation/apks/org.wikipedia.apk");

        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("disableWindowAnimation", true);

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testFirstRun() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@class='android.view.ViewGroup']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic 'searching by Java'",
                15
        );
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );
    }

    @Test
    public void testCompareArticleTitle() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@class='android.view.ViewGroup']//*[@text='Object-oriented programming language']"),
                "Cannot find Search Wikipedia input",
                5
        );
        WebElement title_element = waitForElementPresent(
                By.xpath("//*[@class='android.view.ViewGroup']//*[@text='Java (programming language)']"),
                "Cannot find article title",
                15
        );

        String article_title = title_element.getText();

        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void testSwipeArticle() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Appium",
                "Cannot find search input",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Cannot find Appium article in search",
                5
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/page_toolbar_button_search"),
                "Cannot find article title",
                15
        );
        swipeUpToFindElement(
                By.xpath("//*[@text='View article in browser']"),
                "Cannot find the end of the article",
                20
        );
    }
        @Test
        public void saveFirstArticleToMyList() {
            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find Search Wikipedia input",
                    5
            );
            waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Java",
                    "Cannot find search input",
                    5
            );
            waitForElementAndClick(
                    By.xpath("//*[@class='android.view.ViewGroup']//*[@text='Object-oriented programming language']"),
                    "Cannot find Search Wikipedia input",
                    5
            );
            waitForElementPresent(
                    By.xpath("//*[@class='android.view.ViewGroup']//*[@text='Java (programming language)']"),
                    "Cannot find article title",
                    15
            );
            waitForElementAndClick(
                    By.xpath("//android.widget.TextView[@content-desc='Save']"),
                    "Cannot find button to open article options",
                    5
            );
            waitForElementAndClick(
                    By.id("org.wikipedia:id/snackbar_action"),
                    "Cannot find add to list",
                    5
            );
            waitForElementAndClear(
                    By.id("org.wikipedia:id/text_input"),
                    "Cannot find input to set name of article folder",
                    5
            );
            String name_of_folder = "Learning programming";

            waitForElementAndSendKeys(
                    By.id("org.wikipedia:id/text_input"),
                    name_of_folder,
                    "Cannot put text into article folder input",
                    5
            );
            waitForElementAndClick(
                    By.xpath("//*[@text='OK']"),
                    "Cannot press OK button",
                    5
            );
            waitForElementAndClick(
                    By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                    "Cannot close article",
                    5
            );
            waitForElementAndClick(
                    By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                    "Cannot close article",
                    5
            );
            waitForElementAndClick(
                    By.xpath("//android.widget.FrameLayout[@content-desc='Saved']"),
                    "Cannot find navigation button to Saved",
                    5
            );
            waitForElementAndClick(
                    By.xpath("//*[@text='"+ name_of_folder +"']"),
                    "Cannot find created folder",
                    5
            );
            swipeElementToLeft(
                    By.xpath("//*[@text='Java (programming language)']"),
                    "Cannot find saved article"
            );
            waitForElementNotPresent(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']"),
                    "Cannot delete saved article",
                    5
            );
        }

        @Test
        public void testAmountOfNotEmptySearch(){
            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find Search Wikipedia input",
                    5
            );
            String search_line = "Linkin park discography";
            waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    search_line,
                    "Cannot find search input",
                    5
            );
            String search_result_locator = "//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title' and @text='Linkin Park discography']";
            waitForElementPresent(
                    By.xpath(search_result_locator),
                    "Cannot find anything by the request " + search_line,
                    15
            );
            int amount_of_search_results = getAmountOfElements(
                    By.xpath(search_result_locator)
            );

            Assert.assertTrue(
                    "We found too few results",
                    amount_of_search_results > 0

            );
        }
        @Test
        public void testAmountOfEmptySearch()
        {
            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find Search Wikipedia input",
                    5
            );
            String search_line = "xzcadsadsadawwq";
            waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    search_line,
                    "Cannot find search input",
                    5
            );
            String search_result_locator = "//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title' and @text='Linkin Park discography']";
            String empty_results_label = "//*[@text='No results']";
            waitForElementPresent(
                    By.xpath(empty_results_label),
                    "Cannot find empty result label by the request " + search_line,
                    15
            );
            assertElementNotPresent(
                    By.xpath(search_result_locator),
                    "We've found some results by request " + search_line
            );
        }
        @Test
        public void testChangeScreenOrientationOnSearchResults()
        {
                waitForElementAndClick(
                        By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                        "Cannot find Search Wikipedia input",
                        5
                );
                String search_line = "Java";
                waitForElementAndSendKeys(
                        By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                        search_line,
                        "Cannot find search input",
                        5
                );
            waitForElementAndClick(
                    By.xpath("//*[@class='android.view.ViewGroup']//*[@text='Object-oriented programming language']"),
                    "Cannot find 'Object-oriented programming language' topic searching by " + search_line,
                    15
            );
            String title_before_rotation = waitForElementAndGetAttribute(
                    By.xpath("//*[@class='android.view.ViewGroup']//*[@text='Java (programming language)']"),
                    "text",
                    "Cannot find title of article",
                    15
            );

            driver.rotate(ScreenOrientation.LANDSCAPE);

            String title_after_rotation = waitForElementAndGetAttribute(
                    By.xpath("//*[@class='android.view.ViewGroup']//*[@text='Java (programming language)']"),
                    "text",
                    "Cannot find title of article",
                    15
            );
            Assert.assertEquals(
                    "Article title habe been change after screen rotation",
                    title_before_rotation,
                    title_after_rotation
            );
        }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String errorMessage) {
        return waitForElementPresent(by, errorMessage, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    protected void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }
    protected void swipeUpQuick()
    {
        swipeUp(200);
    }
    protected void swipeUpToFindElement(By by, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        driver.findElements(by);
        driver.findElements(by).size();

        while (driver.findElements(by).size() == 0)
        {
        if (already_swiped > max_swipes) {
            waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
            return;
        }
            swipeUpQuick();
            ++already_swiped;
        }
    }
    protected void swipeElementToLeft(By by, String error_message){
       WebElement element = waitForElementPresent(
               by,
               error_message,
               10);
       int left_x = element.getLocation().getX();
       int right_x = left_x + element.getSize().getWidth();
       int upper_y = element.getLocation().getY();
       int lower_y = upper_y + element.getSize().getHeight();
       int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();

    }
    private int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();

    }
    private void assertElementNotPresent(By by,String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }
    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}
