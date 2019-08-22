package com.ramitsuri.depressiontracker.data;

import com.ramitsuri.depressiontracker.entities.Question;
import com.ramitsuri.depressiontracker.intdef.AnswerType;
import com.ramitsuri.depressiontracker.intdef.DifficultyType;
import com.ramitsuri.depressiontracker.intdef.QuestionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class DummyData {
    public static String[] getTexts() {
        return new String[] {
                "Little interest or pleasure in doing things",
                "Down, depressed, or hopeless",
                "Trouble sleeping or sleeping too much",
                "Tired or little energy",
                "Poor appetite or overeating",
                "Feeling bad about yourself",
                "Trouble concentrating",
                "Noticeably moving or speaking slowly or being fidgety or restless",
                "Self-harm thoughts",
                "Feeling nervous, anxious, or on edge",
                "Worrying too much about different things",
                "Not being able to stop or control worrying",
                "Trouble relaxing",
                "Being so restless that it's hard to sit still",
                "Becoming easily annoyed or irritable",
                "Feeling afraid as if something awful might happen"
        };
    }

    public static List<String> getAllTexts() {
        return new ArrayList<>(Arrays.asList(getTexts()));
    }

    public static long[] getDates() {
        TimeZone timeZone = TimeZone.getDefault();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return new long[] {
                /*(1566518400000L + 0 * 86400000L),
                (1566518400000L + 1 * 86400000L),
                (1566518400000L + 2 * 86400000L)*/
                calendar.getTimeInMillis() + 0 * 86400000L,
                calendar.getTimeInMillis() + 1 * 86400000L,
                calendar.getTimeInMillis() + 2 * 86400000L,
                calendar.getTimeInMillis() + 3 * 86400000L,
                calendar.getTimeInMillis() + 4 * 86400000L,
                calendar.getTimeInMillis() + 5 * 86400000L,
                calendar.getTimeInMillis() + 6 * 86400000L,
                calendar.getTimeInMillis() + 7 * 86400000L,
                calendar.getTimeInMillis() + 8 * 86400000L,
                calendar.getTimeInMillis() + 9 * 86400000L
        };
    }

    public static List<Question> getUnsynced() {
        List<Question> questions = new ArrayList<>();
        for (Question question : getQuestions()) {
            if (!question.isSynced()) {
                questions.add(question);
            }
        }

        return questions;
    }

    public static List<Question> getForDate(long date) {
        List<Question> questions = new ArrayList<>();
        for (Question question : getQuestions()) {
            if (question.getDate() == date) {
                questions.add(question);
            }
        }

        return questions;
    }

    public static List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        Question question;

        // Day 1
        question = new Question();
        question.setQuestionId(0);
        question.setDate(getDates()[0]);
        question.setText(getTexts()[0]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(1);
        question.setDate(getDates()[0]);
        question.setText(getTexts()[1]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(2);
        question.setDate(getDates()[0]);
        question.setText(getTexts()[2]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.NOT_AT_ALL);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(3);
        question.setDate(getDates()[0]);
        question.setText(getTexts()[3]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(4);
        question.setDate(getDates()[0]);
        question.setText(getTexts()[4]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(5);
        question.setDate(getDates()[0]);
        question.setText(getTexts()[5]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.VERY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(6);
        question.setDate(getDates()[0]);
        question.setText(getTexts()[6]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(7);
        question.setDate(getDates()[0]);
        question.setText(getTexts()[7]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(8);
        question.setDate(getDates()[0]);
        question.setText(getTexts()[8]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(9);
        question.setDate(getDates()[0]);
        question.setText(getTexts()[9]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(10);
        question.setDate(getDates()[0]);
        question.setText(getTexts()[10]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(11);
        question.setDate(getDates()[0]);
        question.setText(getTexts()[11]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(12);
        question.setDate(getDates()[0]);
        question.setText(getTexts()[12]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(13);
        question.setDate(getDates()[0]);
        question.setText(getTexts()[13]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.VERY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(14);
        question.setDate(getDates()[0]);
        question.setText(getTexts()[14]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(15);
        question.setDate(getDates()[0]);
        question.setText(getTexts()[15]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        // Day 2
        question = new Question();
        question.setQuestionId(0);
        question.setDate(getDates()[1]);
        question.setText(getTexts()[0]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(1);
        question.setDate(getDates()[1]);
        question.setText(getTexts()[1]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(2);
        question.setDate(getDates()[1]);
        question.setText(getTexts()[2]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.NOT_AT_ALL);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(3);
        question.setDate(getDates()[1]);
        question.setText(getTexts()[3]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(4);
        question.setDate(getDates()[1]);
        question.setText(getTexts()[4]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(5);
        question.setDate(getDates()[1]);
        question.setText(getTexts()[5]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.VERY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(6);
        question.setDate(getDates()[1]);
        question.setText(getTexts()[6]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(7);
        question.setDate(getDates()[1]);
        question.setText(getTexts()[7]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(8);
        question.setDate(getDates()[1]);
        question.setText(getTexts()[8]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(9);
        question.setDate(getDates()[1]);
        question.setText(getTexts()[9]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(10);
        question.setDate(getDates()[1]);
        question.setText(getTexts()[10]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(11);
        question.setDate(getDates()[1]);
        question.setText(getTexts()[11]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(12);
        question.setDate(getDates()[1]);
        question.setText(getTexts()[12]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(13);
        question.setDate(getDates()[1]);
        question.setText(getTexts()[13]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.VERY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(14);
        question.setDate(getDates()[1]);
        question.setText(getTexts()[14]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(15);
        question.setDate(getDates()[1]);
        question.setText(getTexts()[15]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        // Day 3
        question = new Question();
        question.setQuestionId(0);
        question.setDate(getDates()[2]);
        question.setText(getTexts()[0]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(1);
        question.setDate(getDates()[2]);
        question.setText(getTexts()[1]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(2);
        question.setDate(getDates()[2]);
        question.setText(getTexts()[2]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.NOT_AT_ALL);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(3);
        question.setDate(getDates()[2]);
        question.setText(getTexts()[3]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(4);
        question.setDate(getDates()[2]);
        question.setText(getTexts()[4]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(5);
        question.setDate(getDates()[2]);
        question.setText(getTexts()[5]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.VERY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(6);
        question.setDate(getDates()[2]);
        question.setText(getTexts()[6]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(7);
        question.setDate(getDates()[2]);
        question.setText(getTexts()[7]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(8);
        question.setDate(getDates()[2]);
        question.setText(getTexts()[8]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(9);
        question.setDate(getDates()[2]);
        question.setText(getTexts()[9]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(10);
        question.setDate(getDates()[2]);
        question.setText(getTexts()[10]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(11);
        question.setDate(getDates()[2]);
        question.setText(getTexts()[11]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(12);
        question.setDate(getDates()[2]);
        question.setText(getTexts()[12]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(13);
        question.setDate(getDates()[2]);
        question.setText(getTexts()[13]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.VERY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(14);
        question.setDate(getDates()[2]);
        question.setText(getTexts()[14]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(15);
        question.setDate(getDates()[2]);
        question.setText(getTexts()[15]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        // Day 4
        question = new Question();
        question.setQuestionId(0);
        question.setDate(getDates()[3]);
        question.setText(getTexts()[0]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(1);
        question.setDate(getDates()[3]);
        question.setText(getTexts()[1]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(2);
        question.setDate(getDates()[3]);
        question.setText(getTexts()[2]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.NOT_AT_ALL);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(3);
        question.setDate(getDates()[3]);
        question.setText(getTexts()[3]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(4);
        question.setDate(getDates()[3]);
        question.setText(getTexts()[4]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(5);
        question.setDate(getDates()[3]);
        question.setText(getTexts()[5]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.VERY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(6);
        question.setDate(getDates()[3]);
        question.setText(getTexts()[6]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(7);
        question.setDate(getDates()[3]);
        question.setText(getTexts()[7]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(8);
        question.setDate(getDates()[3]);
        question.setText(getTexts()[8]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(9);
        question.setDate(getDates()[3]);
        question.setText(getTexts()[9]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(10);
        question.setDate(getDates()[3]);
        question.setText(getTexts()[10]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(11);
        question.setDate(getDates()[3]);
        question.setText(getTexts()[11]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(12);
        question.setDate(getDates()[3]);
        question.setText(getTexts()[12]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(13);
        question.setDate(getDates()[3]);
        question.setText(getTexts()[13]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.VERY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(14);
        question.setDate(getDates()[3]);
        question.setText(getTexts()[14]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(15);
        question.setDate(getDates()[3]);
        question.setText(getTexts()[15]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        // Day 5
        question = new Question();
        question.setQuestionId(0);
        question.setDate(getDates()[4]);
        question.setText(getTexts()[0]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(1);
        question.setDate(getDates()[4]);
        question.setText(getTexts()[1]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(2);
        question.setDate(getDates()[4]);
        question.setText(getTexts()[2]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.NOT_AT_ALL);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(3);
        question.setDate(getDates()[4]);
        question.setText(getTexts()[3]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(4);
        question.setDate(getDates()[4]);
        question.setText(getTexts()[4]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(5);
        question.setDate(getDates()[4]);
        question.setText(getTexts()[5]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.VERY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(6);
        question.setDate(getDates()[4]);
        question.setText(getTexts()[6]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(7);
        question.setDate(getDates()[4]);
        question.setText(getTexts()[7]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(8);
        question.setDate(getDates()[4]);
        question.setText(getTexts()[8]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(9);
        question.setDate(getDates()[4]);
        question.setText(getTexts()[9]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(10);
        question.setDate(getDates()[4]);
        question.setText(getTexts()[10]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(11);
        question.setDate(getDates()[4]);
        question.setText(getTexts()[11]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(12);
        question.setDate(getDates()[4]);
        question.setText(getTexts()[12]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(13);
        question.setDate(getDates()[4]);
        question.setText(getTexts()[13]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.VERY);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(14);
        question.setDate(getDates()[4]);
        question.setText(getTexts()[14]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(false);
        questions.add(question);

        question = new Question();
        question.setQuestionId(15);
        question.setDate(getDates()[4]);
        question.setText(getTexts()[15]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(false);
        questions.add(question);

        // Day 6
        question = new Question();
        question.setQuestionId(0);
        question.setDate(getDates()[5]);
        question.setText(getTexts()[0]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(1);
        question.setDate(getDates()[5]);
        question.setText(getTexts()[1]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(2);
        question.setDate(getDates()[5]);
        question.setText(getTexts()[2]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.NOT_AT_ALL);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(3);
        question.setDate(getDates()[5]);
        question.setText(getTexts()[3]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(4);
        question.setDate(getDates()[5]);
        question.setText(getTexts()[4]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(5);
        question.setDate(getDates()[5]);
        question.setText(getTexts()[5]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.VERY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(6);
        question.setDate(getDates()[5]);
        question.setText(getTexts()[6]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(7);
        question.setDate(getDates()[5]);
        question.setText(getTexts()[7]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(8);
        question.setDate(getDates()[5]);
        question.setText(getTexts()[8]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(9);
        question.setDate(getDates()[5]);
        question.setText(getTexts()[9]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(10);
        question.setDate(getDates()[5]);
        question.setText(getTexts()[10]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(11);
        question.setDate(getDates()[5]);
        question.setText(getTexts()[11]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(12);
        question.setDate(getDates()[5]);
        question.setText(getTexts()[12]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(13);
        question.setDate(getDates()[5]);
        question.setText(getTexts()[13]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.VERY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(14);
        question.setDate(getDates()[5]);
        question.setText(getTexts()[14]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(15);
        question.setDate(getDates()[5]);
        question.setText(getTexts()[15]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        // Day 7
        question = new Question();
        question.setQuestionId(0);
        question.setDate(getDates()[6]);
        question.setText(getTexts()[0]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(1);
        question.setDate(getDates()[6]);
        question.setText(getTexts()[1]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(2);
        question.setDate(getDates()[6]);
        question.setText(getTexts()[2]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.NOT_AT_ALL);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(3);
        question.setDate(getDates()[6]);
        question.setText(getTexts()[3]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(4);
        question.setDate(getDates()[6]);
        question.setText(getTexts()[4]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(5);
        question.setDate(getDates()[6]);
        question.setText(getTexts()[5]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.VERY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(6);
        question.setDate(getDates()[6]);
        question.setText(getTexts()[6]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(7);
        question.setDate(getDates()[6]);
        question.setText(getTexts()[7]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(8);
        question.setDate(getDates()[6]);
        question.setText(getTexts()[8]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(9);
        question.setDate(getDates()[6]);
        question.setText(getTexts()[9]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(10);
        question.setDate(getDates()[6]);
        question.setText(getTexts()[10]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(11);
        question.setDate(getDates()[6]);
        question.setText(getTexts()[11]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(12);
        question.setDate(getDates()[6]);
        question.setText(getTexts()[12]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(13);
        question.setDate(getDates()[6]);
        question.setText(getTexts()[13]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.VERY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(14);
        question.setDate(getDates()[6]);
        question.setText(getTexts()[14]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(15);
        question.setDate(getDates()[6]);
        question.setText(getTexts()[15]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        // Day 8
        question = new Question();
        question.setQuestionId(0);
        question.setDate(getDates()[7]);
        question.setText(getTexts()[0]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(1);
        question.setDate(getDates()[7]);
        question.setText(getTexts()[1]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(2);
        question.setDate(getDates()[7]);
        question.setText(getTexts()[2]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.NOT_AT_ALL);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(3);
        question.setDate(getDates()[7]);
        question.setText(getTexts()[3]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(4);
        question.setDate(getDates()[7]);
        question.setText(getTexts()[4]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(5);
        question.setDate(getDates()[7]);
        question.setText(getTexts()[5]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.VERY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(6);
        question.setDate(getDates()[7]);
        question.setText(getTexts()[6]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(7);
        question.setDate(getDates()[7]);
        question.setText(getTexts()[7]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(8);
        question.setDate(getDates()[7]);
        question.setText(getTexts()[8]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(9);
        question.setDate(getDates()[7]);
        question.setText(getTexts()[9]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(10);
        question.setDate(getDates()[7]);
        question.setText(getTexts()[10]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(11);
        question.setDate(getDates()[7]);
        question.setText(getTexts()[11]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(12);
        question.setDate(getDates()[7]);
        question.setText(getTexts()[12]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(13);
        question.setDate(getDates()[7]);
        question.setText(getTexts()[13]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.VERY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(14);
        question.setDate(getDates()[7]);
        question.setText(getTexts()[14]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(15);
        question.setDate(getDates()[7]);
        question.setText(getTexts()[15]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        // Day 9
        question = new Question();
        question.setQuestionId(0);
        question.setDate(getDates()[8]);
        question.setText(getTexts()[0]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(1);
        question.setDate(getDates()[8]);
        question.setText(getTexts()[1]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(2);
        question.setDate(getDates()[8]);
        question.setText(getTexts()[2]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.NOT_AT_ALL);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(3);
        question.setDate(getDates()[8]);
        question.setText(getTexts()[3]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(4);
        question.setDate(getDates()[8]);
        question.setText(getTexts()[4]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(5);
        question.setDate(getDates()[8]);
        question.setText(getTexts()[5]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.VERY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(6);
        question.setDate(getDates()[8]);
        question.setText(getTexts()[6]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(7);
        question.setDate(getDates()[8]);
        question.setText(getTexts()[7]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(8);
        question.setDate(getDates()[8]);
        question.setText(getTexts()[8]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(9);
        question.setDate(getDates()[8]);
        question.setText(getTexts()[9]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(10);
        question.setDate(getDates()[8]);
        question.setText(getTexts()[10]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(11);
        question.setDate(getDates()[8]);
        question.setText(getTexts()[11]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(12);
        question.setDate(getDates()[8]);
        question.setText(getTexts()[12]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(13);
        question.setDate(getDates()[8]);
        question.setText(getTexts()[13]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.VERY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(14);
        question.setDate(getDates()[8]);
        question.setText(getTexts()[14]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(15);
        question.setDate(getDates()[8]);
        question.setText(getTexts()[15]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        // Day 10
        question = new Question();
        question.setQuestionId(0);
        question.setDate(getDates()[9]);
        question.setText(getTexts()[0]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(1);
        question.setDate(getDates()[9]);
        question.setText(getTexts()[1]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(2);
        question.setDate(getDates()[9]);
        question.setText(getTexts()[2]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.NOT_AT_ALL);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(3);
        question.setDate(getDates()[9]);
        question.setText(getTexts()[3]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(4);
        question.setDate(getDates()[9]);
        question.setText(getTexts()[4]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(5);
        question.setDate(getDates()[9]);
        question.setText(getTexts()[5]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.VERY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(6);
        question.setDate(getDates()[9]);
        question.setText(getTexts()[6]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(7);
        question.setDate(getDates()[9]);
        question.setText(getTexts()[7]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(8);
        question.setDate(getDates()[9]);
        question.setText(getTexts()[8]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(9);
        question.setDate(getDates()[9]);
        question.setText(getTexts()[9]);
        question.setQuestionType(QuestionType.DEPRESSION);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(10);
        question.setDate(getDates()[9]);
        question.setText(getTexts()[10]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(11);
        question.setDate(getDates()[9]);
        question.setText(getTexts()[11]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.EXTREMELY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(12);
        question.setDate(getDates()[9]);
        question.setText(getTexts()[12]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.NO);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(13);
        question.setDate(getDates()[9]);
        question.setText(getTexts()[13]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.VERY);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(14);
        question.setDate(getDates()[9]);
        question.setText(getTexts()[14]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.SOMEWHAT);
        question.setIsSynced(true);
        questions.add(question);

        question = new Question();
        question.setQuestionId(15);
        question.setDate(getDates()[9]);
        question.setText(getTexts()[15]);
        question.setQuestionType(QuestionType.ANXIETY);
        question.setAnswerType(AnswerType.YES);
        question.setDifficultyType(DifficultyType.NA);
        question.setIsSynced(true);
        questions.add(question);

        return questions;
    }
}
