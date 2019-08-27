package com.example.leavemanager.models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class RequestsModel {
    @Id long id;
    String confirmation,absencetype,datefrom,dateto;

    public RequestsModel(String confirmation, String absencetype, String datefrom, String dateto) {
        this.confirmation = confirmation;
        this.absencetype = absencetype;
        this.datefrom = datefrom;
        this.dateto = dateto;
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
}
