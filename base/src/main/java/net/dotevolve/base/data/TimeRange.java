package net.dotevolve.base.data;

import lombok.Data;

@Data
public class TimeRange {

    private String startTime;
    private String endTime;
    private TimeRangeDurationEnum rangeType = TimeRangeDurationEnum.CUSTOM;
}
