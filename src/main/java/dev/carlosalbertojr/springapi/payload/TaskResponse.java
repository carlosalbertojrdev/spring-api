package dev.carlosalbertojr.springapi.payload;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponse(
        UUID id,
        String title,
        String description,
        Boolean finished,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
