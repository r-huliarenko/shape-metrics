package com.rh.shape_metrics.service;

import com.rh.shape_metrics.model.ShapeType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ShapeMetricsFactoryFactory {
    private static final Map<ShapeType, ShapeMetricsService> shapeMetricsServiceMap = new HashMap<>();

    private final List<ShapeMetricsService> shapeMetricsServices;

    @PostConstruct
    public void init() {
        this.shapeMetricsServices.forEach(
                shapeMetricsService -> shapeMetricsServiceMap.put(shapeMetricsService.getType(), shapeMetricsService)
        );
    }

    public ShapeMetricsService getShapeMetricsCalculator(ShapeType type) {
        return shapeMetricsServiceMap.get(type);
    }

}
