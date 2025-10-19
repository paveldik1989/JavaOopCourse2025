package ru.academits.temperature.dik.model.physical_quantity;

public interface Converter<T extends PhysicalQuantityType,  S extends Scale<T>, U extends PhysicalQuantity<T,S>> {
    U convert(U quantity, S scaleTo);
}