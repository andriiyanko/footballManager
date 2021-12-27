package com.example.codeSeek.footballManager.controllers;

import com.example.codeSeek.footballManager.persistence.dao.service.interfaces.PlayerService;
import com.example.codeSeek.footballManager.persistence.dao.service.interfaces.TeamService;
import com.example.codeSeek.footballManager.persistence.dao.service.interfaces.TransferService;
import com.example.codeSeek.footballManager.persistence.model.Player;
import com.example.codeSeek.footballManager.persistence.model.Team;
import com.example.codeSeek.footballManager.persistence.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

//@RestController
@Controller
@RequestMapping("/transfer")
public class TransferController {
    private TransferService transferService;
    private PlayerService playerService;
    private TeamService teamService;

    @Autowired
    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Autowired
    public void setTransferService(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping("/tasks")
    public String teamTasks(){
        return "transfer_task";
    }

    @GetMapping("/mainPage")
    public String backToMainPage(){
        return "index";
    }

    @PostMapping("/doTransfer")
    public ModelAndView doTransfer(HttpServletRequest request, ModelAndView modelAndView){
        Transfer transfer = new Transfer();
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String transferToTeam = request.getParameter("team_To");

        double commissionParameter = Integer.parseInt(request.getParameter("commission")) / 100;

        String transferDateParameter = request.getParameter("transfer_date");
        LocalDate transferDate = LocalDate.parse(transferDateParameter);

        Team toTeam = teamService.findTeamByName(transferToTeam).stream().findFirst().orElseThrow(NoSuchElementException::new);

        Player player = playerService.findPLayerByFirstNameAndLastName(firstName, lastName).stream().findFirst().orElseThrow(NoSuchElementException::new);
        Team oldTeam = player.getTeam();

        int transferCost = player.getExperience() * 100000 / player.getAge();
        int commission = (int) (commissionParameter * transferCost);
        int transferSum = transferCost + commission;

        toTeam.setBalance(toTeam.getBalance() - transferSum);
        oldTeam.setBalance(oldTeam.getBalance() + transferSum);

        player.setTeam(toTeam);
        toTeam.addPlayer(player);
        oldTeam.removePlayer(player);

        transfer.setPlayer(player);
        transfer.setTransferDate(transferDate);
        transfer.setTeamTo(toTeam);

        toTeam.addTransfer(transfer);
        oldTeam.addTransfer(transfer);

        transferService.doTransfer(transfer);

        modelAndView.setViewName("redirect:/transfer/list");

        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView findAllTransfers(ModelAndView modelAndView){
        if (!transferService.findAllTransfers().isEmpty()){
            modelAndView.addObject("transfers", transferService.findAllTransfers());
            modelAndView.setViewName("/transfers");
        }
        return modelAndView;
    }

    @GetMapping("/id/{id}")
    public Transfer findTransferById(@PathVariable Integer id){
        Transfer transfer = new Transfer();
        Optional<Transfer> transferById = transferService.findTransferById(id);
        if (transferById.isPresent()){
             transfer = transferById.get();
        }
        return transfer;
    }
}
