/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 15/02/23, 9:49 am
 *
 *
 ******************************************************************************/

package net.dotevolve.base.data.commons.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class GridResp {
    private Map<String, String> headers = new HashMap<>();
    private List<Map<String, String>> rows = new ArrayList<>();
    private Map<String, String> display = new HashMap<>();
    private Map<String, String> orderBy = new HashMap<>();
}

