package com.ramitsuri.depressiontracker.intdef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

@IntDef({DifficultyType.NA, DifficultyType.NOT_AT_ALL, DifficultyType.SOMEWHAT, DifficultyType.VERY,
        DifficultyType.EXTREMELY})
@Retention(RetentionPolicy.SOURCE)
public @interface DifficultyType {
    int NA = 0;
    int NOT_AT_ALL = 1;
    int SOMEWHAT = 2;
    int VERY = 3;
    int EXTREMELY = 4;
}
