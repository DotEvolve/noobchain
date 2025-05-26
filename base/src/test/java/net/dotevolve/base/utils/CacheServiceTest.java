package net.dotevolve.base.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CacheServiceTest {

    @Mock
    private CacheManager cacheManager;

    @Mock
    private Cache cache;

    @InjectMocks
    private CacheService<Object> cacheService;

    public CacheServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave_ValidParameters_ObjectSavedToCache() {
        // Arrange
        String id = "123";
        Object obj = new Object();
        String collectionName = "testCollection";
        when(cacheManager.getCache(collectionName)).thenReturn(cache);

        // Act
        Object result = cacheService.save(id, obj, collectionName);

        // Assert
        verify(cache).put("123@testCollection", obj);
        assertEquals(obj, result, "Saved object should match the returned object");
    }

    @Test
    void testSave_NullId_ThrowsException() {
        // Arrange
        Object obj = new Object();
        String collectionName = "testCollection";

        // Act & Assert
        assertThrows(NullPointerException.class,
                () -> cacheService.save(null, obj, collectionName),
                "Should throw NullPointerException when id is null");
    }

    @Test
    void testSave_NullObject_ThrowsException() {
        // Arrange
        String id = "123";
        String collectionName = "testCollection";

        // Act & Assert
        assertThrows(NullPointerException.class,
                () -> cacheService.save(id, null, collectionName),
                "Should throw NullPointerException when object is null");
    }

    @Test
    void testSave_NullCollectionName_ThrowsException() {
        // Arrange
        String id = "123";
        Object obj = new Object();

        // Act & Assert
        assertThrows(NullPointerException.class,
                () -> cacheService.save(id, obj, null),
                "Should throw NullPointerException when collectionName is null");
    }

    @Test
    void testSave_CacheNotFound_ThrowsException() {
        // Arrange
        String id = "123";
        Object obj = new Object();
        String collectionName = "invalidCollection";
        when(cacheManager.getCache(collectionName)).thenReturn(null);

        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> cacheService.save(id, obj, collectionName),
                "Should throw IllegalStateException when cache is not found");
        assertEquals("Cache not found for collection: invalidCollection", exception.getMessage());
    }
}