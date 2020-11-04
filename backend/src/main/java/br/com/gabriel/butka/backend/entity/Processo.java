package br.com.gabriel.butka.backend.entity;

import br.com.gabriel.butka.backend.enums.StatusProcesso;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Processo extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

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
    @Column(name = "status")
    private StatusProcesso status = StatusProcesso.PENDENTE;

    @OneToMany
    @JoinTable(name = "processo_interessado",
            inverseJoinColumns = @JoinColumn(name = "usuario_id"),
            joinColumns = @JoinColumn(name = "processo_id"))
    private List<Usuario> interessados = new ArrayList<>();

    @OneToMany(mappedBy = "processo", cascade = CascadeType.ALL)
    private List<Parecer> pareceres = new ArrayList<>();

    public String getNomesInteressados() {
        return interessados.stream().map(Usuario::getNome)
                .collect(Collectors.joining(", "));
    }

    public boolean isPendente() {
        return status.isPendente();
    }

    public boolean temParecer(Usuario usuario) {
        return pareceres.stream().anyMatch(p -> p.getUsuario().equals(usuario));
    }

    public boolean temInteressado(Usuario usuario) {
        return interessados.contains(usuario);
    }

    public boolean temParecerPendente() {
        return interessados.stream().anyMatch(i -> !temParecer(i));
    }

    public void adicionarParecer(Parecer parecer) {
        pareceres.add(parecer);
        if (!temParecerPendente()) {
            finalizar();
        }
    }

    public void removerInteressado(Usuario usuario) {
        interessados.remove(usuario);
    }

    public void finalizar() {
        status = StatusProcesso.FINALIZADO;
    }

}
