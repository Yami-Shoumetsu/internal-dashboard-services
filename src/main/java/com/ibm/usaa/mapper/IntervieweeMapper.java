/**
 * 
 */
package com.ibm.usaa.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.ibm.usaa.repository.entity.IntervieweeVO;
import com.ibm.usaa.resource.representation.IntervieweeRO;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
public class IntervieweeMapper {

    public static List<IntervieweeRO> mapIntervieweesToRepresentationObject(List<IntervieweeVO> interviewees) {
        List<IntervieweeRO> intervieweeROs = new ArrayList<>();
        if (!CollectionUtils.isEmpty(interviewees)) {
            for (IntervieweeVO interviewee : interviewees) {
                IntervieweeRO intervieweeRO = mapIntervieweeToRepresentationObject(interviewee);
                intervieweeROs.add(intervieweeRO);
            }
        }
        return intervieweeROs;
    }

    public static IntervieweeRO mapIntervieweeToRepresentationObject(IntervieweeVO interviewee) {
        if (interviewee != null) {
            IntervieweeRO intervieweeRO = new IntervieweeRO();
            intervieweeRO.setId(interviewee.getIntervieweeId());
            intervieweeRO.setFirstName(interviewee.getFirstName());
            intervieweeRO.setMiddleName(interviewee.getMiddleName());
            intervieweeRO.setLastName(interviewee.getLastName());
            intervieweeRO.setEmailAddress(interviewee.getEmailAddress());
            intervieweeRO.setContactNumber(interviewee.getContactNumber());
            intervieweeRO.setInternal(interviewee.isInternal());
            return intervieweeRO;
        }
        return null;
    }

    public static IntervieweeVO mapIntervieweeFromRepresentationObject(IntervieweeRO intervieweeRO) {
        if (intervieweeRO != null) {
            IntervieweeVO interViewee = new IntervieweeVO();
            interViewee.setIntervieweeId(intervieweeRO.getId());
            interViewee.setFirstName(intervieweeRO.getFirstName());
            interViewee.setMiddleName(intervieweeRO.getMiddleName());
            interViewee.setLastName(intervieweeRO.getLastName());
            interViewee.setEmailAddress(intervieweeRO.getEmailAddress());
            interViewee.setContactNumber(intervieweeRO.getContactNumber());
            interViewee.setInternal(intervieweeRO.getInternal());
            interViewee.setExpertiseId(intervieweeRO.getExpertiseId());
            return interViewee;
        }
        return null;
    }

}
