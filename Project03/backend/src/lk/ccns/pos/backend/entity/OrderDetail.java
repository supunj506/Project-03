/*
 * @author : xCODE
 * Project : Project03
 * Date    : 7/30/2024 (Tuesday)
 * Time    : 7:50 AM
 * For GDSE course of IJSE institute.
 */

package lk.ccns.pos.backend.entity;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderDetail implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Orders orders;

    @Id
    @ManyToOne
    @JoinColumn(name = "item_code", referencedColumnName = "item_code")
    private Item item;

    private double qty;
    private double price;


}
