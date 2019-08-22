package com.ramitsuri.depressiontracker.data;

import android.content.Context;

import com.ramitsuri.depressiontracker.LiveDataTestUtil;
import com.ramitsuri.depressiontracker.data.dao.QuestionDao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class DepressionTrackerDatabaseTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private static final String TAG = DepressionTrackerDatabase.class.getName();

    private QuestionDao mQuestionDao;
    private DepressionTrackerDatabase mDb;

    @Before
    public void createDb() {
        Context appContext = getContext();
        mDb = Room.inMemoryDatabaseBuilder(appContext, DepressionTrackerDatabase.class).build();
        mQuestionDao = mDb.questionDao();

        mQuestionDao.insertAll(DummyData.getQuestions());
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    private Context getContext() {
        return InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @Test
    public void questionTest() throws Exception {
        Assert.assertEquals(DummyData.getQuestions().size(), mQuestionDao.getAll().size());

        Assert.assertEquals(DummyData.getUnsynced().size(), mQuestionDao.getAllUnsynced().size());

        Assert.assertEquals(DummyData.getForDate(DummyData.getDates()[0]).size(),
                mQuestionDao.getForDate(DummyData.getDates()[0]).size());

        Assert.assertEquals(DummyData.getDates().length,
                LiveDataTestUtil.getValue(mQuestionDao.getDates()).size());

        mQuestionDao.updateUnsynced();
        Assert.assertEquals(0, mQuestionDao.getAllUnsynced().size());
        mQuestionDao.deleteAll();
        mQuestionDao.insertAll(DummyData.getQuestions());

        mQuestionDao.deleteSynced();
        Assert.assertEquals(0, mQuestionDao.getAll().size() - mQuestionDao.getAllUnsynced().size());
        mQuestionDao.deleteAll();
        mQuestionDao.insertAll(DummyData.getQuestions());
    }
}
