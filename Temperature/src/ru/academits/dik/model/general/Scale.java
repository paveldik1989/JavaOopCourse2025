package ru.academits.dik.model.general;

public interface Scale {
    double convertToDefaultScale(double scaleValue);

    double convertFromDefaultScale(double defaultScaleValue );
}