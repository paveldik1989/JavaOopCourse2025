package ru.academits.temperature.dik.model.temperature;

public class CelsiusScale implements TemperatureScale {
    @Override
    public TemperatureQuantityType getPhysicalQuantityType() {
        return new TemperatureQuantityTypeImp();
    }

    @Override
    public double toBaseUnit(double scaleValue) {
        return scaleValue;
    }

    @Override
    public double fromBaseUnit(double defaultScaleValue) {
        return defaultScaleValue;
    }

    @Override
    public String toString() {
        return "Цельсии";
    }
}