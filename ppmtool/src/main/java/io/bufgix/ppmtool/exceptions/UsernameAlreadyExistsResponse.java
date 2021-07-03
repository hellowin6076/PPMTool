package io.bufgix.ppmtool.exceptions;

public class UsernameAlreadyExistsResponse {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UsernameAlreadyExistsResponse(String username) {
        this.username = username;
    }
}
