/**
 * 
 */
package com.ibm.usaa.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.ibm.usaa.repository.entity.EmployeeVO;
import com.ibm.usaa.repository.entity.IntervieweeVO;
import com.ibm.usaa.repository.entity.InterviewerVO;
import com.ibm.usaa.resource.representation.IntervieweeRO;
import com.ibm.usaa.resource.representation.InterviewerRO;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
public class InterviewerMapper {

    public static List<InterviewerRO> mapInterviewersToRepresentationObject(List<InterviewerVO> interviewers) {
        List<InterviewerRO> interviewerROs = new ArrayList<>();
        if (!CollectionUtils.isEmpty(interviewers)) {
            for (InterviewerVO interviewer : interviewers) {
                InterviewerRO interviewerRO = mapInterviewerToRepresentationObject(interviewer);
                interviewerROs.add(interviewerRO);
            }
        }
        return interviewerROs;
    }
    
    public static InterviewerRO mapInterviewerToRepresentationObject(InterviewerVO interviewer) {
        if (interviewer != null) {
            InterviewerRO interviewerRO = new InterviewerRO();
            EmployeeVO employee = interviewer.getEmployee();
            
            interviewerRO.setResourceId(interviewer.getInterviewerId());
            interviewerRO.setInterviewerStatus(interviewer.getInterviewerStatus());
            interviewerRO.setEmployeeId(employee.getEmployeeId());
            interviewerRO.setFirstName(employee.getFirstName());
            interviewerRO.setMiddleName(employee.getMiddleName());
            interviewerRO.setLastName(employee.getLastName());
            interviewerRO.setEmail(employee.getEmail());
            interviewerRO.setContactNumber(employee.getContactNumber());
            interviewerRO.setEmployeeTypeId(employee.getEmployeeTypeVO().getEmployeeTypeId());
            return interviewerRO;
        }
        return null;
    }

}
