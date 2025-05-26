package net.dotevolve.base.application;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Configuration properties for multiple MongoDB connections.
 * Handles both primary and third-party MongoDB configurations.
 */
@Getter
@Builder
@ToString
@Validated
@Configuration
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "mongodb")
public class MultipleMongoProperties {

    /**
     * Primary MongoDB connection properties
     */
    @NotNull
    private final MongoProperties primary;

    /**
     * Mon§goDB connection properties for third-party tables
     */
    @NotNull
    private final MongoProperties thirdPartyTables;

    /**
     * Default constructor providing empty MongoDB properties
     */
    public MultipleMongoProperties() {
        this(new MongoProperties(), new MongoProperties());
    }
}