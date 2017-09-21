package com.ibm.usaa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.usaa.repository.entity.InterviewerVO;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
public interface InterviewerRepository extends JpaRepository<InterviewerVO, Integer> {

    public List<InterviewerVO> findByExpertisesExpertiseName(String expertiseName);

}
