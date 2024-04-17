package bzu.inventory.inventoryManagement.Service.imp;

import bzu.inventory.inventoryManagement.DTO.ItemDTO;
import bzu.inventory.inventoryManagement.Entity.Item;
import bzu.inventory.inventoryManagement.Exception.ResourceNotFoundException;
import bzu.inventory.inventoryManagement.Repository.ItemRepository;
import bzu.inventory.inventoryManagement.Service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ItemServiceImplementation implements ItemService {
    private ItemRepository itemRepository;

    public ItemServiceImplementation(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemDTO createItem(ItemDTO itemDTO) {
        Item item = mapToEntity(itemDTO);
        Item savedItem = itemRepository.save(item);

        ItemDTO itemDTOResponse = maptoDto(savedItem);
        return itemDTOResponse;
    }


    @Override
    public Set<ItemDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();

        return items.stream().map(item -> maptoDto(item)).collect(Collectors.toSet());
    }

    @Override
    public ItemDTO updateItem(ItemDTO itemDTO) {
        Item item = itemRepository.findById(itemDTO.getItem_id()).orElseThrow(() -> new ResourceNotFoundException("Item","id",itemDTO.getItem_id().toString()));
        item.setItem_id(itemDTO.getItem_id());
        item.setName(itemDTO.getName());
        item.setPrice(itemDTO.getPrice());
        item.setQuantity(itemDTO.getQuantity());
        item.setSupplier(itemDTO.getSupplier());

        Item savedItem = itemRepository.save(item);
        return maptoDto(savedItem);
    }

    @Override
    public ItemDTO getItemById(long id) {
        Item item = itemRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Item","id",String.valueOf(id)));
        return maptoDto(item);
    }

    @Override
    public ItemDTO patchItemById(long id, ItemDTO itemDTO) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item","id",String.valueOf(id)));
        item.setItem_id(itemDTO.getItem_id());
        item.setName(itemDTO.getName());
        item.setPrice(itemDTO.getPrice());
        item.setQuantity(itemDTO.getQuantity());
        item.setSupplier(itemDTO.getSupplier());

        Item savedItem = itemRepository.save(item);
        return maptoDto(savedItem);
    }

    @Override
    public void deleteItemById(long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item","id",String.valueOf(id)));
        itemRepository.delete(item);
    }

    private ItemDTO maptoDto(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItem_id(item.getItem_id());
        itemDTO.setName(item.getName());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setQuantity(item.getQuantity());
        itemDTO.setSupplier(item.getSupplier());
        return itemDTO;
    }

    private Item mapToEntity(ItemDTO itemDTO) {
        Item item = new Item();
        item.setItem_id(itemDTO.getItem_id());
        item.setName(itemDTO.getName());
        item.setPrice(itemDTO.getPrice());
        item.setQuantity(itemDTO.getQuantity());
        item.setSupplier(itemDTO.getSupplier());
        return item;
    }
}
