package com.rh.shape_metrics.service;

import org.springframework.beans.factory.annotation.Value;

import static java.util.Objects.isNull;

public abstract class AbstractShapeMetricsService implements ShapeMetricsService {
    @Value("${shape.parameter.min-size}")
    private Double minSize;
    @Value("${shape.parameter.max-size}")
    private Double maxSize;

    protected boolean isInvalid(Double parameter) {
        return isNull(parameter) || parameter <= this.minSize || parameter > this.maxSize;
    }

}
