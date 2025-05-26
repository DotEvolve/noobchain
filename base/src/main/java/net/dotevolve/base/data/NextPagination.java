package net.dotevolve.base.data;

import lombok.Data;

@Data
public class NextPagination {
    private long totalHits;
    private long nextPageNumber;
}
