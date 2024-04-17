package bzu.inventory.inventoryManagement.Controller;

import bzu.inventory.inventoryManagement.DTO.ItemDTO;
import bzu.inventory.inventoryManagement.Exception.BadRequestException;
import bzu.inventory.inventoryManagement.Service.ItemService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1.0/item")
public class ItemController {
    private final Logger log = LoggerFactory.getLogger(ItemController.class);

    private ItemService itemService;
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        return ResponseEntity.ok().body(itemService.getAllItems().stream().toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@Valid @RequestBody ItemDTO itemDTO) {
        if (itemDTO.getItem_id() == null){
            log.error("Cannot have an ID {}", itemDTO.getItem_id());
            throw new BadRequestException(ItemController.class.getSimpleName(),"Name");
        }
        return new ResponseEntity(itemService.createItem(itemDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ItemDTO> updateItem(@Valid @RequestBody ItemDTO itemDTO) {
        if (itemDTO.getItem_id() == null){
            log.error("Cannot have an ID {}", itemDTO.getItem_id());
            throw new BadRequestException(ItemController.class.getSimpleName(),"Name");
        }
        return new ResponseEntity(itemService.updateItem(itemDTO), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ItemDTO> updateItemById(@PathVariable(name = "id") long id, @Valid @RequestBody ItemDTO itemDTO) {
        if (itemDTO.getItem_id() == null){
            log.error("Cannot have an ID {}", itemDTO.getItem_id());
            throw new BadRequestException(ItemController.class.getSimpleName(),"Name");
        }
        return ResponseEntity.ok(itemService.patchItemById(id, itemDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItemById(@PathVariable(name = "id") long id) {
        itemService.deleteItemById(id);
        return new ResponseEntity<>("Deleted successfully.",HttpStatus.OK);
    }


}
