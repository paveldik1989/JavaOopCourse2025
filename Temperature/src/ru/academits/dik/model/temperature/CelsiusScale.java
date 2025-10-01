package ru.academits.dik.model.temperature;

public class CelsiusScale implements TemperatureScale {

    @Override
    public double convertToDefaultScale(double scaleTemperature) {
        return scaleTemperature;
    }

    @Override
    public double convertFromDefaultScale(double celsiusTemperature) {
        return celsiusTemperature;
    }
}