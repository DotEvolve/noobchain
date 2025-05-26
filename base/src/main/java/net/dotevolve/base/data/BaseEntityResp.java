package net.dotevolve.base.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class BaseEntityResp {
    private List<?> response = new ArrayList<>();
    private NextPagination nextPage;
}
