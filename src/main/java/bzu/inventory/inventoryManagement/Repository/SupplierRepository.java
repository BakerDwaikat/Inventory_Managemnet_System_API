package bzu.inventory.inventoryManagement.Repository;

import bzu.inventory.inventoryManagement.Entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
