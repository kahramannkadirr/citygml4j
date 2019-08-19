package org.citygml4j.model.construction;

import org.citygml4j.model.core.AbstractOccupiedSpace;
import org.citygml4j.model.core.AbstractThematicSurface;
import org.xmlobjects.gml.model.common.ChildList;

import java.util.List;

public abstract class AbstractFurniture<T extends AbstractThematicSurface> extends AbstractOccupiedSpace<T> {
    private List<ADEPropertyOfAbstractFurniture> adeProperties;

    public List<ADEPropertyOfAbstractFurniture> getADEPropertyOfAbstractFurniture() {
        if (adeProperties == null)
            adeProperties = new ChildList<>(this);

        return adeProperties;
    }

    public void setADEPropertyOfAbstractFurniture(List<ADEPropertyOfAbstractFurniture> adeProperties) {
        this.adeProperties = asChild(adeProperties);
    }
}
