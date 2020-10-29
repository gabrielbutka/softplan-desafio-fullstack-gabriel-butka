package br.com.gabriel.butka.backend.entity;

import br.com.gabriel.butka.backend.enums.StatusProcesso;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Processo extends BaseEntity {

    @NotBlank
    @Size(max = 100)
    @Column(name = "assunto")
    private String assunto;

    @NotBlank
    @Size(max = 1_000)
    @Column(name = "descricao")
    private String descricao;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Size(max = 20)
    @Column(name = "status")
    private StatusProcesso status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "processo_interessado",
            joinColumns = @JoinColumn(name = "processo_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<Usuario> interessados = new ArrayList<>();

    @OneToMany(mappedBy = "processo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Parecer> pareceres = new ArrayList<>();

}
