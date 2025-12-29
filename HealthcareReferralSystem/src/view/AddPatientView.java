package view;

import controller.PatientController;
import model.Patient;

import javax.swing.*;
import java.awt.*;

public class AddPatientView extends JDialog {

    private PatientController controller;
    private PatientListView parent;
    private JTextField[] fields = new JTextField[14];

    public AddPatientView(PatientController controller, PatientListView parent) {
        this.controller = controller;
        this.parent = parent;

        setTitle("Add Patient");
        setSize(400, 800);
        setLocationRelativeTo(parent);
        setModal(true);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(14, 2, 5, 5));

        String[] labels = {
            "ID",
            "First Name",
            "Last Name",
            "Date of Birth",
            "NHS Number",
            "Gender",
            "Phone Number",
            "Email",
            "Address",
            "Postcode",
            "Emergency Contact Name",
            "Emergency Contact Phone",           
            "Registration Date",          
            "GP Surgery ID",
        };

        for (int i = 0; i < labels.length; i++) {
            formPanel.add(new JLabel(labels[i]));
            fields[i] = new JTextField();
            formPanel.add(fields[i]);
        }

        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");

        JPanel btnPanel = new JPanel();
        btnPanel.add(saveBtn);
        btnPanel.add(cancelBtn);

        add(formPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        saveBtn.addActionListener(e -> saveClinician());
        cancelBtn.addActionListener(e -> dispose());

    }

    private void saveClinician() {

        Patient p = new Patient(
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

        controller.savePatientToCSV(p, "data/patients.csv");
        parent.refreshTable();
        dispose();
    }

}
