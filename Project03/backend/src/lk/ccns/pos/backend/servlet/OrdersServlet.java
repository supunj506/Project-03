/*
 * @author : xCODE
 * Project : Project03
 * Date    : 8/6/2024 (Tuesday)
 * Time    : 2:29 PM
 * For GDSE course of IJSE institute.
 */

package lk.ccns.pos.backend.servlet;

import lk.ccns.pos.backend.dto.OrdersDTO;
import lk.ccns.pos.backend.entity.Item;
import lk.ccns.pos.backend.entity.OrderDetail;
import lk.ccns.pos.backend.entity.Orders;
import lk.ccns.pos.backend.util.Convertor;
import lk.ccns.pos.backend.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/orders")
public class OrdersServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        FactoryConfiguration fa = new FactoryConfiguration();
        Session session = fa.getSession();
        Transaction transaction = session.beginTransaction();
        List<Orders> orderList = session.createQuery("select o from Orders o", Orders.class).getResultList();
        transaction.commit();
        JsonArrayBuilder jArray = Json.createArrayBuilder();
        for(Orders order:orderList){
            JsonObjectBuilder jObject = Json.createObjectBuilder();
            jObject.add("orderId",order.getOrder_id());
            jObject.add("OrderDate", String.valueOf(order.getOrder_date()));
            jObject.add("cusId",order.getCustomer().getCus_id());
            jArray.add(jObject.build());
        }
        session.close();
        resp.setContentType("application/json");
        resp.getWriter().print(jArray.build());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FactoryConfiguration fa = new FactoryConfiguration();
        Convertor con = new Convertor();
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();

        String id = jsonObject.getString("orderId");
        System.out.println(id);
        String date = jsonObject.getString("date");
        String customerId = jsonObject.getString("customerId");

        OrdersDTO ordersDTO = new OrdersDTO(id, date, customerId);
        Orders orderEntity = con.getOrderEntity(ordersDTO);

        Session session = fa.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(orderEntity);

            JsonArray itemDetails = jsonObject.getJsonArray("itemDetails");
            for (JsonValue ob : itemDetails) {
                String code = ob.asJsonObject().getString("code");
                String price = ob.asJsonObject().getString("price");
                String qty = ob.asJsonObject().getString("qty");
                Item item = session.get(Item.class, code);
                OrderDetail od = new OrderDetail(orderEntity, item,Double.parseDouble(qty),Double.parseDouble(price));
                session.save(od);
                item.setItem_qty(item.getItem_qty()-Double.parseDouble(qty));
                System.out.println(item.getItem_qty());
                session.update(item.getItem_code(),item);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            session.close();
        }
    }
}
