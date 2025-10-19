package ru.academits.temperature.dik.model.temperature;

public class TemperatureQuantityImp implements TemperatureQuantity {
    private final TemperatureScale temperatureScale;
    private final TemperatureQuantityType temperatureQuantityType;
    private final double value;

    public TemperatureQuantityImp(TemperatureScale temperatureScale, double value) {
        this.temperatureScale = temperatureScale;
        this.value = value;
        temperatureQuantityType = temperatureScale.getPhysicalQuantityType();
    }

    @Override
    public TemperatureQuantityType getPhysicalQuantityType() {
        return temperatureQuantityType;
    }

    @Override
    public TemperatureScale getScale() {
        return temperatureScale;
    }

    @Override
    public double getValue() {
        return value;
    }
}