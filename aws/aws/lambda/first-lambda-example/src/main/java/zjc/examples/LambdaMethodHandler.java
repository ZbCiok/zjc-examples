package zjc.examples;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.logging.LogLevel;


public class LambdaMethodHandler {

    public Response handleRequest(Request request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log(request.name() + " has invoked the lambda function", LogLevel.INFO);
        return new Response("Subscribe: jreact.com");
    }

    record Request(String name) {}

    record Response(String answer) {}
}

