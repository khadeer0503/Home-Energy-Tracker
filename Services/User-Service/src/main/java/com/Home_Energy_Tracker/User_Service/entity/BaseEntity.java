package com.Home_Energy_Tracker.User_Service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseEntity {
    @Id
    @Column(name = "objectId", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long objectId;

    @Column(name = "createdDate", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "updatedDate")
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Column(name = "active", columnDefinition = "boolean default false")
    private boolean active;
}
