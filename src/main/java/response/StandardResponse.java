package response;

import com.google.gson.JsonElement;

public class StandardResponse {
    private String responseStatus;
    private String message;
    private JsonElement userData;

    public StandardResponse(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public StandardResponse(String responseStatus, String message) {
        this.responseStatus = responseStatus;
        this.message = message;
    }

    public StandardResponse(String responseStatus, JsonElement userData) {
        this.responseStatus = responseStatus;
        this.userData = userData;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonElement getUserData() {
        return userData;
    }

    public void setUserData(JsonElement userData) {
        this.userData = userData;
    }
}
