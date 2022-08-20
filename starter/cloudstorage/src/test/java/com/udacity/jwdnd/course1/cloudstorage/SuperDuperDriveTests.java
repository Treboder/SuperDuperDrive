package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SuperDuperDriveTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;
	private PageObjectLogin loginPage;
	private PageObjectSignup signupPage;
	private PageObjectHomeNotes homePageNotes;
	private PageObjectHomeFiles homepageFiles;
	private PageObjectResult resultPage;

	private String firstName = "Harry";
	private String lastName = "Potter";
	private String user = "Harry";
	private String password = "Nimbus";

	private String noteTitle =  "my first note";
	private String noteText = "Du bist der, der schwach ist. Du wirst nie wissen, was Liebe ist. Oder Freundschaft. Und deswegen kannst du mir nur leidtun";


	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	@Order(1)
	public void loginFail() {
		driver.get("http://localhost:" + port + "/login");
		loginPage = new PageObjectLogin(driver);
		loginPage.login(user, password);
		assertEquals("Invalid username or password", loginPage.getErrorMessage());
	}

	@Test
	@Order(2)
	public void signupSuccess() {
		driver.get("http://localhost:" + port + "/signup");
		signupPage = new PageObjectSignup(driver);
		signupPage.signup(firstName, lastName, user, password);
		assertEquals("You successfully signed up! Please continue to the login page.", signupPage.getSuccessMessage());
	}

	@Test
	@Order(3)
	public void loginSuccess() {
		driver.get("http://localhost:" + port + "/login");
		loginPage = new PageObjectLogin(driver);
		loginPage.login(user, password);

		driver.get("http://localhost:" + port + "/home");
		Assertions.assertEquals("Home", driver.getTitle());

	}

	@Test
	@Order(4)
	public void createNewNoteAndSave() {

		homePageNotes = new PageObjectHomeNotes(driver);
		resultPage = new PageObjectResult(driver);
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		doSignUp();
		doLogin();

		driver.get("http://localhost:" + port + "/home");
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		homePageNotes.switchToNavNotesTab();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-note-button-new")));
		homePageNotes.clickAddNoteButtonNew();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
		homePageNotes.fillNoteDetailsAndSave(noteTitle, noteText);

		webDriverWait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Success", resultPage.getSuccessMessage());

		driver.get("http://localhost:" + this.port + "/home");
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		homePageNotes.switchToNavNotesTab();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-note-button-new")));
		String noteTitleOutput = homePageNotes.getNoteTitle();
		Assertions.assertEquals(noteTitle, noteTitleOutput);

		String noteDescriptionOutput = homePageNotes.getNoteDescription();
		Assertions.assertEquals(noteText, noteDescriptionOutput);
	}

	private void doSignUp() {
		driver.get("http://localhost:" + port + "/signup");
		signupPage = new PageObjectSignup(driver);
		signupPage.signup(firstName, lastName, user, password);
	}

	private void doLogin() {
		driver.get("http://localhost:" + port + "/login");
		loginPage = new PageObjectLogin(driver);
		loginPage.login(user, password);
	}

}
