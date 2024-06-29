package ru.iteco.fmhandroid.ui.pageObject.tests;

import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pageObject.Utils;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.AppBar;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.MainPage;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.WarningError;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationTest {
    AuthorizationPage authorizationPage = new AuthorizationPage();
    AppBar appBar = new AppBar();
    MainPage mainPage = new MainPage();
    WarningError warningError = new WarningError();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        Espresso.onView(isRoot()).perform(Utils.waitDisplayed(appBar.getAppBarFragmentMain(), 5000));
        if (mainPage.isDisplayedButtonProfile()) {
            appBar.logOut();
        }
    }

    @Description("Авторизация в приложении, используя валидные данные")
    @Test
    public void successfulAuthorization() {
        authorizationPage.visibilityElement();
        authorizationPage.inputInFieldLogin(Credentials.VALID_LOGIN);
        authorizationPage.inputInFieldPassword(Credentials.VALID_PASSWORD);
        authorizationPage.pressButton();
        mainPage.isDisplayedButtonProfile();
    }

    @Description("Авторизация в приложении, используя пробелы вместо логина и корректный пароль")
    @Test
    public void authorizationSpacesInputFieldLoginAndValidPassword() {
        authorizationPage.inputInFieldLogin(Credentials.SPACES);
        authorizationPage.inputInFieldPassword(Credentials.VALID_PASSWORD);
        authorizationPage.pressButton();
        authorizationPage.visibilityElement();
    }

    @Description("Авторизация в приложении, используя корректный логин и пробелы вместо пароля")
    @Test
    public void authorizationSpacesInputFieldPasswordAndValidLogin() {
        authorizationPage.inputInFieldLogin(Credentials.VALID_LOGIN);
        authorizationPage.inputInFieldPassword(Credentials.SPACES);
        authorizationPage.pressButton();
        authorizationPage.visibilityElement();
    }

    @Description("Авторизация в приложении, используя пробелы во всех полях ввода")
    @Test
    public void authorizationSpacesInputsField() {
        authorizationPage.inputInFieldLogin(Credentials.SPACES);
        authorizationPage.inputInFieldPassword(Credentials.SPACES);
        authorizationPage.pressButton();
        authorizationPage.visibilityElement();
    }

    @Description("Авторизация в приложении, используя пустое поле ЛОГИН и корректный пароль")
    @Test
    public void authorizationWhenEmptyInputFieldLogin() {
        authorizationPage.inputInFieldPassword(Credentials.VALID_PASSWORD);
        authorizationPage.pressButton();
        authorizationPage.visibilityElement();
    }

    @Description("Авторизация в приложении, используя корректный логин и пустое поле ПАРОЛЬ")
    @Test
    public void authorizationWhenEmptyInputFieldPassword() {
        authorizationPage.inputInFieldLogin(Credentials.VALID_LOGIN);
        authorizationPage.inputInFieldPassword(" ");
        authorizationPage.pressButton();
        authorizationPage.visibilityElement();
    }

    @Description("Авторизация в приложении, используя пустые поля ввода логина и пароля")
    @Test
    public void authorizationWhenEmptyInputsFieldLoginAndPassword() {
        authorizationPage.pressButton();
        authorizationPage.visibilityElement();
    }

    @Description("Авторизация в приложении, введя пробел в начале логина ")
    @Test
    public void authorizationSpaceBeginningLogin() {
        authorizationPage.inputInFieldLogin(" " + Credentials.VALID_LOGIN);
        authorizationPage.inputInFieldPassword(Credentials.VALID_PASSWORD);
        authorizationPage.pressButton();
        authorizationPage.visibilityElement();
        warningError.windowError();
    }

    @Description("Авторизация в приложении, введя пробел в конце логина ")
    @Test
    public void authorizationSpaceInEndBLogin() {
        authorizationPage.inputInFieldLogin(Credentials.VALID_LOGIN + " ");
        authorizationPage.inputInFieldPassword(Credentials.VALID_PASSWORD);
        authorizationPage.pressButton();
        authorizationPage.visibilityElement();
        warningError.windowError();
    }

    @Description("Авторизация в приложении, введя пробел вначале пароля")
    @Test
    public void authorizationSpaceBeginningPassword() {
        authorizationPage.inputInFieldLogin(Credentials.VALID_LOGIN);
        authorizationPage.inputInFieldPassword(" " + Credentials.VALID_PASSWORD);
        authorizationPage.pressButton();
        authorizationPage.visibilityElement();
        warningError.windowError();
    }

    @Description("Авторизация в приложении, введя пробел в конце пароля ")
    @Test
    public void authorizationSpaceInEndPassword() {
        authorizationPage.inputInFieldLogin(Credentials.VALID_LOGIN);
        authorizationPage.inputInFieldPassword(Credentials.VALID_PASSWORD + " ");
        authorizationPage.pressButton();
        authorizationPage.visibilityElement();
        warningError.windowError();
    }

    @Description("Авторизация в приложениии, используя вместо логина -пароль и вместо пароля -логин")
    @Test
    public void authorizationPasswordInsteadOfLogin() {
        authorizationPage.inputInFieldLogin(Credentials.VALID_PASSWORD);
        authorizationPage.inputInFieldPassword(Credentials.VALID_LOGIN);
        authorizationPage.pressButton();
        authorizationPage.visibilityElement();
    }

    @Description("Авторизация в приложении, используя корректный логин, введенный разным регистром в поле ЛОГИН")
    @Test
    public void authorizationUsingRegisterInLogin() {
        authorizationPage.inputInFieldLogin("LOGIn2");
        authorizationPage.inputInFieldPassword(Credentials.VALID_PASSWORD);
        authorizationPage.pressButton();
        authorizationPage.visibilityElement();
    }

    @Description("Авторизация в приложении, используя корректный пароль, введенный разным регистром в поле ПАРОЛЬ")
    @Test
    public void authorizationUsingRegisterInPassword() {
        authorizationPage.inputInFieldLogin(Credentials.VALID_LOGIN);
        authorizationPage.inputInFieldPassword("PASSWORD2");
        authorizationPage.pressButton();
        authorizationPage.visibilityElement();
    }

    @Description("Ввод специальных символов в поле ЛОГИН и в поле ПАРОЛЬ")
    @Test
    public void authorizationUsingSpecialSymbolInFailedLogin() {
        authorizationPage.inputInFieldLogin(Credentials.SPECIAL_CHARACTERS);
        authorizationPage.inputInFieldPassword(Credentials.SPECIAL_CHARACTERS);
        authorizationPage.pressButton();
        authorizationPage.visibilityElement();
    }

    @Description("Авторизация при введении в поле ЛОГИН И в поле ПАРОЛЬ кириллицы")
    @Test
    public void authorizationUsingCyrillicInFailedLogin() {
        authorizationPage.inputInFieldLogin(Credentials.CYRILLIC_LOGIN);
        authorizationPage.inputInFieldPassword(Credentials.CYRILLIC_PASSWORD);
        authorizationPage.pressButton();
        authorizationPage.visibilityElement();
    }
}
