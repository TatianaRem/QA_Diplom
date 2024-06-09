package ru.iteco.fmhandroid.ui.pageObject.tests;

import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Description;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pageObject.Utils;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.AppBar;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.MainPage;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.NewsPage;

@RunWith(AndroidJUnit4.class)
public class NewsPageTest {
    AuthorizationPage authorizationPage = new AuthorizationPage();
    NewsPage newsPage = new NewsPage();
    AppBar appBar = new AppBar();
    MainPage mainPage = new MainPage();

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

    @Description("Сортировка новостей по возрастанию/убыванию")
    @Test
    public void sortingNews() {
        appBar.switchToNews();
        newsPage.buttonSortingNews();
    }

    @Description("Открытие формы 'Фильтровать новости'")
    @Test
    public void openFormFilterNews() {
        appBar.switchToNews();
        newsPage.openFormFilterNews();
    }

    @Description("Открытие раздела 'Панель управления'")
    @Test
    public void openControlPanel() {
        appBar.switchToNews();
        newsPage.switchControlPanelNews();
    }

    @Description("Переход на страницу 'Главная', через навигационное меню")
    @Test
    public void openPageMain() {
        appBar.switchToNews();
        appBar.pageMain();
        mainPage.isDisplayedButtonProfile();
    }

    @Description("Переход на страницу 'О приложении' через навигационное меню")
    @Test
    public void openPageAboutApplication() {
        appBar.switchToNews();
        appBar.AboutApp();
    }
}
