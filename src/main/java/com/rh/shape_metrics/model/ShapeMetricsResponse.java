package com.rh.shape_metrics.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ShapeMetricsResponse {
    Double area;
    Double perimeter;
}
