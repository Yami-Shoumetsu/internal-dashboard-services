/**
 * 
 */
package com.ibm.usaa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.usaa.exception.InvalidIdException;
import com.ibm.usaa.repository.InterviewerAvailabilityRepository;
import com.ibm.usaa.repository.entity.InterviewerAvailabilityVO;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
public class InterviewerAvailabilityService {

    @Autowired
    private InterviewerAvailabilityRepository interviewerAvailabilityRepository;

    public List<InterviewerAvailabilityVO> getInterviewerAvailabilities() {
        return interviewerAvailabilityRepository.findAll();
    }

    public List<InterviewerAvailabilityVO> getInterviewerAvailabilities(int id) throws InvalidIdException {
    	List<InterviewerAvailabilityVO> interviewerAvailabilityVO = interviewerAvailabilityRepository.findByInterviewerAvailabilityIdInterviewerId(id);
        if (interviewerAvailabilityVO == null) {
            throw new InvalidIdException(id);
        }
        return interviewerAvailabilityVO;
    }

}
