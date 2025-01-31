package com.rh.shape_metrics.service;

import com.rh.shape_metrics.model.ShapeMetricsRequest;
import com.rh.shape_metrics.model.ShapeMetricsResponse;
import com.rh.shape_metrics.model.ShapeType;
import org.springframework.stereotype.Service;

@Service
public class CircleMetricsService extends AbstractShapeMetricsService {
    private static final String RADIUS = "radius";
    private static final double PI = 3.14;

    @Override
    public ShapeMetricsResponse calculate(ShapeMetricsRequest request) {
        validate(request);

        double radius = request.getContext().get(RADIUS);
        double area = PI * radius * radius;
        double circumference = 2 * PI * radius;

        return ShapeMetricsResponse.builder()
                .perimeter(circumference)
                .area(area)
                .build();
    }

    @Override
    public ShapeType getType() {
        return ShapeType.CIRCLE;
    }

    private void validate(ShapeMetricsRequest request) {
        Double radius = request.getContext().get(RADIUS);
        if (isInvalid(radius)) {
            throw new IllegalArgumentException("Circle metrics request must include a valid radius.");
        }
    }
}
