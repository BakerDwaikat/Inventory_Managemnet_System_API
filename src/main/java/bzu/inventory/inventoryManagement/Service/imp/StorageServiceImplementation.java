package bzu.inventory.inventoryManagement.Service.imp;

import bzu.inventory.inventoryManagement.DTO.StorageDTO;
import bzu.inventory.inventoryManagement.Entity.Storage;
import bzu.inventory.inventoryManagement.Exception.ResourceNotFoundException;
import bzu.inventory.inventoryManagement.Repository.StorageRepository;
import bzu.inventory.inventoryManagement.Service.StorageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StorageServiceImplementation implements StorageService {
    private StorageRepository storageRepository;
    @Override
    public StorageDTO createStorage(StorageDTO storageDTO) {
        Storage storage = mapToEntity(storageDTO);
        Storage savedStorage = storageRepository.save(storage);

        StorageDTO savedStorageDTO = mapToDTO(savedStorage);
        return savedStorageDTO;
    }

    @Override
    public Set<StorageDTO> getAllStorage() {
        List<Storage> storageList = storageRepository.findAll();

        return storageList.stream().map(storage -> mapToDTO(storage)).collect(Collectors.toSet());
    }

    @Override
    public StorageDTO getStorageById(long id) {
        Storage storage = storageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Storage","id",String.valueOf(id)));
        return mapToDTO(storage);
    }

    @Override
    public StorageDTO updateStorage(StorageDTO storageDTO) {
        Storage storage = storageRepository.findById(storageDTO.getStorageId()).orElseThrow(() -> new ResourceNotFoundException("Storage","id",String.valueOf(storageDTO.getStorageId())));
        storage.setStorage_id(storageDTO.getStorageId());
        storage.setManager(storageDTO.getManager());
        storage.setLocation(storageDTO.getLocation());
        storage.setSize(storageDTO.getSize());
        storage.setStorageItems(storageDTO.getStorageItems());

        Storage savedStorage = storageRepository.save(storage);
        return mapToDTO(savedStorage);
    }

    @Override
    public StorageDTO patchStorageById(long id, StorageDTO storageDTO) {
        Storage storage = storageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Storage","id",String.valueOf(id)));
        storage.setStorage_id(storageDTO.getStorageId());
        storage.setManager(storageDTO.getManager());
        storage.setLocation(storageDTO.getLocation());
        storage.setSize(storageDTO.getSize());
        storage.setStorageItems(storageDTO.getStorageItems());

        Storage savedStorage = storageRepository.save(storage);
        return mapToDTO(savedStorage);
    }

    @Override
    public void deleteStorageById(long id) {
        Storage storage = storageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Storage","id",String.valueOf(id)));
        storageRepository.delete(storage);
    }

    private Storage mapToEntity(StorageDTO storageDTO) {
        Storage storage = new Storage();
        storage.setStorage_id(storageDTO.getStorageId());
        storage.setManager(storageDTO.getManager());
        storage.setLocation(storageDTO.getLocation());
        storage.setSize(storageDTO.getSize());
        storage.setStorageItems(storageDTO.getStorageItems());
        return storage;
    }

    private StorageDTO mapToDTO(Storage storage) {
        StorageDTO storageDTO = new StorageDTO();
        storageDTO.setStorageId(storage.getStorage_id());
        storageDTO.setManager(storage.getManager());
        storageDTO.setLocation(storage.getLocation());
        storageDTO.setSize(storage.getSize());
        storageDTO.setStorageItems(storage.getStorageItems());

        return storageDTO;
    }

}
