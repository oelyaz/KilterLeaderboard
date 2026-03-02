package Klieterboard.controller;

import Klieterboard.entity.Winner;
import Klieterboard.service.WinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RestController
@RequestMapping("/winner")
public class WinnerController {

    private final WinnerService winnerService;

    @Autowired
    public WinnerController(WinnerService winnerService) {
        this.winnerService = winnerService;
    }

    /**
     * Returns a list of all winners.
     * @return A list of all winners.
     */
    @CrossOrigin
    @GetMapping("/")
    public List<Winner> findAll(){
        return winnerService.findAll();
    }
}
