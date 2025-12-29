package view;

import controller.AppointmentController;
import model.Appointment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class AppointmentListView extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private AppointmentController controller;

    private JButton addBtn, editBtn, deleteBtn, backBtn;

    public AppointmentListView(AppointmentController controller) {
        this.controller = controller;

        setTitle("Appointments");
        setSize(1300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{
            "Appointment ID",
            "Patient ID",
            "Clinician ID",
            "Facility ID",
            "Date",
            "Time",
            "Duration",
            "Type",
            "Status",
            "Reason",
            "Notes",
            "Created Date",
            "Last Modified"
        });

        table = new JTable(tableModel);
        add(new JScrollPane(table), "Center");

        addBtn = new JButton("Add");
        editBtn = new JButton("Edit");
        deleteBtn = new JButton("Delete");
        backBtn = new JButton("Back");

        JPanel topPanel = new JPanel();
        topPanel.add(backBtn);
        topPanel.add(addBtn);
        topPanel.add(editBtn);
        topPanel.add(deleteBtn);

        add(topPanel, "North");

        loadAppointments(controller.getAppointments());
        addBtn.addActionListener(e -> {
            new AddAppointmentView(controller, this).setVisible(true);
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

            Appointment selected = controller.getAppointments().get(row);

            new EditAppointmentView(controller, this, selected, row).setVisible(true);
        });
        deleteBtn.addActionListener(e -> {

            int row = table.getSelectedRow();
            if (row == -1) {
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Delete this appointment?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                controller.deleteAppointment(row, "data/appointments.csv");
                refreshTable();
            }
        });
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        controller.loadAppointmentsFromCSV("data/appointments.csv");
        loadAppointments(controller.getAppointments());

    }

    private void loadAppointments(ArrayList<Appointment> list) {
        tableModel.setRowCount(0);

        for (Appointment a : list) {
            tableModel.addRow(new Object[]{
                a.getAppointmentId(),
                a.getPatientId(),
                a.getClinicianId(),
                a.getFacilityId(),
                a.getAppointmentDate(),
                a.getAppointmentTime(),
                a.getDurationMinutes(),
                a.getAppointmentType(),
                a.getStatus(),
                a.getReasonForVisit(),
                a.getNotes(),
                a.getCreatedDate(),
                a.getLastModified()
            });
        }
    }
}
