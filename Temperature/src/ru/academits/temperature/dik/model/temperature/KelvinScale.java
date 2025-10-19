package ru.academits.temperature.dik.model.temperature;

public class KelvinScale implements TemperatureScale  {
    @Override
    public TemperatureQuantityType getPhysicalQuantityType() {
        return new TemperatureQuantityTypeImp();
    }

    @Override
    public double toBaseUnit(double kelvinTemperature) {
        return kelvinTemperature - 273.15;
    }

    @Override
    public double fromBaseUnit(double celsiusTemperature) {
        return celsiusTemperature + 273.15;
    }

    @Override
    public String toString() {
        return "Кельвины";
    }
}