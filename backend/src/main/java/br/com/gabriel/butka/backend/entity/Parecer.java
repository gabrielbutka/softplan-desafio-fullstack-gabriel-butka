package br.com.gabriel.butka.backend.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"processo", "usuario"}, callSuper = true)
public class Parecer extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "processo_id")
    private Processo processo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @NotNull
    @Size(max = 1_000)
    @Column(name = "descricao")
    private String descricao;

}
