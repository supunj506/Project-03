/*
 * @author : xCODE
 * Project : Project03
 * Date    : 7/30/2024 (Tuesday)
 * Time    : 7:41 AM
 * For GDSE course of IJSE institute.
 */

package lk.ccns.pos.backend.util;

import lk.ccns.pos.backend.entity.Customer;
import lk.ccns.pos.backend.entity.Item;
import lk.ccns.pos.backend.entity.OrderDetail;
import lk.ccns.pos.backend.entity.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;

    public final SessionFactory sessionFactory;

    public FactoryConfiguration() throws IOException {
        Configuration configuration = new Configuration();
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
        configuration.setProperties(properties);

        configuration.addAnnotatedClass(Customer.class);
        configuration.addAnnotatedClass(Item.class);
        configuration.addAnnotatedClass(Orders.class);
        configuration.addAnnotatedClass(OrderDetail.class);

        sessionFactory = configuration.buildSessionFactory();

    }

    public static FactoryConfiguration getInstance() throws IOException {
        if (factoryConfiguration == null){
            factoryConfiguration = new FactoryConfiguration();
        }
        return factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
