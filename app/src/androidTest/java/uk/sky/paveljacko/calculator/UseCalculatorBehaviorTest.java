package uk.sky.paveljacko.calculator;

import static android.support.test.espresso.action.ViewActions.click;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.paveljacko.calculator.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import uk.sky.paveljacko.calculator.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class UseCalculatorBehaviorTest {

    public static final String INITIAL_TEXT = "0";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void checkInitalValue() {
        onView(ViewMatchers.withId(R.id.txt_result)).check(matches(withText(INITIAL_TEXT)));
    }

    @Test
    public void checkOutcomeValue() {
        onView(withId(R.id.btn_1)).perform(click());
        onView(withId(R.id.btn_5)).perform(click());
        onView(withId(R.id.btn_multiply)).perform(click());
        onView(withId(R.id.btn_2)).perform(click());
        onView(withId(R.id.btn_equals)).perform(click());
        onView(withId(R.id.txt_result)).check(matches(withText("Result is: 30.0")));
    }
}