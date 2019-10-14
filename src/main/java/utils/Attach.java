package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static steps.BaseSteps.getDriver;

public class Attach {

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] makeScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Text file", type = "txt")
    public static byte[] getTextFiles(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get(resourceName));
    }
}
