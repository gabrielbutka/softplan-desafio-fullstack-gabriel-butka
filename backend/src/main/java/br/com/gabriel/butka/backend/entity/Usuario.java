package br.com.gabriel.butka.backend.entity;

import br.com.gabriel.butka.backend.enums.TipoUsuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "email", callSuper = true)
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
    @Size(max = 60)
    @Column(name = "senha")
    private String senha;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoUsuario tipo;

    @NotNull
    @Column(name = "ativo")
    private boolean ativo = true;

    public UserDetails toUserDetails() {
        var user = User.builder();
        user.username(email);
        user.password(senha);
        user.roles(tipo.getChave());
        return user.build();
    }

    public Usuario ativar() {
        ativo = true;
        return this;
    }

    public Usuario inativar() {
        ativo = false;
        return this;
    }

}
