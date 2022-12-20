package com.cheku.cheku.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for storing information about a car model.
 */
@Data
@Builder
public final class CarModelDTO {
    /** The brand of the car. */
    private final String carBrand;
    /** The model of the car. */
    private final String carModel;
    /** The year the car was manufactured. */
    private final String year;
}
