package Klieterboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@CrossOrigin(origins = "*")
@Controller
@RestController
@RequestMapping(path = "/")
public class FrontendController {

    public FrontendController() {

    }

    /**
     * Redirects view to the frontend.
     * @return the redirected view
     */
    @CrossOrigin
    @RequestMapping(value = {"", "register"})
    public RedirectView index() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("https://kilterleaderboard.de");
        return redirectView;
    }
}