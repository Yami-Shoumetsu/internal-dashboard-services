/**
 * 
 */
package com.ibm.usaa.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.ibm.usaa.repository.entity.InterviewerAvailabilityPK;
import com.ibm.usaa.repository.entity.InterviewerAvailabilityVO;
import com.ibm.usaa.resource.representation.InterviewerAvailabilityRO;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
public class InterviewerAvailabilityMapper {

    public static List<InterviewerAvailabilityRO> mapInterviewerAvailabilityToRepresentationObject(List<InterviewerAvailabilityVO> interviewerAvailabilities) {
        List<InterviewerAvailabilityRO> interviewerAvailabilityROs = new ArrayList<>();
        if (!CollectionUtils.isEmpty(interviewerAvailabilities)) {
            for (InterviewerAvailabilityVO interviewerAvailability : interviewerAvailabilities) {
            	InterviewerAvailabilityRO interviewerAvailabilityRO = mapInterviewerAvailabilityToRepresentationObject(interviewerAvailability);
            	interviewerAvailabilityROs.add(interviewerAvailabilityRO);
            }
        }
        return interviewerAvailabilityROs;
    }

    public static InterviewerAvailabilityRO mapInterviewerAvailabilityToRepresentationObject(InterviewerAvailabilityVO interviewerAvailability) {
        if (interviewerAvailability != null) {
        	InterviewerAvailabilityRO interviewerAvailabilityRO = new InterviewerAvailabilityRO();
        	
        	InterviewerAvailabilityPK interviewAvailabilityPk = new InterviewerAvailabilityPK();        	
        	interviewAvailabilityPk.setInterviewerId(interviewerAvailability.getInterviewAvailabilityId().getInterviewerId());
        	interviewAvailabilityPk.setAvailableDay(interviewerAvailability.getInterviewAvailabilityId().getAvailableDay());
        	interviewAvailabilityPk.setAvailableTime(interviewerAvailability.getInterviewAvailabilityId().getAvailableTime());
        	
        	InterviewerAvailabilityVO interviewerAvailabilityVO = new InterviewerAvailabilityVO();
        	interviewerAvailabilityVO.setInterviewAvailabilityId(interviewAvailabilityPk);
        	interviewerAvailabilityRO.setInterviewerAvailabilityVO(interviewerAvailabilityVO);
        	
            return interviewerAvailabilityRO;
        }
        return null;
    }

}
