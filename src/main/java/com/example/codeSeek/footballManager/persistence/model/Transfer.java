package com.example.codeSeek.footballManager.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "transfers")
public class Transfer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    @JsonBackReference
    private Player player;

    @ManyToOne
    @JoinColumn(name = "team_to", nullable = false)
    @JsonBackReference
    private Team teamTo;

    @Column(name = "transfer_date")
    private LocalDate transferDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transfer)) return false;
        Transfer transfer = (Transfer) o;
        return id == transfer.id && Objects.equals(transferDate, transfer.transferDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transferDate);
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + id +
                ", transferDate=" + transferDate +
                '}';
    }
}
