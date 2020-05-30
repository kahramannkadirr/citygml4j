package org.citygml4j.model.ade.generic;

import org.citygml4j.model.waterbody.ADEOfWaterSurface;
import org.w3c.dom.Element;

public class GenericADEOfWaterSurface extends ADEOfWaterSurface implements ADEGenericPropertyContainer {
    private Element value;

    public GenericADEOfWaterSurface() {
    }

    public GenericADEOfWaterSurface(Element value) {
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
