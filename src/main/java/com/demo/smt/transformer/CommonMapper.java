package com.demo.smt.transformer;

import com.demo.smt.config.MappersConfig;
import org.mapstruct.Mapper;

import java.util.UUID;

/**
 * Mapper class for common mappings.
 */
@Mapper(
        componentModel = "spring",
        config = MappersConfig.class
)
public interface CommonMapper {

    /**
     * Mapping method from {@link String} to {@link UUID}.
     *
     * @param value String value to be converted
     * @return The UUID resulted from string
     */
    default UUID stringToUuid(String value) {
        return UUID.fromString(value);
    }

    /**
     * Mapping method from {@link UUID} to {@link String}.
     *
     * @param uuid UUID value to be converted
     * @return The UUID as string
     */
    default String uuidToString(UUID uuid) {
        return uuid.toString();
    }
}
