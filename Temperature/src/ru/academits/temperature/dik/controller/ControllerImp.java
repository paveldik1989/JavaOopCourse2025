package ru.academits.temperature.dik.controller;

import ru.academits.temperature.dik.model.temperature.TemperatureConverter;
import ru.academits.temperature.dik.model.temperature.TemperatureQuantityImp;
import ru.academits.temperature.dik.model.temperature.TemperatureScale;
import ru.academits.temperature.dik.view.View;

public class ControllerImp implements Controller {
    private TemperatureScale outputTemperatureScale;
    private TemperatureScale inputTemperatureScale;
    double inputTemperature;

    private final TemperatureConverter temperatureConverter;

    public ControllerImp(TemperatureConverter temperatureConverter, View view, TemperatureScale[] temperatureScales) {
        this.temperatureConverter = temperatureConverter;
        inputTemperatureScale = temperatureScales[0];
        outputTemperatureScale = temperatureScales[0];

        view.setController(this);
        view.setTemperatureScales(temperatureScales);
        view.run();
    }

    @Override
    public void setInputTemperatureScale(TemperatureScale inputTemperatureScale) {
        this.inputTemperatureScale = inputTemperatureScale;
    }

    @Override
    public void setInputTemperature(double inputTemperature) {
        this.inputTemperature = inputTemperature;
    }

    @Override
    public void setOutputTemperatureScale(TemperatureScale outputTemperatureScale) {
        this.outputTemperatureScale = outputTemperatureScale;
    }

    @Override
    public double getOutputTemperature() {
        return temperatureConverter.convert(new TemperatureQuantityImp(inputTemperatureScale, inputTemperature), outputTemperatureScale).getValue();
    }
}