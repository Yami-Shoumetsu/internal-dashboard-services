package com.ibm.usaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.usaa.repository.entity.InterviewHistoryPK;
import com.ibm.usaa.repository.entity.InterviewHistoryVO;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
public interface InterviewHistoryRepository extends JpaRepository<InterviewHistoryVO, InterviewHistoryPK> {

}
