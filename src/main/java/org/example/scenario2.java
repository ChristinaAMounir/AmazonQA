package org.example;

import org.openqa.selenium.*;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class scenario2 {
    public scenario2(WebDriver driver) {


        try {
            // Navigate to Amazon's homepage
            driver.get("https://www.amazon.com");
            Thread.sleep(20000); // Wait for the page to load

            // Click on "Today's Deals"
            WebElement todaysDealsLink = driver.findElement(By.linkText("Today's Deals"));
            todaysDealsLink.click();

            // Initialize JavaScript Executor
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Wait for "See more" button and click it
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement seeMoreButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.a-size-base.a-link-normal[aria-labelledby='see-more-departments-label']")));
            js.executeScript("arguments[0].scrollIntoView(true);", seeMoreButton);
            seeMoreButton.click();

            // Locate the "Software" department radio button using CSS selector
            WebElement softwareRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-testid='filter-departments-493964']")));
            js.executeScript("arguments[0].scrollIntoView(true);", softwareRadioButton);

            // Click the "Software" radio button using JavaScript (if a regular click doesn't work)
            try {
                softwareRadioButton.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", softwareRadioButton);
            }

            // Verify that the filter "Electronics" is applied (adjust the text if "Software" is more appropriate)
            WebElement filterLabel = driver.findElement(By.xpath("//span[contains(@class, 'a-size-base a-color-base') and contains(text(), 'Electronics')]"));

            if (filterLabel.isDisplayed()) {
                System.out.println("Test Passed: The 'Electronics' filter is applied.");
            } else {
                System.out.println("Test Failed: The 'Electronics' filter is not applied.");
            }

        } catch (NoSuchElementException e) {
            System.out.println("Test Failed: An expected element was not found.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
