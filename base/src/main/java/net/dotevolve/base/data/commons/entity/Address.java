/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 09/02/23, 5:04 pm
 *
 *
 ******************************************************************************/

package net.dotevolve.base.data.commons.entity;

import lombok.Data;

@Data
public class Address {
    private String houseNumber;
    private String street;
    private String area;
    private String landmark;
    private String zipcode;
    private String city;
    private String state;
    private String country;
}
