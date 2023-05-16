package com.example.volunteerC.service;

import com.example.volunteerC.domain.Role;
import com.example.volunteerC.domain.User;
import com.example.volunteerC.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public User GetCurrentUser()
    {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public void AddUser(User user)
    {
        user.setActive(true);
        user.setFinding(true);
        user.setRoles(Collections.singleton(Role.USER));

        user.setEmail("default");
        user.setName("default");
        user.setSurname("default");
        user.setMidname("default");
        user.setPhone("+123456789");

        userRepo.save(user);
    }

    public void EditUser(String username, Map<String, String> form, User user)
    {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
    }
    public ArrayList<User> getUsersByRole(Role role)
    {
        Iterable<User> users = userRepo.findAll();
        ArrayList<User> users_by_role = new ArrayList<User>();
        for (User user : users)
        {
            if (user.isFinding())
            {
                if (user.isClient() && role==Role.CLIENT)
                {
                    users_by_role.add(user);
                }
                else if (user.isVol() && role==Role.VOLUNTEER)
                {
                    users_by_role.add(user);
                }
                else if (user.isAdmin() && role==Role.ADMIN)
                {
                    users_by_role.add(user);
                }
            }
        }
        return users_by_role;
    }

}
