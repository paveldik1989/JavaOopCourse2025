package ru.academits.temperature.dik.main;

import ru.academits.temperature.dik.controller.Controller;
import ru.academits.temperature.dik.controller.ControllerImp;
import ru.academits.temperature.dik.model.temperature.*;
import ru.academits.temperature.dik.view.DesktopView;
import ru.academits.temperature.dik.view.View;

public class Main {
    public static void main(String[] args) {
        TemperatureScale[] temperatureScales = {new CelsiusScale(), new KelvinScale(), new FahrenheitScale()};

        TemperatureConverter temperatureConverter = new TemperatureConverterImp();
        View view = new DesktopView();
        Controller controller = new ControllerImp(temperatureConverter, view, temperatureScales);
    }
}