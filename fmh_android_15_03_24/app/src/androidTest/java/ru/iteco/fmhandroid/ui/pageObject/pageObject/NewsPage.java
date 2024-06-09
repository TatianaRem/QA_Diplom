package ru.iteco.fmhandroid.ui.pageObject.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.pageObject.Utils;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.ControlPanelNews;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.FilterNews;

public class NewsPage {
    ControlPanelNews controlPanelNews = new ControlPanelNews();
    FilterNews filterNewsPage = new FilterNews();
    private final int buttonSortingNews = R.id.sort_news_material_button;
    private final int buttonControlPanelNews = R.id.edit_news_material_button;
    private final int containerPageNews = R.id.container_list_news_include;
    public ViewInteraction textViewNewsOnPageNews = onView(withText("Новости"));
    private final int buttonFilterNews = R.id.filter_news_material_button;

    private final int containerControlPanel = R.id.layout_background_image_view;

    public int getButtonFilterNews() {
        return buttonFilterNews;
    }

    public int getContainerNews() {
        return containerPageNews;
    }

    @Step("Проверка видимости элемента с текстом Новости")
    public void checkNews() {
        Allure.step("Проверка видимости элемента с текстом Новости");
        onView(withId(containerPageNews)).check(matches(ViewMatchers.isDisplayed()));
        textViewNewsOnPageNews.check(matches(withText("Новости")));
    }

    @Step("Нажатие на кнопку 'Сортировать' новости")
    public void buttonSortingNews() {
        Allure.step("Нажатие на кнопку 'Сортировать' новость");
        // Ждем пока загрузиться элемент
        onView(withId(buttonSortingNews)).check(matches(allOf(isDisplayed(), isClickable())));
        // Клик по элементу
        onView(withId(buttonSortingNews)).perform(ViewActions.click());
    }

    @Step("Нажатие на кнопку 'Фильтровать' новости")
    public void openFormFilterNews() {
        Allure.step("Нажатие на кнопку 'Фильтровать' новость");
        // Проверяем, что элемент видим и кликабелен
        onView(withId(buttonFilterNews)).check(matches(allOf(isDisplayed(), isClickable())));
        // Клик по элементу
        onView(withId(buttonFilterNews)).perform(ViewActions.click());
        // Ждем, пока загрузится форма
        onView(isRoot()).perform(Utils.waitDisplayed(filterNewsPage.getFilter(), 5000));
    }

    @Step("Переход на 'Панель управления'")
    public void switchControlPanelNews() {
        Allure.step("Переход на Панель управления новостями");
        //  Переход на панель управления
        onView(isRoot()).perform(Utils.waitDisplayed(buttonControlPanelNews, 5000));
        // Проверяем, что элемент видим и кликабелен
        onView(withId(buttonControlPanelNews)).check(matches(allOf(isDisplayed(), isClickable())));
        // Клик по элементу
        onView(withId(buttonControlPanelNews)).perform(ViewActions.click());
        // Ждем, пока загрузится панель управления
        onView(isRoot()).perform(Utils.waitDisplayed(controlPanelNews.getButtonAddNews(), 5000));
    }
    @Step("Проверка видимости элемента с текстом 'Панель управления'")
    public void visibilityOfControlPanelLabel() {
        Allure.step("Проверка видимости элемента с текстом 'Панель упараления'");
        onView(withId(containerControlPanel)).check(matches(ViewMatchers.isDisplayed()));
        onView(isRoot()).perform(Utils.waitDisplayed(containerControlPanel, 5000));
    }
}