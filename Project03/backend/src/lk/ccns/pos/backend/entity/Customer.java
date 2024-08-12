/*
 * @author : xCODE
 * Project : Project03
 * Date    : 7/30/2024 (Tuesday)
 * Time    : 7:46 AM
 * For GDSE course of IJSE institute.
 */

package lk.ccns.pos.backend.entity;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "customer")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Customer {
    @Id
    private String cus_id;
    private String cus_name;
    private String cus_address;
    private double cus_Salary;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Orders> orders;

    public Customer(String cus_id, String cus_name, String cus_address, double cus_Salary) {
        this.cus_id = cus_id;
        this.cus_name = cus_name;
        this.cus_address = cus_address;
        this.cus_Salary = cus_Salary;
    }

}
