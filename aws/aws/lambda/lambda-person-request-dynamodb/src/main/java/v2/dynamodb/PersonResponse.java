package v2.dynamodb;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class PersonResponse {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "PersonResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}