package br.com.gabriel.butka.backend.rest.dto;

import br.com.gabriel.butka.backend.enums.BaseEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnumDTO {

    public String chave;
    public String descricao;

    public static EnumDTO build(BaseEnum baseEnum) {
        var dto = new EnumDTO();
        dto.setChave(baseEnum.getChave());
        dto.setDescricao(baseEnum.getDescricao());
        return dto;
    }

}
