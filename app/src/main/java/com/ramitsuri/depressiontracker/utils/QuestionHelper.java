package com.ramitsuri.depressiontracker.utils;

import android.content.Context;

import com.ramitsuri.depressiontracker.R;
import com.ramitsuri.depressiontracker.entities.Question;
import com.ramitsuri.depressiontracker.intdef.AnswerType;
import com.ramitsuri.depressiontracker.intdef.DifficultyType;
import com.ramitsuri.depressiontracker.intdef.QuestionType;

import java.util.ArrayList;
import java.util.List;

public class QuestionHelper {

    public static List<Question> getBaseQuestions(Context context) {
        List<Question> questions = new ArrayList<>();

        Question question;

        question = new Question();
        question.setQuestionId(0);
        question.setText(context.getString(R.string.question1_text));
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(1);
        question.setText(context.getString(R.string.question2_text));
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(2);
        question.setText(context.getString(R.string.question3_text));
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(3);
        question.setText(context.getString(R.string.question4_text));
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(4);
        question.setText(context.getString(R.string.question5_text));
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(5);
        question.setText(context.getString(R.string.question6_text));
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(6);
        question.setText(context.getString(R.string.question7_text));
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(7);
        question.setText(context.getString(R.string.question8_text));
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        //questions.add(question);

        question = new Question();
        question.setQuestionId(8);
        question.setText(context.getString(R.string.question9_text));
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(9);
        question.setText(context.getString(R.string.question10_text));
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(10);
        question.setText(context.getString(R.string.question11_text));
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        //questions.add(question);

        question = new Question();
        question.setQuestionId(11);
        question.setText(context.getString(R.string.question12_text));
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        //questions.add(question);

        question = new Question();
        question.setQuestionId(12);
        question.setText(context.getString(R.string.question13_text));
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        //questions.add(question);

        question = new Question();
        question.setQuestionId(13);
        question.setText(context.getString(R.string.question14_text));
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        //questions.add(question);

        question = new Question();
        question.setQuestionId(14);
        question.setText(context.getString(R.string.question15_text));
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(15);
        question.setText(context.getString(R.string.question16_text));
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        return questions;
    }
}
