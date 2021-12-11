// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class FinalTestTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void finalTest() {
    // Test name: Final_Test
    // Step # | name | target | value
    // 1 | open | http://localhost:63342/ServerDon/com/example/serverdon/html/index.html?_ijt=uvorufv1ikuntc8u4729lh754u&_ij_reload=RELOAD_ON_SAVE | 
    driver.get("http://localhost:63342/ServerDon/com/example/serverdon/html/index.html?_ijt=uvorufv1ikuntc8u4729lh754u&_ij_reload=RELOAD_ON_SAVE");
    // 2 | setWindowSize | 1550x838 | 
    driver.manage().window().setSize(new Dimension(1550, 838));
    // 3 | click | linkText=Page1:Page1.HTML | 
    driver.findElement(By.linkText("Page1:Page1.HTML")).click();
    // 4 | click | linkText=Page 4:Page4.html | 
    driver.findElement(By.linkText("Page 4:Page4.html")).click();
    // 5 | click | linkText=Page 14:Page14.html | 
    driver.findElement(By.linkText("Page 14:Page14.html")).click();
    // 6 | click | linkText=Index | 
    driver.findElement(By.linkText("Index")).click();
    // 7 | click | linkText=Page1:Page1.HTML | 
    driver.findElement(By.linkText("Page1:Page1.HTML")).click();
    // 8 | click | linkText=Page 4:Page4.html | 
    driver.findElement(By.linkText("Page 4:Page4.html")).click();
    // 9 | click | linkText=Page 15:Page15.html | 
    driver.findElement(By.linkText("Page 15:Page15.html")).click();
    // 10 | click | linkText=Index | 
    driver.findElement(By.linkText("Index")).click();
    // 11 | close |  | 
    driver.close();
  }
}
