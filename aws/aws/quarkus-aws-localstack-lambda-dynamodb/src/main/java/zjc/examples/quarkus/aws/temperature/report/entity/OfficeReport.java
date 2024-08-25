package zjc.examples.quarkus.aws.temperature.report.entity;

import java.util.List;
import java.util.Objects;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class OfficeReport {

    private Long id;

    private String name;

    private String country;
    private List<TemperatureReportItem> weeklyReports;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("id")
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @DynamoDbAttribute("name")
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @DynamoDbAttribute("country")
    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    @DynamoDbAttribute("weeklyReports")
    public List<TemperatureReportItem> getWeeklyReports() {
        return weeklyReports;
    }

    public void setWeeklyReports(final List<TemperatureReportItem> weeklyReports) {
        this.weeklyReports = weeklyReports;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OfficeReport)) {
            return false;
        }
        final OfficeReport that = (OfficeReport) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country);
    }
}
