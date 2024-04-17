package bzu.inventory.inventoryManagement.Controller;

import bzu.inventory.inventoryManagement.DTO.SupplierDTO;
import bzu.inventory.inventoryManagement.Exception.BadRequestException;
import bzu.inventory.inventoryManagement.Service.SupplierService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1.0/supplier")
public class SupplierController {
    private final Logger log = LoggerFactory.getLogger(SupplierController.class);

    private SupplierService supplierService;
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        return ResponseEntity.ok().body(supplierService.getAllSuppliers().stream().toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    @PostMapping
    public ResponseEntity<SupplierDTO> createSupplier(@Valid @RequestBody SupplierDTO supplierDTO) {
        if (supplierDTO.getSupplierId() != null){
            log.error("Cannot have an ID {}", supplierDTO.getSupplierId());
            throw new BadRequestException(SupplierController.class.getSimpleName(),"Name");
        }
        return new ResponseEntity(supplierService.createSupplier(supplierDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<SupplierDTO> updateSupplier(@Valid @RequestBody SupplierDTO supplierDTO) {
        if (supplierDTO.getSupplierId() == null){
            log.error("Cannot have an ID {}", supplierDTO.getSupplierId());
            throw new BadRequestException(SupplierController.class.getSimpleName(),"Name");
        }
        return new ResponseEntity(supplierService.updateSupplier(supplierDTO), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SupplierDTO> updateSupplierById(@PathVariable(name = "id") long id, @Valid @RequestBody SupplierDTO supplierDTO) {
        if (supplierDTO.getSupplierId() == null){
            log.error("Cannot have an ID {}", supplierDTO.getSupplierId());
            throw new BadRequestException(SupplierController.class.getSimpleName(),"Name");
        }
        return ResponseEntity.ok(supplierService.patchSupplierById(id, supplierDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSupplierById(@PathVariable(name = "id") long id) {
        supplierService.deleteSupplierById(id);
        return new ResponseEntity<>("Deleted successfully.",HttpStatus.OK);
    }
}
