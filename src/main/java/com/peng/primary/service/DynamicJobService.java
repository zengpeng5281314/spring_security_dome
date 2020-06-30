package com.peng.primary.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peng.primary.dao.JobEntityRepository;
import com.peng.primary.entity.JobEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicJobService {
    @Autowired
    private JobEntityRepository repository;
    //通过Id获取Job
    public JobEntity getJobEntityById(Integer id) {
        return repository.getById(id);
    }
    //从数据库中加载获取到所有Job
    public List<JobEntity> loadJobs() {
        List<JobEntity> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }
    
    //添加Job
    public JobEntity saveJobEntity(JobEntity jobEntity) {
        return repository.save(jobEntity);
    }
    
}