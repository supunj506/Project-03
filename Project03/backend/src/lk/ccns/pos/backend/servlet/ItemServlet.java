/*
 * @author : xCODE
 * Project : Project03
 * Date    : 8/5/2024 (Monday)
 * Time    : 11:24 AM
 * For GDSE course of IJSE institute.
 */

package lk.ccns.pos.backend.servlet;

import lk.ccns.pos.backend.dto.CustomerDTO;
import lk.ccns.pos.backend.dto.ItemDTO;
import lk.ccns.pos.backend.entity.Item;
import lk.ccns.pos.backend.util.Convertor;
import lk.ccns.pos.backend.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.addHeader("Access-Control-Allow-Methods","DELETE,PUT");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin","*");

        FactoryConfiguration factoryConfiguration = new FactoryConfiguration();
        Convertor convertor = new Convertor();

        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        ItemDTO itemDTO = convertor.getItemDTOFromReq(req);
        session.save(convertor.getItemEntity(itemDTO));
        transaction.commit();
        session.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin","*");
        FactoryConfiguration factoryConfiguration =new FactoryConfiguration();

        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        List<Item> resultList = session.createQuery("SELECT item from Item  item", Item.class).getResultList();
        transaction.commit();
        JsonArrayBuilder jArray = Json.createArrayBuilder();
        for(Item item:resultList){
            JsonObjectBuilder jObject = Json.createObjectBuilder();
            jObject.add("code",item.getItem_code());
            jObject.add("description",item.getItem_desc());
            jObject.add("price",item.getItem_price());
            jObject.add("qty",item.getItem_qty());
            jArray.add(jObject.build());
        }
        session.close();
        resp.setContentType("application/json");
        resp.getWriter().print(jArray.build());

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin","*");
        FactoryConfiguration fa = new FactoryConfiguration();
        Convertor con = new Convertor();

        Session session = fa.getSession();
        Transaction transaction = session.beginTransaction();
        ItemDTO itemDTO = con.getItemDTOFromReq(req);
        Item itemEntity = con.getItemEntity(itemDTO);
        session.update(itemEntity);
        transaction.commit();
        session.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin","*");
        FactoryConfiguration fa = new FactoryConfiguration();
        Convertor con = new Convertor();

        Session session = fa.getSession();
        Transaction transaction = session.beginTransaction();
        Item item = session.get(Item.class, req.getParameter("code"));
        if(item != null){
            session.delete(item);
            transaction.commit();
            session.close();
        }

    }
}
