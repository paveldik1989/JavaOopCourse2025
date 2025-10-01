package ru.academits.dik.model.temperature;

public class FahrenheitScale implements TemperatureScale {
    @Override
    public double convertToDefaultScale(double scaleValue) {
        return (scaleValue - 32) * 5 / 9;
    }

    @Override
    public double convertFromDefaultScale(double defaultScaleValue) {
        return defaultScaleValue * 9 / 5 + 32;
    }
}
