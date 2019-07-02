import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.moneytransfer.app.model.SendUser;
import com.moneytransfer.app.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.jayway.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferControllerTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 4567;
    }

    @Test
    public void addUserTest() {
        String exampleAddUserJson = "{\"userName\":\"john_smith12\",\"name\":\"John Smith\",\"age\":\"22\",\"bankAmount\":\"5000\"}";
        Gson gson = new Gson();
        User json = gson.fromJson(exampleAddUserJson, User.class);
        given().contentType("application/json")
                .body(json)
                .when().post("/users").then()
                .statusCode(200);
    }

    @Test
    public void getUserJohnSmith() {
        given().when().get("/user/john_smith12").then()
                .statusCode(200);
    }

    @Test
    public void addSecondUserTest() {
        String addUserJson = "{\"userName\":\"frank_adams45\",\"name\":\"Frank Adams\",\"age\":\"35\",\"bankAmount\":\"25000\"}";
        Gson gson = new Gson();
        User json = gson.fromJson(addUserJson, User.class);
        given().contentType("application/json")
                .body(json)
                .when().post("/users").then()
                .statusCode(200);
    }

    @Test
    public void getUserFrankAdams() {
        given().when().get("/user/frank_adams45").then()
                .statusCode(200);
    }

    @Test
    public void getAllUsersTest() {
        given().when().get("/users").then()
                .statusCode(200);
    }

    /*
        Send over balanced amount
     */
    @Test
    public void sendMoneyToFrankAdamsTest() {
        String sendMoneyJson = "{\"sender\":\"john_smith12\",\"receiver\":\"frank_adams45\",\"sendAmount\":\"6000\"}";
        Gson gson = new Gson();
        SendUser json = gson.fromJson(sendMoneyJson, SendUser.class);
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.body(json);
        Response response = httpRequest.post("/send-money");
        String expectedResponse = "{\"responseStatus\":\"Failed transfer, low balances\"}";
        assertEquals(expectedResponse, response.asString());
    }

    /*
        Send correct balanced amount
     */
    @Test
    public void sendMoneyToFrankAdamsTest2() {
        String sendMoneyJson = "{\"sender\":\"john_smith12\",\"receiver\":\"frank_adams45\",\"sendAmount\":\"1000\"}";
        Gson gson = new Gson();
        SendUser json = gson.fromJson(sendMoneyJson, SendUser.class);
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.body(json);
        Response response = httpRequest.post("/send-money");
        String expectedResponse = "{\"responseStatus\":\"Success\"}";
        assertEquals(expectedResponse, response.asString());
    }

}
