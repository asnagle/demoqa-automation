package pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.demoqaBase;
import utils.demoqaLog;
import utils.dateUtils;
//import utils.splitDOB;

public class formsPage extends demoqaBase {

//	======= Using PageFactory of Selenium ======
	@FindBy(id = "firstName")
	WebElement firstName;

	@FindBy(id = "lastName")
	WebElement lastName;

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(xpath = "//*[@id=\"genterWrapper\"]/div[2]/div[1]/label")
	WebElement genderMale;

	@FindBy(xpath = "//*[@id=\"genterWrapper\"]/div[2]/div[2]/label")
	WebElement genderFemale;

	@FindBy(xpath = "//*[@id=\"genterWrapper\"]/div[2]/div[3]/label")
	WebElement genderOther;

	@FindBy(id = "userNumber")
	WebElement mobile;

	@FindBy(id = "dateOfBirthInput")
	WebElement datePicker;

	@FindBy(xpath = "/html/body/div[2]/div/div/div/div[2]/div[2]/form/div[5]/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div[4]/div[1]")
	WebElement dateofBirth;

	@FindBy(id = "subjectsInput")
	WebElement selectedSubject;

	@FindBy(xpath = "//div[@id='city']/div/div/div")
	WebElement city;

	private WebDriver driver;
	private By PracticeForm = By.cssSelector("div.element-list.collapse.show > ul.menu-list > #item-0 > span.text");
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

	public formsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void accessForms() {

		demoqaLog.info("Accessing Form Card...");
		homePage homePage = new homePage(driver);
		homePage.clickFormCard();
		clickPracticeForm();
	}
	
	public void clickPracticeForm() {
		demoqaLog.info("Clicking on Practice Form...");
		driver.findElement(PracticeForm).click();
		demoqaLog.info("Clicked on Practice Form...");

	}

//	================ Reading values from Excel file and updating form fields ==============

	public void entFirstName(String firstNameText) {
		demoqaLog.info("Entering First Name...");
		firstName.clear();
		firstName.sendKeys(firstNameText);
		demoqaLog.info("Entered First Name: " + firstNameText);
	}

	public String getFirstNameFromForm() {
		return firstName.getAttribute("value").trim();
	}

	public void entLastName(String lastNameText) {
		lastName.clear();
		lastName.sendKeys(lastNameText);
	}

	public String getLastNameFromForm() {
		return lastName.getAttribute("value").trim();
	}

	public void entEmail(String email) {
		userEmail.clear();
		userEmail.sendKeys(email);
	}

	public String getEmailFromForm() {
		return userEmail.getAttribute("value").trim();
	}

	public void selectGender(String genderText) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement genderInput = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//input[@name='gender' and @value='" + genderText + "']")));
		String inputId = genderInput.getAttribute("id");

		WebElement genderLabel = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("label[for='" + inputId + "']")));

		// Scroll into view & use JS click to avoid iframe issue
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", genderLabel);
		js.executeScript("arguments[0].click();", genderLabel);

		System.out.println("Selected gender: " + genderText);
	}

	public void entMobileNo(String mobileNumber) {
		mobile.clear();
		if (mobileNumber.length() != 10) {
			System.out.println("Invalid mobile number: " + mobileNumber);
			return;
		}
		mobile.sendKeys(mobileNumber);
	}

	public String getMobileNoFromForm() {
		return mobile.getAttribute("value").trim();
	}

	public void entDob(String dobStr) {
		demoqaLog.info("Entering Date of Birth: " + dobStr);
		System.out.println("Parsed DOB from Excel: " + dobStr);
//		 ✅ Step 1: Validate Format BEFORE splitting
		if (!dateUtils.isValidDobFormat(dobStr)) {
			throw new RuntimeException("Invalid DOB format. Expected dd-MM-yyyy but got: " + dobStr);
		}

//		 ✅ Step 2: Now it's safe to split
		String[] parts = dobStr.split("-");
		String day = parts[0];
		String month = dateUtils.getMonthName(parts[1]);
		String year = parts[2];

		// ✅ Step 3: Proceed with calendar interaction
		datePicker.click();

		new Select(driver.findElement(By.className("react-datepicker__month-select"))).selectByVisibleText(month);

		new Select(driver.findElement(By.className("react-datepicker__year-select"))).selectByVisibleText(year);

		String paddedDay = String.format("%02d", Integer.parseInt(day));
		String daySelector = String.format("div.react-datepicker__day--0%s", paddedDay);
		WebElement dayElement = driver.findElement(By.cssSelector(daySelector));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", dayElement);
	}

	public String getDobFromForm() {
		return datePicker.getAttribute("value").trim();
	}

	public void enterSubject(String subject) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement subjectInput = driver.findElement(By.id("subjectsInput"));

		subject = subject.trim();

//		 Type the full subject
		subjectInput.sendKeys(subject);
		demoqaLog.info("Typed full subject: " + subject);
		System.out.println("Typing subject: " + subject);

//		 Wait for the matching suggestion to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".subjects-auto-complete__option")));

		List<WebElement> suggestions = driver.findElements(By.cssSelector(".subjects-auto-complete__option"));

		boolean found = false;

		for (WebElement option : suggestions) {
			String text = option.getText().trim();
			System.out.println("Dropdown Option: " + text);
			if (text.equalsIgnoreCase(subject)) {
				option.click();
				found = true;
				System.out.println("Selected: " + text);
				break;
			}
		}

		if (!found) {
			System.err.println("Subject not found in dropdown: " + subject);
		}
	}

	public void selectHobbies(String hobbies) {
		String[] selectedHobbies = hobbies.split(",");

		for (String hobby : selectedHobbies) {
			hobby = hobby.trim().toLowerCase();
			String checkboxId = "";

			switch (hobby) {
			case "sports":
				checkboxId = "hobbies-checkbox-1";
				break;
			case "reading":
				checkboxId = "hobbies-checkbox-2";
				break;
			case "music":
				checkboxId = "hobbies-checkbox-3";
				break;
			default:
				System.out.println("Unknown hobby: " + hobby);
				continue;
			}

			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				By labelBy = By.cssSelector("label[for='" + checkboxId + "']");
				wait.until(ExpectedConditions.elementToBeClickable(labelBy));

				WebElement label = driver.findElement(labelBy);
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", label);
				Thread.sleep(300);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", label);

				System.out.println("Selected hobby: " + hobby);
			} catch (Exception e) {
				System.err.println("Failed to select hobby: " + hobby);
				e.printStackTrace();
			}
		}
	}

	public void uploadPicture(String picturePath) {

		try {
			if (picturePath == null || picturePath.trim().isEmpty()) {
				System.err.println("Photo path is empty in test data.");
				return;
			}

			String fullPath = System.getProperty("user.dir") + "/" + picturePath;

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("uploadPicture")));

			fileInput.sendKeys(fullPath);
			System.out.println("Uploaded photo: " + fullPath);
		} catch (Exception e) {
			System.err.println("Photo upload failed.");
			e.printStackTrace();
		}

	}

	public void enterAddress(String address) {

		address = address.replace("\\n", "\n");
		System.out.println(address);
		demoqaLog.info("Entering Address...");
		driver.findElement(By.id("currentAddress")).clear();
		driver.findElement(By.id("currentAddress")).sendKeys(address);
		demoqaLog.info("Entered Address...");
	}

	public void selectState(String state) {
		demoqaLog.info("Selecting State: " + state);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		try {
//			 1. Scroll into view of the state container
			WebElement stateContainer = driver.findElement(By.id("state"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", stateContainer);

//			 2. Click to open the state dropdown
			WebElement dropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#state .css-1wa3eu0-placeholder")));
			dropdown.click();

//			 3. Wait for dropdown options to load
			List<WebElement> options = wait.until(ExpectedConditions
					.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@id,'react-select-3-option-')]")));

			boolean matched = false;
			for (WebElement option : options) {
				String optionText = option.getText().trim();
				System.out.println("Available state option: " + optionText);
				if (optionText.equalsIgnoreCase(state.trim())) {
					option.click();
					demoqaLog.info("Selected State: " + optionText);
					matched = true;
					break;
				}
			}

			if (!matched) {
				demoqaLog.error("State not found: " + state);
				throw new NoSuchElementException("State option not found: " + state);
			}

//			 4. Wait for City dropdown to update
			wait.until(ExpectedConditions
					.textToBePresentInElementLocated(By.cssSelector("#city .css-1wa3eu0-placeholder"), "Select City"));

		} catch (Exception e) {
			demoqaLog.error("Error selecting state: " + state);
			throw e;
		}
	}

	public void selectCity(String city) {

		demoqaLog.info("Selecting City: " + city);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		try {
//	         1. Wait for city dropdown to become clickable and click to open it
			WebElement cityDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("city")));
			cityDropdown.click();

//	         2. Wait until "Select City" appears (ensures dropdown is loaded)
			wait.until(ExpectedConditions
					.textToBePresentInElementLocated(By.cssSelector("#city .css-1wa3eu0-placeholder"), "Select City"));

//	         3. Scroll to the city container
			WebElement cityContainer = driver.findElement(By.cssSelector("#city .css-1wa3eu0-placeholder"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cityContainer);

//	         4. Get all city options (these change based on selected state)
			List<WebElement> options = wait.until(ExpectedConditions
					.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@id,'react-select-4-option-')]")));

			boolean matched = false;
			for (WebElement option : options) {
				String optionText = option.getText().trim();
				System.out.println("Available city: " + optionText);
				if (optionText.equalsIgnoreCase(city.trim())) {
					option.click();
					demoqaLog.info("Selected City: " + optionText);
					matched = true;
					break;
				}
			}

			if (!matched) {
				demoqaLog.error("City not found in dropdown: " + city);
				throw new NoSuchElementException("City option not found: " + city);
			}

		} catch (Exception e) {
			demoqaLog.error("Error selecting city: " + city);
			e.printStackTrace();
			throw e;
		}
	}

	public void submitButton() {
		demoqaLog.info("Submitting Form...");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));

		try {
//	         Scroll into view
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
			Thread.sleep(500); // small pause

//	         Try normal click first
			btn.click();
			demoqaLog.info("Form Submitted via normal click...");
		} catch (Exception e) {
//	         Fallback to JS click
			demoqaLog.warn("Normal click failed. Trying JS click...");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
			demoqaLog.info("Form Submitted via JS click...");
		}
	}

}
