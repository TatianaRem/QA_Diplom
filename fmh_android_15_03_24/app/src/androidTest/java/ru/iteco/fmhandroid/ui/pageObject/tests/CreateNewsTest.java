package ru.iteco.fmhandroid.ui.pageObject.tests;

import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.qameta.allure.kotlin.Description;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pageObject.Utils;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.AppBar;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.ControlPanelNews;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.CreateNews;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.EditNews;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.MainPage;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.NewsPage;

public class CreateNewsTest {
    AppBar appBar = new AppBar();
    MainPage mainPage = new MainPage();
    AuthorizationPage authorizationPage = new AuthorizationPage();
    ControlPanelNews controlPanelNews = new ControlPanelNews();
    CreateNews createNews = new CreateNews();
    EditNews editNews = new EditNews();
    NewsPage newsPage = new NewsPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        Espresso.onView(isRoot()).perform(Utils.waitDisplayed(appBar.getAppBarFragmentMain(), 5000));
        if (!mainPage.isDisplayedButtonProfile()) {
            authorizationPage.successfulAuthorization(Credentials.LOGIN, Credentials.PASSWORD);
        }
    }

    @Description("Успешное создание новости")
    @Test
    public void successfulNewsCreation() {
        appBar.switchToNews();
        newsPage.switchControlPanelNews();
        controlPanelNews.addNews();
        createNews.addCategory(TestData.NEWS_CATEGORY);
        createNews.addTitle(TestData.NEWS_TITLE);
        createNews.addDate(Utils.currentDate());
        createNews.addTime("20:00");
        createNews.addDescription(TestData.NEWS_DESCRIPTION);
        createNews.pressSave();
        controlPanelNews.searchNewsAndCheckIsDisplayed(TestData.NEWS_TITLE);
    }

    @Description("Создание новости с датой публикации в будущем")
    @Test
    public void shouldStayOnNewsCreationScreenWhenCreatingNewsInPast() {
        appBar.switchToNews();
        newsPage.switchControlPanelNews();
        controlPanelNews.addNews();
        createNews.addCategory(TestData.NEWS_CATEGORY);
        createNews.addTitle(TestData.NEWS_TITLE_FUTURE);
        String pastDate = Utils.dateMore1Years();
        createNews.addDate(pastDate);
        createNews.addTime("20:00");
        createNews.addDescription(TestData.NEWS_DESCRIPTION_FUTURE);
        createNews.pressSave();
        createNews.checkErrorDisplay(TestData.NEWS_PUBLICATION_ERROR);
    }

    @Description("Создание новости с пустыми полями")
    @Test
    public void shouldStayOnNewsCreationScreenWhenCreatingNewsWithEmptyFields() {
        appBar.switchToNews();
        newsPage.switchControlPanelNews();
        controlPanelNews.addNews();
        createNews.pressSave();
        createNews.verifyNewsCreationFormDisplayed();
    }
}
