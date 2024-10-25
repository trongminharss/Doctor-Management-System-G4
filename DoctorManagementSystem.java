/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.doctormanagementsystemlab;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class DoctorManagementSystem {

    private List<Doctor> doctors = new ArrayList<>();

    // Create a new doctor
    public Doctor createDoctor(String name, String dateOfBirth, String specialization, int availability, String email, String mobile) throws IllegalArgumentException {
        validateDoctorInfo(name, dateOfBirth, specialization, availability, email, mobile);
        Doctor newDoctor = new Doctor(name, dateOfBirth, specialization, availability, email, mobile);
        doctors.add(newDoctor);
        return newDoctor;
    }

    // Edit an existing doctor by ID
    public boolean editDoctor(int id, String name, String dateOfBirth, String specialization, int availability, String email, String mobile) {
        Doctor doctor = searchDoctorById(id);
        if (doctor != null) {
            validateDoctorInfo(name, dateOfBirth, specialization, availability, email, mobile);
            doctor.setName(name);
            doctor.setDateOfBirth(dateOfBirth);
            doctor.setSpecialization(specialization);
            doctor.setAvailability(availability);
            doctor.setEmail(email);
            doctor.setMobile(mobile);
            return true;
        }
        return false;
    }

    // Delete a doctor by ID
    public boolean deleteDoctor(int id) {
        Doctor doctor = searchDoctorById(id);
        if (doctor != null) {
            doctors.remove(doctor);
            return true;
        }
        return false;
    }

    // Search doctor by ID
    public Doctor searchDoctorById(int id) {
        return doctors.stream().filter(d -> d.getId() == id).findFirst().orElse(null);
    }

    // Search doctor by Name
    public List<Doctor> searchDoctorByName(String name) {
        List<Doctor> results = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.getName().equalsIgnoreCase(name)) {
                results.add(doctor);
            }
        }
        return results;
    }

    // Sort doctors by Date of Birth
    public List<Doctor> sortDoctorsByDateOfBirth() {
        List<Doctor> sortedList = new ArrayList<>(doctors);
        sortedList.sort(Comparator.comparing(Doctor::getDateOfBirth));
        return sortedList;
    }

    // Validate doctor information
    private void validateDoctorInfo(String name, String dateOfBirth, String specialization, int availability, String email, String mobile) throws IllegalArgumentException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (name.length() > 50) {
            throw new IllegalArgumentException("Name cannot be longer than 50 characters.");
        }
        if (dateOfBirth == null || dateOfBirth.trim().isEmpty()) {
            throw new IllegalArgumentException("Date of birth cannot be empty.");
        }
        if (!isValidDate(dateOfBirth)) {
            throw new IllegalArgumentException("Date of birth must follow dd/MM/yyyy format.");
        }
        if (specialization == null || specialization.trim().isEmpty()) {
            throw new IllegalArgumentException("Specialization cannot be empty.");
        }
        if (specialization.length() > 255) {
            throw new IllegalArgumentException("Specialization cannot be longer than 255 characters.");
        }
        if (availability < 0 || availability > 3) {
            throw new IllegalArgumentException("Availability must be between 0 and 3.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        if (mobile == null || mobile.trim().isEmpty()) {
            throw new IllegalArgumentException("Mobile cannot be empty.");
        }
        if (!isValidMobile(mobile)) {
            throw new IllegalArgumentException("Mobile must follow (000)-000-0000 format.");
        }
    }

    // Check valid date
    private boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // Check valid email
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    // Check valid mobile number
    private boolean isValidMobile(String mobile) {
        String mobileRegex = "\\(\\d{3}\\)-\\d{3}-\\d{4}";
        return Pattern.compile(mobileRegex).matcher(mobile).matches();
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }
}
