package com.ramitsuri.depressiontracker.data;

import com.ramitsuri.depressiontracker.MainApplication;
import com.ramitsuri.depressiontracker.data.dao.QuestionDao;
import com.ramitsuri.depressiontracker.entities.Question;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Question.class}, version = 1)
public abstract class DepressionTrackerDatabase extends RoomDatabase {

    private static volatile DepressionTrackerDatabase INSTANCE;
    private static final String DB_NAME = "depression_tracker_db";

    public static DepressionTrackerDatabase getInstance() {
        if (INSTANCE == null) {
            synchronized (DepressionTrackerDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(MainApplication.getInstance(),
                            DepressionTrackerDatabase.class, DB_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract QuestionDao questionDao();
}
