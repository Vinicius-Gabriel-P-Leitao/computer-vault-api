package tech.vault.server.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.vault.server.core.dto.ComputerRequestBuilder;
import tech.vault.server.core.dto.ComputerResponseBuilder;
import tech.vault.server.core.service.ComputerCrudService;
import tech.vault.server.core.service.implement.ComputerCrudServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/computer")
public class ComputerCrudController {
    @Autowired
    private ComputerCrudService service;

    @GetMapping()
    @CrossOrigin(origins = "*") //TODO: Trocar por ip do front-end
    public ResponseEntity<List<ComputerResponseBuilder>> getAllComputers() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllComputers());
    }

    @GetMapping("/{computer-id}")
    @CrossOrigin(origins = "*") //TODO: Trocar por ip do front-end
    public ResponseEntity<ComputerResponseBuilder> getComputerById(@PathVariable("computer-id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getComputerById(id));
    }

    @PostMapping
    @CrossOrigin(origins = "*") //TODO: Trocar por ip do front-end
    public ResponseEntity<String> postComputer(@RequestBody ComputerRequestBuilder request) {
        service.setComputer(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Computador inserido com sucesso!");
    }

    @PatchMapping("/{computer-id}")
    @CrossOrigin(origins = "*") //TODO: Trocar por ip do front-end
    public ResponseEntity<String> patchComputer(@PathVariable("computer-id") UUID id, @RequestBody ComputerRequestBuilder request) {
        service.patchComputer(id, request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Alteração realizada com sucesso!");
    }

    @DeleteMapping("/{computer-id}")
    @CrossOrigin(origins = "*") //TODO: Trocar por ip do front-end
    public ResponseEntity<String> deleteComputer(@PathVariable("computer=id") UUID id) {
        service.deleteComputer(id);

        return ResponseEntity.status(HttpStatus.OK).body("Computador deletado com sucesso!");
    }
}
