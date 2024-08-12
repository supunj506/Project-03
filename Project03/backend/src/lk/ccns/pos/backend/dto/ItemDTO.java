/*
 * @author : xCODE
 * Project : Project03
 * Date    : 8/5/2024 (Monday)
 * Time    : 11:14 AM
 * For GDSE course of IJSE institute.
 */

package lk.ccns.pos.backend.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ItemDTO implements Serializable {
    private String item_code;
    private String item_desc;
    private double item_price;
    private double item_qty;
}
