/**
 * 
 */
package com.ibm.usaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.usaa.repository.entity.ExpertiseVO;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
public interface ExpertiseRepository extends JpaRepository<ExpertiseVO, Integer> {

    public int countByExpertiseNameIgnoreCase(String expertiseName);

}
