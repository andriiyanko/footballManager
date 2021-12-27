package com.example.codeSeek.footballManager.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "players")
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "start_career")
    private LocalDate startCareer;

    @Transient
    private int age;

    @Transient
    private int experience;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    @JsonBackReference
    private Team team;

    @JsonManagedReference
    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transfer> transfers;

    public void addTransfer(Transfer transfer){
        transfers.add(transfer);
    }

    public void removeTransfer(Transfer transfer){
        transfers.remove(transfer);
    }

    public int getAge() {
        int year = this.birthDate.getYear();
        int month = this.birthDate.getMonthValue();
        int dayOfMonth = this.birthDate.getDayOfMonth();
        this.age = Period.between(LocalDate.of(year,month,dayOfMonth),LocalDate.now()).getYears();
        return age;
    }

    public int getExperience() {
        int year = this.startCareer.getYear();
        int month = this.startCareer.getMonthValue();
        int dayOfMonth = this.startCareer.getDayOfMonth();
        this.experience = Period.between(LocalDate.of(year,month,dayOfMonth),LocalDate.now()).getYears();
        return experience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return id == player.id && age == player.age && experience == player.experience && Objects.equals(firstName, player.firstName) && Objects.equals(lastName, player.lastName) && Objects.equals(birthDate, player.birthDate) && Objects.equals(startCareer, player.startCareer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthDate, startCareer, age, experience);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", startCareer=" + startCareer +
                ", age=" + age +
                ", experience=" + experience +
                '}';
    }
}
