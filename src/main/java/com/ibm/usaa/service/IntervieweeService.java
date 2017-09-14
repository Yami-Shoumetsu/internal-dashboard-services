package com.ibm.usaa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ibm.usaa.exception.InvalidDependentIdException;
import com.ibm.usaa.exception.InvalidIdException;
import com.ibm.usaa.repository.IntervieweeRepository;
import com.ibm.usaa.repository.entity.ExpertiseVO;
import com.ibm.usaa.repository.entity.IntervieweeVO;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
public class IntervieweeService {

    @Autowired
    private IntervieweeRepository intervieweeRepository;

    @Autowired
    private ExpertiseService expertiseService;

    public List<IntervieweeVO> getInterviewees(String expertiseName, Boolean internal) {
        if (!StringUtils.isEmpty(expertiseName) && internal != null) {
            return intervieweeRepository.findByExpertiseExpertiseNameAndIsInternal(expertiseName, internal);
        } else if (!StringUtils.isEmpty(expertiseName) && internal == null) {
            return intervieweeRepository.findByExpertiseExpertiseName(expertiseName);
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

    public int addInterviewee(IntervieweeVO intervieweeVO) throws InvalidDependentIdException {
        ExpertiseVO expertise;
        try {
            expertise = expertiseService.getExpertise(intervieweeVO.getExpertiseId());
        } catch (InvalidIdException e) {
            throw new InvalidDependentIdException(intervieweeVO.getExpertiseId(), "expertise");
        }
        intervieweeVO.setExpertise(expertise);
        intervieweeVO = intervieweeRepository.save(intervieweeVO);
        return intervieweeVO.getIntervieweeId();
    }

    public void updateInterviewee(IntervieweeVO intervieweeVO) throws InvalidIdException {
        IntervieweeVO existingIntervieweeVO = intervieweeRepository.findOne(intervieweeVO.getIntervieweeId());
        if (existingIntervieweeVO == null) {
            throw new InvalidIdException(intervieweeVO.getIntervieweeId());
        }
        intervieweeVO.setExpertise(existingIntervieweeVO.getExpertise());
        intervieweeRepository.save(intervieweeVO);
    }

    public ExpertiseVO getExpertiseOfInterviewee(int id) throws InvalidIdException {
        IntervieweeVO intervieweeVO = intervieweeRepository.findOne(id);
        if (intervieweeVO == null) {
            throw new InvalidIdException(id);
        }
        return intervieweeVO.getExpertise();
    }

    public void updateExpertiseOfInterviewee(int intervieweeId, int expertiseId) throws InvalidIdException, InvalidDependentIdException {
        IntervieweeVO intervieweeVO = intervieweeRepository.findOne(intervieweeId);
        if (intervieweeVO == null) {
            throw new InvalidIdException(intervieweeId);
        }
        ExpertiseVO expertiseVO;
        try {
            expertiseVO = expertiseService.getExpertise(expertiseId);
        } catch (InvalidIdException e) {
            throw new InvalidDependentIdException(expertiseId, "expertise");
        }
        intervieweeVO.setExpertise(expertiseVO);
        intervieweeRepository.save(intervieweeVO);
    }

}
