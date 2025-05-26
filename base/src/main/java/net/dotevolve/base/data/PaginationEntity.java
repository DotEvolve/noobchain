/******************************************************************
 *
 * @copyright 2022 DotEvolve
 * @author
 * @since
 ******************************************************************/

package net.dotevolve.base.data;

import lombok.Data;

@Data
public class PaginationEntity {
    private long totalSize = 50;
    private long pageNo = 1;

}
