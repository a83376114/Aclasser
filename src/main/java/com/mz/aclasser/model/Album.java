package com.mz.aclasser.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class Album {

    @Id
    private String id;
    @Field("a_name")
    private String aName;
    @Field("u_id")
    private String uId;
    @Field("cover")
    private String cover;
    @Field("username")
    private String username;
//    @Field("p_id")
//    private List<String> pId;
    @Field("t_id")
    private List<String> tId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<String> gettId() {
        return tId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void settId(List<String> tId) {
        this.tId = tId;
    }
}
