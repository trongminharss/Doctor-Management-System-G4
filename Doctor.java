/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author gauxa
 */
public class Doctor {

    private int id;
    private String name;
    private String dateOfBirth;
    private String specialization;
    private int availability;
    private String email;
    private String mobile;

    public Doctor(int id, String name, String dateOfBirth, String specialization, int availability, String email, String mobile) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.specialization = specialization;
        this.availability = availability;
        this.email = email;
        this.mobile = mobile;
    }

    public Doctor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Doctor{" + "id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", specialization=" + specialization + ", availability=" + availability + ", email=" + email + ", mobile=" + mobile + '}';
    }

}
