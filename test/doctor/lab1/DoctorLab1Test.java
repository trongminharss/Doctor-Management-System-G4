/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor.lab1;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;


public class DoctorLab1Test {

    @Test
    public void testAddDoctor_Success() {
        boolean result1 = DoctorLab1.addDoctor("pthanh", "30/04/2002", "Cardiology", 1, "pthanh@gmail.com", "(123)-456-7890");
        boolean result2 = DoctorLab1.addDoctor("ly", "26/06/1995", "Cardiology", 1, "ly@gmail.com", "(123)-456-7890");
        boolean result3 = DoctorLab1.addDoctor("hung", "26/06/2000", "Cardiology", 1, "hung@gmail.com", "(123)-456-7890");

        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    public void testGetListDoctors() {
        List<Doctor> doctors = DoctorLab1.getAllDoctors();

        assertEquals(3, doctors.size());
    }

    @Test
    public void testAddDoctor_NameTooLong() {
        boolean result = DoctorLab1.addDoctor("This is a very long name that exceeds fifty characters", "30/04/2002", "Cardiology", 1, "pthanh@gmail.com", "(123)-456-7890");
        assertFalse(result);
    }

    @Test
    public void testAddDoctor_InvalidDateOfBirth() {
        boolean result = DoctorLab1.addDoctor("pthanh", "31/02/1980", "Cardiology", 1, "pthanh@gmail.com", "(123)-456-7890");
        assertFalse(result);
    }

    @Test
    public void testAddDoctor_SpecializationTooLong() {
        boolean result = DoctorLab1.addDoctor("pthanh", "30/04/2002", "This specialization is way too long and exceeds 255 characters."
                + "This specialization is way too long and exceeds 255 characters."
                + "This specialization is way too long and exceeds 255 characters."
                + "This specialization is way too long and exceeds 255 characters."
                + "This specialization is way too long and exceeds 255 characters."
                + "This specialization is way too long and exceeds 255 characters.", 1, "pthanh@gmail.com", "(123)-456-7890");
        assertFalse(result);
    }

    @Test
    public void testAddDoctor_InvalidEmail() {
        boolean result = DoctorLab1.addDoctor("pthanh", "30/04/2002", "Cardiology", 1, "com", "(123)-456-7890");
        assertFalse(result);
    }

    @Test
    public void testAddDoctor_InvalidMobile() {
        boolean result = DoctorLab1.addDoctor("pthanh", "30/04/2002", "Cardiology", 1, "pthanh@gmail.com", "123-456-7890");
        assertFalse(result);
    }

    @Test
    public void testEditDoctor_Success() {
        boolean result = DoctorLab1.editDoctor(1, "Neurology", 2, "pthanh-edited@gmail.com", "(321)-654-0987");
        assertTrue(result);
    }

    @Test
    public void testEditDoctor_DoctorNotFound() {
        boolean result = DoctorLab1.editDoctor(999, "Neurology", 2, "pthanh-edited@gmail.com", "(321)-654-0987");
        assertFalse(result);
    }

    @Test
    public void testEditDoctor_InvalidEmail() {
        boolean result = DoctorLab1.editDoctor(1, "Neurology", 2, "pthanh-editedail.com", "(321)-654-0987");
        assertFalse(result);
    }

    @Test
    public void testEditDoctor_InvalidMobile() {
        boolean result = DoctorLab1.editDoctor(1, "Neurology", 2, "pthanh-edit@gmail.com", "321-654-0987");
        assertFalse(result);
    }

    @Test
    public void testDeleteDoctor_DoctorNotFound() {
        boolean result = DoctorLab1.deleteDoctor(999);
        assertFalse(result);
    }

    @Test
    public void testSearchDoctorById_Success() {
        Doctor doctor = DoctorLab1.searchDoctorById(2);
        assertNotNull(doctor);
        assertEquals("ly", doctor.getName());
    }

    @Test
    public void testSearchDoctorById_NotFound() {
        Doctor doctor = DoctorLab1.searchDoctorById(999);
        assertNull(doctor);
    }

    @Test
    public void testSearchDoctorByName_Success() {
        List<Doctor> doctors = DoctorLab1.searchDoctorByName("pthanh");
        assertEquals("pthanh", doctors.get(0).getName());
    }

    @Test
    public void testSearchDoctorByName_NotFound() {
        List<Doctor> doctors = DoctorLab1.searchDoctorByName("hieu");
        assertTrue(doctors.isEmpty());
    }

    @Test
    public void testSortDoctorsByDOB_Success() {
        DoctorLab1.sortDoctorsByDOB();

        // Access the sorted list of doctors
        List<Doctor> sortedDoctors = DoctorLab1.getAllDoctors();

        // Check if the first doctor in the sorted list is Jane Doe
        assertEquals("ly", sortedDoctors.get(0).getName());
    }

    @Test
    public void testIsValidEmail() {
        assertTrue(DoctorLab1.isValidEmail("valid.email@example.com"));
    }

    @Test
    public void testInValidEmail() {
        assertFalse(DoctorLab1.isValidEmail("invalidemail"));
    }

    @Test
    public void testAddDoctor_InvalidMobileFormat() {
        boolean result = DoctorLab1.addDoctor("pthanh", "30/04/2002", "Cardiology", 1, "pthanh@gmail.com", "(123)-456-789"); // Invalid mobile format
        assertFalse(result);
    }

}
