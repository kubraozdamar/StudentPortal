package com.voltran.ICES4HU.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "banned_user")

public class BannedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "user_id")
    long userId;

    @Column(name = "banned_date")
    Date bannedDate;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getBannedDate() {
        return bannedDate;
    }

    public void setBannedDate(Date bannedDate) {
        this.bannedDate = bannedDate;
    }
}