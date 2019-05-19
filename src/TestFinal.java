import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

class TestFinal {

	@Test
	void testCanVisitFacultyPageMenu() throws InterruptedException {
		// Create a new instance of the Firefox driver
		// Notice that the remainder of the code relies on the interface, 
		// not the implementation.
//		WebDriver driver = new ChromeDriver();
		System.setProperty("webdriver.gecko.driver", "/Users/Enrique Benitez/Desktop/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();

		// And now use this to visit Google
		driver.get("http://cse.uprm.edu");
		driver.manage().window().setSize(new Dimension(2528,1339));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		try {

			// Find the People menu by its name and hover over it
			WebElement peopleMenu = driver.findElement(By.xpath("//span[text()='People']"));
//			Actions myActions = new Actions(driver);
//			myActions.moveToElement(peopleMenu).perform();
		
			Thread.sleep(1000);
			
			peopleMenu.click();
						
			// Find the Faculty menu item and click it
			WebElement facultyMenuItem = driver.findElement(By.xpath("//span[text()='Faculty']"));
			
			Thread.sleep(1000);
			
			facultyMenuItem.click();	

		}
		catch (NoSuchElementException e) {
			// Some menu item not found
			fail(e.getMessage());
		}

		// Verify that you are in the Faculty page
		assertEquals("https://www.uprm.edu/cse/faculty/", driver.getCurrentUrl());

		//Close the browser
		driver.quit();
	}
	
	@ParameterizedTest
	@MethodSource("menuProvider")
	void testAllMenusExist(String menu)  throws InterruptedException {
//		System.setProperty("webdriver.gecko.driver", "/Users/Enrique Benitez/Desktop/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("http://cse.uprm.edu");
		
		String actualMenu = "";
		
		try {
			WebElement theElement = driver.findElement(By.xpath("//span[text()='" + menu + "']"));
			actualMenu = theElement.getText();
		}
		catch (NoSuchElementException e) {
			fail(e.getMessage());
		}

		//Close the browser
		driver.quit();
		
		// Verify that the element text is the correct one
		assertEquals(menu, actualMenu);
	}
	
	static Arguments[] menuProvider() {
		return new Arguments[] {
				Arguments.arguments("Home"),
				Arguments.arguments("About Us"),
				Arguments.arguments("Academics"),
				Arguments.arguments("People"),
				Arguments.arguments("Students"),
				Arguments.arguments("News"),
				Arguments.arguments("Donations")
		};
	}
	
	
	@ParameterizedTest
	@MethodSource("courseProvider")
	void testRequiredCoursesInCatalog(String course)  throws InterruptedException {
//		System.setProperty("webdriver.gecko.driver", "/Users/Enrique Benitez/Desktop/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		driver.get("https://www.uprm.edu/cse/course_catalog/");
		
		String actualCourse = "";
		
		try {
			WebElement theElement = driver.findElement(By.linkText(course));
			actualCourse = theElement.getText();
		}
		catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
		
		//Close the browser
		driver.quit();
		
		// Verify that the link text is the correct one
		assertEquals(course, actualCourse);
	}
	
	static Arguments[] courseProvider() {
		return new Arguments[] {
				Arguments.arguments("CIIC 3011"),
				Arguments.arguments("CIIC 3075"),
				Arguments.arguments("CIIC 3081"),
				Arguments.arguments("CIIC 4010"),
				Arguments.arguments("CIIC 4020"),
				Arguments.arguments("CIIC 4025"),
				
				Arguments.arguments("CIIC 4030"),
				Arguments.arguments("CIIC 4050"),
				Arguments.arguments("CIIC 4060"),
				Arguments.arguments("CIIC 4070"),
				Arguments.arguments("CIIC 4082"),
				Arguments.arguments("CIIC 4995"),
				
				Arguments.arguments("CIIC 4998"),
				Arguments.arguments("CIIC 5015"),
				Arguments.arguments("CIIC 5017"),
				Arguments.arguments("CIIC 5018"),
				Arguments.arguments("CIIC 5019"),
				Arguments.arguments("CIIC 5029"),
				
				Arguments.arguments("CIIC 5045"),
				Arguments.arguments("CIIC 5110"),
				Arguments.arguments("CIIC 5120"),
				Arguments.arguments("CIIC 5130"),
				Arguments.arguments("CIIC 5140"),
				Arguments.arguments("CIIC 5995"),
				
				Arguments.arguments("INSO 4101"),
				Arguments.arguments("INSO 4115"),
				Arguments.arguments("INSO 4116"),
				Arguments.arguments("INSO 4117"),
				Arguments.arguments("INSO 4151"),
				Arguments.arguments("INSO 4995"),
				
				Arguments.arguments("INSO 4998"),
				Arguments.arguments("INSO 5111"),
				Arguments.arguments("INSO 5118")
		};
	}
	
	@Test
	void testCIIC3011HasSyllabus()  throws InterruptedException {
//		System.setProperty("webdriver.gecko.driver", "/Users/Enrique Benitez/Desktop/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.uprm.edu/cse/course_catalog/");
		
		String syllabusLink = "";
		
		try {
			driver.findElement(By.linkText("CIIC 3075")).click();
			
			/*
			 * XPATH IS NOT WORKING FOR SOME REASON
			 * */
//			WebElement linkElement = driver.findElement(By.xpath("//*[@id=\"ultimate-heading-47645cda020f4b31f\"]/div[3]/p[7]/a"));
			
			WebElement linkElement = driver.findElement(By.linkText("here"));
			syllabusLink = linkElement.getAttribute("href");
		}
		catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
		
		//Close the browser
		driver.quit();
		
		// Verify that the element link is the correct one
		assertEquals("https://www.uprm.edu/cse/wp-content/uploads/sites/153/2019/03/CIIC-3011-Introduction-to-Computer-Programming-I.pdf", syllabusLink);
	}
	
	//fail("Test under construction");

}