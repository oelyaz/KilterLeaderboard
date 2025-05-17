package Klieterboard.controller;

@Controller
public class FrontendController {

    @RequestMapping(value = {"/", "/register"})
    public String index() {
        return "forward:/app.html";
    }
}