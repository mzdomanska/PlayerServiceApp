package com.kchmielewski.sda.java.spring01java.web;

import com.kchmielewski.sda.java.spring01java.entity.PlayerEntity;
import com.kchmielewski.sda.java.spring01java.model.Player;
import com.kchmielewski.sda.java.spring01java.service.PlayerService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@SessionAttributes("playerSession")
public class PlayerController {

    private PlayerService service;
    private MessageSource messageSource;

    public PlayerController(PlayerService service, MessageSource messageSource) {
        this.service = service;
        this.messageSource = messageSource;
    }

    @ModelAttribute
    public Player defaultPlayer() {
        return PlayerService.toPlayerDto(new PlayerEntity("Jim","Moriarty"));
    }

    @ModelAttribute
    public PlayerSession session() {
        return new PlayerSession();
    }

    @GetMapping(value = "/players")
    public String getAllPlayers(Model model) {

        model.addAttribute("players",service.getAllPlayers());
        return "playersDisplay";
    }

    @PostMapping(value = "/players")
    public String addNewPlayer(@Valid @ModelAttribute Player player, BindingResult bindingResult, @RequestParam("action") String paramValue,
                               @RequestParam("playerToEdit") String playerToEditId, @RequestParam("playerToDelete") String playerToDeleteId,
                               @ModelAttribute PlayerSession session, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("players",service.getAllPlayers());
            return "playersDisplay";
        }
        else {
            if (paramValue.equals("ADD")) {
                model.addAttribute("players",service.addNewPlayer(player));
                session.setCounter(session.getCounter()+1);
                session.setRecentylAddedPlayer(player);
            }
            else if (paramValue.equals("EDIT")) {
                model.addAttribute("playerToEdit",service.getPlayerByID(playerToEditId));
                return "editPlayer";
            }
            else if (paramValue.equals("DELETE")){
                model.addAttribute("players",service.deletePlayerById(Integer.parseInt(playerToDeleteId)));
                return "redirect:players";
            }
            return "redirect:players";
        }
    }

    @PostMapping("/edit")
    public String editPlayer(@Valid @ModelAttribute Player player, @RequestParam("playerToEdit") String playerToEditId, BindingResult bindingResult, Model model) {

        service.editPlayer(service.getPlayerByID(playerToEditId));
        model.addAttribute("Players",service.getAllPlayers());
        return "redirect:players";
    }

    @GetMapping(value = "/players/get/{number}")
    public String getSpecificPlayer(@PathVariable int number, Model model) {

        List<Player> singlePlayerList = new ArrayList<>();
        Player selectedPlayer = service.getAllPlayers().get(number);
        singlePlayerList.add(selectedPlayer);
        model.addAttribute("players",singlePlayerList);

        return "specificPlayersDisplay";
    }

    @GetMapping(value = "/players/get/{from}/{to}")
    public String getSpecificPlayer(@PathVariable int from, @PathVariable int to, Model model) {

        model.addAttribute("players",service.getAllPlayers().subList(from,to));
        return "specificPlayersDisplay";
    }

    @ExceptionHandler(Exception.class)
    public String error(Exception exception, Model model, Locale locale) {
        model.addAttribute("exception",exception);
        model.addAttribute("message",messageSource.getMessage("error.message",null,locale));

        return "errors";
    }
}
