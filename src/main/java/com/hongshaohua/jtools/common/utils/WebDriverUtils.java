package com.hongshaohua.jtools.common.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Aska on 2017/3/29.
 */
public class WebDriverUtils {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Object executeScript(WebDriver webDriver, String js) {
        JavascriptExecutor executor = (JavascriptExecutor)webDriver;
        return executor.executeScript(js);
    }

    public static Object executeScript(WebDriver webDriver, By by, String js) {
        WebElement webElement = webDriver.findElement(by);
        return executeScript(webDriver, webElement, js);
    }

    public static Object executeScript(WebDriver webDriver, WebElement webElement, String js) {
        JavascriptExecutor executor = (JavascriptExecutor)webDriver;
        return executor.executeScript(js, webElement);
    }

    public static WebElement findElement(WebDriver webDriver, By by) {
        List<WebElement> elms = webDriver.findElements(by);
        if(elms == null) {
            return null;
        }
        if(elms.isEmpty()) {
            return null;
        }
        return elms.get(0);
    }

    public static WebElement findElement(WebElement webElement, By by) {
        List<WebElement> elms = webElement.findElements(by);
        if(elms == null) {
            return null;
        }
        if(elms.isEmpty()) {
            return null;
        }
        return elms.get(0);
    }

    public static WebElement findElement4Finished(WebDriver webDriver, long periodMillis, int times, By by) {
        int curTimes = 0;
        while(true) {
            WebElement elm = findElement(webDriver, by);
            if(elm != null) {
                return elm;
            }
            curTimes++;
            if(times > 0 && curTimes >= times) {
                break;
            }
            sleep(periodMillis);
        }
        return null;
    }

    public static WebElement findElement4Finished(WebElement webElement, long periodMillis, int times, By by) {
        int curTimes = 0;
        while(true) {
            WebElement elm = findElement(webElement, by);
            if(elm != null) {
                return elm;
            }
            curTimes++;
            if(times > 0 && curTimes >= times) {
                break;
            }
            sleep(periodMillis);
        }
        return null;
    }

    public static WebElement findElement4Finished(WebDriver webDriver, long periodMillis, By by) {
        return findElement4Finished(webDriver, periodMillis, 0, by);
    }

    public static WebElement findElement4Finished(WebElement webElement, long periodMillis, By by) {
        return findElement4Finished(webElement, periodMillis, 0, by);
    }

    public static List<WebElement> findElements(WebDriver webDriver, By by) {
        return webDriver.findElements(by);
    }

    public static List<WebElement> findElements(WebElement webElement, By by) {
        return webElement.findElements(by);
    }

    //----------------特殊处理函数-------------------------

    public static void removeReadonly(WebDriver webDriver, By by) {
        executeScript(webDriver, by, "arguments[0].removeAttribute(\"readonly\")");
    }

    public static void removeReadonly(WebDriver webDriver, WebElement webElement) {
        executeScript(webDriver, webElement, "arguments[0].removeAttribute(\"readonly\")");
    }

    public static void hiddenElement(WebDriver webDriver, By by) {
        executeScript(webDriver, by, "arguments[0].style.display=\"none\"");
    }

    public static void hiddenElement(WebDriver webDriver, WebElement webElement) {
        executeScript(webDriver, webElement, "arguments[0].style.display=\"none\"");
    }

    public static void removeElement(WebDriver webDriver, By by) {
        executeScript(webDriver, by, "arguments[0].remove()");
    }

    public static void removeElement(WebDriver webDriver, WebElement webElement) {
        executeScript(webDriver, webElement, "arguments[0].remove()");
    }

    public static void setInputText(WebDriver webDriver, By by, String text) {
        executeScript(webDriver, by, "arguments[0].value=\"" + text + "\"");
    }

    public static void setInputText(WebDriver webDriver, WebElement webElement, String text) {
        executeScript(webDriver, webElement, "arguments[0].value=\"" + text + "\"");
    }

    public static String getRickText(WebDriver webDriver, By by) {
        return (String)executeScript(webDriver, by, "var result=arguments[0].innerHTML;return result");
    }

    public static String getRickText(WebDriver webDriver, WebElement webElement) {
        return (String)executeScript(webDriver, webElement, "var result=arguments[0].innerHTML;return result");
    }

    public static void setRickText(WebDriver webDriver, By by, String text) {
        executeScript(webDriver, by, "arguments[0].innerHTML = \"" + text + "\"");
    }

    public static void setRickText(WebDriver webDriver, WebElement webElement, String text) {
        executeScript(webDriver, webElement, "arguments[0].innerHTML = \"" + text + "\"");
    }

    public static void sendEvent(WebDriver webDriver, By by, String event) {
        String js =
                "var event;" +
                "if (document.createEvent) {" +
                    "event = document.createEvent(\"HTMLEvents\");" +
                    "event.initEvent(\"" + event + "\", true, false);" +
                    "arguments[0].dispatchEvent(event);" +
                "} else {" +
                    "arguments[0].fireEvent(\"on" + event + "\")" +
                "}";
        executeScript(webDriver, by, js);
    }

    public static void sendEvent(WebDriver webDriver, WebElement webElement, String event) {
        String js =
                "var event;" +
                        "if (document.createEvent) {" +
                        "event = document.createEvent(\"HTMLEvents\");" +
                        "event.initEvent(\"" + event + "\", true, false);" +
                        "arguments[0].dispatchEvent(event);" +
                        "} else {" +
                        "arguments[0].fireEvent(\"on" + event + "\")" +
                        "}";
        executeScript(webDriver, webElement, js);
    }
}
