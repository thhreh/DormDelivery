package com.DormDeliver.dao;

import com.DormDeliver.entity.Authorities;
import com.DormDeliver.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CustomerDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void signUp(Customer customer) {
        Authorities authorities = new Authorities();
        authorities.setAuthorities("ROLE_USER");
        authorities.setEmail(customer.getEmail());

        Session session = null;
        try {
            //create transaction to save previous commit
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(authorities);
            session.save(customer);
            session.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            //if signup failed, return to previous commit to not affect old data
            if (session != null) session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    public Customer getCustomer(String email) {
        Customer customer = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            customer = session.get(Customer.class, email);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return customer;
    }

}

