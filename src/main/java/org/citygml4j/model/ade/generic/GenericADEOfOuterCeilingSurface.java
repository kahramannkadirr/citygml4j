package org.citygml4j.model.ade.generic;

import org.citygml4j.model.construction.ADEOfOuterCeilingSurface;
import org.w3c.dom.Element;

public class GenericADEOfOuterCeilingSurface extends ADEOfOuterCeilingSurface implements ADEGenericPropertyContainer {
    private Element value;

    public GenericADEOfOuterCeilingSurface() {
    }

    public GenericADEOfOuterCeilingSurface(Element value) {
        this.value = value;
    }

    @Override
    public Element getValue() {
        return value;
    }

    @Override
    public void setValue(Element value) {
        this.value = value;
    }
}
