package ru.academits.dik.model.temperature;

public class KelvinScale implements TemperatureScale {
    @Override
    public double convertToDefaultScale(double kelvinTemperature) {
        return kelvinTemperature - 273.15;
    }

    @Override
    public double convertFromDefaultScale(double celsiusTemperature) {
        return celsiusTemperature + 273.15;
    }
}