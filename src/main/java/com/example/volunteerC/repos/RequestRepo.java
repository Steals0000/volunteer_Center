package com.example.volunteerC.repos;

import com.example.volunteerC.domain.Request;
import org.springframework.data.repository.CrudRepository;


import java.util.ArrayList;
import java.util.List;

public interface RequestRepo extends CrudRepository<Request, Long> {
    ArrayList<Request> findByUserCId(Long n);
    ArrayList<Request> findByUserVId(Long n);

    Request findById(long n);
}
