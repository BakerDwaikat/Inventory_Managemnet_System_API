package bzu.inventory.inventoryManagement.Service.imp;

import bzu.inventory.inventoryManagement.DTO.OrderDTO;
import bzu.inventory.inventoryManagement.Entity.Order;
import bzu.inventory.inventoryManagement.Exception.ResourceNotFoundException;
import bzu.inventory.inventoryManagement.Repository.OrderRepository;
import bzu.inventory.inventoryManagement.Service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplementation implements OrderService {
    private OrderRepository orderRepository;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = mapToEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);

        OrderDTO orderDTOResponse = mapToDTO(savedOrder);
        return orderDTOResponse;
    }

    @Override
    public Set<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(order -> mapToDTO(order)).collect(Collectors.toSet());
    }

    @Override
    public OrderDTO getOrderById(long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order","id",String.valueOf(id)));
        return mapToDTO(order);
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderDTO.getOrderId()).orElseThrow(() -> new ResourceNotFoundException("Order","id",String.valueOf(orderDTO.getOrderId())));
        order.setOrder_id(orderDTO.getOrderId());
        order.setPrice(orderDTO.getPrice());
        order.setQuantity(orderDTO.getQuantity());
        order.setSupplier(orderDTO.getSupplierId());
        order.setDate(orderDTO.getDate());
        order.setItems(orderDTO.getItems());

        Order savedOrder = orderRepository.save(order);
        return mapToDTO(savedOrder);
    }

    @Override
    public OrderDTO patchOrder(long id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order","id",String.valueOf(id)));
        order.setOrder_id(orderDTO.getOrderId());
        order.setPrice(orderDTO.getPrice());
        order.setQuantity(orderDTO.getQuantity());
        order.setSupplier(orderDTO.getSupplierId());
        order.setDate(orderDTO.getDate());
        order.setItems(orderDTO.getItems());

        Order savedOrder = orderRepository.save(order);
        return mapToDTO(savedOrder);
    }

    @Override
    public void deleteOrderById(long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order","id",String.valueOf(id)));
        orderRepository.delete(order);
    }

    private OrderDTO mapToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrder_id());
        orderDTO.setPrice(order.getPrice());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setSupplierId(order.getSupplier());
        orderDTO.setDate(order.getDate());
        orderDTO.setItems(order.getItems());

        return orderDTO;
    }

    private Order mapToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrder_id(orderDTO.getOrderId());
        order.setPrice(orderDTO.getPrice());
        order.setQuantity(orderDTO.getQuantity());
        order.setSupplier(orderDTO.getSupplierId());
        order.setDate(orderDTO.getDate());
        order.setItems(orderDTO.getItems());

        return order;
    }
}
