package net.dotevolve.base.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class DataRef {
    @SerializedName(value = "_id", alternate = "id")
    private String id;
    private String name;
}
