package com.rh.shape_metrics.controller;

import com.rh.shape_metrics.model.ShapeMetricsRequest;
import com.rh.shape_metrics.model.ShapeMetricsResponse;
import com.rh.shape_metrics.service.ShapeMetricsFactoryFactory;
import com.rh.shape_metrics.service.ShapeMetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shape/metrics")
@RequiredArgsConstructor
public class ShapeMetricsController {
    private final ShapeMetricsFactoryFactory shapeMetricsCalculationFactory;

    @PostMapping("/calculate")
    public ShapeMetricsResponse shapeMetrics( @RequestBody ShapeMetricsRequest request) {
        ShapeMetricsService shapeMetricsService = shapeMetricsCalculationFactory.getShapeMetricsCalculator(request.getType());
        return shapeMetricsService.calculate(request);
    }
}
