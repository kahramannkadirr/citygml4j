package org.citygml4j.model.core;

import org.citygml4j.model.common.GeometryInfo;
import org.citygml4j.visitor.ObjectVisitor;
import org.xmlobjects.gml.model.geometry.Envelope;
import org.xmlobjects.gml.model.geometry.aggregates.MultiPointProperty;
import org.xmlobjects.gml.util.EnvelopeOptions;
import org.xmlobjects.model.ChildList;

import java.util.List;

public class Address extends AbstractFeature {
    private XALAddressProperty xalAddress;
    private MultiPointProperty multiPoint;
    private List<ADEPropertyOfAddress<?>> adeProperties;

    public Address() {
    }

    public Address(XALAddressProperty xalAddress) {
        setXALAddress(xalAddress);
    }

    public XALAddressProperty getXALAddress() {
        return xalAddress;
    }

    public void setXALAddress(XALAddressProperty xalAddress) {
        this.xalAddress = asChild(xalAddress);
    }

    public MultiPointProperty getMultiPoint() {
        return multiPoint;
    }

    public void setMultiPoint(MultiPointProperty multiPoint) {
        this.multiPoint = asChild(multiPoint);
    }

    public List<ADEPropertyOfAddress<?>> getADEPropertiesOfAddress() {
        if (adeProperties == null)
            adeProperties = new ChildList<>(this);

        return adeProperties;
    }

    public void setADEPropertiesOfAddress(List<ADEPropertyOfAddress<?>> adeProperties) {
        this.adeProperties = asChild(adeProperties);
    }

    @Override
    protected void updateEnvelope(Envelope envelope, EnvelopeOptions options) {
        if (multiPoint != null && multiPoint.getObject() != null)
            envelope.include(multiPoint.getObject().computeEnvelope());

        if (adeProperties != null) {
            for (ADEPropertyOfAddress<?> property : adeProperties)
                updateEnvelope(property, envelope, options);
        }
    }

    @Override
    protected void updateGeometryInfo(GeometryInfo geometryInfo) {
        super.updateGeometryInfo(geometryInfo);

        geometryInfo.addGeometry(multiPoint);

        if (adeProperties != null) {
            for (ADEPropertyOfAddress<?> property : adeProperties)
                updateGeometryInfo(property, geometryInfo);
        }
    }

    @Override
    public void accept(ObjectVisitor visitor) {
        visitor.visit(this);
    }
}
