package com.example.volunteerC.service;

import com.example.volunteerC.domain.Request;
import com.example.volunteerC.domain.User;
import com.example.volunteerC.repos.RequestRepo;
import com.example.volunteerC.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RequestRepo requestRepo;


    public RequestService() {
    }
    public ArrayList<User> FindPairs(User current_user) {
        ArrayList<User> users_to_vol = new ArrayList<>();
        if (!current_user.getFinding()) {
            ArrayList<Request> reqs = GetUserReqs(current_user);
            List<User> users = userRepo.findAll();
            for (User user : users) {
                for (Request req : reqs) {
                    if (user.getId() == req.getUserCId() && !users_to_vol.contains(user)) {
                        users_to_vol.add(user);
                    }
                    if (user.getId() == req.getUserVId() && !users_to_vol.contains(user)) {
                        users_to_vol.add(user);
                    }
                }
            }
        }
        return users_to_vol;
    }

    public ArrayList<Request> GetUserReqs(User user)
    {
       ArrayList<Request> reqs = requestRepo.findByUserCId(user.getId());
        if (reqs.isEmpty())
        {
            reqs = requestRepo.findByUserVId(user.getId());
        }
        return reqs;
    }

    public long GetReq(User user)
    {
        if (GetUserReqs(user).isEmpty())
            return -1;
        return GetUserReqs(user).get(0).getId();
    }

    public void ChangeState(User user,User c_user)
    {
        if (user.isFinding())
            user.setFinding(false);
        else
            user.setFinding(true);
        if (c_user.isFinding())
            c_user.setFinding(false);
        else
            c_user.setFinding(true);
    }
    public void DeleteReq(long id)
    {
        Request req = requestRepo.findById(id);
        ChangeState(userRepo.findById(req.getUserCId()), userRepo.findById(req.getUserVId()));

        requestRepo.delete(req);
    }

    public void CreateRequestAndRefresh(User user,User c_user)
    {
        if (c_user.getFinding() && user.getFinding()) {
            Request req = new Request(c_user.getId(), user.getId());
            requestRepo.save(req);
            ChangeState(user,c_user);
            userRepo.save(user);
            userRepo.save(c_user);
        }
    }
}
