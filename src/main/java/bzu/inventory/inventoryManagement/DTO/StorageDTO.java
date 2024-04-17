package bzu.inventory.inventoryManagement.DTO;

import bzu.inventory.inventoryManagement.Entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageDTO {
    private Long storage_id;
    private Long size;
    private String manager;
    private String location;
    private Set<Item> storageItems;
}
