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
	//private PageObjectHomeFiles homepageFiles;
	private PageObjectResult resultPage;

	private String firstName = "Harry";
	private String lastName = "Potter";
	private String user = "Harry";
	private String password = "Nimbus";

	private String noteTitle1 = "first note";
	private String noteText1 = "Du bist der, der schwach ist. Du wirst nie wissen, was Liebe ist. Oder Freundschaft. Und deswegen kannst du mir nur leidtun";

	private String noteTitle2 = "edited note";
	private String noteText2 = "Es sind nicht unsere Fähigkeiten, die zeigen wer wir sind, sondern unsere Entscheidungen";

	private String noteTitle3 = "another note";
	private String noteText3 = "Wenn du wissen willst, wie ein Mensch ist, dann sieh dir genau an wie er seine Untergebenen behandelt, nicht die Gleichrangigen";


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
	public void addNote() {

		homePageNotes = new PageObjectHomeNotes(driver);
		resultPage = new PageObjectResult(driver);
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		doSignUp();
		doLogin();

		driver.get("http://localhost:" + port + "/home");
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		homePageNotes.switchToNavNotesTab();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-note-button")));
		homePageNotes.clickAddNoteButtonNew();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
		homePageNotes.fillNoteDetailsAndSave(noteTitle1, noteText1);

		webDriverWait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Success", resultPage.getSuccessMessage());

		driver.get("http://localhost:" + this.port + "/home");
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		homePageNotes.switchToNavNotesTab();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-note-button")));
		String noteTitleOutput = homePageNotes.getNoteTitle();
		Assertions.assertEquals(noteTitle1, noteTitleOutput);

		String noteDescriptionOutput = homePageNotes.getNoteDescription();
		Assertions.assertEquals(noteText1, noteDescriptionOutput);
	}

	@Test
	@Order(5)
	public void editNote() {

		homePageNotes = new PageObjectHomeNotes(driver);
		resultPage = new PageObjectResult(driver);
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		doSignUp();
		doLogin();
		// doFirstNote();  ... first note already exists after test addNewNoteAndSave() has passed

		driver.get("http://localhost:" + port + "/home");
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		homePageNotes.switchToNavNotesTab();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-note-button")));
		homePageNotes.clickEditNoteButton();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
		homePageNotes.fillNoteDetailsAndSave(noteTitle2, noteText2);

		webDriverWait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Success", resultPage.getSuccessMessage());

		driver.get("http://localhost:" + this.port + "/home");
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		homePageNotes.switchToNavNotesTab();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-note-button")));
		String noteTitleOutput = homePageNotes.getNoteTitle();
		String noteDescriptionOutput = homePageNotes.getNoteDescription();

		// expectation is to see the changed note
		Assertions.assertEquals(noteTitle2, noteTitleOutput);
		Assertions.assertEquals(noteText2, noteDescriptionOutput);
	}

	@Test
	@Order(6)
	public void deleteNote() {

		homePageNotes = new PageObjectHomeNotes(driver);
		resultPage = new PageObjectResult(driver);
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		doSignUp();
		doLogin();
		// first note already exists after test addNote() has passed
		// first note has then been edited after editNote() has passed
		doAddSecondNote();

		driver.get("http://localhost:" + port + "/home");
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		homePageNotes.switchToNavNotesTab();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete-note-button")));
		homePageNotes.clickDeleteNoteButton();

		webDriverWait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Success", resultPage.getSuccessMessage());

		driver.get("http://localhost:" + this.port + "/home");
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		homePageNotes.switchToNavNotesTab();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-note-button")));
		String noteTitleOutput = homePageNotes.getNoteTitle();
		String noteDescriptionOutput = homePageNotes.getNoteDescription();

		// expectation is to see the second note, after the first has been (edited and) deleted
		Assertions.assertEquals(noteTitle3, noteTitleOutput);
		Assertions.assertEquals(noteText3, noteDescriptionOutput);
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

	private void doAddFirstNote() {
		homePageNotes = new PageObjectHomeNotes(driver);
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		doSignUp();
		doLogin();

		driver.get("http://localhost:" + port + "/home");
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		homePageNotes.switchToNavNotesTab();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-note-button")));
		homePageNotes.clickAddNoteButtonNew();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
		homePageNotes.fillNoteDetailsAndSave(noteTitle1, noteText1);
	}

	private void doAddSecondNote() {
		homePageNotes = new PageObjectHomeNotes(driver);
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		doSignUp();
		doLogin();

		driver.get("http://localhost:" + port + "/home");
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		homePageNotes.switchToNavNotesTab();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-note-button")));
		homePageNotes.clickAddNoteButtonNew();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
		homePageNotes.fillNoteDetailsAndSave(noteTitle3, noteText3);
	}

}
