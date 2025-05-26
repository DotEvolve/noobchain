package net.dotevolve.base.data;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import lombok.Data;

@Data
public class DataSheetResponse {
    private List<String> headers = Lists.newArrayList();
    private List<Map<String, String>> rows = Lists.newArrayList();
}
