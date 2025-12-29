package controller;

import model.Clinician;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class ClinicianController {

    private ArrayList<Clinician> clinicians = new ArrayList<>();

    public ArrayList<Clinician> getClinicians() {
        return clinicians;
    }

    public void loadCliniciansFromCSV(String filePath) {

        clinicians.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); 

            while ((line = br.readLine()) != null) {

                String[] data
                        = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                Clinician c = new Clinician(
                        clean(data, 0), 
                        clean(data, 1), 
                        clean(data, 2), 
                        clean(data, 3), 
                        clean(data, 4), 
                        clean(data, 5), 
                        clean(data, 6), 
                        clean(data, 7), 
                        clean(data, 8), 
                        clean(data, 9), 
                        clean(data, 10), 
                        clean(data, 11) 
                );

                clinicians.add(c);
            }

        } catch (Exception e) {
            System.out.println("Error loading clinicians: " + e.getMessage());
        }
    }

    public void saveClinicianToCSV(Clinician c, String filePath) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {

            bw.newLine();
            bw.write(
                    q(c.getClinicianId()) + ","
                    + q(c.getFirstName()) + ","
                    + q(c.getLastName()) + ","
                    + q(c.getTitle()) + ","
                    + q(c.getSpeciality()) + ","
                    + q(c.getGmcNumber()) + ","
                    + q(c.getPhoneNumber()) + ","
                    + q(c.getEmail()) + ","
                    + q(c.getWorkplaceId()) + ","
                    + q(c.getWorkplaceType()) + ","
                    + q(c.getEmploymentStatus()) + ","
                    + q(c.getStartDate())
            );

        } catch (Exception e) {
            System.out.println("Error saving clinician: " + e.getMessage());
        }
    }

    public void updateClinician(int index, Clinician updated, String filePath) {

        clinicians.set(index, updated);

        rewriteCSV(filePath);
    }

    public void deleteClinician(int index, String filePath) {

        clinicians.remove(index);

        rewriteCSV(filePath);
    }

    private void rewriteCSV(String filePath) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {

            // header
            bw.write(
                    "clinician_id,first_name,last_name,title,speciality,"
                    + "gmc_number,phone_number,email,workplace_id,"
                    + "workplace_type,employment_status,start_date"
            );

            for (Clinician c : clinicians) {
                bw.newLine();
                bw.write(
                        q(c.getClinicianId()) + ","
                        + q(c.getFirstName()) + ","
                        + q(c.getLastName()) + ","
                        + q(c.getTitle()) + ","
                        + q(c.getSpeciality()) + ","
                        + q(c.getGmcNumber()) + ","
                        + q(c.getPhoneNumber()) + ","
                        + q(c.getEmail()) + ","
                        + q(c.getWorkplaceId()) + ","
                        + q(c.getWorkplaceType()) + ","
                        + q(c.getEmploymentStatus()) + ","
                        + q(c.getStartDate())
                );
            }

        } catch (Exception e) {
            System.out.println("Error rewriting clinicians CSV: " + e.getMessage());
        }
    }

    private String q(String v) {
        return "\"" + v + "\"";
    }

    private String clean(String[] data, int index) {
        if (index >= data.length) {
            return "";
        }
        return data[index].replace("\"", "").trim();
    }
}
