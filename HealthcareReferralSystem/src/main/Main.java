
package main;

import controller.PrescriptionController;
import view.PrescriptionListView;
import controller.AppointmentController;
import view.AppointmentListView;
import controller.ClinicianController;
import view.ClinicianListView;
import controller.PatientController;
import view.PatientListView;
import view.MainDashboard;

public class Main {

    public static void main(String[] args) {

//        PrescriptionController controller = new PrescriptionController();
//        controller.loadPrescriptionsFromCSV("data/prescriptions.csv");
//
//        new PrescriptionListView(controller).setVisible(true);
// AppointmentController controller = new AppointmentController();
//    controller.loadAppointmentsFromCSV("data/appointments.csv");
//
//    new AppointmentListView(controller).setVisible(true);
//ClinicianController controller = new ClinicianController();
//controller.loadCliniciansFromCSV("data/clinicians.csv");
//
//new ClinicianListView(controller).setVisible(true);
//PatientController controller = new PatientController();
//controller.loadPatientsFromCSV("data/patients.csv");
//
//new PatientListView(controller).setVisible(true);
        new MainDashboard().setVisible(true);
    }
}
