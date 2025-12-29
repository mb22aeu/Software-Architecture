package view;

import controller.*;

import javax.swing.*;
import java.awt.*;

public class MainDashboard extends JFrame {

    public MainDashboard() {

        setTitle("Healthcare Management System");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(5, 1, 10, 10));

        JButton patientBtn = new JButton("Patient Management");
        JButton appointmentBtn = new JButton("Appointment Management");
        JButton prescriptionBtn = new JButton("Prescription Management");
        JButton clinicianBtn = new JButton("Clinician Management");
        JButton exitBtn = new JButton("Exit");

        add(patientBtn);
        add(appointmentBtn);
        add(prescriptionBtn);
        add(clinicianBtn);
        add(exitBtn);

        patientBtn.addActionListener(e -> {
            dispose();
            PatientController controller = new PatientController();
            controller.loadPatientsFromCSV("data/patients.csv");
            new PatientListView(controller).setVisible(true);
        });

        appointmentBtn.addActionListener(e -> {
            dispose();
            AppointmentController controller = new AppointmentController();
            controller.loadAppointmentsFromCSV("data/appointments.csv");
            new AppointmentListView(controller).setVisible(true);
        });

        prescriptionBtn.addActionListener(e -> {
            dispose();
            PrescriptionController controller = new PrescriptionController();
            controller.loadPrescriptionsFromCSV("data/prescriptions.csv");
            new PrescriptionListView(controller).setVisible(true);
        });

        clinicianBtn.addActionListener(e -> {
            dispose();
            ClinicianController controller = new ClinicianController();
            controller.loadCliniciansFromCSV("data/clinicians.csv");
            new ClinicianListView(controller).setVisible(true);
        });

        exitBtn.addActionListener(e -> System.exit(0));
    }
}
