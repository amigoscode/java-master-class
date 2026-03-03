package com.bennet.booking.model;

import com.bennet.booking.value_objects.TimeSequence;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@NoArgsConstructor( access = AccessLevel.PROTECTED )
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    @Column(nullable = false)
    private UUID userId;

    @NonNull
    @Column(nullable = false)
    private UUID carId;

    @NonNull
    @Embedded
    private TimeSequence timeSequence;

    @NonNull
    @Column(unique = true)
    private String idempotencyKey;
}
