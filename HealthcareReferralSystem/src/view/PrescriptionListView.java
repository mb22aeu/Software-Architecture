/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.PrescriptionController;
import model.Prescription;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class PrescriptionListView extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private PrescriptionController controller;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton backButton;

    public PrescriptionListView(PrescriptionController controller) {
        controller.loadPrescriptionsFromCSV("data/prescriptions.csv");

        this.controller = controller;

        setTitle("Prescriptions");
        setSize(1200, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tableModel = new DefaultTableModel();

        tableModel.addColumn("Prescription ID");
        tableModel.addColumn("Patient ID");
        tableModel.addColumn("Clinician ID");
        tableModel.addColumn("Appointment ID");
        tableModel.addColumn("Prescription Date");
        tableModel.addColumn("Medication Name");
        tableModel.addColumn("Dosage");
        tableModel.addColumn("Frequency");
        tableModel.addColumn("Duration Days");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Instructions");
        tableModel.addColumn("Pharmacy Name");
        tableModel.addColumn("Status");
        tableModel.addColumn("Issue Date");
        tableModel.addColumn("Collection Date");

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

        loadPrescriptions(controller.getPrescriptions());
          addButton.addActionListener(e -> {
            new AddPrescrptionView(controller, this).setVisible(true);
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

            Prescription selected = controller.getPrescriptions().get(row);

            new EditPrescriptionView(controller, this, selected, row).setVisible(true);
        });
        deleteButton.addActionListener(e -> {

            int row = table.getSelectedRow();
            if (row == -1) {
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this prescription?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                controller.deletePrescription(row, "data/prescriptions.csv");
                refreshTable();
            }
        });

    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        controller.loadPrescriptionsFromCSV("data/prescriptions.csv");
        loadPrescriptions(controller.getPrescriptions());

    }

    private void loadPrescriptions(ArrayList<Prescription> prescriptions) {
        for (Prescription p : prescriptions) {
            tableModel.addRow(new Object[]{
                p.getPrescriptionId(),
                p.getPatientId(),
                p.getClinicianId(),
                p.getAppointmentId(),
                p.getPrescriptionDate(),
                p.getMedicationName(),
                p.getDosage(),
                p.getFrequency(),
                p.getDurationDays(),
                p.getQuantity(),
                p.getInstructions(),
                p.getPharmacyName(),
                p.getStatus(),
                p.getIssueDate(),
                p.getCollectionDate()
            });
        }
    }
}
