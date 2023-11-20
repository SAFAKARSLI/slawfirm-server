package firm.seytihanlaw.slawfirm.model;

public class NTAInfo {

    private String locationOfEntry;

    private String dateOfEntry;

    private String ntaDate;

    public NTAInfo() {

    }

    public NTAInfo(String locationOfEntry, String dateOfEntry, String ntaDate) {
        this.locationOfEntry = locationOfEntry;
        this.dateOfEntry = dateOfEntry;
        this.ntaDate = ntaDate;
    }

    public String getLocationOfEntry() {
        return locationOfEntry;
    }

    public void setLocationOfEntry(String locationOfEntry) {
        this.locationOfEntry = locationOfEntry;
    }

    public String getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(String dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public String getNtaDate() {
        return ntaDate;
    }

    public void setNtaDate(String ntaDate) {
        this.ntaDate = ntaDate;
    }
}
