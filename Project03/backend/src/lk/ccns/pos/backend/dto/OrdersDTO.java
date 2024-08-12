/*
 * @author : xCODE
 * Project : Project03
 * Date    : 8/8/2024 (Thursday)
 * Time    : 2:31 PM
 * For GDSE course of IJSE institute.
 */

package lk.ccns.pos.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrdersDTO {
    private String orderId;
    private String orderDate;
    private String cusId;
}
