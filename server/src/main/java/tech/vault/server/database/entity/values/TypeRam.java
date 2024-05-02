package tech.vault.server.database.entity.values;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TypeRam {
    DDR3(2L),
    DDR4(2L);
    Long id;
}
