package org.citygml4j.adapter.xml.deprecated.building;

import org.citygml4j.adapter.xml.CityGMLBuilderHelper;
import org.citygml4j.adapter.xml.CityGMLSerializerHelper;
import org.citygml4j.adapter.xml.core.AbstractCityObjectAdapter;
import org.citygml4j.adapter.xml.core.ImplicitGeometryPropertyAdapter;
import org.citygml4j.model.ade.generic.GenericADEPropertyOfAbstractFillingSurface;
import org.citygml4j.model.construction.ADEPropertyOfAbstractFillingSurface;
import org.citygml4j.model.construction.AbstractFillingSurface;
import org.citygml4j.model.core.ImplicitGeometryProperty;
import org.citygml4j.model.deprecated.DeprecatedProperties;
import org.citygml4j.util.CityGMLConstants;
import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.gml.adapter.geometry.aggregates.MultiSurfacePropertyAdapter;
import org.xmlobjects.gml.model.geometry.aggregates.MultiSurfaceProperty;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public abstract class AbstractOpeningAdapter<T extends AbstractFillingSurface> extends AbstractCityObjectAdapter<T> {
    private final QName[] substitutionGroups = new QName[] {
            new QName(CityGMLConstants.CITYGML_2_0_BUILDING_NAMESPACE, "_GenericApplicationPropertyOfOpening"),
            new QName(CityGMLConstants.CITYGML_1_0_BUILDING_NAMESPACE, "_GenericApplicationPropertyOfOpening"),
    };

    @Override
    public void buildChildObject(T object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (CityGMLBuilderHelper.isCityGMLBuildingNamespace(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "lod3MultiSurface":
                    object.setLod3MultiSurface(reader.getObjectUsingBuilder(MultiSurfacePropertyAdapter.class));
                    return;
                case "lod4MultiSurface":
                    object.getLocalProperties().set(DeprecatedProperties.LOD4_MULTI_SURFACE, reader.getObjectUsingBuilder(MultiSurfacePropertyAdapter.class));
                    return;
                case "lod3ImplicitRepresentation":
                    object.getLocalProperties().set(DeprecatedProperties.LOD3_IMPLICIT_REPRESENTATION, reader.getObjectUsingBuilder(ImplicitGeometryPropertyAdapter.class));
                    return;
                case "lod4ImplicitRepresentation":
                    object.getLocalProperties().set(DeprecatedProperties.LOD4_IMPLICIT_REPRESENTATION, reader.getObjectUsingBuilder(ImplicitGeometryPropertyAdapter.class));
                    return;
            }
        }

        if (CityGMLBuilderHelper.isADENamespace(name.getNamespaceURI())) {
            ObjectBuilder<ADEPropertyOfAbstractFillingSurface> builder = reader.getXMLObjects().getBuilder(name, ADEPropertyOfAbstractFillingSurface.class);
            if (builder != null)
                object.getADEPropertiesOfAbstractFillingSurface().add(reader.getObjectUsingBuilder(builder));
            else if (CityGMLBuilderHelper.createAsGenericADEProperty(name, reader, substitutionGroups))
                object.getADEPropertiesOfAbstractFillingSurface().add(GenericADEPropertyOfAbstractFillingSurface.of(reader.getDOMElement()));
        } else
            super.buildChildObject(object, name, attributes, reader);
    }

    @Override
    public void writeChildElements(T object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.writeChildElements(object, namespaces, writer);
        String buildingNamespace = CityGMLSerializerHelper.getBuildingNamespace(namespaces);

        if (object.getLod3MultiSurface() != null)
            writer.writeElementUsingSerializer(Element.of(buildingNamespace, "lod3MultiSurface"), object.getLod3MultiSurface(), MultiSurfacePropertyAdapter.class, namespaces);

        if (object.getLocalProperties().contains(DeprecatedProperties.LOD4_MULTI_SURFACE)) {
            MultiSurfaceProperty property = object.getLocalProperties().get(DeprecatedProperties.LOD4_MULTI_SURFACE, MultiSurfaceProperty.class);
            writer.writeElementUsingSerializer(Element.of(buildingNamespace, "lod4MultiSurface"), property, MultiSurfacePropertyAdapter.class, namespaces);
        }

        if (object.getLocalProperties().contains(DeprecatedProperties.LOD3_IMPLICIT_REPRESENTATION)) {
            ImplicitGeometryProperty property = object.getLocalProperties().get(DeprecatedProperties.LOD3_IMPLICIT_REPRESENTATION, ImplicitGeometryProperty.class);
            writer.writeElementUsingSerializer(Element.of(buildingNamespace, "lod3ImplicitRepresentation"), property, ImplicitGeometryPropertyAdapter.class, namespaces);
        }

        if (object.getLocalProperties().contains(DeprecatedProperties.LOD4_IMPLICIT_REPRESENTATION)) {
            ImplicitGeometryProperty property = object.getLocalProperties().get(DeprecatedProperties.LOD4_IMPLICIT_REPRESENTATION, ImplicitGeometryProperty.class);
            writer.writeElementUsingSerializer(Element.of(buildingNamespace, "lod4ImplicitRepresentation"), property, ImplicitGeometryPropertyAdapter.class, namespaces);
        }

        for (ADEPropertyOfAbstractFillingSurface property : object.getADEPropertiesOfAbstractFillingSurface())
            CityGMLSerializerHelper.serializeADEProperty(property, namespaces, writer);
    }
}