package MyFirstTest;//import org.junit.jupiter.api.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class WhenDoingASearch {

    //First example, raw
//    @Test
//    public void should_see_the_title_of_the_search_performed(){
//
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/windows/chromedriver.exe");
//        WebDriver browser = new ChromeDriver();
//        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(5));
//
//        browser.get("https://www.bing.com/");
//        browser.findElement(By.xpath("//*[@id='sb_form_q']")).sendKeys("CHEESECAKE" + Keys.ENTER);
//
//        wait.until(presenceOfElementLocated(By.xpath("//*[@id='b_pole']/div")));
//        String title = browser.findElement(By.xpath("//*[@id='b_wfCarousel']/div[1]/h2")).getText();
//
//        assertThat(title).isEqualToIgnoringCase("Recipes");
//    }

    //Second example, improved
    private WebDriver browser;
    private WebDriverWait wait; //Explicit wait

    @Before
    public void Setup(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/windows/chromedriver.exe");
        browser = new ChromeDriver();
        wait = new WebDriverWait(browser, Duration.ofSeconds(5));
        //browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); //Implicit wait
    }

    @Test
    public void should_see_the_title_of_the_search_performed(){
        //Given
        browser.get("https://www.bing.com/");

        //When
        browser.findElement(By.id("sb_form_q")).sendKeys("CHEESECAKE" + Keys.ENTER);
        wait.until(presenceOfElementLocated(By.cssSelector(".b_poleContent")));
        String title = browser.findElement(By.cssSelector(".b_antiSideBleed")).getText();

        //Then
        assertThat(title).isEqualToIgnoringCase("Recipes");
    }

    @After
    public void tearDown(){
        browser.quit();
    }
}
