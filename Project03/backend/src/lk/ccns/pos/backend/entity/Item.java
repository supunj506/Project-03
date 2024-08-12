/*
 * @author : xCODE
 * Project : Project03
 * Date    : 7/30/2024 (Tuesday)
 * Time    : 7:53 AM
 * For GDSE course of IJSE institute.
 */

package lk.ccns.pos.backend.entity;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class Item {
    @Id
    private String item_code;
    private String item_desc;
    private double item_price;
    private double item_qty;
    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetailList;

    public Item(String item_code, String item_desc, double item_price, double item_qty) {
        this.item_code = item_code;
        this.item_desc = item_desc;
        this.item_price = item_price;
        this.item_qty = item_qty;
    }
}
