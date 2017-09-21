package com.ibm.usaa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ibm.usaa.exception.InvalidIdException;
import com.ibm.usaa.repository.InterviewerRepository;
import com.ibm.usaa.repository.entity.InterviewerVO;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
public class InterviewerService {

    @Autowired
    private InterviewerRepository interviewerRepository;

    public List<InterviewerVO> getInterviewers(String expertiseName) {
        if (!StringUtils.isEmpty(expertiseName)) {
        	return interviewerRepository.findByExpertisesExpertiseName(expertiseName);
        } else {
            return interviewerRepository.findAll();
        }
    }
    
    public InterviewerVO getInterviewer(int id) throws InvalidIdException {
        InterviewerVO interviewerVO = interviewerRepository.findOne(id);
        if (interviewerVO == null) {
            throw new InvalidIdException(id);
        }
        return interviewerVO;
    }

}
