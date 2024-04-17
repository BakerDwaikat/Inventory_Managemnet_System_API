package bzu.inventory.inventoryManagement.Repository;

import bzu.inventory.inventoryManagement.Entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepository extends JpaRepository<Storage, Long> {
}
