package controller;

import model.Prescription;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class PrescriptionController {

    private ArrayList<Prescription> prescriptions = new ArrayList<>();

    public void savePrescriptionToCSV(Prescription p, String filePath) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {

            bw.newLine();
            bw.write(
                    q(p.getPrescriptionId()) + ","
                    + q(p.getPatientId()) + ","
                    + q(p.getClinicianId()) + ","
                    + q(p.getAppointmentId()) + ","
                    + q(p.getPrescriptionDate()) + ","
                    + q(p.getMedicationName()) + ","
                    + q(p.getDosage()) + ","
                    + q(p.getFrequency()) + ","
                    + q(p.getDurationDays()) + ","
                    + q(p.getQuantity()) + ","
                    + q(p.getInstructions()) + ","
                    + q(p.getPharmacyName()) + ","
                    + q(p.getStatus()) + ","
                    + q(p.getIssueDate()) + ","
                    + q(p.getCollectionDate())
            );

        } catch (Exception e) {
            System.out.println("Error saving prescription to CSV: " + e.getMessage());
        }
    }

    public String[] loadIdsFromCSV(String filePath) {

        java.util.ArrayList<String> ids = new java.util.ArrayList<>();

        try (java.io.BufferedReader br
                = new java.io.BufferedReader(new java.io.FileReader(filePath))) {

            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                ids.add(data[0].replace("\"", "").trim()); // first column = ID
            }

        } catch (Exception e) {
            System.out.println("Error loading IDs from " + filePath);
        }

        return ids.toArray(new String[0]);
    }

    public void generatePrescriptionTextFile(Prescription p) {

        try {
            File dir = new File("output/prescriptions");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName
                    = "output/prescriptions/prescription_" + p.getPrescriptionId() + ".txt";

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

                bw.write("PRESCRIPTION DETAILS");
                bw.newLine();
                bw.write("=================================");
                bw.newLine();

                bw.write("Prescription ID: " + p.getPrescriptionId());
                bw.newLine();
                bw.write("Patient ID: " + p.getPatientId());
                bw.newLine();
                bw.write("Clinician ID: " + p.getClinicianId());
                bw.newLine();
                bw.write("Appointment ID: " + p.getAppointmentId());
                bw.newLine();
                bw.write("Prescription Date: " + p.getPrescriptionDate());
                bw.newLine();
                bw.write("Medication Name: " + p.getMedicationName());
                bw.newLine();
                bw.write("Dosage: " + p.getDosage());
                bw.newLine();
                bw.write("Frequency: " + p.getFrequency());
                bw.newLine();
                bw.write("Duration (Days): " + p.getDurationDays());
                bw.newLine();
                bw.write("Quantity: " + p.getQuantity());
                bw.newLine();
                bw.write("Instructions: " + p.getInstructions());
                bw.newLine();
                bw.write("Pharmacy Name: " + p.getPharmacyName());
                bw.newLine();
                bw.write("Status: " + p.getStatus());
                bw.newLine();
                bw.write("Issue Date: " + p.getIssueDate());
                bw.newLine();
                bw.write("Collection Date: " + p.getCollectionDate());
                bw.newLine();
            }

        } catch (Exception e) {
            System.out.println("Error creating prescription text file: " + e.getMessage());
        }
    }

    private String q(String value) {
        return "\"" + value + "\"";
    }

    public void loadPrescriptionsFromCSV(String filePath) {

        prescriptions.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].replace("\"", "").trim();
                }

                Prescription p = new Prescription(
                        getValue(data, 0),
                        getValue(data, 1),
                        getValue(data, 2),
                        getValue(data, 3),
                        getValue(data, 4),
                        getValue(data, 5),
                        getValue(data, 6),
                        getValue(data, 7),
                        getValue(data, 8),
                        getValue(data, 9),
                        getValue(data, 10),
                        getValue(data, 11),
                        getValue(data, 12),
                        getValue(data, 13),
                        getValue(data, 14)
                );

                prescriptions.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error loading prescriptions: " + e.getMessage());
        }
    }

    private String getValue(String[] data, int index) {
        return index < data.length ? data[index] : "";
    }

    public void updatePrescription(int index, Prescription updated, String filePath) {

        prescriptions.set(index, updated);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {

            bw.write("prescription_id,patient_id,clinician_id,appointment_id,"
                    + "prescription_date,medication_name,dosage,frequency,"
                    + "duration_days,quantity,instructions,pharmacy_name,"
                    + "status,issue_date,collection_date");

            for (Prescription p : prescriptions) {
                bw.newLine();
                bw.write(
                        q(p.getPrescriptionId()) + ","
                        + q(p.getPatientId()) + ","
                        + q(p.getClinicianId()) + ","
                        + q(p.getAppointmentId()) + ","
                        + q(p.getPrescriptionDate()) + ","
                        + q(p.getMedicationName()) + ","
                        + q(p.getDosage()) + ","
                        + q(p.getFrequency()) + ","
                        + q(p.getDurationDays()) + ","
                        + q(p.getQuantity()) + ","
                        + q(p.getInstructions()) + ","
                        + q(p.getPharmacyName()) + ","
                        + q(p.getStatus()) + ","
                        + q(p.getIssueDate()) + ","
                        + q(p.getCollectionDate())
                );
            }

        } catch (Exception e) {
            System.out.println("Error updating prescriptions: " + e.getMessage());
        }
    }

    public void deletePrescription(int index, String filePath) {

        prescriptions.remove(index);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {

            bw.write("prescription_id,patient_id,clinician_id,appointment_id,"
                    + "prescription_date,medication_name,dosage,frequency,"
                    + "duration_days,quantity,instructions,pharmacy_name,"
                    + "status,issue_date,collection_date");

            for (Prescription p : prescriptions) {
                bw.newLine();
                bw.write(
                        q(p.getPrescriptionId()) + ","
                        + q(p.getPatientId()) + ","
                        + q(p.getClinicianId()) + ","
                        + q(p.getAppointmentId()) + ","
                        + q(p.getPrescriptionDate()) + ","
                        + q(p.getMedicationName()) + ","
                        + q(p.getDosage()) + ","
                        + q(p.getFrequency()) + ","
                        + q(p.getDurationDays()) + ","
                        + q(p.getQuantity()) + ","
                        + q(p.getInstructions()) + ","
                        + q(p.getPharmacyName()) + ","
                        + q(p.getStatus()) + ","
                        + q(p.getIssueDate()) + ","
                        + q(p.getCollectionDate())
                );
            }

        } catch (Exception e) {
            System.out.println("Error deleting prescription: " + e.getMessage());
        }
    }

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }
}
