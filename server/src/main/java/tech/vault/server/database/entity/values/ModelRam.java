package tech.vault.server.database.entity.values;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ModelRam {
    DIMM(1L),
    SODIMM(2L),
    SOLDADA(3L);
    Long id;
}
