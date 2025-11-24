package com.ecoride.passengerservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("ratings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {

    @Id
    private UUID id;

    @Column("trip_id")
    private UUID tripId;

    @Column("from_id")
    private UUID fromId;

    @Column("to_id")
    private UUID toId;

    private Double score;
    private String comment;

}
