package net.dotevolve.base.service;

import java.util.List;

import net.dotevolve.base.data.BaseEntity;
import net.dotevolve.base.data.BaseEntityReq;
import net.dotevolve.base.utils.CacheService;
import net.dotevolve.base.utils.CodeHelp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CachedSearchService<Data extends BaseEntity, SReq extends BaseEntityReq> {

    private static final Logger logger = LogManager.getLogger(CachedSearchService.class);

    private final SearchService<Data, SReq> searchService;

    private final CacheService<Data> cacheService;

    public CachedSearchService(SearchService<Data, SReq> searchService, CacheService<Data> cacheService) {
        this.searchService = searchService;
        this.cacheService = cacheService;
    }

    public List<Data> search(SReq request) {
        if (cacheService.exists(CodeHelp.toJson(request), request.getClass().getSimpleName())) {
            return (List<Data>) cacheService.get(CodeHelp.toJson(request), request.getClass().getSimpleName()).get();
        }
        List<Data> resp = searchService.search(request);
        if (!resp.isEmpty()) {
            cacheService.save(CodeHelp.toJson(request), resp.get(0), request.getClass().getSimpleName());
        } else {
            // Handle the case where the list is empty (e.g., log a warning)
            logger.info("No data found for request {}", request);
        }
        return resp;
    }
}
