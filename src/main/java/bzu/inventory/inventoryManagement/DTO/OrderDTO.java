package bzu.inventory.inventoryManagement.DTO;

import bzu.inventory.inventoryManagement.Entity.Item;
import bzu.inventory.inventoryManagement.Entity.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long order_id;
    private BigDecimal price;
    private Long quantity;
    private Date date;
    private Supplier supplier;
    private Set<Item> items;

}
