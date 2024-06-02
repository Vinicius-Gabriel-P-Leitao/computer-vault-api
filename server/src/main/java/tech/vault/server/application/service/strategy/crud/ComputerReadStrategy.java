package tech.vault.server.application.service.strategy.crud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.vault.server.application.dto.computer.ComputerResponseBuilder;

public interface ComputerReadStrategy {
     Page<ComputerResponseBuilder> selectAllPageable(Pageable pageable);
     ComputerResponseBuilder selectById(Integer id);
}
