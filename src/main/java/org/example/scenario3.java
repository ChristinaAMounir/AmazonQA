package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class scenario3 {
    public scenario3(WebDriver driver) {

        try {
            // Navigate to Amazon's homepage
            driver.get("https://www.amazon.com");
            Thread.sleep(20000);
            // Locate the "Choose a language for shopping" element by ID and click on it
            WebElement changeLanguagebutton = driver.findElement(By.id("icp-nav-flyout"));
            changeLanguagebutton.click();
            Thread.sleep(3000);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement currencyDropdown = driver.findElement(By.cssSelector(".a-dropdown-container"));
            js.executeScript("arguments[0].scrollIntoView(true);", currencyDropdown);

            // Open the dropdown by clicking on it
            currencyDropdown.click();
            Thread.sleep(2000); // Wait for the dropdown to open

            // Select AED (U.A.E. Dirham) from the dropdown by clicking it
            WebElement aedOption = driver.findElement(By.id("icp-currency-dropdown_23"));
            aedOption.click();
            Thread.sleep(2000); // Wait for the selection to take effect


            // Click the "Save Changes" button
            WebElement saveChangesButton = driver.findElement(By.id("icp-save-button"));
            saveChangesButton.click();

            // Wait for changes to take effect and verify
            Thread.sleep(5000); // Wait for the changes to be saved
            System.out.println("Currency successfully changed to AED.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
