package steam.pages;

import framework.elements.Button;
import framework.elements.Label;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.io.File;

import static framework.PropertyReader.getProperty;
import static org.testng.Assert.assertTrue;

public class DownloadPage extends BaseSteamPage {
    private static String pageLocator = "//div[@class='steam_logo']";

    private Label lblSteam = new Label(By.xpath("//div[@class='about_subtitle']"));
    private Button btnInstallSteam = new Button(By.xpath("//div[@id='about_greeting']//a[@class='about_install_steam_link']"));

    public DownloadPage() {
        super(By.xpath(pageLocator), "DownloadPage");
    }
    @Step("Click on Install Steam button on the Header")
    public void clickOnInstallSteam() {
        getHeader().clickOnInstallSteam();
        lblSteam.isDisplayed();
    }
    @Step("Click on Install Steam button on the Download page and download SteamSetup file")
    public void downloadSteam() throws InterruptedException {
        btnInstallSteam.click();
        Thread.sleep(10000);
        String filePath = System.getProperty("user.dir") + getProperty("filePath");
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        boolean found = false;
        File file = null;

        for (File fileItem : listOfFiles) {
            if (fileItem.isFile()) {
                String fileName = fileItem.getName();
                System.out.println("File " + fileItem.getName());
                if (fileName.matches(fileName)) {
                    file = new File(fileName);
                    found = true;
                }
            }
        }
        assertTrue(found, "Загруженный документ не найден");
    }
}
