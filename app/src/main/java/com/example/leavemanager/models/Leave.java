package com.example.leavemanager.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Leave {
    @SerializedName("userid")
    @Expose
    private Integer userid;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("absencetype")
    @Expose
    private String absencetype;
    @SerializedName("datefrom")
    @Expose
    private String datefrom;
    @SerializedName("dateto")
    @Expose
    private String dateto;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAbsencetype() {
        return absencetype;
    }

    public void setAbsencetype(String absencetype) {
        this.absencetype = absencetype;
    }

    public String getDatefrom() {
        return datefrom;
    }

    public void setDatefrom(String datefrom) {
        this.datefrom = datefrom;
    }

    public String getDateto() {
        return dateto;
    }

    public void setDateto(String dateto) {
        this.dateto = dateto;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

