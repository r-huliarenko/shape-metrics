package com.rh.shape_metrics.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class ShapeMetricsRequest {
    @NotNull
    ShapeType type;
    @NotEmpty
    Map<String, Double> context;
}
