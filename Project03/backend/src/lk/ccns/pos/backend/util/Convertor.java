/*
 * @author : xCODE
 * Project : Project03
 * Date    : 8/4/2024 (Sunday)
 * Time    : 12:57 PM
 * For GDSE course of IJSE institute.
 */

package lk.ccns.pos.backend.util;

import lk.ccns.pos.backend.dto.CustomerDTO;
import lk.ccns.pos.backend.dto.ItemDTO;
import lk.ccns.pos.backend.dto.OrderDetailsDTO;
import lk.ccns.pos.backend.dto.OrdersDTO;
import lk.ccns.pos.backend.entity.Customer;
import lk.ccns.pos.backend.entity.Item;
import lk.ccns.pos.backend.entity.OrderDetail;
import lk.ccns.pos.backend.entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Convertor {
    public CustomerDTO getCustomerDTO(Customer customer){
        return new CustomerDTO(customer.getCus_id(), customer.getCus_name(), customer.getCus_address(), customer.getCus_Salary());
    }
    public Customer getCustomerEntity(CustomerDTO customerDTO){
        return new Customer(customerDTO.getCus_id(),customerDTO.getCus_name(),customerDTO.getCus_address(), customerDTO.getCus_Salary());
    }
    public CustomerDTO getCustomerDTOFromReq(HttpServletRequest request) throws IOException {
            JsonReader reader = Json.createReader(request.getReader());
            JsonObject jo = reader.readObject();
            return new CustomerDTO(jo.getString("id"),jo.getString("name"),jo.getString("address"),Double.parseDouble(jo.getString("salary")));
    }
    public Item getItemEntity(ItemDTO itemDTO){
        return new Item(itemDTO.getItem_code(), itemDTO.getItem_desc(), itemDTO.getItem_price(), itemDTO.getItem_qty());
    }
    public ItemDTO getItemDTO(Item item){
        return new ItemDTO(item.getItem_code(), item.getItem_desc(), item.getItem_price() , item.getItem_qty());
    }
    public ItemDTO getItemDTOFromReq(HttpServletRequest req) throws IOException {
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jo = reader.readObject();
        return new ItemDTO(jo.getString("code"),jo.getString("description"),Double.parseDouble(jo.getString("price")),Double.parseDouble(jo.getString("qty")));
    }
    public OrderDetail getOrderDetailsEntity (OrderDetailsDTO odDTO) throws IOException {
        FactoryConfiguration fa = new FactoryConfiguration();
        Session session = fa.getSession();
        Transaction transaction = session.beginTransaction();
        Orders orders = session.get(Orders.class, odDTO.getOrderId());
        Item item = session.get(Item.class, odDTO.getItemCode());
        transaction.commit();
        session.close();
        return new OrderDetail(orders,item,odDTO.getQty(),odDTO.getPrice());
    }

    public Orders getOrderEntity(OrdersDTO ordersDTO) throws IOException {
        FactoryConfiguration fa = new FactoryConfiguration();
        Session session = fa.getSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, ordersDTO.getCusId());
        transaction.commit();
        session.close();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(ordersDTO.getOrderDate(), formatter);
        String orderId = ordersDTO.getOrderId();
        System.out.println(orderId);
        return new Orders(ordersDTO.getOrderId(), localDate,customer);
    }
}
