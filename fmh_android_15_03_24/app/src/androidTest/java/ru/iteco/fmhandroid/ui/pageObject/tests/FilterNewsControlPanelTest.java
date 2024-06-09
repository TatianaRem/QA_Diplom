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
import ru.iteco.fmhandroid.ui.pageObject.pageObject.FilterNews;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.MainPage;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.NewsPage;

public class FilterNewsControlPanelTest {
    AppBar appBar = new AppBar();
    FilterNews filterNews = new FilterNews();
    NewsPage newsPage = new NewsPage();
    MainPage mainPage = new MainPage();
    AuthorizationPage authorizationPage = new AuthorizationPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        Espresso.onView(isRoot()).perform(Utils.waitDisplayed(appBar.getAppBarFragmentMain(), 5000));
        if (!mainPage.isDisplayedButtonProfile()) {
            authorizationPage.successfulAuthorization();
        }
    }

    @Description("Фильтрация новостей по корректным данным с установленными чек-боксами")
    @Test
    public void filterNewsByValidDateAndCheckBoxes() {
        appBar.switchToNews();
        newsPage.switchControlPanelNews();
        newsPage.openFormFilterNews();
        filterNews.addCategoryFilter("Зарплата");
        filterNews.setDateFromFilter(Utils.currentDate());
        filterNews.setDateToFilter(Utils.currentDate());
        filterNews.confirmFilter();
        newsPage.visibilityOfControlPanelLabel();
    }

    @Description("Фильтрация новостей по корректным данным с убранными чек-боксами")
    @Test
    public void filterNewsNotCheckBoxes() {
        appBar.switchToNews();
        newsPage.switchControlPanelNews();
        newsPage.openFormFilterNews();
        filterNews.addCategoryFilter("Зарплата");
        filterNews.setDateFromFilter(Utils.currentDate());
        filterNews.setDateToFilter(Utils.currentDate());
        filterNews.setCheckBoxActive();
        filterNews.setCheckBoxNotActive();
        filterNews.confirmFilter();
        newsPage.visibilityOfControlPanelLabel();
    }

    @Description("Фильтрация новостей, используя незаполненную форму ")
    @Test
    public void filterNewsEmptyForm() {
        appBar.switchToNews();
        newsPage.switchControlPanelNews();
        newsPage.openFormFilterNews();
        filterNews.addCategoryFilter("");
        filterNews.setDateFromFilter("");
        filterNews.setDateToFilter("");
        filterNews.setCheckBoxActive();
        filterNews.setCheckBoxNotActive();
        filterNews.confirmFilter();
        newsPage.visibilityOfControlPanelLabel();
    }
}
