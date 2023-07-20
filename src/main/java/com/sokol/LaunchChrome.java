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

            processingHtml(driver);

        } finally {
            if (driver != null) {
                driver.quit();
            }
            if (service != null) {
                service.stop();
            }
        }
    }

    private static void processingHtml(ChromeDriver driver) {
        WebElement scrollContent = driver.findElement(By.className("scroll__container"));

        List<WebElement> searchSnippets = scrollAndGetAllSearchSnippets(driver, scrollContent);
        log.info("Snippets: {}", searchSnippets.size());

        List<SearchSnippet> snippets = new ArrayList<>();
        int i = 0;

        for (WebElement snippet : searchSnippets) {
            SearchSnippet searchSnippet = new SearchSnippet();

            WebElement divViewBody = snippet.findElement(By.cssSelector("div.search-snippet-view__body"));
            log.info("divViewBody: {}", divViewBody);
            searchSnippet.setDataId(divViewBody.getAttribute("data-id"));
            searchSnippet.setDataCoordinates(divViewBody.getAttribute("data-coordinates"));

            WebElement link = snippet.findElement(By.className("search-snippet-view__link-overlay"));
            log.info("link: {}", link);
            searchSnippet.setHref(link.getAttribute("href"));

            WebElement titleElement = snippet.findElement(By.className("search-business-snippet-view__title"));
            log.info("titleElement: {}", titleElement);
            searchSnippet.setTitle(titleElement.getText());

            WebElement addressElement = findFirstElementByClassOrElseNull(snippet,
                    "search-business-snippet-view__address");
            if (addressElement != null) {
                log.info("addressElement: {}", addressElement);
                searchSnippet.setAddress(addressElement.getText());
            }

            WebElement workingStatusElement = findFirstElementByClassOrElseNull(snippet,
                    "business-working-status-view");
            if (workingStatusElement != null) {
                log.info("workingStatusElement: {}", workingStatusElement);
                searchSnippet.setWorkingStatus(workingStatusElement.getText());
            }

            i += 1;
            log.info("searchSnippet {}: {}", i, searchSnippet);

            snippets.add(searchSnippet);
        }

        parseLinks(driver, snippets);
    }

    private static void parseLinks(ChromeDriver driver, List<SearchSnippet> snippets) {
        int i = 0;
        for (SearchSnippet snippet : snippets) {
            log.info("snippet {}: {}", i, snippet);
            String href = snippet.getHref();
            log.info("href: {}", href);
            if (href != null) {
                driver.get(href);
                sleep(500);

                WebElement headerElement = driver.findElement(By.className("orgpage-header-view__header"));
                log.info("headerElement: {}", headerElement);
                snippet.setTitle2(headerElement.getText());

                WebElement phoneNumberElement = findFirstElementByClassOrElseNull(driver,
                        "orgpage-phones-view__phone-number");
                if (phoneNumberElement != null) {
                    log.info("phoneNumberElement: {}", phoneNumberElement);
                    snippet.setPhoneNumber(phoneNumberElement.getText());
                }

                List<WebElement> elements = driver.findElements(By.cssSelector(
                        ".tabs-select-view__title._name_gallery, .tabs-select-view__title._name_reviews"));

                if (!elements.isEmpty()) {
                    WebElement counterElement1 = elements.get(0).findElement(By.className("tabs-select-view__counter"));
                    log.info("counterElement1: {}", counterElement1);
                    snippet.setPictureCount(Integer.parseInt(counterElement1.getText().trim()));

                    WebElement counterElement2 = elements.get(0).findElement(By.className("tabs-select-view__counter"));
                    log.info("counterElement2: {}", counterElement2);
                    snippet.setReviewCount(Integer.parseInt(counterElement2.getText().trim()));
                }

                WebElement urlElement = driver.findElement(By.className("business-urls-view__text"));
                log.info("urlElement: {}", urlElement);
                snippet.setBusinessUrl(urlElement.getText());
            }

            i += 1;
            log.info("snippet {}: {}", i, snippet);
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

    private static WebElement findFirstElementByClassOrElseNull(Object element, String className) {
        List<WebElement> elems = new ArrayList<>();

        if (element instanceof WebElement) {
            elems = ((WebElement) element).findElements(By.className(className));
        } else if (element instanceof ChromeDriver) {
            elems = ((ChromeDriver) element).findElements(By.className(className));
        }

        if (!elems.isEmpty()) {
            return elems.get(0);
        } else {
            return null;
        }
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
