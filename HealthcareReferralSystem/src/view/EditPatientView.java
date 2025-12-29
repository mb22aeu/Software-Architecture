package view;

import controller.PatientController;
import model.Patient;

import javax.swing.*;
import java.awt.*;

public class EditPatientView extends JDialog {

    private PatientController controller;
    private PatientListView parent;
    private int rowIndex;
    private JTextField[] fields = new JTextField[14];

    public EditPatientView(
            PatientController controller,
            PatientListView parent,
            Patient patient,
            int rowIndex
    ) {
        this.controller = controller;
        this.parent = parent;
        this.rowIndex = rowIndex;

        setTitle("Edit Patient");
        setSize(600, 500);
        setLocationRelativeTo(parent);
        setModal(true);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(14, 2, 5, 5));

        String[] labels = {
            "ID", "First Name", "Last Name", "DOB", "NHS No",
            "Gender", "Phone", "Email", "Address", "Postcode",
            "Emergency Name", "Emergency Phone",
            "Registration Date", "GP Surgery ID"
        };

        String[] values = {
            patient.getId(),
            patient.getFirstName(),
            patient.getLastName(),
            patient.getDateOfBirth(),
            patient.getNhsNumber(),
            patient.getGender(),
            patient.getPhone(),
            patient.getEmail(),
            patient.getAddress(),
            patient.getPostcode(),
            patient.getEmergencyContactName(),
            patient.getEmergencyContactPhone(),
            patient.getRegistrationDate(),
            patient.getGpSurgeryId()
        };

        for (int i = 0; i < labels.length; i++) {
            panel.add(new JLabel(labels[i]));
            fields[i] = new JTextField(values[i]);
            panel.add(fields[i]);
        }

        JButton updateBtn = new JButton("Update");
        JButton cancelBtn = new JButton("Cancel");

        JPanel btnPanel = new JPanel();
        btnPanel.add(updateBtn);
        btnPanel.add(cancelBtn);

        add(panel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        updateBtn.addActionListener(e -> updatePatient());
        cancelBtn.addActionListener(e -> dispose());
    }

    private void updatePatient() {

        Patient updated = new Patient(
                fields[0].getText(),
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
                fields[11].getText(),
                fields[12].getText(),
                fields[13].getText()
        );

        controller.updatePatient(rowIndex, updated, "data/patients.csv");
        parent.refreshTable();
        dispose();
    }
}
