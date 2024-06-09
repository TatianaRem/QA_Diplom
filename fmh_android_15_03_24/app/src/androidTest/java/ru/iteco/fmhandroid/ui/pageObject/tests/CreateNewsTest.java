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
            authorizationPage.successfulAuthorization();
        }
    }

    @Description("Успешное создание новости")
    // при одинаковых заголовках у новостей падает, так как нет уникального идентификатора
    @Test
    public void successfulNewsCreation() {
        // Переход на страницу новостей
        appBar.switchToNews();
        // Переход на панель управления новостями
        newsPage.switchControlPanelNews();
        // Добавление новой новости
        controlPanelNews.addNews();
        // Добавление категории
        createNews.addCategory("Объявление");
        // Добавление заголовка новости
        createNews.addTitle("Создание новости");
        // Добавление даты
        createNews.addDate(Utils.currentDate());
        // Добавление времени
        createNews.addTime("20:00");
        // Добавление описания
        createNews.addDescription("Описание новости");
        // Нажатие на кнопку сохранения
        createNews.pressSave();
        controlPanelNews.searchNewsAndCheckIsDisplayed("Создание новости");
    }

    @Description("Создание новости с датой публикации в будущем")
    @Test
    public void shouldStayOnNewsCreationScreenWhenCreatingNewsInPast() {
        appBar.switchToNews();
        newsPage.switchControlPanelNews();
        controlPanelNews.addNews();
        createNews.addCategory("Объявление");
        String text = "Создание новости в будущем";
        createNews.addTitle(text);
        String pastDate = Utils.dateMore1Years();
        createNews.addDate(pastDate);
        createNews.addTime("20:00");
        createNews.addDescription("Описание новости в будущем");
        createNews.pressSave();
        // Проверка отображения ошибки
        createNews.checkErrorDisplay("Неверная дата публикации");
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
