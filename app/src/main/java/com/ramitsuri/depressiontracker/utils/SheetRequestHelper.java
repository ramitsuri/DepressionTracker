package com.ramitsuri.depressiontracker.utils;

import android.util.LongSparseArray;

import com.google.api.services.sheets.v4.model.AppendCellsRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.BooleanCondition;
import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.DataValidationRule;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import com.google.api.services.sheets.v4.model.NumberFormat;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.RowData;
import com.ramitsuri.depressiontracker.entities.Question;
import com.ramitsuri.depressiontracker.intdef.AnswerType;
import com.ramitsuri.depressiontracker.intdef.DifficultyType;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class SheetRequestHelper {

    public static BatchUpdateSpreadsheetRequest getUpdateRequestBody(List<Question> questionList,
            String sheetId) {
        BatchUpdateSpreadsheetRequest requestBody = new BatchUpdateSpreadsheetRequest();
        List<Request> requests = new ArrayList<>();
        Request request = new Request();
        AppendCellsRequest appendCellsRequest = new AppendCellsRequest();
        appendCellsRequest.setFields("*");
        int sheetIdInt;
        try {
            sheetIdInt = Integer.parseInt(sheetId);
        } catch (NumberFormatException ex) {
            Timber.e("Failed to convert sheet id to string");
            return null;
        }
        appendCellsRequest.setSheetId(sheetIdInt);

        LongSparseArray<List<Question>> groupedQuestions = getGroupedQuestions(questionList);

        List<RowData> rowDataList = new ArrayList<>();
        for (int i = 0; i < groupedQuestions.size(); i++) {
            List<Question> group = groupedQuestions.get(groupedQuestions.keyAt(i));
            RowData rowData = new RowData();
            List<CellData> cellDataList = new ArrayList<>();
            CellData cellData;

            // Date
            cellData = new CellData();
            cellData.setUserEnteredValue(new ExtendedValue()
                    .setNumberValue(DateHelper.toSheetsDate(group.get(0).getDate())));
            cellData.setUserEnteredFormat(
                    new CellFormat().setNumberFormat(
                            new NumberFormat().setType("DATE").setPattern("M/d/yyyy")));
            cellData.setDataValidation(new DataValidationRule()
                    .setCondition(new BooleanCondition().setType("DATE_IS_VALID")));
            cellDataList.add(cellData);

            for (Question question : group) {
                cellData = new CellData();
                cellData.setUserEnteredValue(
                        new ExtendedValue().setStringValue(getFormattedAnswer(question)));
                cellDataList.add(cellData);
            }
            rowData.setValues(cellDataList);
            rowDataList.add(rowData);
        }
        appendCellsRequest.setRows(rowDataList);
        request.setAppendCells(appendCellsRequest);
        requests.add(request);
        requestBody.setRequests(requests);
        return requestBody;
    }

    private static String getFormattedAnswer(Question question) {
        StringBuilder sb = new StringBuilder();

        if (question.getAnswerType() == AnswerType.NO) {
            sb.append("NO");
        } else {
            sb.append("YES, ");
            switch (question.getDifficultyType()) {
                case DifficultyType.NA:
                    sb.append("NA");
                    break;

                case DifficultyType.NOT_AT_ALL:
                    sb.append("Not at all");
                    break;

                case DifficultyType.SOMEWHAT:
                    sb.append("Somewhat");
                    break;

                case DifficultyType.VERY:
                    sb.append("Very");
                    break;

                case DifficultyType.EXTREMELY:
                    sb.append("Extremely");
                    break;
            }
        }
        return sb.toString();
    }

    private static LongSparseArray<List<Question>> getGroupedQuestions(
            List<Question> questionList) {
        LongSparseArray<List<Question>> groupedQuestions = new LongSparseArray<>();
        for (Question question : questionList) {
            List<Question> questions;

            if (groupedQuestions.get(question.getDate()) == null) {
                questions = new ArrayList<>();
            } else {
                questions = groupedQuestions.get(question.getDate());
            }
            questions.add(question);
            groupedQuestions.put(question.getDate(), questions);
        }
        return groupedQuestions;
    }
}
