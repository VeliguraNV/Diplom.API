import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class UserLoginTest {
    private StellarBurgerClient client = new StellarBurgerClient();
    private User user;
    private String token;

    @Before
    public void createUser_success() {
        user = new User(System.currentTimeMillis() + "@mail.ru", "password", "Username");
        ValidatableResponse response = client.createUser(user);
        token = response.extract().jsonPath().getString("accessToken");
        response.assertThat()
                .statusCode(200)
                .body("success",is(true));
    }


    @Test
    public void loginUser_success() {

        UserCredentials creds = UserCredentials.fromUser(user, token);
        ValidatableResponse response = client.loginUser(creds);

        response.assertThat().statusCode(200);
    }

@After
public void deleteUser_afterTest() {
    if (token != null) {
        client.deleteUser(token);
        //Рабочий комп не дает провести этот метод, поэтому всегда 404
    }
}
    }
