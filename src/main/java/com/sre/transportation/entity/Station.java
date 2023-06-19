package com.sre.transportation.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "station")
@Getter
@Setter
public class Station {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    UUID id;

    @Column(length = 7, unique = true)
    @NotNull
    @NotBlank
    String code;

    @NotNull(message = "title cannot be null")
    @NotBlank
    String title;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "route_id")
    @JsonBackReference
    Route route;

    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "station_id")
    @JsonManagedReference
    List<Vehicle> vehicles = new ArrayList<>();

    public Station(String code, String title) {
        super();
        this.code = code;
        this.title = title;
    }

    public Station(String code, String title, Route route) {
        super();
        this.code = code;
        this.title = title;
        this.route = route;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles.clear();
        this.vehicles.addAll(vehicles);
    }

}
