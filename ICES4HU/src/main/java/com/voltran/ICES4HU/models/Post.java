package com.voltran.ICES4HU.models;

import com.voltran.ICES4HU.payload.element.TranscriptRow;
import jakarta.persistence.*;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.Date;


@Data
@Entity
@Table(name = "post")

public class Post implements Comparable<Post> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "date")
    private Date date;

    @Column(name = "info")
    private String info;

    @Column(name = "header")
    private String header;

    @Column(name = "content")
    private String content;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public int compareTo(@NotNull Post o) {
        int ret = this.date.compareTo(o.date);
        return ret;
    }

}
