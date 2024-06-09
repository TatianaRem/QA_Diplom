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

public class ControlPanelNewsTest {
    AuthorizationPage authorizationPage = new AuthorizationPage();
    NewsPage newsPage = new NewsPage();
    AppBar appBar = new AppBar();
    MainPage mainPage = new MainPage();
    ControlPanelNews controlPanelNews = new ControlPanelNews();
    CreateNews createNews = new CreateNews();
    EditNews editNews = new EditNews();

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

    @Description("Нажатие на кнопку сортировка новостей по возрастанию/убыванию")
    @Test
    public void sortingNews() {
        appBar.switchToNews();
        newsPage.switchControlPanelNews();
        newsPage.buttonSortingNews();
    }

    @Description("Открытие формы 'Cоздания новости'")
    @Test
    public void openFormCreateNews() {
        appBar.switchToNews();
        newsPage.switchControlPanelNews();
        controlPanelNews.addNews();
    }

    @Description("Редактирование новости")
    @Test
    public void shouldEditTheNewsAfterEditing() {
        appBar.switchToNews();
        newsPage.switchControlPanelNews();
        controlPanelNews.addNews();
        createNews.methodCreatingNews("Объявление", "День", Utils.currentDate(), "17:25", "Описание новости");
        controlPanelNews.searchNewsAndCheckIsDisplayed("День");
        controlPanelNews.pressEditPanelNews();
        editNews.editCategory("Зарплата");
        editNews.pressSave();
    }
    @Description("Удаление новости")
    @Test
    public void shouldNewsBeDeletedAfterDelete() {
        appBar.switchToNews();
        newsPage.switchControlPanelNews();
        controlPanelNews.addNews();
        createNews.methodCreatingNews("Some Category", "Создание новости", Utils.currentDate(), "10:00", "Описание новости для удаления");
        controlPanelNews.searchNewsAndCheckIsDisplayed("Создание новости");
        controlPanelNews.deleteNews();
        controlPanelNews.checkDoesNotExistNews("Создание новости");
    }
}

