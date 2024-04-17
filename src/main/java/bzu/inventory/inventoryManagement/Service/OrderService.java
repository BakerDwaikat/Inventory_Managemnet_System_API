package bzu.inventory.inventoryManagement.Service;

import bzu.inventory.inventoryManagement.DTO.OrderDTO;

import java.util.Set;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);

    Set<OrderDTO> getAllOrders();

    OrderDTO getOrderById(long id);

    OrderDTO updateOrder(OrderDTO orderDTO);

    OrderDTO patchOrder(long id, OrderDTO orderDTO);

    void deleteOrderById(long id);
}
