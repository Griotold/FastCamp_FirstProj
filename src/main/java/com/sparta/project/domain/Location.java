package com.sparta.project.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Entity
@Table(name="p_location")
public class Location extends BaseEntity { // 지역

    @Id
    @Column(name="location_id", length=36, nullable=false, updatable=false)
    private String locationId;

    @Column(name="name", length=20, nullable=false) // 이름
    private String name;

    @Column(name="description", length=50) // 설명
    private String description;
}
