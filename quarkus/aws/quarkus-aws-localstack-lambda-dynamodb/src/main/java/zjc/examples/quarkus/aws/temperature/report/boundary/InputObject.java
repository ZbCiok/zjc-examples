package zjc.examples.quarkus.aws.temperature.report.boundary;

public class InputObject {

    private long officeId;

    public long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(final long officeId) {
        this.officeId = officeId;
    }
}
