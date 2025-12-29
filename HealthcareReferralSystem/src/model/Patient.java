
package model;

public class Patient {

    private String id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String nhsNumber;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private String postcode;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private String registrationDate;
    private String gpSurgeryId;

    public Patient() {
    }

    public Patient(String id, String firstName, String lastName,
            String dateOfBirth, String nhsNumber, String gender,
            String phone, String email, String address,
            String postcode, String emergencyContactName,
            String emergencyContactPhone, String registrationDate,
            String gpSurgeryId) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.nhsNumber = nhsNumber;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactPhone = emergencyContactPhone;
        this.registrationDate = registrationDate;
        this.gpSurgeryId = gpSurgeryId;
    }

    public static Patient fromCSV(String line) {

        String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].replace("\"", "").trim();
        }

        return new Patient(
                data[0], 
                data[1], 
                data[2], 
                data[3], 
                data[4], 
                data[5], 
                data[6], 
                data[7], 
                data[8], 
                data[9], 
                data[10], 
                data[11], 
                data[12], 
                data[13] 
        );
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getNhsNumber() {
        return nhsNumber;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getGpSurgeryId() {
        return gpSurgeryId;
    }
}
