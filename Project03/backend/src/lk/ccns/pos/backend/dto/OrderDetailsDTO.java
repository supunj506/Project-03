/*
 * @author : xCODE
 * Project : Project03
 * Date    : 8/8/2024 (Thursday)
 * Time    : 2:06 PM
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
public class OrderDetailsDTO {
    private String orderId;
    private String itemCode;
    private double qty;
    private double price;

}
