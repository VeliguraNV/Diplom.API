public class UserCredentials {
    private final String email;
    private final String password;
    private final String token;


    private UserCredentials(String email, String password, String token) {
        this.email = email;
        this.password = password;
        this.token = token;
    }

    public static UserCredentials fromUser(User user, String token) {
        return new UserCredentials(user.getEmail(), user.getPassword(), token);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }
}