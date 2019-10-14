package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ComputersPage extends BasePage {

    String notebooks = "//a[contains(text(), '%s')]/parent::div";

    public ComputersPage(){
        waitForVisibility(allCategoryButton);
    }

    public void chooseCategory(String category){
        WebElement notebooksElement = driver.findElement(By.xpath(String.format(notebooks, category)));
        waitForVisibility(notebooksElement);
        click(notebooksElement);
    }
}
