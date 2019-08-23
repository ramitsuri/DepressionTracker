package com.ramitsuri.depressiontracker.spreadsheet.consumerResponse;

public class BaseConsumerResponse {
    private Exception mException;

    public Exception getException() {
        return mException;
    }

    public void setException(Exception exception) {
        mException = exception;
    }

    @Override
    public String toString() {
        return "BaseConsumerResponse{" +
                "mException=" + mException +
                '}';
    }
}
