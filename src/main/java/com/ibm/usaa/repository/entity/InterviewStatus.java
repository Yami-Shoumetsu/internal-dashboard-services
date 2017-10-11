/**
 * 
 */
package com.ibm.usaa.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
@Entity
@Table(name = "INTERVIEW_STATUS")
public class InterviewStatus {

    @Id
    @GeneratedValue
    @Column(name = "STATUS_ID", nullable = false, unique = true)
    private Integer statusId;

    @Column(name = "DESCRIPTION")
    private String description;

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
