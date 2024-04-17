package bzu.inventory.inventoryManagement.Controller;

import bzu.inventory.inventoryManagement.DTO.OrderDTO;
import bzu.inventory.inventoryManagement.Exception.BadRequestException;
import bzu.inventory.inventoryManagement.Service.OrderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1.0/order")
public class OrderController {
    private final Logger log = LoggerFactory.getLogger(OrderController.class);
    
    private OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok().body(orderService.getAllOrders().stream().toList());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        if (orderDTO.getOrder_id() == null){
            log.error("Cannot have an ID {}", orderDTO.getOrder_id());
            throw new BadRequestException(OrderController.class.getSimpleName(),"Name");
        }
        return new ResponseEntity(orderService.createOrder(orderDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<OrderDTO> updateOrder(@Valid @RequestBody OrderDTO orderDTO) {
        if (orderDTO.getOrder_id() == null){
            log.error("Cannot have an ID {}", orderDTO.getOrder_id());
            throw new BadRequestException(OrderController.class.getSimpleName(),"Name");
        }
        return new ResponseEntity(orderService.updateOrder(orderDTO), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrderById(@PathVariable(name = "id") long id, @Valid @RequestBody OrderDTO orderDTO) {
        if (orderDTO.getOrder_id() == null){
            log.error("Cannot have an ID {}", orderDTO.getOrder_id());
            throw new BadRequestException(OrderController.class.getSimpleName(),"Name");
        }
        return ResponseEntity.ok(orderService.patchOrder(id, orderDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable(name = "id") long id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<>("Deleted successfully.",HttpStatus.OK);
    }
}
