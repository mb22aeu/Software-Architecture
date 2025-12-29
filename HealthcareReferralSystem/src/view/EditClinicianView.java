package view;

import controller.ClinicianController;
import model.Clinician;

import javax.swing.*;
import java.awt.*;

public class EditClinicianView extends JDialog {

    private ClinicianController controller;
    private ClinicianListView parent;
    private int rowIndex;
    private JTextField[] fields = new JTextField[12];

    public EditClinicianView(
            ClinicianController controller,
            ClinicianListView parent,
            Clinician clinician,
            int rowIndex
    ) {
        this.controller = controller;
        this.parent = parent;
        this.rowIndex = rowIndex;

        setTitle("Edit Clinician");
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

        String[] values = {
            clinician.getClinicianId(),
            clinician.getFirstName(),
            clinician.getLastName(),
            clinician.getTitle(),
            clinician.getSpeciality(),
            clinician.getGmcNumber(),
            clinician.getPhoneNumber(),
            clinician.getEmail(),
            clinician.getWorkplaceId(),
            clinician.getWorkplaceType(),
            clinician.getEmploymentStatus(),
            clinician.getStartDate()
        };

        for (int i = 0; i < labels.length; i++) {
            formPanel.add(new JLabel(labels[i]));
            fields[i] = new JTextField(values[i]);
            formPanel.add(fields[i]);
        }

        JButton updateBtn = new JButton("Update");
        JButton cancelBtn = new JButton("Cancel");

        JPanel btnPanel = new JPanel();
        btnPanel.add(updateBtn);
        btnPanel.add(cancelBtn);

        add(formPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        updateBtn.addActionListener(e -> updateClinician());
        cancelBtn.addActionListener(e -> dispose());
    }

    private void updateClinician() {

        Clinician updated = new Clinician(
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

        controller.updateClinician(rowIndex, updated, "data/clinicians.csv");
        parent.refreshTable();
        dispose();
    }
}
