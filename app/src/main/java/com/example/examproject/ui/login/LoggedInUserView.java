package com.example.examproject.ui.login;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private String displayName;
    private String token;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(String token, String displayName) {
        this.token = token;
        this.displayName = displayName;

    }

    String getDisplayName() {
        return displayName;
    }

    String getToken(){
        return token;
    }
}