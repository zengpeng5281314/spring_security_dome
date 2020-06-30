package com.peng.primary.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.peng.primary.entity.JobEntity;


public interface JobEntityRepository extends JpaRepository<JobEntity, Long> {
	
    JobEntity getById(Integer id);
    
}