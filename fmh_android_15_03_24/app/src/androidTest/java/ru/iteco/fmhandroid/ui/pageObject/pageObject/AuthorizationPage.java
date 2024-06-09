package ru.iteco.fmhandroid.ui.pageObject.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.endsWith;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.pageObject.Utils;

public class AuthorizationPage {
    MainPage mainPage = new MainPage();
    AppBar appBar = new AppBar();

    @Step("Ввод в поле Login")
    public void inputInFieldLogin(String login) {
        Allure.step("Ввод в поле Login");
        ViewInteraction inputInFieldLogin = onView(withHint("Логин"));
        // Ввод текста с заменой предыдущего содержимого
        inputInFieldLogin.perform(replaceText(login));
        // Закрытие клавиатуры
        closeSoftKeyboard();
    }

    @Step("Ввод в поле Password")
    public void inputInFieldPassword(String password) {
        Allure.step("Ввод в поле Password");
        ViewInteraction inputInFieldPassword = onView(withHint("Пароль"));
        // Ввод текста с заменой предыдущего содержимого
        inputInFieldPassword.perform(replaceText(password), closeSoftKeyboard());
        // Подтверждаем ввод
        pressImeActionButton();
        // Возвращаемся
        pressBack();
    }

    @Step("Нажатие на кнопку Войти")
    public void pressButton() {
        Allure.step("Нажатие на кнопку Войти");
        ViewInteraction buttonSingIn = onView(withId(R.id.enter_button));
        buttonSingIn.check(matches(isDisplayed()));
        buttonSingIn.perform(click());
    }

    @Step("Проверка видимости элемента с текстом Авторизация")
    public void visibilityElement() {
        Allure.step("Проверка видимости элемента с текстом Авторизация");
        ViewInteraction textViewAuth = onView(withText("Авторизация"));
        textViewAuth.check(matches(isDisplayed()));
        textViewAuth.check(matches(withText(endsWith("Авторизация"))));
    }

    @Step("Успешная авторизация пользователя")
    public void successfulAuthorization() {
        Allure.step("Успешная авторизация пользователя");
        inputInFieldLogin("login2");
        inputInFieldPassword("password2");
        pressButton();
        onView(isRoot()).perform(Utils.waitDisplayed(appBar.getPressProfile(), 5000));
        mainPage.checkNews();
    }
}
