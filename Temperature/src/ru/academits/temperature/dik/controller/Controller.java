package ru.academits.temperature.dik.controller;

import ru.academits.temperature.dik.model.temperature.TemperatureScale;

public interface Controller {
    void setInputTemperatureScale(TemperatureScale inputTemperatureScale);

    void setInputTemperature(double inputTemperature);

    void setOutputTemperatureScale(TemperatureScale outputTemperatureScale);

    double getOutputTemperature();
}