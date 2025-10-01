package ru.academits.main;

import ru.academits.dik.model.temperature.CelsiusScale;
import ru.academits.dik.model.temperature.DefaultTemperatureConverter;
import ru.academits.dik.model.temperature.FahrenheitScale;
import ru.academits.dik.model.temperature.KelvinScale;

public class Test {
    public static void main(String[] args) {
        CelsiusScale celsiusScale = new CelsiusScale();
        KelvinScale kelvinScale = new KelvinScale();
        FahrenheitScale fahrenheitScale = new FahrenheitScale();

        DefaultTemperatureConverter temperatureConverter = new DefaultTemperatureConverter();

        double temperature = 100;

        System.out.println("From Celsius " + temperature);
        System.out.println(temperatureConverter.convert(celsiusScale,celsiusScale,temperature));
        System.out.println(temperatureConverter.convert(celsiusScale,kelvinScale,temperature));
        System.out.println(temperatureConverter.convert(celsiusScale,fahrenheitScale,temperature));
        System.out.println();

        System.out.println("From Kelvin " + temperature);
        System.out.println(temperatureConverter.convert(kelvinScale,celsiusScale,temperature));
        System.out.println(temperatureConverter.convert(kelvinScale,kelvinScale,temperature));
        System.out.println(temperatureConverter.convert(kelvinScale,fahrenheitScale,temperature));
        System.out.println();

        System.out.println("From Fahrenheit " + temperature);
        System.out.println(temperatureConverter.convert(fahrenheitScale,celsiusScale,temperature));
        System.out.println(temperatureConverter.convert(fahrenheitScale,kelvinScale,temperature));
        System.out.println(temperatureConverter.convert(fahrenheitScale,fahrenheitScale,temperature));
    }
}