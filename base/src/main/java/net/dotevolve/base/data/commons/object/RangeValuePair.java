package net.dotevolve.base.data.commons.object;

import lombok.Data;

@Data
public class RangeValuePair<R extends Number, V> {
    private Range<R> range;
    private V value;

}
