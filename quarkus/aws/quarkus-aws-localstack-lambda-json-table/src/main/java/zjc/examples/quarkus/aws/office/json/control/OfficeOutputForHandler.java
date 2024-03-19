package zjc.examples.quarkus.aws.office.json.control;

import zjc.examples.quarkus.aws.office.json.boundary.InputObject;
import zjc.examples.quarkus.aws.office.json.boundary.OutputObject;
import zjc.examples.quarkus.aws.office.json.entity.OfficeTable;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.ApplicationScoped;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

@ApplicationScoped
public class  OfficeOutputForHandler implements Serializable {
    OfficeTable officeTable;

    public OutputObject getOfficeOutputForHandler(final InputObject input) {

        int officeId = (int) input.getOfficeId();

        final OfficeTable officeTable =  build(officeId);
        OutputObject out = new OutputObject();
        out.setOfficeTable(officeTable);

        out.setMessage("message");

        return out;
    }

    public static OfficeTable build(int id)  {

        String jsonString = "[\n" +
                "    {\n" +
                "      \"id\": \"0\", \"name\": \"name0\", \"country\": \"country0\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"1\", \"name\": \"name1\", \"country\": \"country1\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"2\", \"name\": \"name2\", \"country\": \"country2\"\n" +
                "    }\n" +
                "  ]";


        ObjectMapper objectMapper = new ObjectMapper();

        try {
            OfficeTable[] officeTable = objectMapper.readValue(jsonString, OfficeTable[].class);
            return officeTable[id];
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
