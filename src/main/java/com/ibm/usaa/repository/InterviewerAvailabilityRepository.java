package com.ibm.usaa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.usaa.repository.entity.InterviewerAvailabilityVO;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
public interface InterviewerAvailabilityRepository extends JpaRepository<InterviewerAvailabilityVO, Integer> {

	public List<InterviewerAvailabilityVO> findByInterviewerAvailabilityIdInterviewerId(Integer interviewerId);

}
