
package controller;

import model.Patient;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class PatientController {

    private ArrayList<Patient> patients = new ArrayList<>();

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void loadPatientsFromCSV(String filePath) {

        patients.clear(); 

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            br.readLine(); 

            while ((line = br.readLine()) != null) {
                patients.add(Patient.fromCSV(line));
            }

        } catch (Exception e) {
            System.out.println("Error loading patients: " + e.getMessage());
        }
    }

    public void updatePatient(int index, Patient updated, String filePath) {

        patients.set(index, updated);

        rewriteCSV(filePath);
    }

    public void deletePatient(int index, String filePath) {

        patients.remove(index);

        rewriteCSV(filePath);
    }

    private void rewriteCSV(String filePath) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {

            bw.write(
                    "id,first_name,last_name,date_of_birth,nhs_number,gender,"
                    + "phone,email,address,postcode,"
                    + "emergency_contact_name,emergency_contact_phone,"
                    + "registration_date,gp_surgery_id"
            );

            for (Patient p : patients) {
                bw.newLine();
                bw.write(
                        quote(p.getId()) + ","
                        + quote(p.getFirstName()) + ","
                        + quote(p.getLastName()) + ","
                        + quote(p.getDateOfBirth()) + ","
                        + quote(p.getNhsNumber()) + ","
                        + quote(p.getGender()) + ","
                        + quote(p.getPhone()) + ","
                        + quote(p.getEmail()) + ","
                        + quote(p.getAddress()) + ","
                        + quote(p.getPostcode()) + ","
                        + quote(p.getEmergencyContactName()) + ","
                        + quote(p.getEmergencyContactPhone()) + ","
                        + quote(p.getRegistrationDate()) + ","
                        + quote(p.getGpSurgeryId())
                );
            }

        } catch (Exception e) {
            System.out.println("Error rewriting patients CSV: " + e.getMessage());
        }
    }

    public void savePatientToCSV(Patient p, String filePath) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {

            bw.newLine();
            bw.write(
                    quote(p.getId()) + ","
                    + quote(p.getFirstName()) + ","
                    + quote(p.getLastName()) + ","
                    + quote(p.getDateOfBirth()) + ","
                    + quote(p.getNhsNumber()) + ","
                    + quote(p.getGender()) + ","
                    + quote(p.getPhone()) + ","
                    + quote(p.getEmail()) + ","
                    + quote(p.getAddress()) + ","
                    + quote(p.getPostcode()) + ","
                    + quote(p.getEmergencyContactName()) + ","
                    + quote(p.getEmergencyContactPhone()) + ","
                    + quote(p.getRegistrationDate()) + ","
                    + quote(p.getGpSurgeryId())
            );

        } catch (Exception e) {
            System.out.println("Error saving patient: " + e.getMessage());
        }
    }

    private String quote(String value) {
        return "\"" + value + "\"";
    }
}
