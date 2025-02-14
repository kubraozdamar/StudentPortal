package com.voltran.ICES4HU.models;

import com.voltran.ICES4HU.payload.element.TranscriptRow;
import jakarta.persistence.*;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.Date;


@Data
@Entity
@Table(name = "faculty")

public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
