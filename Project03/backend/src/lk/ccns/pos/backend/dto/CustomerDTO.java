/*
 * @author : xCODE
 * Project : Project03
 * Date    : 8/4/2024 (Sunday)
 * Time    : 12:56 PM
 * For GDSE course of IJSE institute.
 */

package lk.ccns.pos.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDTO {
    private String cus_id;
    private String cus_name;
    private String cus_address;
    private double cus_Salary;
}
