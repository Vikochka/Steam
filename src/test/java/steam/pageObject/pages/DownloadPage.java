package steam.pageObject.pages;

import framework.Browser;
import framework.elements.Button;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static framework.PropertyReader.getIntProperty;
import static framework.PropertyReader.getProperty;
import static org.testng.Assert.assertTrue;

public class DownloadPage extends BaseSteamPage {
    private static String pageLocator = "//div[@class='steam_logo']";

    private Button btnInstallSteam = new Button(By.xpath("//div[@id='about_greeting']//a[@class='about_install_steam_link']"));

    public DownloadPage() {
        super(By.xpath(pageLocator), "Download page");
    }

    public void downloadSteam() {
        System.out.println("Start install steam");

        String filePath = System.getProperty("user.dir") + getProperty("filePath");

        FluentWait<Browser> wait = new FluentWait<>(browser)
                .withTimeout(Duration.ofMillis(3000))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(Exception.class);
        btnInstallSteam.click();
        wait.until((x) -> {
            File[] files = new File(filePath).listFiles();
            for (File file : files) {
                if (file.getName().contains(getProperty("fileSetup"))) {
                    return true;
                }
            }
            return false;
        });
        System.out.println("Finish install steam");
    }


    public void deleteFile() {
        File file = new File(getProperty("pathtodelete")+getProperty("fileSetup"));
        if (file.delete()) {
            System.out.println("File deleted");
        } else {
            System.out.println("File didn't delete");
        }
    }
}
