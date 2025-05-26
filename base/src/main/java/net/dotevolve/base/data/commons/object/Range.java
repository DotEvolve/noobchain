package net.dotevolve.base.data.commons.object;

import lombok.Data;

@Data
public class Range<T extends Number> {
    private T min;
    private T max;
}
