package bzu.inventory.inventoryManagement.Controller;

import bzu.inventory.inventoryManagement.DTO.StorageDTO;
import bzu.inventory.inventoryManagement.Exception.BadRequestException;
import bzu.inventory.inventoryManagement.Service.StorageService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1.0/storage")
public class StorageController {
    private final Logger log = LoggerFactory.getLogger(StorageController.class);

    private StorageService storageService;
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public ResponseEntity<List<StorageDTO>> getAllStorages() {
        return ResponseEntity.ok().body(storageService.getAllStorage().stream().toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StorageDTO> getStorageById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(storageService.getStorageById(id));
    }

    @PostMapping
    public ResponseEntity<StorageDTO> createStorage(@Valid @RequestBody StorageDTO storageDTO) {
        if (storageDTO.getStorageId() != null){
            log.error("Cannot have an ID {}", storageDTO.getStorageId());
            throw new BadRequestException(StorageController.class.getSimpleName(),"Name");
        }
        return new ResponseEntity(storageService.createStorage(storageDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StorageDTO> updateStorage(@Valid @RequestBody StorageDTO storageDTO) {
        if (storageDTO.getStorageId() == null){
            log.error("Cannot have an ID {}", storageDTO.getStorageId());
            throw new BadRequestException(StorageController.class.getSimpleName(),"Name");
        }
        return new ResponseEntity(storageService.updateStorage(storageDTO), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StorageDTO> updateStorageById(@PathVariable(name = "id") long id, @Valid @RequestBody StorageDTO storageDTO) {
        if (storageDTO.getStorageId() == null){
            log.error("Cannot have an ID {}", storageDTO.getStorageId());
            throw new BadRequestException(StorageController.class.getSimpleName(),"Name");
        }
        return ResponseEntity.ok(storageService.patchStorageById(id, storageDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStorageById(@PathVariable(name = "id") long id) {
        storageService.deleteStorageById(id);
        return new ResponseEntity<>("Deleted successfully.",HttpStatus.OK);
    }
}
