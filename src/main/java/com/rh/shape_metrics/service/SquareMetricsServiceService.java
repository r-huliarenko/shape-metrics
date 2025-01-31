package com.rh.shape_metrics.service;

import com.rh.shape_metrics.model.ShapeMetricsRequest;
import com.rh.shape_metrics.model.ShapeMetricsResponse;
import com.rh.shape_metrics.model.ShapeType;
import org.springframework.stereotype.Service;

@Service
public class SquareMetricsServiceService extends AbstractShapeMetricsService {
    private static final String SIDE = "side";
    private static final int NUMBER_OF_SIDES = 4;

    @Override
    public ShapeMetricsResponse calculate(ShapeMetricsRequest request) {
        validateRequest(request);

        double side = request.getContext().get(SIDE);
        double area = side * side;
        double perimeter = NUMBER_OF_SIDES * side;

        return ShapeMetricsResponse.builder()
                .perimeter(perimeter)
                .area(area)
                .build();
    }

    @Override
    public ShapeType getType() {
        return ShapeType.SQUARE;
    }

    private void validateRequest(ShapeMetricsRequest request) {
        Double side = request.getContext().get(SIDE);
        if (isInvalid(side)) {
            throw new IllegalArgumentException("Square metrics request must include a valid side.");
        }
    }
}
