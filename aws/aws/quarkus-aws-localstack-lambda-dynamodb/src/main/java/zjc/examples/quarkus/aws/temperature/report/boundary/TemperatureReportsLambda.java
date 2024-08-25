package zjc.examples.quarkus.aws.temperature.report.boundary;

import zjc.examples.quarkus.aws.temperature.report.control.TemperatureReportStore;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("temperatureReportLambda")
public class TemperatureReportsLambda implements RequestHandler<InputObject, OutputObject> {

    @Inject
    TemperatureReportStore reportStore;

    @Override
    public OutputObject handleRequest(InputObject input, Context context) {
        final var output = reportStore.getOfficeTemperatureReport(input);
        output.setRequestId(context.getAwsRequestId());
        return output;
    }
}
