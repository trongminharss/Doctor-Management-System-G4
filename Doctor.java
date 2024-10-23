/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor.lab1;

/**
 *
 * @author thanhtnnt
 */
class Doctor {
    private static int idCounter = 1;
    private int id;
    private String name;
    private String dateOfBirth;
    private String specialization;
    private int availability;
    private String email;
    private String mobile;

    public Doctor(String name, String dateOfBirth, String specialization, int availability, String email, String mobile) {
        this.id = idCounter++;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.specialization = specialization;
        this.availability = availability;
        this.email = email;
        this.mobile = mobile;
    }

    // Getters and setters for doctor attributes
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Doctor [ID=" + id + ", Name=" + name + ", DOB=" + dateOfBirth + ", Specialization=" + specialization 
                + ", Availability=" + availability + ", Email=" + email + ", Mobile=" + mobile + "]";
    }
}
