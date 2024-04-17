package bzu.inventory.inventoryManagement.DTO;

import bzu.inventory.inventoryManagement.Entity.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private Long item_id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private Supplier supplier;

}
