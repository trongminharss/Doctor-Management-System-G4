/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor.lab1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class DoctorLab1 {

    public static List<Doctor> doctors = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nDoctor Management System:");
            System.out.println("1. Add Doctor");
            System.out.println("2. Edit Doctor");
            System.out.println("3. Delete Doctor");
            System.out.println("4. Search Doctor by ID");
            System.out.println("5. Search Doctor by Name");
            System.out.println("6. Sort Doctors by Date of Birth");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addDoctorFromInput();
                    break;
                case 2:
                    editDoctorFromInput();
                    break;
                case 3:
                    deleteDoctorFromInput();
                    break;
                case 4:
                    searchDoctorByIdFromInput();
                    break;
                case 5:
                    searchDoctorByNameFromInput();
                    break;
                case 6:
                    sortDoctorsByDOB();
                    break;
                case 7:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    // Phương thức để thêm bác sĩ với việc nhập từ người dùng
    public static void addDoctorFromInput() {
        System.out.print("Enter name (max 50 characters): ");
        String name = scanner.nextLine();
        System.out.print("Enter Date of Birth (dd/MM/yyyy): ");
        String dob = scanner.nextLine();
        System.out.print("Enter specialization (max 255 characters): ");
        String specialization = scanner.nextLine();
        System.out.print("Enter availability (0: Vacation, 1: Available, 2: Emergency, 3: Diagnosing): ");
        int availability = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter mobile (format: (000)-000-0000): ");
        String mobile = scanner.nextLine();

        addDoctor(name, dob, specialization, availability, email, mobile);
    }

    // Phương thức chính để thêm bác sĩ (dùng trong test và trong chương trình)
    public static boolean addDoctor(String name, String dob, String specialization, int availability, String email, String mobile) {
        if (name.length() > 50) {
            System.out.println("Name is too long. Doctor not added.");
            return false;
        }
        if (!isValidDate(dob)) {
            System.out.println("Invalid date format. Doctor not added.");
            return false;
        }
        if (specialization.length() > 255) {
            System.out.println("Specialization is too long. Doctor not added.");
            return false;
        }
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format. Doctor not added.");
            return false;
        }
        if (!isValidMobile(mobile)) {
            System.out.println("Invalid mobile format. Doctor not added.");
            return false;
        }

        doctors.add(new Doctor(name, dob, specialization, availability, email, mobile));
        System.out.println("Doctor added successfully.");
        return true;
    }

    // Tương tự, hàm sửa bác sĩ với việc nhập từ người dùng
    public static void editDoctorFromInput() {
        System.out.print("Enter the doctor ID to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Doctor doctor = findDoctorById(id);

        if (doctor != null) {
            System.out.print("Enter new specialization: ");
            String specialization = scanner.nextLine();
            System.out.print("Enter new availability (0: Vacation, 1: Available, 2: Emergency, 3: Diagnosing): ");
            int availability = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter new email: ");
            String email = scanner.nextLine();
            System.out.print("Enter new mobile: ");
            String mobile = scanner.nextLine();

            editDoctor(id, specialization, availability, email, mobile);
        } else {
            System.out.println("Doctor not found.");
        }
    }

    // Phương thức chính để sửa bác sĩ (dùng trong test và trong chương trình)
    public static boolean editDoctor(int id, String specialization, int availability, String email, String mobile) {
        Doctor doctor = findDoctorById(id);
        if (doctor != null) {
            if (!isValidEmail(email)) {
                System.out.println("Invalid email format.");
                return false;
            }
            if (!isValidMobile(mobile)) {
                System.out.println("Invalid mobile format.");
                return false;
            }
            doctor.setSpecialization(specialization);
            doctor.setAvailability(availability);
            doctor.setEmail(email);
            doctor.setMobile(mobile);
            System.out.println("Doctor information updated.");
            return true;
        } else {
            System.out.println("Doctor not found.");
            return false;
        }
    }

    // Tương tự, hàm xóa bác sĩ
    public static void deleteDoctorFromInput() {
        System.out.print("Enter the doctor ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        deleteDoctor(id);
    }

    // Phương thức chính để xóa bác sĩ (dùng trong test và trong chương trình)
    public static boolean deleteDoctor(int id) {
        Doctor doctor = findDoctorById(id);
        if (doctor != null) {
            doctors.remove(doctor);
            System.out.println("Doctor deleted.");
            return true;
        } else {
            System.out.println("Doctor not found.");
            return false;
        }
    }

    // Tương tự, tìm kiếm bác sĩ theo ID và tên
    public static void searchDoctorByIdFromInput() {
        System.out.print("Enter doctor ID to search: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        searchDoctorById(id);
    }

    public static Doctor searchDoctorById(int id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) {
                System.out.println(doctor);
                return doctor;
            }
        }
        System.out.println("Doctor not found.");
        return null;
    }

    public static void searchDoctorByNameFromInput() {
        System.out.print("Enter doctor name to search: ");
        String name = scanner.nextLine();
        searchDoctorByName(name);
    }

    public static List<Doctor> searchDoctorByName(String name) {
        List<Doctor> result = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.getName().equalsIgnoreCase(name)) {
                System.out.println(doctor);
                result.add(doctor);
            }
        }
        if (result.isEmpty()) {
            System.out.println("Doctor not found.");
        }
        return result;
    }

    // Phương thức sắp xếp bác sĩ theo ngày sinh
    public static void sortDoctorsByDOB() {
        Collections.sort(doctors, new Comparator<Doctor>() {
            @Override
            public int compare(Doctor d1, Doctor d2) {
                try {
                    return new SimpleDateFormat("dd/MM/yyyy").parse(d1.getDateOfBirth())
                            .compareTo(new SimpleDateFormat("dd/MM/yyyy").parse(d2.getDateOfBirth()));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });

        System.out.println("Doctors sorted by Date of Birth:");
        for (Doctor doctor : doctors) {
            System.out.println(doctor);
        }
    }

    // Helper method để tìm bác sĩ theo ID
    public static Doctor findDoctorById(int id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) {
                return doctor;
            }
        }
        return null;
    }

    // Các hàm helper để validate dữ liệu đầu vào
    public static boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isValidEmail(String email) {
        return email.contains("@");
    }

    public static boolean isValidMobile(String mobile) {
        return mobile.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}");
    }

    public static List<Doctor> getAllDoctors() {
        return doctors;
    }

}
