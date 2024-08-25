package zjc.examples.quarkus.aws.office.json.boundary;

import zjc.examples.quarkus.aws.office.json.control.OfficeOutputForHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("officeLambda")
public class OfficeLambda implements RequestHandler<InputObject, OutputObject> {

    @Inject
    OfficeOutputForHandler officeOutputForHandler;


    @Override
    public OutputObject handleRequest(InputObject input, Context context) {
        final var output = officeOutputForHandler.getOfficeOutputForHandler(input);
        output.setRequestId(context.getAwsRequestId());
        return output;
    }
}
