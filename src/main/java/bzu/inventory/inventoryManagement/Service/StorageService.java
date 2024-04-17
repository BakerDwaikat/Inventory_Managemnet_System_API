package bzu.inventory.inventoryManagement.Service;

import bzu.inventory.inventoryManagement.DTO.StorageDTO;

import java.util.Set;

public interface StorageService {
    StorageDTO createStorage(StorageDTO storageDTO);

    Set<StorageDTO> getAllStorage();

    StorageDTO getStorageById(long id);

    StorageDTO updateStorage(StorageDTO storageDTO);

    StorageDTO patchStorageById(long id, StorageDTO storageDTO);

    void deleteStorageById(long id);
}
