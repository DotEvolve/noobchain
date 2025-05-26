/******************************************************************
 *
 * @copyright 2022 DotEvolve
 * @author
 * @since
 ******************************************************************/
package net.dotevolve.base.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class BaseEntity {
    @SerializedName("_id")
    private String id;
    private MetaDataEntity metaData = new MetaDataEntity();

}
