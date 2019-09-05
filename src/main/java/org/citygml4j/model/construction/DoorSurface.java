package org.citygml4j.model.construction;

import org.citygml4j.model.core.AddressProperty;
import org.xmlobjects.gml.model.common.ChildList;

import java.util.List;

public class DoorSurface extends AbstractFillingSurface {
    private List<AddressProperty> addresses;
    private List<ADEPropertyOfDoorSurface> adeProperties;

    public List<AddressProperty> getAddresses() {
        if (addresses == null)
            addresses = new ChildList<>(this);

        return addresses;
    }

    public void setAddresses(List<AddressProperty> addresses) {
        this.addresses = asChild(addresses);
    }

    public List<ADEPropertyOfDoorSurface> getADEPropertiesOfDoorSurface() {
        if (adeProperties == null)
            adeProperties = new ChildList<>(this);

        return adeProperties;
    }

    public void setADEPropertiesOfDoorSurface(List<ADEPropertyOfDoorSurface> adeProperties) {
        this.adeProperties = asChild(adeProperties);
    }
}