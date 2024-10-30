/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package doctor;

import Entity.Doctor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

/**
 *
 * @author gauxa
 */
public class Manager {

    private static int currentId = 1;

    public static int menu() {
        System.out.println("Doctor Management Program");
        System.out.println("1. Create a doctor");
        System.out.println("2. Edit a doctor Information");
        System.out.println("3. Delete a doctor");
        System.out.println("4. Search doctor by ID and Name");
        System.out.println("5. Sort doctor by date of birth");
        System.out.println("6. Display all doctor");
        System.out.print("Enter your choice: ");
        int choice = Validate.checkInputIntLimit(1, 6);
        return choice;
    }

    public static void addDoctor(ArrayList<Doctor> ld) {
        System.out.println("------Add New Doctor------");
        int id = currentId++;
        System.out.println("ID: " + id);

        System.out.print("Enter name: ");
        String name = Validate.checkInputString(50);
        String dateofBirth = Validate.checkDateOfBirth();
        System.out.print("Enter new specialization: ");
        String specialization = Validate.checkInputString(255);
        System.out.print("Enter availability: ");
        int availability = Validate.checkInputIntLimit(0, 3);
        String email = Validate.checkEmail();
        String mobile = Validate.checkMobile();

        // Kiểm tra trùng lặp doctor
        if (!Validate.checkDuplicate(ld, id, name, dateofBirth, specialization, availability, email, mobile)) {
            System.err.println("Duplicate.");
            return;
        }

        ld.add(new Doctor(id, name, dateofBirth, specialization, availability, email, mobile));
        System.err.println("Add successful.");
    }

    public static void updateDoctor(ArrayList<Doctor> ld) {
        System.out.println("------Edit Doctor------");
        System.out.print("Enter ID- you want to edit: ");
        int id = Validate.checkInputIntLimit(1, Integer.MAX_VALUE);
        if (Validate.checkCodeExist(ld, id)) {
            System.err.println("Not found doctor");
            return;
        }
        System.out.print("Enter new code: ");
        int codeUpdate = Validate.checkInputIntLimit(1, Integer.MAX_VALUE);
        Doctor doctor = getDoctorByCode(ld, id);
        System.out.print("Enter name: ");
        String name = Validate.checkInputString(50);
        String dateofBirth = Validate.checkDateOfBirth();
        System.out.print("Enter specialization: ");
        String specialization = Validate.checkInputString(255);
        System.out.print("Enter availability: ");
        int availability = Validate.checkInputIntLimit(0, 3);
        String email = Validate.checkEmail();
        String mobile = Validate.checkMobile();
        if (!Validate.checkChangeInfo(doctor, id, name, dateofBirth, specialization, availability, email, mobile)) {
            System.err.println("No change");
            return;
        }

        doctor.setId(codeUpdate);
        doctor.setName(name);
        doctor.setDateOfBirth(dateofBirth);
        doctor.setSpecialization(specialization);
        doctor.setAvailability(availability);
        doctor.setEmail(email);
        doctor.setMobile(mobile);
        System.err.println("Update successful");
    }

    public static void deleteDoctor(ArrayList<Doctor> ld) {
        System.out.println("------Delete Doctor------");
        System.out.print("Enter code: ");
        int id = Validate.checkInputIntLimit(1, Integer.MAX_VALUE);
        Doctor doctor = getDoctorByCode(ld, id);
        if (doctor == null) {
            System.err.println("Not found doctor.");
            return;
        } else {
            ld.remove(doctor);
        }
        System.err.println("Delete successful.");
    }

    public static Doctor getDoctorByCode(ArrayList<Doctor> ld, int code) {
        for (Doctor doctor : ld) {
            if (doctor.getId() == (code)) {
                return doctor;
            }
        }
        return null;
    }

    public static ArrayList<Doctor> listFoundByName(ArrayList<Doctor> ld, String name) {
        ArrayList<Doctor> listFoundByName = new ArrayList<>();
        for (Doctor doctor : ld) {
            if (doctor.getName().contains(name)) {
                listFoundByName.add(doctor);
            }
        }
        return listFoundByName;
    }

    public static void searchDoctor(ArrayList<Doctor> ld) {
        System.out.println("------Search Doctor------");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.print("Enter your choice: ");
        int choice = Validate.checkInputIntLimit(1, 2);
        String format = "%-10d%-15s%-20s%-25s%-15d%-15s%-15s\n";
        if (choice == 1) {
            System.out.print("Enter ID: ");
            int idSearch = Validate.checkInputIntLimit(1, Integer.MAX_VALUE);
            Doctor doctor = getDoctorByCode(ld, idSearch);
            if (doctor == null) {
                System.err.println("Doctor not found.");
            } else {
                System.out.printf("%-10s%-15s%-20s%-25s%-15s%-15s%-15s\n", "ID", "Name", "Date of Birth", "Specialization", "Availability", "Email", "Mobile");
                System.out.printf(format, doctor.getId(), doctor.getName(), doctor.getDateOfBirth(), doctor.getSpecialization(), doctor.getAvailability(), doctor.getEmail(), doctor.getMobile());
            }
        } else if (choice == 2) {
            System.out.print("Enter name: ");
            String nameSearch = Validate.checkString();
            ArrayList<Doctor> listFoundByName = listFoundByName(ld, nameSearch);
            if (listFoundByName.isEmpty()) {
                System.err.println("No doctors found.");
            } else {
                System.out.printf("%-10s%-15s%-20s%-25s%-15s%-15s%-15s\n", "ID", "Name", "Date of Birth", "Specialization", "Availability", "Email", "Mobile");
                for (Doctor doctor : listFoundByName) {
                    System.out.printf(format, doctor.getId(), doctor.getName(), doctor.getDateOfBirth(), doctor.getSpecialization(), doctor.getAvailability(), doctor.getEmail(), doctor.getMobile());
                }
            }
        }
    }

    public static void sortDoctorByDateOfBirth(ArrayList<Doctor> ld) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        Collections.sort(ld, new Comparator<Doctor>() {
            @Override
            public int compare(Doctor d1, Doctor d2) {
                LocalDate dob1 = LocalDate.parse(d1.getDateOfBirth(), formatter);
                LocalDate dob2 = LocalDate.parse(d2.getDateOfBirth(), formatter);
                return dob1.compareTo(dob2);
            }
        });

        System.out.println("Doctors sorted by date of birth:");
        System.out.printf("%-10d%-15s%-20s%-25s%-15d%-15s%-15s\n", "ID", "Name", "Date of Birth", "Specialization", "Availability", "Email", "Mobile");
        for (Doctor doctor : ld) {
            System.out.printf("%-10d%-15s%-20s%-25s%-15d%-15s%-15s\n",
                    doctor.getId(),
                    doctor.getName(),
                    doctor.getDateOfBirth(),
                    doctor.getSpecialization(),
                    doctor.getAvailability(),
                    doctor.getEmail(),
                    doctor.getMobile());
        }
    }

    public static void displayDoctors(ArrayList<Doctor> ld) {
        if (ld.isEmpty()) {
            System.out.println("No doctors to display.");
            return;
        }
        System.out.println("------List Doctor------");
        System.out.printf("%-10d%-15s%-20s%-25s%-15d%-15s%-15s\n", "ID", "Name", "Date of Birth", "Specialization", "Availability", "Email", "Mobile");
        for (Doctor doctor : ld) {
            System.out.printf("%-10d%-15s%-20s%-25s%-15d%-15s%-15s\n",
                    doctor.getId(),
                    doctor.getName(),
                    doctor.getDateOfBirth(),
                    doctor.getSpecialization(),
                    doctor.getAvailability(),
                    doctor.getEmail(),
                    doctor.getMobile());
        }
    }

}
