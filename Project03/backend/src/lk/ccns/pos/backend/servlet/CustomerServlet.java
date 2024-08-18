/*
 * @author : xCODE
 * Project : Project03
 * Date    : 7/30/2024 (Tuesday)
 * Time    : 7:38 AM
 * For GDSE course of IJSE institute.
 */

package lk.ccns.pos.backend.servlet;

import lk.ccns.pos.backend.dto.CustomerDTO;
import lk.ccns.pos.backend.entity.Customer;
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


@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    FactoryConfiguration factoryConfiguration;
    Convertor convertor;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        factoryConfiguration = new FactoryConfiguration();
        convertor = new Convertor();
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(convertor.getCustomerEntity(convertor.getCustomerDTOFromReq(req)));
        transaction.commit();
        session.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        factoryConfiguration = new FactoryConfiguration();
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        List <Customer>allCustomer = session.createQuery("from Customer").getResultList();
        transaction.commit();
        JsonArrayBuilder cusArray = Json.createArrayBuilder();
        for(Customer customer :allCustomer){
            JsonObjectBuilder cusObject = Json.createObjectBuilder();
            cusObject.add("id",customer.getCus_id());
            cusObject.add("name",customer.getCus_name());
            cusObject.add("address",customer.getCus_address());
            cusObject.add("salary",customer.getCus_Salary());
            cusArray.add(cusObject.build());
        }
        session.close();
        resp.setContentType("application/json");
        resp.getWriter().print(cusArray.build());

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        factoryConfiguration = new FactoryConfiguration();
        convertor = new Convertor();
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, req.getParameter("id"));
        if(customer != null){
            session.delete(customer);
        }
        transaction.commit();
        session.close();


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        factoryConfiguration = new FactoryConfiguration();
        convertor = new Convertor();

        CustomerDTO customerDTO = convertor.getCustomerDTOFromReq(req);

        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(convertor.getCustomerEntity(customerDTO));
        transaction.commit();
        session.close();
    }


}
