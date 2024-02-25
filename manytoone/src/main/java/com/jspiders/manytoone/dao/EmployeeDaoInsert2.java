package com.jspiders.manytoone.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.manytoone.dto.CompanyDto;
import com.jspiders.manytoone.dto.EmployeeDto;

public class EmployeeDaoInsert2 {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
	
	public static void main(String[] args) {
				
		openConnection();
		entityTransaction.begin();	
		
		EmployeeDto employee = new EmployeeDto();
		employee.setName("Suresh");
		employee.setEmail("suresh@gmail.com");
		employee.setMobile(8999999999l);
		
		CompanyDto company = entityManager.find(CompanyDto.class, 1);
		
		employee.setCompany(company);
		
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
