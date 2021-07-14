package steam.pageObject.pages;

import framework.Browser;
import framework.elements.Button;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.time.Duration;

import static framework.PropertyReader.getProperty;

@Log4j2
public class DownloadPage extends BaseSteamPage {
    private static String pageLocator = "//div[@class='steam_logo']";

    private Button btnInstallSteam = new Button(By.xpath("//div[@id='about_greeting']//a[@class='about_install_steam_link']"));

    public DownloadPage() {
        super(By.xpath(pageLocator), "Download page");
    }

    public void downloadSteam() {
        log.info("Start install Steam");

        String filePath = System.getProperty("user.dir") + getProperty("filePath");

        FluentWait<Browser> wait = new FluentWait<>(browser)
                .withTimeout(Duration.ofMillis(20000))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(Exception.class);
        btnInstallSteam.click();
        wait.until((x) -> {
            File[] files = new File(filePath).listFiles();
            for (File file : files) {
                if (file.getName().contains(getProperty("fileSetup"))) {
                    log.info("File appeared in the folder: " + filePath);
                    return true;
                }
            }
            return false;
        });
        log.info("Finish install steam");
    }

    public void deleteFile() {
        File file = new File(getProperty("pathtodelete") + getProperty("fileSetup"));
        if (file.delete()) {
            log.info("File deleted");
        } else {
            log.info("File didn't delete");
        }
    }
}
