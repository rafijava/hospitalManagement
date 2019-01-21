package com.rafi.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rafi.entity.Patient;

@Repository
public class PatientDAOImpl implements PatientDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<Patient> getPatients() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query  ... sort by last name
		Query<Patient> theQuery = 
				currentSession.createQuery("from Patient order by lastName",
											Patient.class);
		
		// execute query and get result list
		List<Patient> patients = theQuery.getResultList();
				
		// return the results		
		return patients;
	}

	@Override
	public void savePatient(Patient thePatient) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/upate the patient ... finally LOL
		currentSession.saveOrUpdate(thePatient);
		
	}

	@Override
	public Patient getPatient(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Patient thePatient = currentSession.get(Patient.class, theId);
		
		return thePatient;
	}

	@Override
	public void deletePatient(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from Patient where id=:PatientId");
		theQuery.setParameter("PatientId", theId);
		
		theQuery.executeUpdate();		
	}

}











