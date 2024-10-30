package doctor;

import Entity.Doctor;
import java.util.ArrayList;

/**
 *
 * @author gauxa
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Doctor> ld = new ArrayList<>();
        while (true) {
            int choice = Manager.menu();
            switch (choice) {
                case 1:
                    Manager.addDoctor(ld);
                    break;
                case 2:
                    Manager.updateDoctor(ld);
                    break;
                case 3:
                    Manager.deleteDoctor(ld);
                    break;
                case 4:
                    Manager.searchDoctor(ld);
                    break;
                case 5:
                    Manager.sortDoctorByDateOfBirth(ld);
                    break;
                case 6:
                    Manager.displayDoctors(ld);
                    break;
                case 7:
                    return;
            }
        }
    }

   
}
