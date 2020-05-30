package org.citygml4j.model.ade.generic;

import org.citygml4j.model.bridge.ADEOfBridgePart;
import org.w3c.dom.Element;

public class GenericADEOfBridgePart extends ADEOfBridgePart implements ADEGenericPropertyContainer {
    private Element value;

    public GenericADEOfBridgePart() {
    }

    public GenericADEOfBridgePart(Element value) {
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
