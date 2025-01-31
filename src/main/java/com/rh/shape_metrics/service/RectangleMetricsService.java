package com.rh.shape_metrics.service;

import com.rh.shape_metrics.model.ShapeMetricsRequest;
import com.rh.shape_metrics.model.ShapeMetricsResponse;
import com.rh.shape_metrics.model.ShapeType;
import org.springframework.stereotype.Service;

@Service
public class RectangleMetricsService extends AbstractShapeMetricsService {
    private static final String LENGTH = "length";
    private static final String WIDTH = "width";
    private static final int NUMBER_OF_SIDES = 2;

    @Override
    public ShapeMetricsResponse calculate(ShapeMetricsRequest request) {
        validateRequest(request);

        double width = request.getContext().get(WIDTH);
        double length = request.getContext().get(LENGTH);
        double area = length * width;
        double perimeter = length * NUMBER_OF_SIDES + width * NUMBER_OF_SIDES;

        return ShapeMetricsResponse.builder()
                .perimeter(perimeter)
                .area(area)
                .build();
    }

    @Override
    public ShapeType getType() {
        return ShapeType.RECTANGLE;
    }

    private void validateRequest(ShapeMetricsRequest request) {
        Double width = request.getContext().get(WIDTH);
        Double length = request.getContext().get(LENGTH);
        if (isInvalid(width) || isInvalid(length)) {
            throw new IllegalArgumentException("Rectangle metrics request must include valid width and length.");
        }
    }
}
