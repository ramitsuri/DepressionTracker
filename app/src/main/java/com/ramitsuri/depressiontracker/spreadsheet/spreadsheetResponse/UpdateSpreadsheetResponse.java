package com.ramitsuri.depressiontracker.spreadsheet.spreadsheetResponse;

import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetResponse;

public class UpdateSpreadsheetResponse extends BaseSpreadsheetResponse {
    private BatchUpdateSpreadsheetResponse mBatchUpdateSpreadsheetResponse;

    public BatchUpdateSpreadsheetResponse getBatchUpdateSpreadsheetResponse() {
        return mBatchUpdateSpreadsheetResponse;
    }

    public void setBatchUpdateSpreadsheetResponse(
            BatchUpdateSpreadsheetResponse batchUpdateSpreadsheetResponse) {
        mBatchUpdateSpreadsheetResponse = batchUpdateSpreadsheetResponse;
    }
}
