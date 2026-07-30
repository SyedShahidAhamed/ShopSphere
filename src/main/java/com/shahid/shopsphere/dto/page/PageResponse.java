package com.shahid.shopsphere.dto.page;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Generic paginated response")
public class PageResponse<T> {

    @Schema(
        description = "List of records for the current page"
    )
    private List<T> content;

    @Schema(
        description = "Current page number (0-based)",
        example = "0"
    )
    private int page;

    @Schema(
        description = "Number of records per page",
        example = "10"
    )
    private int size;

    @Schema(
        description = "Total number of records",
        example = "125"
    )
    private long totalElements;

    @Schema(
        description = "Total number of available pages",
        example = "13"
    )
    private int totalPages;

    @Schema(
        description = "Whether this is the first page",
        example = "true"
    )
    private boolean first;

    @Schema(
        description = "Whether this is the last page",
        example = "false"
    )
    private boolean last;
}