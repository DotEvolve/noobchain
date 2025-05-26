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
public class Contact {
    private String phone;
    private String mobile;
    private String email;
    private String alternatePhone;
    private String alternateMobile;
    private String alternateEmail;
}
