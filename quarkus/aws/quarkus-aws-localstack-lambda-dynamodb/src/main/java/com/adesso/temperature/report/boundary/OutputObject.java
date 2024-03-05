package com.adesso.temperature.report.boundary;

import com.adesso.temperature.report.entity.OfficeReport;

public class OutputObject {

    private String requestId;

    private OfficeReport officeReport;

    private String message;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(final String requestId) {
        this.requestId = requestId;
    }

    public OfficeReport getOfficeReport() {
        return officeReport;
    }

    public void setOfficeReport(final OfficeReport officeReport) {
        this.officeReport = officeReport;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
