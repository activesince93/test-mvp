package com.test.aassanjobs;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.test.aassanjobs", appContext.getPackageName());
    }

    @Test
    public void matchEditTextInput() {
        // Type text and then press the button.
        onView(withId(R.id.edtTxtSearch))
                .perform(typeText("Hello"), closeSoftKeyboard());

        // Check that the text was changed.
        onView(withId(R.id.edtTxtSearch))
                .check(matches(withText("Hello")));
    }

    @Test
    public void recyclerViewClick(){
        if (getRVcount() > 0){
            onView(withId(R.id.recyclerViewCityList))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        }
    }

    private int getRVcount(){
        RecyclerView recyclerView = (RecyclerView) mActivityRule.getActivity().findViewById(R.id.recyclerViewCityList);
        return recyclerView.getAdapter().getItemCount();
    }
}
