package controller;

import model.Referral;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class ReferralController {

    public void saveReferralToCSV(Referral r, String filePath) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {

            bw.newLine();
            bw.write(
                    q(r.getReferralId()) + ","
                    + q(r.getPatientId()) + ","
                    + q(r.getReferringClinicianId()) + ","
                    + q(r.getReferredToClinicianId()) + ","
                    + q(r.getReferringFacilityId()) + ","
                    + q(r.getReferredToFacilityId()) + ","
                    + q(r.getReferralDate()) + ","
                    + q(r.getUrgencyLevel()) + ","
                    + q(r.getReferralReason()) + ","
                    + q(r.getClinicalSummary()) + ","
                    + q(r.getRequestedInvestigations()) + ","
                    + q(r.getStatus()) + ","
                    + q(r.getAppointmentId()) + ","
                    + q(r.getNotes()) + ","
                    + q(r.getCreatedDate()) + ","
                    + q(r.getLastUpdated())
            );

        } catch (Exception e) {
            System.out.println("Error saving referral to CSV: " + e.getMessage());
        }
    }

    public void generateReferralTextFile(Referral r) {

        try {
            File dir = new File("output/referrals");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = "output/referrals/referral_" + r.getReferralId() + ".txt";

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

                bw.write("REFERRAL DETAILS");
                bw.newLine();
                bw.write("=================================");
                bw.newLine();

                bw.write("Referral ID: " + r.getReferralId());
                bw.newLine();
                bw.write("Patient ID: " + r.getPatientId());
                bw.newLine();
                bw.write("Referring Clinician ID: " + r.getReferringClinicianId());
                bw.newLine();
                bw.write("Referred To Clinician ID: " + r.getReferredToClinicianId());
                bw.newLine();
                bw.write("Referring Facility ID: " + r.getReferringFacilityId());
                bw.newLine();
                bw.write("Referred To Facility ID: " + r.getReferredToFacilityId());
                bw.newLine();
                bw.write("Referral Date: " + r.getReferralDate());
                bw.newLine();
                bw.write("Urgency Level: " + r.getUrgencyLevel());
                bw.newLine();
                bw.write("Referral Reason: " + r.getReferralReason());
                bw.newLine();
                bw.write("Clinical Summary: " + r.getClinicalSummary());
                bw.newLine();
                bw.write("Requested Investigations: " + r.getRequestedInvestigations());
                bw.newLine();
                bw.write("Status: " + r.getStatus());
                bw.newLine();
                bw.write("Appointment ID: " + r.getAppointmentId());
                bw.newLine();
                bw.write("Notes: " + r.getNotes());
                bw.newLine();
                bw.write("Created Date: " + r.getCreatedDate());
                bw.newLine();
                bw.write("Last Updated: " + r.getLastUpdated());
                bw.newLine();
            }

        } catch (Exception e) {
            System.out.println("Error creating referral text file: " + e.getMessage());
        }
    }

    private String q(String value) {
        return "\"" + value + "\"";
    }
}
