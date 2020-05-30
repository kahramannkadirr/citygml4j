package org.citygml4j.model.ade.generic;

import org.citygml4j.model.construction.ADEOfOtherConstruction;
import org.w3c.dom.Element;

public class GenericADEOfOtherConstruction extends ADEOfOtherConstruction implements ADEGenericPropertyContainer {
    private Element value;

    public GenericADEOfOtherConstruction() {
    }

    public GenericADEOfOtherConstruction(Element value) {
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
