package com.kach.studyhelperback.Models.DAO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "edges")
public class Edge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "from_id", nullable = false)
    private Article from;

    @OneToOne
    @JoinColumn(name = "to_id", nullable = false)
    private Article to;

    @NotNull
    private Double weight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Article getFrom() {
        return from;
    }

    public void setFrom(Article from) {
        this.from = from;
    }

    public Article getTo() {
        return to;
    }

    public void setTo(Article to) {
        this.to = to;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
