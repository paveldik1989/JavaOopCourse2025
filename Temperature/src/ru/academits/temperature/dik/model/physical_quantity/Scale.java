package ru.academits.temperature.dik.model.physical_quantity;

public interface Scale<T extends PhysicalQuantityType> {
    T getPhysicalQuantityType();

    double toBaseUnit(double value);

    double fromBaseUnit(double physicalQuantity);
}