package com.example.incident_management_system.models;

public class JwtResponse {
    private String jwtToken;
    private String userName;

    public static Builder builder() {
        return new Builder();
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public JwtResponse(String jwtToken, String userName) {
        this.jwtToken = jwtToken;
        this.userName = userName;
    }

    public JwtResponse() {
    }

    public static class Builder {
        private String jwtToken;
        private String userName;

        public Builder jwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this;
        }

        public Builder username(String userName) {
            this.userName = userName;
            return this;
        }

        public JwtResponse build() {
            return new JwtResponse(jwtToken, userName);
        }
    }
}
