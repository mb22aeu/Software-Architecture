package model;

public class Referral {

    private static Referral instance;

    private Referral() {
    }

    public static Referral getInstance() {
        if (instance == null) {
            instance = new Referral();
        }
        return instance;
    }

    // ---------- CSV COLUMNS (16) ----------
    private String referralId;
    private String patientId;
    private String referringClinicianId;
    private String referredToClinicianId;
    private String referringFacilityId;
    private String referredToFacilityId;
    private String referralDate;
    private String urgencyLevel;
    private String referralReason;
    private String clinicalSummary;
    private String requestedInvestigations;
    private String status;
    private String appointmentId;
    private String notes;
    private String createdDate;
    private String lastUpdated;

    public void setReferralId(String referralId) {
        this.referralId = referralId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setReferringClinicianId(String referringClinicianId) {
        this.referringClinicianId = referringClinicianId;
    }

    public void setReferredToClinicianId(String referredToClinicianId) {
        this.referredToClinicianId = referredToClinicianId;
    }

    public void setReferringFacilityId(String referringFacilityId) {
        this.referringFacilityId = referringFacilityId;
    }

    public void setReferredToFacilityId(String referredToFacilityId) {
        this.referredToFacilityId = referredToFacilityId;
    }

    public void setReferralDate(String referralDate) {
        this.referralDate = referralDate;
    }

    public void setUrgencyLevel(String urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    public void setReferralReason(String referralReason) {
        this.referralReason = referralReason;
    }

    public void setClinicalSummary(String clinicalSummary) {
        this.clinicalSummary = clinicalSummary;
    }

    public void setRequestedInvestigations(String requestedInvestigations) {
        this.requestedInvestigations = requestedInvestigations;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getReferralId() {
        return referralId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getReferringClinicianId() {
        return referringClinicianId;
    }

    public String getReferredToClinicianId() {
        return referredToClinicianId;
    }

    public String getReferringFacilityId() {
        return referringFacilityId;
    }

    public String getReferredToFacilityId() {
        return referredToFacilityId;
    }

    public String getReferralDate() {
        return referralDate;
    }

    public String getUrgencyLevel() {
        return urgencyLevel;
    }

    public String getReferralReason() {
        return referralReason;
    }

    public String getClinicalSummary() {
        return clinicalSummary;
    }

    public String getRequestedInvestigations() {
        return requestedInvestigations;
    }

    public String getStatus() {
        return status;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getNotes() {
        return notes;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }
}
