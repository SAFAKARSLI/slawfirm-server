package firm.seytihanlaw.slawfirm.model;

import java.util.Date;

public class Case {

    private boolean caseOpened = true;
    private Date docketDate;
    private String judgeName;
    private String hearingDate;
    private String hearingType;
    private String courtDate;

    public Case() {
    }

    public Case(boolean caseOpened) {
        this.caseOpened = caseOpened;
    }

    public Case(Date docketDate, String judgeName, String hearingDate, String hearingType, String courtDate) {
        this.docketDate = docketDate;
        this.judgeName = judgeName;
        this.hearingDate = hearingDate;
        this.hearingType = hearingType;
        this.courtDate = courtDate;
    }

    public boolean isCaseOpened() {
        return caseOpened;
    }

    public void setCaseOpened(boolean caseOpened) {
        this.caseOpened = caseOpened;
    }

    public Date getDocketDate() {
        return docketDate;
    }

    public void setDocketDate(Date docketDate) {
        this.docketDate = docketDate;
    }

    public String getJudgeName() {
        return judgeName;
    }

    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName;
    }

    public String getHearingDate() {
        return hearingDate;
    }

    public void setHearingDate(String hearingDate) {
        this.hearingDate = hearingDate;
    }

    public String getHearingType() {
        return hearingType;
    }

    public void setHearingType(String hearingType) {
        this.hearingType = hearingType;
    }

    public String getCourtDate() {
        return courtDate;
    }

    public void setCourtDate(String courtDate) {
        this.courtDate = courtDate;
    }
}
