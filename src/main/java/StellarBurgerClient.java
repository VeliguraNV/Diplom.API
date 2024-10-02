import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class StellarBurgerClient {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    public ValidatableResponse createUser(User user) {
        return given()
                .log()
                .all()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(user)
                .post("/api/auth/register")
                .then()
                .log()
                .all();
    }

    public ValidatableResponse loginUser(UserCredentials credentials) {
        return given()
                .log()
                .all()
                .auth()
                .oauth2(credentials.getToken())
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(credentials)
                .post("/api/auth/login")
                .then()
                .log()
                .all();
    }

    public void logoutUser(String refreshToken) {
        given()
                .log()
                .all()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body("{\n" +
                        "    \"token\":\"" + refreshToken + "\"\n" +
                        "}")
                .delete("/api/auth/logout")
                .then()
                .log()
                .all();
    }

    public void deleteUser(String token) {
         given()
                .log()
                .all()
                .auth()
                .oauth2("Bearer " + token )
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .delete("/api/auth/login")
                .then()
                .log()
                .all();
                  //рабочий комп не дает запустить метод
           }
}



