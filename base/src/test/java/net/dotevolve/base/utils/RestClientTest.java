/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 25/01/23, 2:06 am
 *
 *
 ******************************************************************************/

package net.dotevolve.base.utils;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RestClientTest {

    @InjectMocks
    private RestClient restClient;

    @Mock
    private RestTemplate restTemplate;

    private final String TEST_URI = "https://test.com/api";
    private final String TEST_RESPONSE = "{\"key\":\"value\"}";
    private final String TEST_REQUEST = "{\"request\":\"data\"}";
    private final String TEST_USERNAME = "username";
    private final String TEST_PASSWORD = "password";

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        restClient.init(); // Call the @PostConstruct method
        ReflectionTestUtils.setField(restClient, "rest", restTemplate); // Replace the RestTemplate with our mock
    }

    @Test
    public void testGet() {
        // Arrange
        ResponseEntity<String> responseEntity = new ResponseEntity<>(TEST_RESPONSE, HttpStatus.OK);
        when(restTemplate.exchange(
                eq(TEST_URI),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class)
        )).thenReturn(responseEntity);

        // Act
        String result = restClient.get(TEST_URI);

        // Assert
        assertEquals(TEST_RESPONSE, result);
    }

    @Test
    public void testPost() {
        // Arrange
        ResponseEntity<String> responseEntity = new ResponseEntity<>(TEST_RESPONSE, HttpStatus.OK);
        when(restTemplate.exchange(
                eq(TEST_URI),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(String.class)
        )).thenReturn(responseEntity);

        // Act
        String result = restClient.post(TEST_URI, TEST_REQUEST);

        // Assert
        assertEquals(TEST_RESPONSE, result);
    }

    @Test
    public void testPostWithAuth() {
        // Arrange
        ResponseEntity<String> responseEntity = new ResponseEntity<>(TEST_RESPONSE, HttpStatus.OK);
        when(restTemplate.exchange(
                eq(TEST_URI),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(String.class)
        )).thenReturn(responseEntity);

        // Act
        String result = restClient.post(TEST_URI, TEST_REQUEST, TEST_USERNAME, TEST_PASSWORD);

        // Assert
        assertEquals(TEST_RESPONSE, result);
    }

    @Test
    public void testPut() {
        // Arrange
        ResponseEntity<String> responseEntity = new ResponseEntity<>(TEST_RESPONSE, HttpStatus.OK);
        when(restTemplate.exchange(
                eq(TEST_URI),
                eq(HttpMethod.PUT),
                any(HttpEntity.class),
                eq(String.class)
        )).thenReturn(responseEntity);

        // Act
        String result = restClient.put(TEST_URI, TEST_REQUEST);

        // Assert
        assertEquals(TEST_RESPONSE, result);
    }

    @Test
    public void testPutWithAuth() {
        // Arrange
        ResponseEntity<String> responseEntity = new ResponseEntity<>(TEST_RESPONSE, HttpStatus.OK);
        when(restTemplate.exchange(
                eq(TEST_URI),
                eq(HttpMethod.PUT),
                any(HttpEntity.class),
                eq(String.class)
        )).thenReturn(responseEntity);

        // Act
        String result = restClient.putWithBasicAuth(TEST_URI, TEST_REQUEST, TEST_USERNAME, TEST_PASSWORD);

        // Assert
        assertEquals(TEST_RESPONSE, result);
    }

    @Test
    public void testGetWithDataAndBasicAuth() {
        // Arrange
        ResponseEntity<String> responseEntity = new ResponseEntity<>(TEST_RESPONSE, HttpStatus.OK);
        when(restTemplate.exchange(
                eq(TEST_URI),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class)
        )).thenReturn(responseEntity);

        // Act
        String result = restClient.getWithDataAndBasicAuth(TEST_URI, TEST_REQUEST, TEST_USERNAME, TEST_PASSWORD);

        // Assert
        assertEquals(TEST_RESPONSE, result);
    }

    @Test
    public void testDelete() {
        // Arrange
        ResponseEntity<String> responseEntity = new ResponseEntity<>("", HttpStatus.OK);
        when(restTemplate.exchange(
                eq(TEST_URI),
                eq(HttpMethod.DELETE),
                any(HttpEntity.class),
                eq(String.class)
        )).thenReturn(responseEntity);

        // Act - this method doesn't return anything
        restClient.delete(TEST_URI, TEST_USERNAME, TEST_PASSWORD);

        Mockito.verify(restTemplate, Mockito.times(1)).exchange(
                eq(TEST_URI),
                eq(HttpMethod.DELETE),
                any(HttpEntity.class),
                eq(String.class));
    }
}