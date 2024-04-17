package bzu.inventory.inventoryManagement.Repository;


import bzu.inventory.inventoryManagement.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
