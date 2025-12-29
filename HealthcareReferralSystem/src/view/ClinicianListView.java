package view;

import controller.ClinicianController;
import model.Clinician;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ClinicianListView extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private ClinicianController controller;

    private JButton addBtn, editBtn, deleteBtn, backBtn;

    public ClinicianListView(ClinicianController controller) {
        this.controller = controller;

        setTitle("Clinicians");
        setSize(1200, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{
            "Clinician ID",
            "First Name",
            "Last Name",
            "Title",
            "Speciality",
            "GMC Number",
            "Phone",
            "Email",
            "Workplace ID",
            "Workplace Type",
            "Employment Status",
            "Start Date"
        });

        table = new JTable(tableModel);
        add(new JScrollPane(table), "Center");

        backBtn = new JButton("Back");
        addBtn = new JButton("Add");
        editBtn = new JButton("Edit");
        deleteBtn = new JButton("Delete");

        JPanel topPanel = new JPanel();
        topPanel.add(backBtn);
        topPanel.add(addBtn);
        topPanel.add(editBtn);
        topPanel.add(deleteBtn);

        add(topPanel, "North");

        loadClinicians(controller.getClinicians());

        addBtn.addActionListener(e -> {
            new AddClinicianView(controller, this).setVisible(true);
        });
        backBtn.addActionListener(e -> {
            dispose();
            new MainDashboard().setVisible(true);
        });
        editBtn.addActionListener(e -> {

            int row = table.getSelectedRow();
            if (row == -1) {
                return;
            }

            Clinician selected = controller.getClinicians().get(row);

            new EditClinicianView(controller, this, selected, row).setVisible(true);
        });
        deleteBtn.addActionListener(e -> {

            int row = table.getSelectedRow();
            if (row == -1) {
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this clinician?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                controller.deleteClinician(row, "data/clinicians.csv");
                refreshTable();
            }
        });
    }

    private void loadClinicians(ArrayList<Clinician> list) {
        tableModel.setRowCount(0);

        for (Clinician c : list) {
            tableModel.addRow(new Object[]{
                c.getClinicianId(),
                c.getFirstName(),
                c.getLastName(),
                c.getTitle(),
                c.getSpeciality(),
                c.getGmcNumber(),
                c.getPhoneNumber(),
                c.getEmail(),
                c.getWorkplaceId(),
                c.getWorkplaceType(),
                c.getEmploymentStatus(),
                c.getStartDate()
            });
        }
    }

    public void refreshTable() {
        controller.loadCliniciansFromCSV("data/clinicians.csv");
        loadClinicians(controller.getClinicians());
    }

    public JButton getAddBtn() {
        return addBtn;
    }

    public JButton getEditBtn() {
        return editBtn;
    }

    public JButton getDeleteBtn() {
        return deleteBtn;
    }

    public JTable getTable() {
        return table;
    }
}
