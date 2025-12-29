package controller;

import model.Appointment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class AppointmentController {

    private ArrayList<Appointment> appointments = new ArrayList<>();

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void loadAppointmentsFromCSV(String filePath) {
        appointments.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                Appointment a = new Appointment(
                        removeQuotes(data[0]),
                        removeQuotes(data[1]),
                        removeQuotes(data[2]),
                        removeQuotes(data[3]),
                        removeQuotes(data[4]),
                        removeQuotes(data[5]),
                        removeQuotes(data[6]),
                        removeQuotes(data[7]),
                        removeQuotes(data[8]),
                        removeQuotes(data[9]),
                        removeQuotes(data[10]),
                        removeQuotes(data[11]),
                        removeQuotes(data[12])
                );

                appointments.add(a);
            }

        } catch (Exception e) {
            System.out.println("Error loading appointments: " + e.getMessage());
        }
    }

    public void updateAppointment(int index, Appointment updated, String filePath) {

        appointments.set(index, updated);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {

            bw.write(
                    "appointment_id,patient_id,clinician_id,facility_id,"
                    + "appointment_date,appointment_time,duration_minutes,"
                    + "appointment_type,status,reason_for_visit,notes,"
                    + "created_date,last_modified"
            );

            for (Appointment a : appointments) {
                bw.newLine();
                bw.write(
                        q(a.getAppointmentId()) + ","
                        + q(a.getPatientId()) + ","
                        + q(a.getClinicianId()) + ","
                        + q(a.getFacilityId()) + ","
                        + q(a.getAppointmentDate()) + ","
                        + q(a.getAppointmentTime()) + ","
                        + q(a.getDurationMinutes()) + ","
                        + q(a.getAppointmentType()) + ","
                        + q(a.getStatus()) + ","
                        + q(a.getReasonForVisit()) + ","
                        + q(a.getNotes()) + ","
                        + q(a.getCreatedDate()) + ","
                        + q(a.getLastModified())
                );
            }

        } catch (Exception e) {
            System.out.println("Error updating appointment: " + e.getMessage());
        }
    }
        public String[] loadIdsFromCSV(String filePath) {

    java.util.ArrayList<String> ids = new java.util.ArrayList<>();

    try (java.io.BufferedReader br =
                 new java.io.BufferedReader(new java.io.FileReader(filePath))) {

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

    public void saveAppointmentToCSV(Appointment a, String filePath) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {

            bw.newLine();
            bw.write(
                    q(a.getAppointmentId()) + ","
                    + q(a.getPatientId()) + ","
                    + q(a.getClinicianId()) + ","
                    + q(a.getFacilityId()) + ","
                    + q(a.getAppointmentDate()) + ","
                    + q(a.getAppointmentTime()) + ","
                    + q(String.valueOf(a.getDurationMinutes())) + ","
                    + q(a.getAppointmentType()) + ","
                    + q(a.getStatus()) + ","
                    + q(a.getReasonForVisit()) + ","
                    + q(a.getNotes()) + ","
                    + q(a.getCreatedDate()) + ","
                    + q(a.getLastModified())
            );

        } catch (Exception e) {
            System.out.println("Error saving appointment: " + e.getMessage());
        }
    }

    public void deleteAppointment(int index, String filePath) {

        appointments.remove(index);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {

            bw.write("appointment_id,patient_id,clinician_id,facility_id,appointment_date,appointment_time,duration_minutes,appointment_type,status,reason_for_visit,notes,created_date,last_modified");

            for (Appointment a : appointments) {
                bw.newLine();
                bw.write(
                        q(a.getAppointmentId()) + ","
                        + q(a.getPatientId()) + ","
                        + q(a.getClinicianId()) + ","
                        + q(a.getFacilityId()) + ","
                        + q(a.getAppointmentDate()) + ","
                        + q(a.getAppointmentTime()) + ","
                        + q(String.valueOf(a.getDurationMinutes())) + ","
                        + q(a.getAppointmentType()) + ","
                        + q(a.getStatus()) + ","
                        + q(a.getReasonForVisit()) + ","
                        + q(a.getNotes()) + ","
                        + q(a.getCreatedDate()) + ","
                        + q(a.getLastModified())
                );
            }

        } catch (Exception e) {
            System.out.println("Error deleting appointment: " + e.getMessage());
        }
    }

    private String q(String v) {
        return "\"" + v + "\"";
    }

    private String removeQuotes(String v) {
        return v.replace("\"", "");
    }
}
