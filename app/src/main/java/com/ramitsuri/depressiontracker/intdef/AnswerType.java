package com.ramitsuri.depressiontracker.intdef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

@IntDef({AnswerType.YES, AnswerType.NO})
@Retention(RetentionPolicy.SOURCE)
public @interface AnswerType {
    int YES = 0;
    int NO = 1;
}
