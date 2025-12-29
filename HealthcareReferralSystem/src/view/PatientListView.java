/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.PatientController;
import model.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class PatientListView extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton backButton;
    private PatientController controller;

    public PatientListView(PatientController controller) {
        this.controller = controller;

        setTitle("Patient List");
        setSize(1200, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("DOB");
        tableModel.addColumn("NHS No");
        tableModel.addColumn("Gender");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Email");
        tableModel.addColumn("Address");
        tableModel.addColumn("Postcode");
        tableModel.addColumn("Emergency Name");
        tableModel.addColumn("Emergency Phone");
        tableModel.addColumn("Registration Date");
        tableModel.addColumn("GP Surgery ID");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        backButton = new JButton("Back");

        JPanel topPanel = new JPanel();
        topPanel.add(backButton);
        topPanel.add(addButton);
        topPanel.add(editButton);
        topPanel.add(deleteButton);

        add(topPanel, "North");
        add(scrollPane, "Center");

        loadPatients(controller.getPatients());
        addButton.addActionListener(e -> {
            new AddPatientView(this.controller, this).setVisible(true);
        });
        backButton.addActionListener(e -> {
            dispose();
            new MainDashboard().setVisible(true);
        });
        editButton.addActionListener(e -> {

            int row = table.getSelectedRow();
            if (row == -1) {
                return;
            }

            Patient selected = controller.getPatients().get(row);

            new EditPatientView(controller, this, selected, row).setVisible(true);
        });
        deleteButton.addActionListener(e -> {

            int row = table.getSelectedRow();
            if (row == -1) {
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this patient?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                controller.deletePatient(row, "data/patients.csv");
                refreshTable();
            }
        });
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        controller.loadPatientsFromCSV("data/patients.csv");
        loadPatients(controller.getPatients());
    }

    private void loadPatients(ArrayList<Patient> patients) {
        for (Patient p : patients) {
            tableModel.addRow(new Object[]{
                p.getId(),
                p.getFirstName(),
                p.getLastName(),
                p.getDateOfBirth(),
                p.getNhsNumber(),
                p.getGender(),
                p.getPhone(),
                p.getEmail(),
                p.getAddress(),
                p.getPostcode(),
                p.getEmergencyContactName(),
                p.getEmergencyContactPhone(),
                p.getRegistrationDate(),
                p.getGpSurgeryId()
            });
        }
    }
}
