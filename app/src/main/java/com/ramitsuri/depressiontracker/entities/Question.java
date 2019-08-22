package com.ramitsuri.depressiontracker.entities;

import com.ramitsuri.depressiontracker.intdef.AnswerType;
import com.ramitsuri.depressiontracker.intdef.DifficultyType;
import com.ramitsuri.depressiontracker.intdef.QuestionType;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Question {

    @PrimaryKey(autoGenerate = true)
    private int mId;

    @ColumnInfo(name = "question_id")
    private int mQuestionId;

    @ColumnInfo(name = "date")
    private long mDate;

    @ColumnInfo(name = "text")
    private String mText;

    @ColumnInfo(name = "question_type")
    @QuestionType
    private int mQuestionType;

    @ColumnInfo(name = "answer_type")
    @AnswerType
    private int mAnswerType;

    @ColumnInfo(name = "difficulty_type")
    @DifficultyType
    private int mDifficultyType;

    @ColumnInfo(name = "is_synced")
    private boolean mIsSynced;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getQuestionId() {
        return mQuestionId;
    }

    public void setQuestionId(int questionId) {
        mQuestionId = questionId;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public int getQuestionType() {
        return mQuestionType;
    }

    public void setQuestionType(int questionType) {
        mQuestionType = questionType;
    }

    public int getAnswerType() {
        return mAnswerType;
    }

    public void setAnswerType(int answerType) {
        mAnswerType = answerType;
    }

    public int getDifficultyType() {
        return mDifficultyType;
    }

    public void setDifficultyType(int difficultyType) {
        mDifficultyType = difficultyType;
    }

    public long getDate() {
        return mDate;
    }

    public void setDate(long date) {
        mDate = date;
    }

    public boolean isSynced() {
        return mIsSynced;
    }

    public void setIsSynced(boolean synced) {
        mIsSynced = synced;
    }
}
