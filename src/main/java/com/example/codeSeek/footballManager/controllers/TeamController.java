package com.example.codeSeek.footballManager.controllers;

import com.example.codeSeek.footballManager.persistence.dao.service.interfaces.TeamService;
import com.example.codeSeek.footballManager.persistence.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


//@RestController
@Controller
@RequestMapping("/team")
public class TeamController {
    private TeamService teamService;

    @Autowired
    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/tasks")
    public String teamTasks(){
        return "team_task";
    }

    @GetMapping("/mainPage")
    public String backToMainPage(){
        return "index";
    }

    @GetMapping("/name/{name}")
    public ModelAndView findTeamByName(@PathVariable String name, ModelAndView modelAndView){
        if (!teamService.findTeamByName(name).isEmpty()){
            modelAndView.addObject("teams", teamService.findTeamByName(name));
            modelAndView.setViewName("/search_results");
        }
        return modelAndView;
    }

    @PostMapping("/name")
    public ModelAndView findTeamsByName(@RequestParam("name") String name, ModelAndView modelAndView){
//        List<Team> teamList = teamService.findTeamByName(name).stream().filter(s -> s.getName().contains(name)).collect(Collectors.toList());
        if (!teamService.findTeamByName(name).isEmpty()){
            modelAndView.addObject("teams", teamService.findTeamByName(name));
            modelAndView.setViewName("/search_results");
        }
        return modelAndView;
    }


    /*@GetMapping("/country/{country}")
    public List<Team> findTeamByCountry(@PathVariable String country){
        return teamService.findTeamByCountry(country);
    }*/

    @PostMapping("/country")
    public ModelAndView findTeamByCountry(@RequestParam("country") String country, ModelAndView modelAndView){
        if (!teamService.findTeamByCountry(country).isEmpty()){
            modelAndView.addObject("teams", teamService.findTeamByCountry(country));
            modelAndView.setViewName("/search_results");
        }
        return modelAndView;
    }


    @GetMapping("/id/{id}")
    public Team findTeamById(@PathVariable Integer id){
        Team team = new Team();
        Optional<Team> teamById = teamService.findTeamById(id);
        if (teamById.isPresent()){
             team = teamById.get();
        }
        return team;
    }

    @PostMapping("/name_country")
    public ModelAndView findTeamByNameAndCountry(@RequestParam("name") String name, @RequestParam("country") String country, ModelAndView modelAndView){
        if (!teamService.findTeamByNameAndCountry(name,country).isEmpty()){
            modelAndView.addObject("teams", teamService.findTeamByNameAndCountry(name,country));
            modelAndView.setViewName("/search_results");
        }
        return modelAndView;
    }

  /*  @GetMapping("/list")
    public List<Team> getTeamsInfo(){
        return teamService.findAllTeams();
    }
    */

    @GetMapping("/list")
    public ModelAndView getTeamsInfo(ModelAndView modelAndView){
        modelAndView.addObject("teams", teamService.findAllTeams());
        modelAndView.setViewName("/teams");
        return modelAndView;
    }

/*
    @PutMapping ("/add")
    public Team addNewTeam(@RequestBody Team team){
        return teamService.addTeam(team);
    }
*/
    @PostMapping("/add")
    public ModelAndView addTeam(HttpServletRequest request, ModelAndView modelAndView){
        Team team = new Team();
        team.setName(request.getParameter("name"));
        team.setCountry(request.getParameter("country"));
        team.setTown(request.getParameter("town"));
        team.setBalance(Integer.parseInt(request.getParameter("balance")));
        teamService.addTeam(team);
        modelAndView.setViewName("redirect:/team/list");
        return modelAndView;
    }

/*    @GetMapping("/remove/{id}")
    public void removeTeamById(@PathVariable Integer id){
        teamService.removeTeamById(id);
    }*/

    @GetMapping("/remove/{id}")
    public ModelAndView removeTeam(@PathVariable Integer id, ModelAndView modelAndView){
        teamService.removeTeamById(id);
        modelAndView.addObject("teams", teamService.findAllTeams());
        modelAndView.setViewName("redirect:/team/list");
        return modelAndView;
    }
}
