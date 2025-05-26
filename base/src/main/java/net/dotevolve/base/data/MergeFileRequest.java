package net.dotevolve.base.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MergeFileRequest {
    private List<String> fileIds = new ArrayList<>();
    private String contentType;
}
