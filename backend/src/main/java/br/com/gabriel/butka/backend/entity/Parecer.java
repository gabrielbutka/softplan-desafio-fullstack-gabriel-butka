package br.com.gabriel.butka.backend.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@EqualsAndHashCode(of = {"processo", "usuario"}, callSuper = false)
public class Parecer extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "process_id")
    private Processo processo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    @Size(max = 1_000)
    @Column(name = "parecer")
    private String parecer;

}
