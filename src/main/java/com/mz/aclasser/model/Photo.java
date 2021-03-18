package com.mz.aclasser.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

public class Photo {

    @Id
    private String id;
    @Field("p_name")
    private String pName;
    @Field("creat_time")
    private String createTime;
    @Field("tags")
    private List<String> tags;
    @Field("u_id")
    private String uId;
    @Field("a_id")
    private String aId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", name='" + pName + '\'' +
                ", createTime=" + createTime +
                ", tags=" + tags +
                ", uId='" + uId + '\'' +
                ", aId='" + aId + '\'' +
                '}';
    }
}
