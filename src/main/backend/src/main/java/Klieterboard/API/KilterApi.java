package Klieterboard.API;

import Klieterboard.entity.*;
import Klieterboard.projectRepository.Logbook;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;
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
     * <br> If the username is not clear, {@code null} is returned
     * <br> If the user is not found or there was an error, {@code null} is returned.
     */
    public User searchUser(String username){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/explore?q="+username+"&t=user"))
                .GET()
                .header("Cookie", "PHPSESSID=v328j3dchjemljsh4ns339fubq")
                .header("Cookie", "token="+token)
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
            JSONObject json;
            JSONArray json2 = new JSONObject(response.body()).getJSONArray("results");

            if (json2.isEmpty()){
                System.out.println("No results found, please enter a valid username");
                return null;
            }
            json = json2.getJSONObject(0);
            if (!json.getString(username).toLowerCase().equals(username.toLowerCase())) {
                System.out.println("Please enter a complete username");
                return null;
            }
            newUser.setUsername(json.getString("username"));
            newUser.setKilterId(""+ json.getInt("id"));
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
                .header("Cookie", "token="+token)
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
            JSONObject json = new JSONObject(response.body()).getJSONArray("users").getJSONObject(0);
            user.setKilterId(""+json.getInt("id"));
            user.setUsername(json.getString("username"));
            user.setName(json.getString("name"));
        } catch (JSONException e) {
            System.out.println("User not found");
            return null;
        }
        return user;
    }

    /**
     * Gets logbook from climber with the given id. Gets only ascents no bids.
     * @param id id of the user whose logbook is requested
     * @return a Logbook object. <br> If the user is not found or there was an error, {@code null} is returned.
     */
    public Logbook getLogBook(String id){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/users/"+id+"/logbook?types=ascent"))
                .GET()
                .header("Cookie", "PHPSESSID=v328j3dchjemljsh4ns339fubq")
                .header("Cookie", "token="+token)
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response;
        try{
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }

        try {
            return new Logbook(new JSONObject(response.body()).getJSONArray("logbook"));
        } catch (JSONException e) {
            return null;
        }
    }


    /**
     * Gets friends from climber with the given id.
     * @param id  id of the user whose friends are requested
     * @return A list containing the friends, an empty list if the user doesn't have friends and {@code null} if there was an error.
     */
    public List<Friends> getFriends(String id){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/users/"+id+"/followees"))
                .GET()
                .header("Cookie", "PHPSESSID=v328j3dchjemljsh4ns339fubq")
                .header("Cookie", "token="+token)
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response;
        try{
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        List<Friends> list =  new ArrayList<>();
        try {
            JSONArray jsonArray =  new JSONObject(response.body()).getJSONArray("users");
            if(jsonArray.isEmpty()) return list;
            for (int i = 0; i < jsonArray.length(); i++) {
                String username = jsonArray.getJSONObject(i).getString("username");
                list.add(new Friends(username));
            }
        } catch (JSONException e) {
            return null;
        }
        return list;
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
