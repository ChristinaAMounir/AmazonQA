package org.example;



import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\christina.adel\\Downloads\\driver\\chromedriver-win64\\chromedriver.exe");
        System.out.println("Choose scenario 1 2 3 4: ");
        int n = scanner.nextInt();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        if(n == 1){
            scenario1 s1 = new scenario1(driver);
        } else if (n == 2) {
            scenario2 s2 = new scenario2(driver);
        } else if ( n == 3) {
            scenario3 s3 = new scenario3(driver);
        } else if ( n == 4) {
            scenario4 s4 = new scenario4(driver);
        }

    }
}
