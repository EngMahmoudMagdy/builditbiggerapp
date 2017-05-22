package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
/**
 * Created by kev on 3/21/16.
 */

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {

    @Test
    public void testDoInBackground() throws Exception {	
		try {
            com.udacity.gradle.builditbigger.MainActivityFragment mainActivity = new com.udacity.gradle.builditbigger.MainActivityFragment();
             mainActivity.testFlag = true;
			EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
            endpointsAsyncTask.execute(mainActivity);
            String result = endpointsAsyncTask.get(30, TimeUnit.SECONDS);
			Thread.sleep(1000);
            assertNotNull(result);
            assertTrue(result.length() > 0);
        } catch (Exception e){
            Log.e("EndpointsAsyncTaskTest", "testDoInBackground: Timed out");
        }
    }
}