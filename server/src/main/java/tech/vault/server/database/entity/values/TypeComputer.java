package tech.vault.server.database.entity.values;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TypeComputer {
    DESKTOP(1L),
    ALL_IN_ONE(2L),
    MICRO(3L),
    MONTADo(4L);
    Long id;
}
