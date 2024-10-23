/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Doctor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DoctorDAOImpl implements DoctorDAO {

    private List<Doctor> doctors = new ArrayList<>();

    public DoctorDAOImpl() {
        doctors = new ArrayList<>();
    }

    @Override
    public void createDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    @Override
    public void editDoctor(int id, Doctor updatedDoctor) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) {
                doctor.setName(updatedDoctor.getName());
                doctor.setSpecialization(updatedDoctor.getSpecialization());
                doctor.setDateOfBirth(updatedDoctor.getDateOfBirth());
                doctor.setAvailability(updatedDoctor.getAvailability());
                doctor.setEmail(updatedDoctor.getEmail());
                doctor.setMobile(updatedDoctor.getMobile());
                return; 
            }
        }
        throw new IllegalArgumentException("Doctor with ID " + id + " not found.");
    }

    public void editDoctor(Doctor doctor) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId() == doctor.getId()) {
                doctors.set(i, doctor); 
                return; 
            }
        }
    }

    @Override
    public void deleteDoctor(int id) {
        doctors.removeIf(doctor -> doctor.getId() == id);
    }

    @Override
    public Doctor searchDoctorById(int id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) {
                return doctor;
            }
        }
        return null; 
    }

    @Override
    public List<Doctor> searchDoctorByName(String name) {
        List<Doctor> result = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.getName().equalsIgnoreCase(name)) {
                result.add(doctor);
            }
        }
        return result; 
    }

    @Override
    public List<Doctor> sortDoctorsByDateOfBirth() {
        if (doctors == null || doctors.isEmpty()) {
            return Collections.emptyList(); 
        }
        doctors.sort(Comparator.comparing(Doctor::getDateOfBirth));
        return new ArrayList<>(doctors); 
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors); 
    }

    @Override
    public int getNextId() {
        if (doctors.isEmpty()) {
            return 1; 
        } else {
            return doctors.stream().mapToInt(Doctor::getId).max().orElse(0) + 1;
        }
    }

}
