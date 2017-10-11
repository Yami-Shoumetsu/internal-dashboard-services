/**
 * 
 */
package com.ibm.usaa.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.usaa.exception.InvalidDependentIdException;
import com.ibm.usaa.exception.InvalidIdException;
import com.ibm.usaa.repository.InterviewHistoryRepository;
import com.ibm.usaa.repository.entity.InterviewHistoryPK;
import com.ibm.usaa.repository.entity.InterviewHistoryVO;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
public class InterviewHistoryService {

    @Autowired
    private InterviewHistoryRepository interviewHistoryRepository;

    @Autowired
    private ExpertiseService expertiseService;

    @Autowired
    private IntervieweeService intervieweeService;

    @Autowired
    private InterviewerService interviewerService;

    @Transactional
    public void addIntervieweeHistory(InterviewHistoryVO interviewHistoryVO) throws InvalidDependentIdException {
        InterviewHistoryPK interviewHistoryId = interviewHistoryVO.getInterviewHistoryId();

        Integer expertiseId = interviewHistoryId.getExpertiseId();
        if (!expertiseService.expertiseExists(expertiseId)) {
            throw new InvalidDependentIdException(expertiseId, "expertise");
        }

        Integer intervieweeId = interviewHistoryId.getIntervieweeId();
        if (!intervieweeService.intervieweeExists(intervieweeId)) {
            throw new InvalidDependentIdException(intervieweeId, "interviewee");
        }

        Integer interviewerId = interviewHistoryId.getInterviewerId();
        if (!interviewerService.interviewerExists(interviewerId)) {
            throw new InvalidDependentIdException(interviewerId, "interviewer");
        }

        interviewHistoryVO.setDateAssigned(new Date());
        interviewHistoryRepository.save(interviewHistoryVO);
    }

    public InterviewHistoryVO getInterviewHistory(int interviewerId, int intervieweeId, int expertiseId) throws InvalidIdException {
        InterviewHistoryPK interviewHistoryId = new InterviewHistoryPK();
        interviewHistoryId.setInterviewerId(interviewerId);
        interviewHistoryId.setIntervieweeId(intervieweeId);
        interviewHistoryId.setExpertiseId(expertiseId);
        InterviewHistoryVO interviewHistoryVO = interviewHistoryRepository.findOne(interviewHistoryId);
        if (interviewHistoryVO == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(interviewerId).append('-').append(intervieweeId).append('-').append(expertiseId);
            throw new InvalidIdException(stringBuilder.toString());
        }
        return interviewHistoryVO;
    }

}
