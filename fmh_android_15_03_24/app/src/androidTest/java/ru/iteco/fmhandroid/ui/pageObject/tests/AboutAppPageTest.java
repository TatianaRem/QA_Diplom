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
import ru.iteco.fmhandroid.ui.pageObject.pageObject.AboutAppPage;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.AppBar;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pageObject.pageObject.MainPage;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pageObject.Utils;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AboutAppPageTest {
    AuthorizationPage authorizationPage = new AuthorizationPage();
    AppBar appBar = new AppBar();
    MainPage mainPage = new MainPage();

    AboutAppPage aboutAppPage = new AboutAppPage();
    String urlPrivacyPolicy = "https://vhospice.org/#/privacy-policy";
    String urlTermsOfUse = "https://vhospice.org/#/terms-of-use";

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

    @Description("Переход по ссылке 'Политика конфиденциальности'")
    @Test
    public void openPrivacyPolicy() {
        //переход на страницу "О приложении"
        appBar.AboutApp();
        //переход по ссылке "Политика конфиденциальности"
        aboutAppPage.intentPrivatePolicy(urlPrivacyPolicy);
        aboutAppPage.back();
    }

    @Description("Переход по ссылке 'Условия использования'")
    @Test
    public void openTermsOfUse() {
        appBar.AboutApp();
        aboutAppPage.intentTermOfUse(urlTermsOfUse);
        aboutAppPage.back();
    }

    @Description("Открытие страницы 'О приложении' и нажатие кнопки назад в приложении(для возвращения на предыдущую страницу)")
    @Test
    public void openPageAboutAppAndGoingBack() {
        appBar.AboutApp();
        aboutAppPage.getButtonBack();
    }
}

