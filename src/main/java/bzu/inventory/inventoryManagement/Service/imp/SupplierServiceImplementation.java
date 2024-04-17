package bzu.inventory.inventoryManagement.Service.imp;

import bzu.inventory.inventoryManagement.DTO.SupplierDTO;
import bzu.inventory.inventoryManagement.Entity.Supplier;
import bzu.inventory.inventoryManagement.Exception.ResourceNotFoundException;
import bzu.inventory.inventoryManagement.Repository.SupplierRepository;
import bzu.inventory.inventoryManagement.Service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImplementation implements SupplierService {
    private SupplierRepository supplierRepository;
    @Override
    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = mapToEntity(supplierDTO);
        Supplier savedSupplier = supplierRepository.save(supplier);

        SupplierDTO supplierDTOResponse = mapToDTO(savedSupplier);
        return supplierDTOResponse;
    }

    @Override
    public Set<SupplierDTO> getAllSuppliers() {
        List<Supplier> supplierList = supplierRepository.findAll();
        return supplierList.stream().map(supplier -> mapToDTO(supplier)).collect(Collectors.toSet());
    }

    @Override
    public SupplierDTO updateSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = supplierRepository.findById(supplierDTO.getSupplierId()).orElseThrow(() -> new ResourceNotFoundException("Supplier","id",supplierDTO.getSupplierId().toString()));
        supplier.setSupplier_id(supplierDTO.getSupplierId());
        supplier.setName(supplierDTO.getName());
        supplier.setPhone(supplierDTO.getPhone());
        supplier.setLocation(supplierDTO.getLocation());
        Supplier savedSupplier = supplierRepository.save(supplier);
        return mapToDTO(savedSupplier);
    }

    @Override
    public SupplierDTO getSupplierById(long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier","id",String.valueOf(id)));
        return mapToDTO(supplier);
    }

    @Override
    public SupplierDTO patchSupplierById(long id, SupplierDTO supplierDTO) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier","id",String.valueOf(id)));
        supplier.setSupplier_id(supplierDTO.getSupplierId());
        supplier.setName(supplierDTO.getName());
        supplier.setPhone(supplierDTO.getPhone());
        supplier.setLocation(supplierDTO.getLocation());
        Supplier savedSupplier = supplierRepository.save(supplier);
        return mapToDTO(savedSupplier);
    }

    @Override
    public void deleteSupplierById(long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier","id",String.valueOf(id)));
        supplierRepository.delete(supplier);
    }

    private Supplier mapToEntity(SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier();
        supplier.setSupplier_id(supplierDTO.getSupplierId());
        supplier.setName(supplierDTO.getName());
        supplier.setPhone(supplierDTO.getPhone());
        supplier.setLocation(supplierDTO.getLocation());
        return supplier;
    }

    private SupplierDTO mapToDTO(Supplier Supplier) {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplierId(Supplier.getSupplier_id());
        supplierDTO.setName(Supplier.getName());
        supplierDTO.setPhone(Supplier.getPhone());
        supplierDTO.setLocation(Supplier.getLocation());
        return supplierDTO;
    }
}
