package com.example.codeSeek.footballManager.controllers;

import com.example.codeSeek.footballManager.exceptions.ErrorResponse;
import com.example.codeSeek.footballManager.persistence.dao.service.interfaces.PlayerService;
import com.example.codeSeek.footballManager.persistence.dao.service.interfaces.TeamService;
import com.example.codeSeek.footballManager.persistence.model.Player;
import com.example.codeSeek.footballManager.persistence.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

//@RestController
@Controller
@RequestMapping("/player")
public class PlayerController {
    private PlayerService playerService;
    private TeamService teamService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Autowired
    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/tasks")
    public String playerTasks(){
        return "player_task";
    }

/*
    @GetMapping("/findByName/{name}")
    public List<Player> findPlayersByFirstName(@PathVariable String name){
        return playerService.findPlayersByFirstName(name);
    }
*/

   @PostMapping("/findByFirstName")
   public ModelAndView findPlayersByFirstName(@RequestParam("name") String firstName, ModelAndView modelAndView){
       if (!playerService.findPlayersByFirstName(firstName).isEmpty()){
            modelAndView.addObject("players", playerService.findPlayersByFirstName(firstName));
            modelAndView.setViewName("/search_player_results");
       }
       return modelAndView;
   }

   @PostMapping("/findByFirstAndLastName")
   public ModelAndView findPlayerByFirstNameAndLastName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, ModelAndView modelAndView){
       if (!playerService.findPLayerByFirstNameAndLastName(firstName,lastName).isEmpty()){
           modelAndView.addObject("players", playerService.findPLayerByFirstNameAndLastName(firstName,lastName));
           modelAndView.setViewName("/search_player_results");
       }
       return modelAndView;
   }

    @GetMapping("/findPlayersByTeam/{name}")
    public ModelAndView findPlayersInTeam(@PathVariable String name, ModelAndView modelAndView){
        if (!playerService.findPlayersByTeamName(name).isEmpty()){
            Team team = playerService.findPlayersByTeamName(name).stream().map(p -> p.getTeam()).findFirst().orElseThrow(NoSuchElementException::new);
            modelAndView.addObject("team", team);
            modelAndView.addObject("players", playerService.findPlayersByTeamName(name));
            modelAndView.setViewName("/search_player_by_team_name");
        }
        return modelAndView;
    }

  @PostMapping("/findPlayersByTeamName")
  public ModelAndView findPlayersByTeam(@RequestParam("teamName") String teamName, ModelAndView modelAndView){
      if (!playerService.findPlayersByTeamName(teamName).isEmpty()){
          Team team = playerService.findPlayersByTeamName(teamName).stream().map(p -> p.getTeam()).findFirst().orElseThrow(NoSuchElementException::new);
          modelAndView.addObject("team", team);
          modelAndView.addObject("players", playerService.findPlayersByTeamName(teamName));
          modelAndView.setViewName("/search_player_by_team_name");
      }
      return modelAndView;
  }

   /* @PutMapping ("/add/{name}")
    public Player addNewPLayer(@RequestBody Player player, @PathVariable String name){
        Team team = teamService.findTeamByName(name).stream().findFirst().orElseThrow(NoSuchElementException::new);
        player.setTeam(team);
        return playerService.addPlayer(player);
    }*/

    @PostMapping("/add")
    public ModelAndView addNewPLayer(HttpServletRequest request, ModelAndView modelAndView) throws ParseException {
        Player player = new Player();
        player.setFirstName(request.getParameter("first_name"));
        player.setLastName(request.getParameter("last_name"));

        String birthDateParameter = request.getParameter("birth_date");
        LocalDate birthDate = LocalDate.parse(birthDateParameter);
        player.setBirthDate(birthDate);

        String startCareerParameter = request.getParameter("start_career");
        LocalDate startCareer = LocalDate.parse(startCareerParameter);
        player.setStartCareer(startCareer);

        String teamName = request.getParameter("team_name");
        Team team = teamService.findTeamByName(teamName).stream().findFirst().orElseThrow(NoSuchElementException::new);
        player.setTeam(team);

        team.addPlayer(player);
        playerService.addPlayer(player);

        modelAndView.setViewName("redirect:/player/list");
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView getPlayersInfo(ModelAndView modelAndView){
        modelAndView.addObject("players", playerService.findAllPlayers());
        modelAndView.setViewName("/players");
        return modelAndView;
    }

    @GetMapping("/remove/{id}")
    public ModelAndView removePlayer(@PathVariable Integer id, ModelAndView modelAndView){
        Player player = new Player();
        Optional<Player> playerById = playerService.findPlayerById(id);
        if (playerById.isPresent()){
             player = playerById.get();
        }
        Team team = player.getTeam();
        playerService.removePlayerById(id);
        team.removePlayer(player);

        modelAndView.addObject("players", playerService.findAllPlayers());
        modelAndView.setViewName("redirect:/player/list");
        return modelAndView;
    }
}
