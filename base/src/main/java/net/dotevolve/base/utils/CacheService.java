package net.dotevolve.base.utils;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class CacheService<T> {
    private static final String KEY_FORMAT = "%s@%s";

    private final CacheManager cacheManager;

    @Autowired
    public CacheService(CacheManager cacheManager) {
        this.cacheManager = requireNonNull(cacheManager, "CacheManager must not be null");
    }

    /**
     * Saves an object to the cache
     *
     * @param id             The identifier
     * @param obj            The object to cache
     * @param collectionName The collection name
     *
     * @return The cached object
     *
     * @throws IllegalArgumentException if any parameter is null
     */
    public T save(String id, T obj, String collectionName) {
        requireNonNull(id, "Id must not be null");
        requireNonNull(obj, "Object must not be null");
        requireNonNull(collectionName, "Collection name must not be null");

        String key = generateKey(id, collectionName);
        Cache cache = getCacheForCollection(collectionName);
        cache.put(key, obj);
        return obj;
    }

    /**
     * Retrieves an object from the cache
     *
     * @param id             The identifier
     * @param collectionName The collection name
     *
     * @return Optional containing the cached object if present
     */
    public Optional<T> get(String id, String collectionName) {
        requireNonNull(id, "Id must not be null");
        requireNonNull(collectionName, "Collection name must not be null");

        String key = generateKey(id, collectionName);
        Cache cache = getCacheForCollection(collectionName);
        Cache.ValueWrapper wrapper = cache.get(key);
        return Optional.ofNullable(wrapper).map(w -> (T) w.get());
    }

    /**
     * Checks if an object exists in the cache
     */
    public boolean exists(String id, String collectionName) {
        requireNonNull(id, "Id must not be null");
        requireNonNull(collectionName, "Collection name must not be null");

        String key = generateKey(id, collectionName);
        return getCacheForCollection(collectionName).get(key) != null;
    }

    /**
     * Clears all entries from the specified collection
     */
    public void clearAll(String collectionName) {
        requireNonNull(collectionName, "Collection name must not be null");
        getCacheForCollection(collectionName).clear();
    }

    private String generateKey(String id, String collectionName) {
        return String.format(KEY_FORMAT, id, collectionName);
    }

    private Cache getCacheForCollection(String collectionName) {
        Cache cache = cacheManager.getCache(collectionName);
        if (cache == null) {
            throw new IllegalStateException("Cache not found for collection: " + collectionName);
        }
        return cache;
    }
}