package ru.academits.temperature.dik.view;

import ru.academits.temperature.dik.controller.Controller;
import ru.academits.temperature.dik.model.temperature.TemperatureScale;

public interface View {
     void setTemperatureScales(TemperatureScale[] temperatureScales);

     void setController(Controller controller);

     void run();
}