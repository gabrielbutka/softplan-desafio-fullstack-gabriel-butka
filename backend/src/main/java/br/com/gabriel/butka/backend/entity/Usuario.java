package br.com.gabriel.butka.backend.entity;

import br.com.gabriel.butka.backend.enums.TipoUsuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Usuario extends BaseEntity {

    @NotBlank
    @Size(max = 100)
    @Column(name = "nome")
    private String nome;

    @NotBlank
    @Email
    @Size(max = 100)
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(max = 32)
    @Column(name = "senha")
    private String senha;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Size(max = 20)
    @Column(name = "tipo")
    private TipoUsuario tipo;

    @NotNull
    @Column(name = "ativo")
    private boolean ativo;

}
