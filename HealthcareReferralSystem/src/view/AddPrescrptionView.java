package view;

import controller.PrescriptionController;
import model.Prescription;

import javax.swing.*;
import java.awt.*;

public class AddPrescrptionView extends JDialog {

    private PrescriptionController controller;
    private PrescriptionListView parent;

    private JTextField[] textFields = new JTextField[12];
    private JComboBox<String> patientBox;
    private JComboBox<String> clinicianBox;
    private JComboBox<String> appointmentBox;

    public AddPrescrptionView(PrescriptionController controller, PrescriptionListView parent) {
        this.controller = controller;
        this.parent = parent;

        setTitle("Add Appointment");
        setSize(500, 500);
        setLocationRelativeTo(parent);
        setModal(true);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(15, 2, 5, 5));

        patientBox = new JComboBox<>(this.controller.loadIdsFromCSV("data/patients.csv"));
        clinicianBox = new JComboBox<>(this.controller.loadIdsFromCSV("data/clinicians.csv"));
        appointmentBox = new JComboBox<>(this.controller.loadIdsFromCSV("data/appointments.csv"));

        formPanel.add(new JLabel("Prescription ID"));
        textFields[0] = new JTextField();
        formPanel.add(textFields[0]);

        formPanel.add(new JLabel("Patient ID"));
        formPanel.add(patientBox);

        formPanel.add(new JLabel("Clinician ID"));
        formPanel.add(clinicianBox);

        formPanel.add(new JLabel("Appointment ID"));
        formPanel.add(appointmentBox);

        formPanel.add(new JLabel("Prescription Date"));
        textFields[1] = new JTextField();
        formPanel.add(textFields[1]);

        formPanel.add(new JLabel("Medication Name"));
        textFields[2] = new JTextField();
        formPanel.add(textFields[2]);

        formPanel.add(new JLabel("Dosage"));
        textFields[3] = new JTextField();
        formPanel.add(textFields[3]);

        formPanel.add(new JLabel("Frequency"));
        textFields[4] = new JTextField();
        formPanel.add(textFields[4]);

        formPanel.add(new JLabel("Duration Days"));
        textFields[5] = new JTextField();
        formPanel.add(textFields[5]);

        formPanel.add(new JLabel("Quantity"));
        textFields[6] = new JTextField();
        formPanel.add(textFields[6]);

        formPanel.add(new JLabel("Instructions"));
        textFields[7] = new JTextField();
        formPanel.add(textFields[7]);

        formPanel.add(new JLabel("Pharmacy Name"));
        textFields[8] = new JTextField();
        formPanel.add(textFields[8]);

        formPanel.add(new JLabel("Status"));
        textFields[9] = new JTextField();
        formPanel.add(textFields[9]);

        formPanel.add(new JLabel("Issue Date"));
        textFields[10] = new JTextField();
        formPanel.add(textFields[10]);

        formPanel.add(new JLabel("Collection Date"));
        textFields[11] = new JTextField();
        formPanel.add(textFields[11]);
        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");

        JPanel btnPanel = new JPanel();
        btnPanel.add(saveBtn);
        btnPanel.add(cancelBtn);

        add(formPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        saveBtn.addActionListener(e -> saveAppointment());
        cancelBtn.addActionListener(e -> dispose());
    }

    private void saveAppointment() {
        try {
            Prescription p = new Prescription(
                    textFields[0].getText(),
                    (String) patientBox.getSelectedItem(),
                    (String) clinicianBox.getSelectedItem(),
                    (String) appointmentBox.getSelectedItem(),
                    textFields[1].getText(),
                    textFields[2].getText(),
                    textFields[3].getText(),
                    textFields[4].getText(),
                    textFields[5].getText(),
                    textFields[6].getText(),
                    textFields[7].getText(),
                    textFields[8].getText(),
                    textFields[9].getText(),
                    textFields[10].getText(),
                    textFields[11].getText()
            );

            controller.savePrescriptionToCSV(p, "data/prescriptions.csv");
            controller.generatePrescriptionTextFile(p);
            parent.refreshTable();
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
