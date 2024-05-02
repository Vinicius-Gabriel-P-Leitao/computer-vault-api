package tech.vault.server.domain.entity.values;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum LocationComputer {
    MATRIZ(1L),
    POSTO(2L),
    ESTOQUE(3L);
    Long id;
}
