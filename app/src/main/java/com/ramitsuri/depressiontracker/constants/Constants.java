package com.ramitsuri.depressiontracker.constants;

import com.google.api.services.sheets.v4.SheetsScopes;

public class Constants {

    public static final String[] SCOPES = {SheetsScopes.SPREADSHEETS, SheetsScopes.DRIVE};

    public class RequestCode {
        public static final int GOOGLE_SIGN_IN = 100;
    }
}
