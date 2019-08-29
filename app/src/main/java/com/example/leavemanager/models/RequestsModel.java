package com.example.leavemanager.models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class RequestsModel {
    @Id long id;
    String confirmation,absencetype,datefrom,dateto,reason,comment;

    public RequestsModel(String confirmation, String absencetype, String datefrom, String dateto, String reason, String comment) {
        this.confirmation = confirmation;
        this.absencetype = absencetype;
        this.datefrom = datefrom;
        this.dateto = dateto;
        this.reason = reason;
        this.comment = comment;
    }

    public RequestsModel() {
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
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
}
