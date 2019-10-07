package pages;

import org.openqa.selenium.By;

public class ComputersPage extends BasePage {

    String notebooks = "//a[contains(text(), '%s')]/parent::div";

    public ComputersPage(){
        waitForVisibility(allCategoryButton);
    }

    public void chooseCategory(String category){
        waitForVisibility(driver.findElement(By.xpath(String.format(notebooks, category))));
        driver.findElement(By.xpath(String.format(notebooks, category))).click();
    }
}
