package com.qa.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.qa.quiz.SQPApplication;

@SpringBootTest(classes = SQPApplication.class,webEnvironment = WebEnvironment.RANDOM_PORT )
@ActiveProfiles("testing")
@Sql(scripts = {"classpath:test-schema.sql","classpath:test-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class indexJsHtmlTest {
	
	@LocalServerPort
	private int port;
	
	private static RemoteWebDriver driver;


	@BeforeEach
	void setup() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/andre/Documents/eclipse/QuizSpringProject/src/test/resources/chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);
		driver = new ChromeDriver(options);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	void titleTest() {
		driver.get("http://localhost:" + port); 

		assertEquals("Quiz Spring Project", driver.getTitle()); 
	}
	
	@Test
	void readQuiz() {
		driver.get("http://localhost:" + port); 
		
		assertEquals("Starting Name Value", driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div/h5")).getText());
		assertEquals("Starting Desc Value", driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div/h6")).getText());
	}
	
	@Test
	void readQuestion() {
		driver.get("http://localhost:" + port); 
		
		assertEquals("Example Q", driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/table/tbody/tr/td[1]")).getText());
		assertEquals("Example A", driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/table/tbody/tr/td[2]")).getText());
	}
	
	@Test
	void createQuiz(){
		driver.get("http://localhost:" + port); 
		
		driver.findElement(By.id("quizName")).sendKeys("Test Name 01");
		driver.findElement(By.id("quizDescription")).sendKeys("Test Desc 02");
		driver.findElement(By.id("createNewQuiz")).click();

		assertEquals("Test Name 01", driver.findElement(By.xpath("/html/body/main/div[2]/div[2]/div/h5")).getText());
		assertEquals("Test Desc 02", driver.findElement(By.xpath("/html/body/main/div[2]/div[2]/div/h6")).getText());
		
	}
	
	@Test
	void createQuestion() {
		driver.get("http://localhost:" + port); 
		
		driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/button[3]")).click();
		driver.findElement(By.id("questionQuestion")).sendKeys("Test Question 01");
		driver.findElement(By.id("questionAnswer")).sendKeys("Test Answer 01");
		driver.findElement(By.id("questionCreateModalButton")).click();
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div[3]/button[2]")).click();
		
		assertEquals("Test Question 01", driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/table/tbody/tr[2]/td[1]")).getText());
		assertEquals("Test Answer 01", driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/table/tbody/tr[2]/td[2]")).getText());
		
	}

	@Test
	void deleteQuiz() {
		driver.get("http://localhost:" + port); 

		driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/button[2]")).click();
		driver.findElement(By.id("quizDeleteModalButton")).click();
		
		assertEquals("No Quizzes Made Yet", driver.findElement(By.xpath("/html/body/main/div[2]/h1")).getText());
	}
	
	@Test
	void deleteQuestion() {
		driver.get("http://localhost:" + port); 

		driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/table/tbody/tr[1]/td[3]/button[2]")).click();
		
		assertTrue(!driver.findElements(By.xpath("/html/body/main/div[2]/div[1]/table")).isEmpty());

	}
	
	@Test
	void updateQuiz() throws InterruptedException {
		driver.get("http://localhost:" + port); 

		driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/button[2]")).click();
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/form/div[1]/input")).sendKeys(" - Edit");
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/form/div[2]/input")).sendKeys(" - Edit Also");
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[3]/button[1]")).click();
		
		driver.navigate().refresh();
	
		assertEquals("Starting Name Value", driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div/h5")).getText());
		assertEquals("Starting Desc Value", driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div/h6")).getText());
	}
	
	@Test
	void updateQuestion(){
		driver.get("http://localhost:" + port); 
		
		driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/table/tbody/tr[1]/td[3]/button[1]")).click();
		driver.findElement(By.id("questionEditQuestion")).sendKeys(" - Edit");
		driver.findElement(By.id("questionEditAnswer")).sendKeys(" - Edit Also");
		driver.findElement(By.id("questionEditModalButton")).click();
		
		driver.navigate().refresh();
		
		assertEquals("Example Q - Edit", driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/table/tbody/tr/td[1]")).getText());
		assertEquals("Example A - Edit Also", driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/table/tbody/tr/td[2]")).getText());
	}
	
	

	@AfterEach
	void tearDown() {
		driver.close();
	}
	
	@AfterAll
	static void completeQuit() {
		driver.quit();
	}
	
}
