/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package doctor;

import Entity.Doctor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author gauxa
 */
public class Validate {

    private final static Scanner in = new Scanner(System.in);

    public static int checkInputIntLimit(int min, int max) {
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();

                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number in rage [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }

    public static String checkInputString(int max) {
        while (true) {
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Input cannot be empty");
                System.out.print("Enter again: ");
            } else if (result.length() > max) {
                System.err.println("Input exceeds the maximum length of " + max + " characters.");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    public static boolean checkInputYN(int max) {
        while (true) {
            String result = checkInputString(max);
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }

    public static String checkString() {
        while (true) {
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    public static String checkDateOfBirth() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // To ensure strict parsing of the date
        while (true) {
            System.out.print("Enter Date of Birth (dd/MM/yyyy): ");
            String dateOfBirth = in.nextLine().trim();
            try {
                sdf.parse(dateOfBirth);
                return dateOfBirth;
            } catch (ParseException e) {
                System.err.println("Invalid date format. Please enter in dd/MM/yyyy format.");
            }
        }
    }

    public static String checkEmail() {
        String emailPattern = "^[\\w-\\.]+@[\\w-]+(\\.[\\w-]+)+$";
        while (true) {
            System.out.print("Enter email: ");
            String email = in.nextLine().trim();
            if (Pattern.matches(emailPattern, email)) {
                return email;
            } else {
                System.err.println("Invalid email format. Please try again.");
            }
        }
    }

    // Check mobile format (000)-000-0000
    public static String checkMobile() {
        String mobilePattern = "^\\(\\d{3}\\)-\\d{3}-\\d{4}$";
        while (true) {
            System.out.print("Enter mobile number (format: (000)-000-0000): ");
            String mobile = in.nextLine().trim();
            if (Pattern.matches(mobilePattern, mobile)) {
                return mobile;
            } else {
                System.err.println("Invalid mobile number format. Please try again.");
            }
        }
    }

    public static boolean checkCodeExist(ArrayList<Doctor> ld, int id) {
        for (Doctor doctor : ld) {
            if (doctor.getId() == id) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDuplicate(ArrayList<Doctor> ld, int id,
            String name, String dateOfBirth, String specialization, int availability, String email, String mobile) {
        for (Doctor doctor : ld) {
            if (doctor.getId() == id
                    && doctor.getName().equalsIgnoreCase(name)
                    && doctor.getDateOfBirth().equalsIgnoreCase(dateOfBirth)
                    && doctor.getSpecialization().equalsIgnoreCase(specialization)
                    && doctor.getAvailability() == availability
                    && doctor.getEmail().equalsIgnoreCase(email)
                    && doctor.getMobile().equals(mobile)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkChangeInfo(Doctor doctor, int id,
            String name, String dateOfBirth, String specialization, int availability, String email, String mobile) {
        if (doctor.getId() == id
                && doctor.getName().equalsIgnoreCase(name)
                && doctor.getDateOfBirth().equalsIgnoreCase(dateOfBirth)
                && doctor.getSpecialization().equalsIgnoreCase(specialization)
                && doctor.getAvailability() == availability
                && doctor.getEmail().equalsIgnoreCase(email)
                && doctor.getMobile().equals(mobile)) {
            return false;
        }
        return true;
    }

}
