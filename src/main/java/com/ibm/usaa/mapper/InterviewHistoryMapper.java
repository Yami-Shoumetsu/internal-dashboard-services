/**
 * 
 */
package com.ibm.usaa.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.ibm.usaa.repository.entity.InterviewHistoryPK;
import com.ibm.usaa.repository.entity.InterviewHistoryVO;
import com.ibm.usaa.repository.entity.InterviewStatus;
import com.ibm.usaa.resource.representation.InterviewHistoryRO;
import com.ibm.usaa.resource.representation.StatusRO;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
public class InterviewHistoryMapper {

    public static List<InterviewHistoryRO> mapInterviewHistoriesToRepresentationObject(List<InterviewHistoryVO> interviewHistories) {
        List<InterviewHistoryRO> interviewHistoryROs = new ArrayList<>();
        if (!CollectionUtils.isEmpty(interviewHistories)) {
            for (InterviewHistoryVO interviewHistory : interviewHistories) {
                InterviewHistoryRO interviewHistoryRO = mapInterviewHistoryToRepresentationObject(interviewHistory);
                interviewHistoryROs.add(interviewHistoryRO);
            }
        }
        return interviewHistoryROs;
    }

    public static InterviewHistoryRO mapInterviewHistoryToRepresentationObject(InterviewHistoryVO interviewHistory) {
        if (interviewHistory != null) {
            InterviewHistoryRO interviewHistoryRO = new InterviewHistoryRO();
            InterviewHistoryPK interviewHistoryId = interviewHistory.getInterviewHistoryId();
            interviewHistoryRO.setInterviewerId(interviewHistoryId.getInterviewerId());
            interviewHistoryRO.setIntervieweeId(interviewHistoryId.getIntervieweeId());
            interviewHistoryRO.setExpertiseId(interviewHistoryId.getExpertiseId());
            interviewHistoryRO.setAssignedSchedule(interviewHistory.getScheduledDate());
            InterviewStatus status = interviewHistory.getStatus();
            if (status != null) {
                interviewHistoryRO.setStatus(StatusRO.valueOf(status.getDescription()));
            }
            return interviewHistoryRO;
        }
        return null;
    }

    public static List<InterviewHistoryVO> mapInterviewHistoriesFromRepresentationObject(List<InterviewHistoryRO> interviewHistoryROs) {
        List<InterviewHistoryVO> interviewHistories = new ArrayList<>();
        if (!CollectionUtils.isEmpty(interviewHistoryROs)) {
            for (InterviewHistoryRO interviewHistoryRO : interviewHistoryROs) {
                InterviewHistoryVO interviewHistory = mapInterviewHistoryFromRepresentationObject(interviewHistoryRO);
                interviewHistories.add(interviewHistory);
            }
        }
        return interviewHistories;
    }

    public static InterviewHistoryVO mapInterviewHistoryFromRepresentationObject(InterviewHistoryRO interviewHistoryRO) {
        if (interviewHistoryRO != null) {
            InterviewHistoryVO interviewHistory = new InterviewHistoryVO();
            InterviewHistoryPK interviewHistoryId = new InterviewHistoryPK();
            interviewHistoryId.setInterviewerId(interviewHistoryRO.getInterviewerId());
            interviewHistoryId.setIntervieweeId(interviewHistoryRO.getIntervieweeId());
            interviewHistoryId.setExpertiseId(interviewHistoryRO.getExpertiseId());
            interviewHistory.setInterviewHistoryId(interviewHistoryId);
            interviewHistory.setScheduledDate(interviewHistoryRO.getAssignedSchedule());
            if (interviewHistoryRO.getStatus() != null) {
                InterviewStatus status = new InterviewStatus();
                status.setDescription(interviewHistoryRO.getStatus()
                        .toString());
                interviewHistory.setStatus(status);
            }
            return interviewHistory;
        }
        return null;
    }

}
