package com.sokol;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sokol.util.PropertiesUtil.getProperty;

public class LaunchChrome {

    private static final Logger log = LoggerFactory.getLogger(LaunchChrome.class);

    public static void main(String[] args) {
        String url = getProperty("url");

        try (ChromeDriverService service = new ChromeDriverService.Builder()
                .withLogOutput(System.out)
                .build();) {

            ChromeDriver driver = new ChromeDriver(service);

            driver.get(url);
            log.info("Title: {}", driver.getTitle());

            driver.quit();
        }
    }
}
