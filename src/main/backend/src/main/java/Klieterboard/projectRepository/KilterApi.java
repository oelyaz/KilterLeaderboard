package Klieterboard.projectRepository;

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
@Configuration
public class KilterApi {

    @Getter
    private String token;

    private String baseUrl;

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
     * Determines the session token.
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
            System.out.println("JSON Exception, token not available.");;
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



}
