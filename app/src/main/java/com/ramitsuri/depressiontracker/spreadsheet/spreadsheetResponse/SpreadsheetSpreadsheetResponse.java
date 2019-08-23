package com.ramitsuri.depressiontracker.spreadsheet.spreadsheetResponse;

import com.google.api.services.sheets.v4.model.Spreadsheet;

public class SpreadsheetSpreadsheetResponse extends BaseSpreadsheetResponse {
    private Spreadsheet mSpreadsheet;

    public Spreadsheet getSpreadsheet() {
        return mSpreadsheet;
    }

    public void setSpreadsheet(Spreadsheet spreadsheet) {
        mSpreadsheet = spreadsheet;
    }
}
