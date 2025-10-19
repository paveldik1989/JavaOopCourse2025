package ru.academits.temperature.dik.model.physical_quantity;

public interface PhysicalQuantity<
        T extends PhysicalQuantityType,
        S extends Scale<T>> {
    T getPhysicalQuantityType();

    S getScale();

    double getValue();
}