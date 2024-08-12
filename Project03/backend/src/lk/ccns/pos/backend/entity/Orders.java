/*
 * @author : xCODE
 * Project : Project03
 * Date    : 7/30/2024 (Tuesday)
 * Time    : 7:52 AM
 * For GDSE course of IJSE institute.
 */

package lk.ccns.pos.backend.entity;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Orders {
    @Id
    private
    String order_id;

    @CreationTimestamp
    private
    LocalDate order_date;

    @ManyToOne
    @JoinColumn(name = "cus_id", referencedColumnName = "cus_id")
    private Customer customer;

    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetailList;

    public Orders(String order_id, LocalDate order_date, Customer customer) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.customer = customer;
    }

}
