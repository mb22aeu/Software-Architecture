package view;

import controller.ClinicianController;
import model.Clinician;

import javax.swing.*;
import java.awt.*;

public class AddClinicianView extends JDialog {

    private ClinicianController controller;
    private ClinicianListView parent;
    private JTextField[] fields = new JTextField[12];

    public AddClinicianView(ClinicianController controller, ClinicianListView parent) {
        this.controller = controller;
        this.parent = parent;

        setTitle("Add Clinician");
        setSize(500, 500);
        setLocationRelativeTo(parent);
        setModal(true);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(12, 2, 5, 5));

        String[] labels = {
            "Clinician ID",
            "First Name",
            "Last Name",
            "Title",
            "Speciality",
            "GMC Number",
            "Phone Number",
            "Email",
            "Workplace ID",
            "Workplace Type",
            "Employment Status",
            "Start Date"
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

        Clinician c = new Clinician(
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
                fields[11].getText()
        );

        controller.saveClinicianToCSV(c, "data/clinicians.csv");
        parent.refreshTable();
        dispose();
    }
}
