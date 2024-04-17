package bzu.inventory.inventoryManagement.Service;

import bzu.inventory.inventoryManagement.DTO.ItemDTO;

import java.util.Set;

public interface ItemService {
    ItemDTO createItem(ItemDTO itemDTO);

    Set<ItemDTO> getAllItems();

    ItemDTO updateItem(ItemDTO itemDTO);

    ItemDTO getItemById(long id);

    ItemDTO patchItemById(long id, ItemDTO itemDTO);

    void deleteItemById(long id);
}
