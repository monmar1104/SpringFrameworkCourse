package com.monmar.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.monmar.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Customer> query = currentSession.createQuery("from Customer order by lastName", Customer.class);

		List<Customer> customers = query.getResultList();

		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Customer customer = currentSession.get(Customer.class, id);
		return customer;
	}

	@Override
	public void deleteCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();
//		Query query = session.createQuery("delete from Customer where id=:customerId", Customer.class);
//		query.setParameter("customerId", id);
//		query.executeUpdate();

		Customer customer = session.get(Customer.class, id);
		session.delete(customer);
	}

	@Override
	public List<Customer> searchCustomerByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		Query query = null;

		if (name != null && name.trim().length() > 0) {
			query = session.createQuery("from Customer where lower(firstName) like :name or lower(lastName) like :name",
					Customer.class);
			query.setParameter("name", "%" + name.toLowerCase() + "%");
		} else {
			query = session.createQuery("from Customer");
		}

		List<Customer> customers = query.getResultList();

		return customers;
	}

}
