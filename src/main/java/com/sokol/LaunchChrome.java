package com.sokol;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.sokol.util.PropertiesUtil.getProperty;

public class LaunchChrome {

    public static void main(String[] args) {
        String url = getProperty("url");
        System.out.println(url);


//        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
//        WebDriver driver = new ChromeDriver();
//
//        driver.get("https://www.google.com");
//        System.out.println(driver.getTitle());
//
//        driver.quit();


    }
}
