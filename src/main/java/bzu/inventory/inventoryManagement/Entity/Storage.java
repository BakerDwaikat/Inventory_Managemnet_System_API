package bzu.inventory.inventoryManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "storages")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storage_id")
    private Long storage_id;

    @Column(name = "size")
    private Long size;

    @Column(name = "manager")
    private String manager;

    @Column(name = "location")
    private String location;

    @ManyToMany(mappedBy = "storages")
    private Set<Item> storageItems;

}
