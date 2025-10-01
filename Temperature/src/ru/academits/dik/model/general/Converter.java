package ru.academits.dik.model.general;

public interface Converter {
    double convert(Scale scaleFrom, Scale scaleTo, double valueFrom);
}