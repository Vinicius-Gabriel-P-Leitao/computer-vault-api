package tech.vault.server.application.controller.computer;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.vault.server.application.dto.computer.ComputerRequestBuilder;
import tech.vault.server.application.dto.computer.ComputerResponseBuilder;
import tech.vault.server.application.service.strategy.ComputerCrudService;

@Validated
@RestController
@RequestMapping("/v1/computer")
public class ComputerCrudController {
    @Autowired
    private ComputerCrudService service;

    @GetMapping()
    @CrossOrigin(origins = "*") //TODO: Trocar por ip do front-end
    public ResponseEntity<Page<ComputerResponseBuilder>> getAllComputers(
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Page<ComputerResponseBuilder> allComputers = service.getAllComputers(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(allComputers);
    }

    @GetMapping("/{computer-id}")
    @CrossOrigin(origins = "*") //TODO: Trocar por ip do front-end
    public ResponseEntity<ComputerResponseBuilder> getComputerById(@PathVariable("computer-id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getComputerById(id));
    }

    @PostMapping
    @CrossOrigin(origins = "*") //TODO: Trocar por ip do front-end
    public ResponseEntity<String> postComputer(@Valid @RequestBody ComputerRequestBuilder request) {
        service.setComputer(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Computador inserido com sucesso!");
    }

    @PatchMapping("/{computer-id}")
    @CrossOrigin(origins = "*") //TODO: Trocar por ip do front-end
    public ResponseEntity<String> patchComputer(@PathVariable("computer-id") Integer id, @Valid @RequestBody ComputerRequestBuilder request) {
        service.patchComputer(id, request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Alteração realizada com sucesso!");
    }

    @DeleteMapping("/{computer-id}")
    @CrossOrigin(origins = "*") //TODO: Trocar por ip do front-end
    public ResponseEntity<String> deleteComputer(@PathVariable("computer-id") Integer id) {
        service.deleteComputer(id);

        return ResponseEntity.status(HttpStatus.OK).body("Computador deletado com sucesso!");
    }
}
