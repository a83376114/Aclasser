package com.mz.aclasser.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class User {

    @Id
    private String id;
    @Field("username")
    private String username;
    @Field("password")
    private String password;
    @Field("info")
    private String info;
    @Field("a_id")
    private List<String> aId;
    @Field("p_id")
    private List<String> pId;
    @Field("t_id")
    private List<String> tId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<String> getaId() {
        return aId;
    }

    public void setaId(List<String> aId) {
        this.aId = aId;
    }

    public List<String> getpId() {
        return pId;
    }

    public void setpId(List<String> pId) {
        this.pId = pId;
    }

    public List<String> gettId() {
        return tId;
    }

    public void settId(List<String> tId) {
        this.tId = tId;
    }
}
