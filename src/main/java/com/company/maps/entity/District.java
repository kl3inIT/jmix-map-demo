package com.company.maps.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@JmixEntity
@Table(name = "DISTRICT", indexes = {
        @Index(name = "IDX_DISTRICT_PROVINCE", columnList = "PROVINCE_ID")
})
@Entity
public class District extends GeographicEntity {

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "PROVINCE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Province province;

    @OneToMany(mappedBy = "district")
    private Set<Commune> communes;

    public Set<Commune> getCommunes() {
        return communes;
    }

    public void setCommunes(Set<Commune> communes) {
        this.communes = communes;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}