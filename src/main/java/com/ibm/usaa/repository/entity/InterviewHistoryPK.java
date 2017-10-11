package com.ibm.usaa.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InterviewHistoryPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "INT_ID")
    private Integer interviewerId;

    @Column(name = "INT_VIEW_ID")
    private Integer intervieweeId;

    @Column(name = "ACCT_EXT_ID")
    private Integer expertiseId;

    public Integer getInterviewerId() {
        return interviewerId;
    }

    public void setInterviewerId(Integer interviewerId) {
        this.interviewerId = interviewerId;
    }

    public Integer getIntervieweeId() {
        return intervieweeId;
    }

    public void setIntervieweeId(Integer intervieweeId) {
        this.intervieweeId = intervieweeId;
    }

    public Integer getExpertiseId() {
        return expertiseId;
    }

    public void setExpertiseId(Integer expertiseId) {
        this.expertiseId = expertiseId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((expertiseId == null) ? 0 : expertiseId.hashCode());
        result = prime * result + ((intervieweeId == null) ? 0 : intervieweeId.hashCode());
        result = prime * result + ((interviewerId == null) ? 0 : interviewerId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof InterviewHistoryPK)) {
            return false;
        }
        InterviewHistoryPK other = (InterviewHistoryPK) obj;
        if (expertiseId == null) {
            if (other.expertiseId != null) {
                return false;
            }
        } else if (!expertiseId.equals(other.expertiseId)) {
            return false;
        }
        if (intervieweeId == null) {
            if (other.intervieweeId != null) {
                return false;
            }
        } else if (!intervieweeId.equals(other.intervieweeId)) {
            return false;
        }
        if (interviewerId == null) {
            if (other.interviewerId != null) {
                return false;
            }
        } else if (!interviewerId.equals(other.interviewerId)) {
            return false;
        }
        return true;
    }

}
