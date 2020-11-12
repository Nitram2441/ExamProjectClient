package com.example.examproject.data;

import com.example.examproject.data.model.LoggedInUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {
        HttpURLConnection c = null;
        try {
            URL url = new URL("http://192.168.0.103:8080/ExamProject/api/auth/login?uid=" + username + "&pwd=" + password);
            c = (HttpURLConnection) url.openConnection();
            c.setUseCaches(true);
            c.setRequestMethod("GET");

            if(c.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream(), "UTF-8"));
                String token = br.readLine();
                System.out.println(token);
                LoggedInUser fakeUser = new LoggedInUser(username, token);
                c.getInputStream().close();
                return new Result.Success<>(fakeUser);
            }
            return new Result.Error(new IOException("Error logging in " + c.getResponseMessage()));


        } catch (Exception e) {
            System.err.println("Failed to call " + e);
            return new Result.Error(new IOException("Error logging in", e));
        } finally{
            if(c != null) c.disconnect();
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}