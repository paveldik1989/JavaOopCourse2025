package ru.academits.dik.model.temperature;

import ru.academits.dik.model.general.Scale;

public class DefaultTemperatureConverter implements TemperatureConverter {
    @Override
    public double convert(Scale scaleFrom, Scale scaleTo, double temperature) {
        return scaleTo.convertFromDefaultScale(scaleFrom.convertToDefaultScale(temperature));
    }
}