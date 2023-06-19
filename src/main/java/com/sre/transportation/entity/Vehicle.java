package com.sre.transportation.entity;

import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle")
@Getter
@Setter
public class Vehicle {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    UUID id;
    @NotNull(message = "numberPlate cannot be null")
    @NotBlank
    //@Column( unique = true)
    String numberPlate;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "station_id")
     @JsonBackReference
     Station station;
     //List<Station> stations = new ArrayList<>();


    public Vehicle(String numberPlate) {
        super();
        this.numberPlate = numberPlate;
    }

}
