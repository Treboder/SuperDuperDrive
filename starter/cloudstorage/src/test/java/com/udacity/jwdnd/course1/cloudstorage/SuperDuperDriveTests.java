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
	private PageObjectHomeFiles homePageFiles;
	private PageObjectHomeCredentials homePageCredentials;
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

	private String filePath1 = System.getProperty("user.dir");
	private String fileName1 = "README.md";

	private String credentialUrl1 = "website.com";
	private String credentialUser1 = "user";
	private String credentialPassword1 = "pwd";

	private String credentialUrl2 = "website.de";
	private String credentialUser2 = "sunny76";
	private String credentialPassword2 = "123";

	private String credentialUrl3 = "website.info";
	private String credentialUser3 = "incognito";
	private String credentialPassword3 = "forgotten";

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
		String noteDescriptionOutput = homePageNotes.getNoteDescription();

		// expectation is to see the first note
		Assertions.assertEquals(noteTitle1, noteTitleOutput);
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
		// ... first note already exists after test addNote() has passed

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

		// expectation is to see the third note in the first place, after the first has been (edited and) deleted
		Assertions.assertEquals(noteTitle3, noteTitleOutput);
		Assertions.assertEquals(noteText3, noteDescriptionOutput);
	}

	@Test
	@Order(7)
	public void addFileSuccess() {

		final String dir = System.getProperty("user.dir");
		System.out.println("current dir = " + dir);

		homePageFiles = new PageObjectHomeFiles(driver);
		resultPage = new PageObjectResult(driver);
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		doSignUp();
		doLogin();

		driver.get("http://localhost:" + port + "/home");
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		homePageFiles.chooseFileAndClickUploadButton(filePath1 + "\\" + fileName1);

		webDriverWait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Success", resultPage.getSuccessMessage());

		driver.get("http://localhost:" + this.port + "/home");
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		String fileTitleOutput = homePageFiles.getFileName();

		// expectation is to see the first file, which is README.md in this test case
		Assertions.assertEquals(fileName1, fileTitleOutput);
	}


	@Test
	@Order(8)
	public void addFileFailure() {

		final String dir = System.getProperty("user.dir");
		System.out.println("current dir = " + dir);

		homePageFiles = new PageObjectHomeFiles(driver);
		resultPage = new PageObjectResult(driver);
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		doSignUp();
		doLogin();

		driver.get("http://localhost:" + port + "/home");
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		homePageFiles.chooseFileAndClickUploadButton(filePath1 + "\\" + fileName1);

		webDriverWait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Error", resultPage.getErrorMessage());

		driver.get("http://localhost:" + this.port + "/home");
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		String fileTitleOutput = homePageFiles.getFileName();

		// expectation is to see the first file, which is README.md in this test case
		Assertions.assertEquals(fileName1, fileTitleOutput);

		// ToDo: check second row to test that no second file with the same name exists
	}

	@Test
	@Order(9)
	public void addCredential() {
		homePageCredentials = new PageObjectHomeCredentials(driver);
		resultPage = new PageObjectResult(driver);
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		doSignUp();
		doLogin();

		driver.get("http://localhost:" + port + "/home");
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		homePageCredentials.switchToNavCredentialsTab();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-credential-button")));
		homePageCredentials.clickAddCredentialButtonNew();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
		homePageCredentials.fillCredentialDetailsAndSave(credentialUrl1, credentialUser1, credentialPassword1);

		webDriverWait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Success", resultPage.getSuccessMessage());

		driver.get("http://localhost:" + this.port + "/home");
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		homePageCredentials.switchToNavCredentialsTab();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-credential-button")));
		String credentialUrlOutput = homePageCredentials.getCredentialUrl();
		String credentialUserOutput = homePageCredentials.getCredentialUsername();
		String credentialPwdOutput = homePageCredentials.getCredentialPassword();

		// expectation is to see the first note
		Assertions.assertEquals(credentialUrl1, credentialUrlOutput);
		Assertions.assertEquals(credentialUser1, credentialUserOutput);
		Assertions.assertEquals(credentialPassword1, credentialPwdOutput);
	}

	@Test
	@Order(9)
	public void editCredential() {
		homePageCredentials = new PageObjectHomeCredentials(driver);
		resultPage = new PageObjectResult(driver);
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		doSignUp();
		doLogin();
		// ... first note already exists after test addCredential() has passed

		driver.get("http://localhost:" + port + "/home");
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		homePageCredentials.switchToNavCredentialsTab();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-credential-button")));
		homePageCredentials.clickEditCredentialButton();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
		homePageCredentials.fillCredentialDetailsAndSave(credentialUrl2, credentialUser2, credentialPassword2);

		webDriverWait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Success", resultPage.getSuccessMessage());

		driver.get("http://localhost:" + this.port + "/home");
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		homePageCredentials.switchToNavCredentialsTab();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-credential-button")));
		String credentialUrlOutput = homePageCredentials.getCredentialUrl();
		String credentialUserOutput = homePageCredentials.getCredentialUsername();
		String credentialPwdOutput = homePageCredentials.getCredentialPassword();

		// expectation is to see the changed credential
		Assertions.assertEquals(credentialUrl2, credentialUrlOutput);
		Assertions.assertEquals(credentialUser2, credentialUserOutput);
		Assertions.assertEquals(credentialPassword2, credentialPwdOutput);
	}

	@Test
	@Order(9)
	public void deleteCredential() {
		homePageCredentials = new PageObjectHomeCredentials(driver);
		resultPage = new PageObjectResult(driver);
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		doSignUp();
		doLogin();
		// first credential already exists after test addCredential() has passed
		// first note has then been edited after editCredential() has passed
		doAddSecondCredential();

		driver.get("http://localhost:" + port + "/home");
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		homePageCredentials.switchToNavCredentialsTab();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete-credential-button")));
		homePageCredentials.clickDeleteCredentialButton();

		webDriverWait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Success", resultPage.getSuccessMessage());

		driver.get("http://localhost:" + this.port + "/home");
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		homePageCredentials.switchToNavCredentialsTab(); ;

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-credential-button")));
		String credentialUrlOutput = homePageCredentials.getCredentialUrl();
		String credentialUserOutput = homePageCredentials.getCredentialUsername();
		String credentialPwdOutput = homePageCredentials.getCredentialPassword();

		// expectation is to see the third credential in the first place, after the first has been (edited and) deleted
		Assertions.assertEquals(credentialUrl3, credentialUrlOutput);
		Assertions.assertEquals(credentialUser3, credentialUserOutput);
		Assertions.assertEquals(credentialPassword3, credentialPwdOutput);
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

	private void doAddSecondCredential() {
		homePageCredentials = new PageObjectHomeCredentials(driver);
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		doSignUp();
		doLogin();

		driver.get("http://localhost:" + port + "/home");
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		homePageCredentials.switchToNavCredentialsTab();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-credential-button")));
		homePageCredentials.clickAddCredentialButtonNew();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
		homePageCredentials.fillCredentialDetailsAndSave(credentialUrl3, credentialUser3, credentialPassword3);
	}

}
