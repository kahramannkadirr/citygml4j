package org.citygml4j.model.construction;

import org.citygml4j.model.ade.ADEProperty;

public abstract class ADEPropertyOfAbstractConstruction<T> extends ADEProperty<T> {

    public ADEPropertyOfAbstractConstruction() {
    }

    public ADEPropertyOfAbstractConstruction(T value) {
        super(value);
    }
}