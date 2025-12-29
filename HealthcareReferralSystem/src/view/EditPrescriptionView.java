package view;

import controller.PrescriptionController;
import model.Prescription;

import javax.swing.*;
import java.awt.*;

public class EditPrescriptionView extends JDialog {

    private PrescriptionController controller;
    private PrescriptionListView parent;
    private int rowIndex;
    private JTextField[] fields = new JTextField[12];
    private JComboBox<String> patientBox;
    private JComboBox<String> clinicianBox;
    private JComboBox<String> appointmentBox;

    public EditPrescriptionView(
            PrescriptionController controller,
            PrescriptionListView parent,
            Prescription prescription,
            int rowIndex
    ) {
        this.controller = controller;
        this.parent = parent;
        this.rowIndex = rowIndex;

        setTitle("Edit Appointment");
        setSize(500, 500);
        setLocationRelativeTo(parent);
        setModal(true);

        patientBox = new JComboBox<>(this.controller.loadIdsFromCSV("data/patients.csv"));
        clinicianBox = new JComboBox<>(this.controller.loadIdsFromCSV("data/clinicians.csv"));
        appointmentBox = new JComboBox<>(this.controller.loadIdsFromCSV("data/appointments.csv"));
        patientBox.setSelectedItem(prescription.getPatientId());
        clinicianBox.setSelectedItem(prescription.getClinicianId());
        appointmentBox.setSelectedItem(prescription.getAppointmentId());

        JPanel panel = new JPanel(new GridLayout(15, 2, 5, 5));

        panel.add(new JLabel("Prescription ID"));
        fields[0] = new JTextField(prescription.getPrescriptionId());
        panel.add(fields[0]);

        panel.add(new JLabel("Patient ID"));
        panel.add(patientBox);

        panel.add(new JLabel("Clinician ID"));
        panel.add(clinicianBox);

        panel.add(new JLabel("Appointment ID"));
        panel.add(appointmentBox);

        panel.add(new JLabel("Prescription Date"));
        fields[1] = new JTextField(prescription.getPrescriptionDate());
        panel.add(fields[1]);


        panel.add(new JLabel("Medication Name"));
        fields[2] = new JTextField(prescription.getMedicationName());
        panel.add(fields[2]);

        panel.add(new JLabel("Dosage"));
        fields[3] = new JTextField(prescription.getDosage());
        panel.add(fields[3]);

        panel.add(new JLabel("Frequency"));
        fields[4] = new JTextField(prescription.getFrequency());
        panel.add(fields[4]);

        panel.add(new JLabel("Duration Days"));
        fields[5] = new JTextField(prescription.getDurationDays());
        panel.add(fields[5]);

        panel.add(new JLabel("Quantity"));
        fields[6] = new JTextField(prescription.getQuantity());
        panel.add(fields[6]);

        panel.add(new JLabel("Instructions"));
        fields[7] = new JTextField(prescription.getInstructions());
        panel.add(fields[7]);

        panel.add(new JLabel("Pharmacy Name"));
        fields[8] = new JTextField(prescription.getPharmacyName());
        panel.add(fields[8]);
        
         panel.add(new JLabel("Status"));
        fields[9] = new JTextField(prescription.getStatus());
        panel.add(fields[9]);
        
         panel.add(new JLabel("Issue Date"));
        fields[10] = new JTextField(prescription.getIssueDate());
        panel.add(fields[10]);
        
         panel.add(new JLabel("Collection Date"));
        fields[11] = new JTextField(prescription.getCollectionDate());
        panel.add(fields[11]);

        JButton saveBtn = new JButton("Update");
        JButton cancelBtn = new JButton("Cancel");

        JPanel btnPanel = new JPanel();
        btnPanel.add(saveBtn);
        btnPanel.add(cancelBtn);

        add(panel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        saveBtn.addActionListener(e -> updateAppointment());
        cancelBtn.addActionListener(e -> dispose());
    }

    private void updateAppointment() {

         Prescription updated = new Prescription(
            fields[0].getText(),                  
            (String) patientBox.getSelectedItem(),    
            (String) clinicianBox.getSelectedItem(),  
            (String) appointmentBox.getSelectedItem(),   
            fields[1].getText(),                  
            fields[2].getText(),                  
            fields[3].getText(),                 
            fields[4].getText(),                
            fields[5].getText(),                 
            fields[6].getText(),                  
            fields[7].getText(),                  
            fields[8].getText(),                 
            fields[9].getText(),
                 fields[10].getText(),                 
            fields[11].getText()  
    );

        controller.updatePrescription(rowIndex, updated, "data/prescriptions.csv");
        parent.refreshTable();
        dispose();
    }
}
