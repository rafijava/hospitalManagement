package com.rafi.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafi.entity.Patient;
import com.rafi.service.PatientService;

@RestController
@RequestMapping("/api")
public class PatientRestController {

	// autowire the PatientService
	@Autowired
	private PatientService patientService;
	
	// add mapping for GET /Patients
	@GetMapping("/patients")
	public List<Patient> getPatients() {
		
		return patientService.getPatients();
		
	}
	
	// add mapping for GET /Patients/{PatientId}
	
	@GetMapping("/patients/{patientId}")
	public Patient getPatient(@PathVariable int patientId) {
		
		Patient thePatient = patientService.getPatient(patientId);
		
		if (thePatient == null) {
			throw new PatientNotFoundException("Patient id not found - " + patientId);
		}
		
		return thePatient;
	}
	
	// add mapping for POST /Patients  - add new Patient
	
	@PostMapping("/patients")
	public Patient addPatient(@RequestBody Patient thePatient) {
		
		// also just in case the pass an id in JSON ... set id to 0
		// this is force a save of new item ... instead of update
		
		thePatient.setId(0);
		
		patientService.savePatient(thePatient);
		
		return thePatient;
	}
	
	// add mapping for PUT /Patients - update existing Patient
	
	@PutMapping("/patients")
	public Patient updatePatient(@RequestBody Patient thePatient) {
		
		patientService.savePatient(thePatient);
		
		return thePatient;
		
	}
	
	// add mapping for DELETE /Patients/{PatientId} - delete Patient
	
	@DeleteMapping("/patients/{patientId}")
	public String deletePatient(@PathVariable int patientId) {
		
		Patient tempPatient = patientService.getPatient(patientId);
		
		// throw exception if null
		
		if (tempPatient == null) {
			throw new PatientNotFoundException("Patient id not found - " + patientId);
		}
				
		patientService.deletePatient(patientId);
		
		return "Deleted Patient id - " + patientId;
	}
	
}


















