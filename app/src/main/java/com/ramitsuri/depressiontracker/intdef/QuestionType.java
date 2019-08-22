package com.ramitsuri.depressiontracker.intdef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

@IntDef({QuestionType.DEPRESSION, QuestionType.ANXIETY})
@Retention(RetentionPolicy.SOURCE)
public @interface QuestionType {
    int DEPRESSION = 0;
    int ANXIETY = 1;
}
