package com.company.maps.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@JmixEntity
@Table(name = "COMMUNE", indexes = {
        @Index(name = "IDX_COMMUNE_DISTRICT", columnList = "DISTRICT_ID")
})
@Entity
public class Commune extends GeographicEntity {
    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "DISTRICT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private District district;

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}