package pages;

import enums.GoodsCategories;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ComputersPage extends BasePage {

    String notebooks = "//a[contains(text(), '%s')]/parent::div";

    public ComputersPage(){
        waitForLoad(allCategoryButtonLocator);
    }

    public void chooseCategory(GoodsCategories category){
        WebElement notebooksElement = driver.findElement(By.xpath(String.format(notebooks, category.getTextValue())));
        waitForVisibility(notebooksElement);
        click(notebooksElement);
    }
}
