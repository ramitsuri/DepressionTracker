package com.ramitsuri.depressiontracker.data.dao;

import com.ramitsuri.depressiontracker.entities.Question;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface QuestionDao {
    @Query("SELECT * FROM question")
    List<Question> getAll();

    @Query("SELECT * FROM question WHERE is_synced = 0")
    List<Question> getAllUnsynced();

    @Query("SELECT * FROM question WHERE date = :date")
    List<Question> getForDate(long date);

    @Query("SELECT date FROM question group by date")
    LiveData<List<Long>> getDates();

    @Insert
    void insert(Question question);

    @Insert
    void insertAll(List<Question> questions);

    @Query("UPDATE question SET is_synced = 1 WHERE is_synced = 0")
    void updateUnsynced();

    @Query("DELETE FROM question")
    void deleteAll();

    @Query("DELETE FROM question WHERE is_synced = 1")
    void deleteSynced();
}
