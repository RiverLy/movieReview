package com.river.movieReview.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
abstract class BaseEntity {

    @Column(name="regDate", updatable = false)
    @CreatedDate
    private LocalDateTime regDate;

    @Column(name = "modDate")
    @LastModifiedDate
    private LocalDateTime modDate;

}
