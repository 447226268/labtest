package com.gooalgene.labtest.dao;

import com.gooalgene.labtest.entity.Staff_list;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffMapper {
    List<Staff_list> findAll();

    Staff_list Sel(int id);
}



