package com.example.leavemanager.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewRequestsModel implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("userid")
    @Expose
    private Integer userid;
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
    @SerializedName("substitute")
    @Expose
    private String substitute;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    protected ViewRequestsModel(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            userid = null;
        } else {
            userid = in.readInt();
        }
        absencetype = in.readString();
        datefrom = in.readString();
        dateto = in.readString();
        reason = in.readString();
        substitute = in.readString();
        status = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    public static final Creator<ViewRequestsModel> CREATOR = new Creator<ViewRequestsModel>() {
        @Override
        public ViewRequestsModel createFromParcel(Parcel in) {
            return new ViewRequestsModel(in);
        }

        @Override
        public ViewRequestsModel[] newArray(int size) {
            return new ViewRequestsModel[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public String getSubstitute() {
        return substitute;
    }

    public void setSubstitute(String substitute) {
        this.substitute = substitute;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        if (userid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(userid);
        }
        dest.writeString(absencetype);
        dest.writeString(datefrom);
        dest.writeString(dateto);
        dest.writeString(reason);
        dest.writeString(substitute);
        dest.writeString(status);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
    }
}