package ru.academits.temperature.dik.model.temperature;

public class TemperatureConverterImp implements TemperatureConverter {

    @Override
    public TemperatureQuantity convert(TemperatureQuantity quantity, TemperatureScale scaleTo) {
        TemperatureScale scaleFrom = quantity.getScale();
        double valueFrom = quantity.getValue();
        double valueTo = scaleTo.fromBaseUnit(scaleFrom.toBaseUnit(valueFrom));

        return new TemperatureQuantityImp(scaleTo, valueTo);
    }
}