package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static steps.BaseSteps.getDriver;

public class Attach {

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] makeScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
