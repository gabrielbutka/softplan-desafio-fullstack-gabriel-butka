package br.com.gabriel.butka.backend.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter(value = AccessLevel.PACKAGE)
@MappedSuperclass
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class BaseEntity implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "criacao")
    @Setter(value = AccessLevel.PUBLIC)
    private LocalDateTime criacao;

    @NotNull
    @Column(name = "edicao")
    @Setter(value = AccessLevel.PUBLIC)
    private LocalDateTime edicao;

    @Override
    public boolean isNew() {
        return Objects.isNull(getId());
    }

    @PreUpdate
    @PrePersist
    public void onPreUpdatePersist() {
        if (isNew() && Objects.isNull(criacao)) {
            criacao = LocalDateTime.now();
        }
        edicao = LocalDateTime.now();
    }

}
