package com.sokol;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.sokol.util.PropertiesUtil.getProperty;

public class LaunchChrome {

    private static final Logger log = LoggerFactory.getLogger(LaunchChrome.class);

    public static void main(String[] args) {
        String url = getProperty("url");

        ChromeDriverService service = null;
        ChromeDriver driver = null;

        try {
            service = new ChromeDriverService.Builder()
                    .withLogOutput(System.out)
                    .build();

            driver = new ChromeDriver(service);

            driver.get(url);
            log.info("Title: {}", driver.getTitle());

            WebElement scrollContent = driver.findElement(By.className("scroll__container"));

            List<WebElement> searchSnippets = scrollAndGetAllSearchSnippets(driver, scrollContent);
            log.info("Snippets: {}", searchSnippets.size());

        } finally {
            if (driver != null) {
                driver.quit();
            }
            if (service != null) {
                service.stop();
            }
        }
    }

    private static void scrollToEnd(ChromeDriver driver, WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollTo(0, arguments[0].scrollHeight)", element);
    }

    private static List<WebElement> scrollAndGetAllSearchSnippets(ChromeDriver driver, WebElement scrollContent) {
        List<WebElement> searchSnippets = new ArrayList<>();

        while (true) {
            scrollToEnd(driver, scrollContent);
            sleep(1000);

            List<WebElement> currentSearchSnippets = scrollContent.findElements(By.className("search-snippet-view"));
            log.info("currentSearchSnippets: {}", currentSearchSnippets.size());

            if (currentSearchSnippets.size() == searchSnippets.size()) {
                break;
            }

            searchSnippets = new ArrayList<>(currentSearchSnippets);
        }

        return searchSnippets;
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
