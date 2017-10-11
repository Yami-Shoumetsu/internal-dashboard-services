package com.ibm.usaa.repository.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "INTERVIEW_HISTORY")
public class InterviewHistoryVO {

    @EmbeddedId
    private InterviewHistoryPK interviewHistoryId;

    @OneToOne
    @JoinColumn(name = "ACCT_EXT_ID", insertable = false, updatable = false)
    private ExpertiseVO expertise;

    @Column(name = "DATE_ASSIGNED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAssigned;

    @Column(name = "SCHEDULED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduledDate;

    @OneToOne
    @JoinColumn(name = "STATUS_ID")
    private InterviewStatus status;

    public InterviewHistoryPK getInterviewHistoryId() {
        return interviewHistoryId;
    }

    public void setInterviewHistoryId(InterviewHistoryPK interviewHistoryId) {
        this.interviewHistoryId = interviewHistoryId;
    }

    public Date getDateAssigned() {
        return dateAssigned;
    }

    public void setDateAssigned(Date dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public InterviewStatus getStatus() {
        return status;
    }

    public void setStatus(InterviewStatus status) {
        this.status = status;
    }

}
