package steam.tests;

import io.qameta.allure.Step;
import framework.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steam.pageObject.pages.*;

@Log4j2
public class SteamTest extends BaseTest {

    @Parameters({"language", "year"})
    @Step("Chose game with max discount and download SteamSetup file")
    @Test(description = "Chose game with max discount and download SteamSetup file")
    public void steamTest(String language, String year) {

        MainPage mainPage = new MainPage();
        mainPage.getHeader().selectLanguage(language);
        mainPage.getMainMenu().navigateSection("categories_key", "action_key");

        ActionPage actionPage = new ActionPage();
        actionPage.selectGameWithMaxDiscount();

        String page = browser.getLocation().split("/")[3];
        log.info(page);
        String namePage = "app";
        if (!page.equals(namePage)) {
            log.info("Age page was opened");
            AgePage agePage = new AgePage();
            agePage.checkAge(year);
            GamePage gamePage = new GamePage();
            gamePage.checkCurrentGame();
            gamePage.getHeader().clickOnInstallSteam();
        } else {
            log.info("Game page was opened");
            GamePage gamePage = new GamePage();
            gamePage.checkCurrentGame();
            gamePage.getHeader().clickOnInstallSteam();
        }

        DownloadPage downloadPage = new DownloadPage();
        downloadPage.downloadSteam();
        downloadPage.deleteFile();
    }
}