package com.jspiders.manytoone.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.manytoone.dto.CompanyDto;
import com.jspiders.manytoone.dto.EmployeeDto;


public class EmployeeDaoInsert {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
	
	public static void main(String[] args) {
		
		EmployeeDto employee = new EmployeeDto();
		employee.setName("Ramesh");
		employee.setEmail("ramesh@gmail.com");
		employee.setMobile(9999999999l);
		
		CompanyDto company = new CompanyDto();
		company.setName("Accenture");
		company.setLocation("Mumbai");
		
		employee.setCompany(company);
		
		openConnection();
		entityTransaction.begin();	
		
		entityManager.persist(company);
		entityManager.persist(employee);
		
		entityTransaction.commit();
		closeConnection();
	}
	
	private static void openConnection() {
		entityManagerFactory = Persistence.createEntityManagerFactory("employee");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction  = entityManager.getTransaction();
	}
	
	private static void closeConnection() {
		if (entityManagerFactory!=null) {
			entityManagerFactory.close();
		}
		if (entityManager!=null) {
			entityManager.close();
		}
		if (entityTransaction!=null) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
		}
	}
}
