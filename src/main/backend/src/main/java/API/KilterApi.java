package API;

import Klieterboard.entity.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import org.json.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;


@Service
@PropertySource("classpath:credentials.properties")
//@Configuration
public class KilterApi {

    @Getter
    private String token;

    private final String baseUrl;

    @Value("${kilter.username}")
    private String kilter_username;
    @Value("${kilter.password}")
    private String kilter_password;

//    private final WebClient webclient;

//    @Autowired
//    public KilterApi(WebClient webclient) {
//        this.webclient = webclient;
//        this.baseUrl = "https://kilterboardapp.com";
//    }

    public KilterApi() {
        this.baseUrl = "https://kilterboardapp.com";
    }


    /**
     * Determines the session token. Used to Log in.
     */
    public void determineToken(){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/sessions"))
                .header("Cookie", "PHPSESSID=v328j3dchjemljsh4ns339fubq")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(
                        "{"
                                + "\"password\": \"" + kilter_password +"\", "
                                + "\"pp\" : \"accepted\", "
                                + "\"tou\": \"accepted\", "
                                + "\"ua\": \"app\", "
                                + "\"username\": \"" +kilter_username +"\""
                                + "}"
                ))
                .build();
        HttpResponse<String> response;
        try{
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return;
        }
        try {
            token =  new JSONObject(response.body()).getJSONObject("session").getString("token");
        } catch (JSONException e) {
            System.out.println("token not available, JSON Exception "+e);;
        }
    }

    //    public void getToken(){
//
//        String response = webclient.post()
//                .uri(URI.create(baseUrl + "/sessions"))
//                .header("Cookie", "PHPSESSID=v328j3dchjemljsh4ns339fubq")
//                .header("Cache-Control", "no-cache")
//                .header("Content-Type", "application/json")
//                .header("Accept", "*/*")
//                .bodyValue("{"
//                        + "\"password\": \"" + kilter_password +"\", "
//                        + "\"pp\" : \"accepted\", "
//                        + "\"tou\": \"accepted\", "
//                        + "\"ua\": \"app\", "
//                        + "\"username\": \"" +kilter_username +"\""
//                        + "}")
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//    }


    /**
     * Searches a user based on their username.
     * @param username username of the searched user
     * @return The found user.
     * <br> If the user is not found or there was an error, {@code null} is returned.
     */
    public User searchUser(String username){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/explore?q="+username+"&t=user"))
                .GET()
                .header("Cookie", "PHPSESSID=v328j3dchjemljsh4ns339fubq")
                .header("Cookie", "\""+token+"\"")
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response;
        try{
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        User newUser = new User();
        try {
            JSONObject json = new JSONObject(response.body()).getJSONObject("results");
            newUser.setUsername(json.getString("username"));
            newUser.setKilterId(json.getString("id"));
            newUser.setName(json.getString("name"));
        } catch (JSONException e) {
            System.out.println("User not found");
            return null;
        }
        return newUser;
    }

    /**
     * Searches a user based on their id.
     * @param id id of the searched user
     * @return The found user.
     * <br> If the user is not found or there was an error, {@code null} is returned.
     */
    public User getUserById(String id){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/users/"+id))
                .GET()
                .header("Cookie", "PHPSESSID=v328j3dchjemljsh4ns339fubq")
                .header("Cookie", "\""+token+"\"")
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response;
        try{
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        User user = new User();

        try {
            JSONObject json = new JSONObject(response.body()).getJSONObject("users");
            user.setKilterId(json.getString("id"));
            user.setUsername(json.getString("username"));
            user.setName(json.getString("name"));
        } catch (JSONException e) {
            System.out.println("User not found");
            return null;
        }
        return user;
    }

    /**
     * Gets logbook from climber with the given id.
     * @param id id of the user whose logbook is requested
     * @return JSON-Array containing the climbs. <br> If the user is not found or there was an error, {@code null} is returned.
     */
    //TODO should return an Object, not yet thought about which, new class will probably be needed
    public JSONArray getLogBook(String id){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/users/"+id+"/logbook?types=bid,ascent"))
                .GET()
                .header("Cookie", "PHPSESSID=v328j3dchjemljsh4ns339fubq")
                .header("Cookie", "\""+token+"\"")
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response;
        try{
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }

        return new JSONObject(response.body()).getJSONArray("logbook");
    }


    /**
     * Log out, token is invalidated.
     */
    public void logOut(){
        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .header("Cookie", "PHPSESSID=v328j3dchjemljsh4ns339fubq")
                .header("Authorization", "Bearer " + "92efbafe12efd323cb00464461782040ad862cfc")
                .build();
        HttpResponse<String> response;
        try{
            HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
