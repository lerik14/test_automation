package pages;

import core.utils.ItemUtils;
import core.utils.WebDriverUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.ParseException;
import java.util.List;

public class SearchResultPage extends BasePage {

    @FindBy(xpath = "//span[@class='a-size-medium a-color-base a-text-normal']")
    private List<WebElement> listOfElementsInListView;

    @FindBy(xpath = "//span[@class='a-size-base-plus a-color-base a-text-normal']")
    private List<WebElement> listOfElementsInGridView;

    @FindBy(xpath = "//*[@id='low-price']")
    private WebElement minPriceInput;

    @FindBy(xpath = "//form[@method='get']//input[@type='submit']")
    private WebElement acceptPriceRangeButton;

    @FindBy(xpath = "//span[starts-with(@cel_widget_id, 'MAIN-SEARCH_RESULTS')]//span[@class='a-price-whole']")
    private List<WebElement> listOfElementsPrices;

    public SearchResultPage() {
        super();
    }

    @Step
    public List<String> getTitlesFromListView() {
        return WebDriverUtils.getTextForElementsList(listOfElementsInListView);
    }

    @Step
    public Boolean isElemSponsored(String title) {
        String locatorForSearch = String.format("//span[starts-with(@cel_widget_id, 'MAIN-SEARCH_RESULTS') and " +
                ".//span[contains(text(),'%s')]]//*[text()='Sponsored']",title);
        return WebDriverUtils.doesElementExist(driver, locatorForSearch);
    }

    @Step
    public void setMinPrice(int minPrice) {
        minPriceInput.sendKeys(String.valueOf(minPrice));
        acceptPriceRangeButton.click();
    }

    @Step
    public int getElemPrice(int index){
       return (int) ItemUtils.parseItemPrice(listOfElementsPrices.get(index).getText());
    }

    @Step
    public void clickOnItemInGridView(int index) {
        listOfElementsInGridView.get(index).click();
    }
}
