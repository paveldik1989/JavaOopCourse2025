package ru.academits.temperature.dik.model.temperature;

public class FahrenheitScale implements TemperatureScale  {
    @Override
    public TemperatureQuantityType getPhysicalQuantityType() {
        return new TemperatureQuantityTypeImp();
    }

    @Override
    public double toBaseUnit(double scaleValue) {
        return (scaleValue - 32) * 5 / 9;
    }

    @Override
    public double fromBaseUnit(double defaultScaleValue) {
        return defaultScaleValue * 9 / 5 + 32;
    }

    @Override
    public String toString() {
        return "Фаренгейты";
    }
}