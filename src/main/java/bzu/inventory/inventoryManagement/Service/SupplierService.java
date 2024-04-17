package bzu.inventory.inventoryManagement.Service;

import bzu.inventory.inventoryManagement.DTO.SupplierDTO;

import java.util.Set;

public interface SupplierService {
    SupplierDTO createSupplier(SupplierDTO supplierDTO);

    Set<SupplierDTO> getAllSuppliers();

    SupplierDTO updateSupplier(SupplierDTO supplierDTO);

    SupplierDTO getSupplierById(long id);

    SupplierDTO patchSupplierById(long id, SupplierDTO supplierDTO);

    void deleteSupplierById(long id);
}
