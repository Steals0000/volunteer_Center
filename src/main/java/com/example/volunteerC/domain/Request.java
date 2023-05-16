package com.example.volunteerC.domain;

import javax.persistence.*;

@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long userCId;
    private long userVId;

    public Request(long user_c_id, long user_v_id) {
        this.userCId = user_c_id;
        this.userVId = user_v_id;
    }

    public Request(long id,long user_c_id, long user_v_id) {
        this.userCId = user_c_id;
        this.userVId = user_v_id;
        this.id = id;
    }

    public Request() {
    }

    public long getUserCId() {
        return userCId;
    }

    public void setUserCId(long user_c_id) {
        this.userCId = user_c_id;
    }

    public long getUserVId() {
        return userVId;
    }

    public void setUserVId(long user_v_id) {
        this.userVId = user_v_id;
    }

    public Long getId() {
        return id;
    }
}
