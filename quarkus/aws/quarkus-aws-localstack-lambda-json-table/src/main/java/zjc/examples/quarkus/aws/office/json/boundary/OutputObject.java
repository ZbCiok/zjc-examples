package zjc.examples.quarkus.aws.office.json.boundary;

import zjc.examples.quarkus.aws.office.json.entity.OfficeTable;

public class OutputObject {

    private String requestId;

    private OfficeTable officeTable;

    private String message;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(final String requestId) {
        this.requestId = requestId;
    }

    public OfficeTable getOfficeTable() {
        return officeTable;
    }

    public void setOfficeTable(final OfficeTable officeTable) {
        this.officeTable = officeTable;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
