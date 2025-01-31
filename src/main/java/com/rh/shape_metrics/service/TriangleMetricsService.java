package com.rh.shape_metrics.service;

import com.rh.shape_metrics.model.ShapeMetricsRequest;
import com.rh.shape_metrics.model.ShapeMetricsResponse;
import com.rh.shape_metrics.model.ShapeType;
import org.springframework.stereotype.Service;

@Service
public class TriangleMetricsService extends AbstractShapeMetricsService {
    private static final String SIDE_A = "sideA";
    private static final String SIDE_B = "sideB";
    private static final String SIDE_C = "sideC";

    @Override
    public ShapeMetricsResponse calculate(ShapeMetricsRequest request) {
        validateRequest(request);

        Double sideA = request.getContext().get(SIDE_A);
        Double sideC = request.getContext().get(SIDE_B);
        Double sideB = request.getContext().get(SIDE_C);

        double perimeter = sideA + sideB + sideC;
        double semiPerimeter = perimeter / 2;
        double area = Math.sqrt(semiPerimeter * (semiPerimeter - sideA) * (semiPerimeter - sideB) * (semiPerimeter - sideC));

        return ShapeMetricsResponse.builder()
                .perimeter(perimeter)
                .area(area)
                .build();
    }

    @Override
    public ShapeType getType() {
        return ShapeType.TRIANGLE;
    }

    private void validateRequest(ShapeMetricsRequest request) {
        Double sideA = request.getContext().get(SIDE_A);
        Double sideC = request.getContext().get(SIDE_B);
        Double sideB = request.getContext().get(SIDE_C);

        if (isInvalid(sideA) || isInvalid(sideB) || isInvalid(sideC)) {
            throw new IllegalArgumentException("Circle metrics request must include valid sideA, sideB and sideC.");
        }
    }
}
