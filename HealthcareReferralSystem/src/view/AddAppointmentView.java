package view;

import controller.AppointmentController;
import model.Appointment;

import javax.swing.*;
import java.awt.*;

public class AddAppointmentView extends JDialog {

    private AppointmentController controller;
    private AppointmentListView parent;

    private JTextField[] textFields  = new JTextField[10];
    private JComboBox<String> patientBox;
private JComboBox<String> clinicianBox;
private JComboBox<String> facilityBox;

    public AddAppointmentView(AppointmentController controller, AppointmentListView parent) {
        this.controller = controller;
        this.parent = parent;

        setTitle("Add Appointment");
        setSize(500, 500);
        setLocationRelativeTo(parent);
        setModal(true);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(13, 2, 5, 5));
        
        patientBox = new JComboBox<>(this.controller.loadIdsFromCSV("data/patients.csv"));
clinicianBox = new JComboBox<>(this.controller.loadIdsFromCSV("data/clinicians.csv"));
facilityBox = new JComboBox<>(this.controller.loadIdsFromCSV("data/facilities.csv"));

formPanel.add(new JLabel("Appointment ID"));
textFields[0] = new JTextField();
formPanel.add(textFields[0]);

formPanel.add(new JLabel("Patient ID"));
formPanel.add(patientBox);

formPanel.add(new JLabel("Clinician ID"));
formPanel.add(clinicianBox);

formPanel.add(new JLabel("Facility ID"));
formPanel.add(facilityBox);

formPanel.add(new JLabel("Appointment Date"));
textFields[1] = new JTextField();
formPanel.add(textFields[1]);

formPanel.add(new JLabel("Appointment Time"));
textFields[2] = new JTextField();
formPanel.add(textFields[2]);

formPanel.add(new JLabel("Duration Minutes"));
textFields[3] = new JTextField();
formPanel.add(textFields[3]);

formPanel.add(new JLabel("Appointment Type"));
textFields[4] = new JTextField();
formPanel.add(textFields[4]);

formPanel.add(new JLabel("Status"));
textFields[5] = new JTextField();
formPanel.add(textFields[5]);

formPanel.add(new JLabel("Reason For Visit"));
textFields[6] = new JTextField();
formPanel.add(textFields[6]);

formPanel.add(new JLabel("Notes"));
textFields[7] = new JTextField();
formPanel.add(textFields[7]);

formPanel.add(new JLabel("Created Date"));
textFields[8] = new JTextField();
formPanel.add(textFields[8]);

formPanel.add(new JLabel("Last Modified"));
textFields[9] = new JTextField();
formPanel.add(textFields[9]);

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
          Appointment a = new Appointment(
        textFields[0].getText(),                
        (String) patientBox.getSelectedItem(),   
        (String) clinicianBox.getSelectedItem(), 
        (String) facilityBox.getSelectedItem(),  
        textFields[1].getText(),                 
        textFields[2].getText(),                 
        textFields[3].getText(),                 
        textFields[4].getText(),                
        textFields[5].getText(),                 
        textFields[6].getText(),                 
        textFields[7].getText(),                 
        textFields[8].getText(),                 
        textFields[9].getText()                  
);

            controller.saveAppointmentToCSV(a, "data/appointments.csv");
            parent.refreshTable();
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
