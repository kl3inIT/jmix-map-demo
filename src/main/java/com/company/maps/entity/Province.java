package com.company.maps.entity;

import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@JmixEntity
@Table(name = "PROVINCE")
@Entity
public class Province extends GeographicEntity {

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @OneToMany(mappedBy = "province")
    private Set<District> districts;

    public Set<District> getDistricts() {
        return districts;
    }

    public void setDistricts(Set<District> districts) {
        this.districts = districts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}