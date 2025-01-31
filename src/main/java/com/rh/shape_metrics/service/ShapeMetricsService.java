package com.rh.shape_metrics.service;

import com.rh.shape_metrics.model.ShapeMetricsRequest;
import com.rh.shape_metrics.model.ShapeMetricsResponse;
import com.rh.shape_metrics.model.ShapeType;

public interface ShapeMetricsService {

    ShapeMetricsResponse calculate(ShapeMetricsRequest request);

    ShapeType getType();
}
