package org.citygml4j.model.ade;

import org.xmlobjects.gml.model.GMLObject;
import org.xmlobjects.model.Child;

public abstract class ADEProperty<T> extends GMLObject implements ADEObject {
    private T value;

    public ADEProperty() {
    }

    public ADEProperty(T value) {
        setValue(value);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        if (value instanceof Child)
            ((Child) value).setParent(this);

        this.value = value;
    }
}