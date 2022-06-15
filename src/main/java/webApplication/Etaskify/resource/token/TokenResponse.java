package webApplication.Etaskify.resource.token;

import java.util.List;


public class TokenResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> authority;

    public TokenResponse(String accessToken, Long id, String username, String email, List<String> authority) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.authority = authority;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getAuthority() {
        return authority;
    }
}