import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.moneytransfer.app.model.User;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class MoneyTransferControllerTest {

    public static HashMap<String, User> mockUserMap;

    @BeforeClass
    public static void setup() {
        mockUserMap = new HashMap<>();
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
    public void getAllUserTest() {
        given().when().get("/users").then()
                .body(containsString("john_smith12"));
    }
}
