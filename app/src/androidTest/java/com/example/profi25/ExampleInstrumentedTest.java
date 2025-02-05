package com.example.profi25;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.profi25.Presentation.HomeActivity;
import com.example.profi25.Presentation.SignInActivity;
import com.google.android.material.textfield.TextInputEditText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {


    @Rule
    public ActivityScenarioRule<SignInActivity> activityScenarioRule =
            new ActivityScenarioRule<>(SignInActivity.class);

    //проверка пустого поля почты
    @Test
    public void checkIsEmailEmpty() {
        // type 5 value in first EditText
        onView(withId(R.id.logInEmailAddressField))
                .perform(typeText(""));


        activityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<SignInActivity>() {
            @Override
            public void perform(SignInActivity activity) {
                TextInputEditText e = activity.findViewById(R.id.logInEmailAddressField);
                assertEquals(e.getText().toString(), "");
            }
        });

    }

    //проверка непустого поля почты
    @Test
    public void checkIsEmailNotEmpty() {
        // type 5 value in first EditText
        onView(withId(R.id.logInEmailAddressField))
                .perform(typeText(""));


        activityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<SignInActivity>() {
            @Override
            public void perform(SignInActivity activity) {
                TextInputEditText e = activity.findViewById(R.id.logInEmailAddressField);
                assertEquals(e.getText().toString(), "");
            }
        });

    }

    //проверка что все буквы почты маленькие
    @Test
    public void checkIsEmailToLowerCaseSuccess() {
        // type 5 value in first EditText
        onView(withId(R.id.logInEmailAddressField))
                .perform(typeText("test@test.ru"));


        activityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<SignInActivity>() {
            @Override
            public void perform(SignInActivity activity) {
                TextInputEditText e = activity.findViewById(R.id.logInEmailAddressField);
                assertEquals(e.getText().toString(), e.getText().toString().toLowerCase());
            }
        });
    }

    //проверка что НЕ все буквы почты маленькие
    @Test
    public void checkIsEmailToLowerCaseFail() {
        // type 5 value in first EditText
        onView(withId(R.id.logInEmailAddressField))
                .perform(typeText("TEST@test.ru"));


        activityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<SignInActivity>() {
            @Override
            public void perform(SignInActivity activity) {
                TextInputEditText e = activity.findViewById(R.id.logInEmailAddressField);
                assertNotEquals(e.getText().toString(), e.getText().toString().toLowerCase());
            }
        });
    }

    //проверка что почта соответствует маске почты
    @Test
    public void checkIsEmailMaskCorrect() {
        // type 5 value in first EditText
        onView(withId(R.id.logInEmailAddressField))
                .perform(typeText("test@test.ru"));


        activityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<SignInActivity>() {
            @Override
            public void perform(SignInActivity activity) {
                TextInputEditText e = activity.findViewById(R.id.logInEmailAddressField);
                assertTrue(activity.isEmailValid(e.getText().toString()));
            }
        });
    }

    //проверка что почта НЕ соответствует маске почты
    @Test
    public void checkIsEmailMaskIsNotCorrect1() {
        // type 5 value in first EditText
        onView(withId(R.id.logInEmailAddressField))
                .perform(typeText("test test.ru"));


        activityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<SignInActivity>() {
            @Override
            public void perform(SignInActivity activity) {
                TextInputEditText e = activity.findViewById(R.id.logInEmailAddressField);
                assertFalse(activity.isEmailValid(e.getText().toString()));
            }
        });
    }

    @Test
    public void checkIsEmailMaskIsNotCorrect2() {
        // type 5 value in first EditText
        onView(withId(R.id.logInEmailAddressField))
                .perform(typeText("test@test,ru"));


        activityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<SignInActivity>() {
            @Override
            public void perform(SignInActivity activity) {
                TextInputEditText e = activity.findViewById(R.id.logInEmailAddressField);
                assertFalse(activity.isEmailValid(e.getText().toString()));
            }
        });
    }

}