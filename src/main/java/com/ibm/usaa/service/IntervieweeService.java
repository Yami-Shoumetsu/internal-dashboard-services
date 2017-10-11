package com.ibm.usaa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ibm.usaa.exception.InvalidDependentIdException;
import com.ibm.usaa.exception.InvalidIdException;
import com.ibm.usaa.repository.IntervieweeRepository;
import com.ibm.usaa.repository.entity.InterviewHistoryPK;
import com.ibm.usaa.repository.entity.InterviewHistoryVO;
import com.ibm.usaa.repository.entity.IntervieweeVO;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
public class IntervieweeService {

    @Autowired
    private IntervieweeRepository intervieweeRepository;

    @Autowired
    private InterviewHistoryService interviewHistoryService;

    public List<IntervieweeVO> getInterviewees(String expertiseName, Boolean internal) {
        if (!StringUtils.isEmpty(expertiseName) && internal != null) {
            return intervieweeRepository.findByInterviewHistoriesExpertiseExpertiseNameAndIsInternal(expertiseName, internal);
        } else if (!StringUtils.isEmpty(expertiseName) && internal == null) {
            return intervieweeRepository.findByInterviewHistoriesExpertiseExpertiseName(expertiseName);
        } else if (StringUtils.isEmpty(expertiseName) && internal != null) {
            return intervieweeRepository.findByIsInternal(internal);
        } else {
            return intervieweeRepository.findAll();
        }
    }

    public IntervieweeVO getInterviewee(int id) throws InvalidIdException {
        IntervieweeVO intervieweeVO = intervieweeRepository.findOne(id);
        if (intervieweeVO == null) {
            throw new InvalidIdException(id);
        }
        return intervieweeVO;
    }

    @Transactional(rollbackFor = InvalidDependentIdException.class)
    public int addInterviewee(IntervieweeVO intervieweeVO) throws InvalidDependentIdException {
        intervieweeVO = intervieweeRepository.save(intervieweeVO);
        List<InterviewHistoryVO> interviewHistories = intervieweeVO.getInterviewHistories();
        for (InterviewHistoryVO interviewHistoryVO : interviewHistories) {
            InterviewHistoryPK interviewHistoryId = interviewHistoryVO.getInterviewHistoryId();
            interviewHistoryId.setIntervieweeId(intervieweeVO.getIntervieweeId());
            interviewHistoryService.addIntervieweeHistory(interviewHistoryVO);
        }
        return intervieweeVO.getIntervieweeId();
    }

    public void updateInterviewee(IntervieweeVO intervieweeVO) throws InvalidIdException {
        IntervieweeVO existingIntervieweeVO = intervieweeRepository.findOne(intervieweeVO.getIntervieweeId());
        if (existingIntervieweeVO == null) {
            throw new InvalidIdException(intervieweeVO.getIntervieweeId());
        }
        intervieweeRepository.save(intervieweeVO);
    }

    public boolean intervieweeExists(int id) {
        return intervieweeRepository.exists(id);
    }

    @Transactional
    public List<InterviewHistoryVO> getInterviewHistoriesOfInterviewee(int id) throws InvalidIdException {
        IntervieweeVO intervieweeVO = intervieweeRepository.findOne(id);
        if (intervieweeVO == null) {
            throw new InvalidIdException(id);
        }
        List<InterviewHistoryVO> interviewHistories = intervieweeVO.getInterviewHistories();
        interviewHistories.size(); // This is to force lazy load
        return interviewHistories;
    }

}
