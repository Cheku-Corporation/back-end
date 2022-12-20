package com.cheku.cheku.model.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for storing information about a car model.
 */
@Data
public final class CarModelDTO {
    /** The brand of the car. */
    private  String carBrand;
    /** The model of the car. */
    private  String carModel;
    /** The year the car was manufactured. */
    private  String year;
}
