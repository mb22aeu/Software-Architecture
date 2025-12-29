package view;

import controller.AppointmentController;
import model.Appointment;

import javax.swing.*;
import java.awt.*;

public class EditAppointmentView extends JDialog {

    private AppointmentController controller;
    private AppointmentListView parent;
    private int rowIndex;
    private JTextField[] fields = new JTextField[10];
    private JComboBox<String> patientBox;
    private JComboBox<String> clinicianBox;
    private JComboBox<String> facilityBox;

    public EditAppointmentView(
            AppointmentController controller,
            AppointmentListView parent,
            Appointment appointment,
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
        facilityBox = new JComboBox<>(this.controller.loadIdsFromCSV("data/facilities.csv"));
        patientBox.setSelectedItem(appointment.getPatientId());
        clinicianBox.setSelectedItem(appointment.getClinicianId());
        facilityBox.setSelectedItem(appointment.getFacilityId());

        JPanel panel = new JPanel(new GridLayout(13, 2, 5, 5));

        panel.add(new JLabel("Appointment ID"));
        fields[0] = new JTextField(appointment.getAppointmentId());
        panel.add(fields[0]);

        panel.add(new JLabel("Patient ID"));
        panel.add(patientBox);

        panel.add(new JLabel("Clinician ID"));
        panel.add(clinicianBox);

        panel.add(new JLabel("Facility ID"));
        panel.add(facilityBox);

        panel.add(new JLabel("Appointment Date"));
        fields[1] = new JTextField(appointment.getAppointmentDate());
        panel.add(fields[1]);

        panel.add(new JLabel("Appointment Time"));
        fields[2] = new JTextField(appointment.getAppointmentTime());
        panel.add(fields[2]);

        panel.add(new JLabel("Duration Minutes"));
        fields[3] = new JTextField(appointment.getDurationMinutes());
        panel.add(fields[3]);

        panel.add(new JLabel("Appointment Type"));
        fields[4] = new JTextField(appointment.getAppointmentType());
        panel.add(fields[4]);

        panel.add(new JLabel("Status"));
        fields[5] = new JTextField(appointment.getStatus());
        panel.add(fields[5]);

        panel.add(new JLabel("Reason For Visit"));
        fields[6] = new JTextField(appointment.getReasonForVisit());
        panel.add(fields[6]);

        panel.add(new JLabel("Notes"));
        fields[7] = new JTextField(appointment.getNotes());
        panel.add(fields[7]);

        panel.add(new JLabel("Created Date"));
        fields[8] = new JTextField(appointment.getCreatedDate());
        panel.add(fields[8]);

        panel.add(new JLabel("Last Modified"));
        fields[9] = new JTextField(appointment.getLastModified());
        panel.add(fields[9]);

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

         Appointment updated = new Appointment(
            fields[0].getText(),                  
            (String) patientBox.getSelectedItem(),    
            (String) clinicianBox.getSelectedItem(),  
            (String) facilityBox.getSelectedItem(),   
            fields[1].getText(),                  
            fields[2].getText(),                  
            fields[3].getText(),                 
            fields[4].getText(),                
            fields[5].getText(),                 
            fields[6].getText(),                  
            fields[7].getText(),                  
            fields[8].getText(),                 
            fields[9].getText()                  
    );

        controller.updateAppointment(rowIndex, updated, "data/appointments.csv");
        parent.refreshTable();
        dispose();
    }
}
