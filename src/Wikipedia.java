import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Wikipedia {

	public static void main(String[] args) throws InterruptedException {
		// Project > properties > Java Build path > libraries > add external jars
		System.setProperty("webdriver.gecko.driver", "/Users/Enrique Benitez/Desktop/geckodriver.exe");
		
		WebDriver driver = new FirefoxDriver();
		
		driver.get("http://cse.uprm.edu");
		WebElement test = driver.findElement(By.xpath("//span[text()='Home']"));
		System.out.println(test.getText());
		
		driver.get("https://www.wikipedia.org/");
//		driver.get("https://google.com/");
		
		
		WebElement searchBox = driver.findElement(By.id("searchInput"));
		searchBox.sendKeys("Software");
		
		Thread.sleep(5000);
		
		searchBox.submit();
		
		Thread.sleep(5000);
		
		driver.quit();
	}

}