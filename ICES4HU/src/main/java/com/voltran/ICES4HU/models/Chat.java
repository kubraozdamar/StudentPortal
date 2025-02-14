package com.voltran.ICES4HU.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "chat")

public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "user_id_1")
    private long userId1;

    @Column(name = "user_id_2")
    private long userId2;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId1() {
        return userId1;
    }

    public void setUserId1(long userId1) {
        this.userId1 = userId1;
    }

    public long getUserId2() {
        return userId2;
    }

    public void setUserId2(long userId2) {
        this.userId2 = userId2;
    }
}