package com.example.codeSeek.footballManager.persistence.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "teams")
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String country;
    private String town;
    private int balance;

    @JsonManagedReference
    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Player> players;

    @JsonManagedReference
    @OneToMany(mappedBy = "teamTo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Transfer> transfers;


    public void addPlayer(Player player){
        players.add(player);
    }

    public void removePlayer(Player player){
        players.remove(player);
    }

    public void addTransfer(Transfer transfer){
        transfers.add(transfer);
    }

    public void removeTransfer(Transfer transfer){
        transfers.remove(transfer);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return id == team.id && balance == team.balance && name.equals(team.name) && country.equals(team.country) && town.equals(team.town);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, town, balance);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", town='" + town + '\'' +
                ", balance=" + balance +
                '}';
    }
}
