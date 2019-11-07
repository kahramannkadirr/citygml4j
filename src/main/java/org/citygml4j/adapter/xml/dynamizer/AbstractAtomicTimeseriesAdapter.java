package org.citygml4j.adapter.xml.dynamizer;

import org.citygml4j.adapter.xml.CityGMLBuilderHelper;
import org.citygml4j.model.ade.generic.GenericADEPropertyOfAbstractAtomicTimeseries;
import org.citygml4j.model.dynamizer.ADEPropertyOfAbstractAtomicTimeseries;
import org.citygml4j.model.dynamizer.AbstractAtomicTimeseries;
import org.citygml4j.util.CityGMLConstants;
import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public abstract class AbstractAtomicTimeseriesAdapter<T extends AbstractAtomicTimeseries> extends AbstractTimeseriesAdapter<T> {
    private final QName substitutionGroup = new QName(CityGMLConstants.CITYGML_3_0_DYNAMIZER_NAMESPACE, "AbstractGenericApplicationPropertyOfAbstractAtomicTimeseries");

    @Override
    public void buildChildObject(T object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (CityGMLConstants.CITYGML_3_0_DYNAMIZER_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "observationProperty":
                    reader.getTextContent().ifPresent(object::setObservationProperty);
                    return;
                case "uom":
                    reader.getTextContent().ifPresent(object::setUom);
                    return;
            }
        }

        if (CityGMLBuilderHelper.isADENamespace(name.getNamespaceURI())) {
            ObjectBuilder<ADEPropertyOfAbstractAtomicTimeseries> builder = reader.getXMLObjects().getBuilder(name, ADEPropertyOfAbstractAtomicTimeseries.class);
            if (builder != null)
                object.getADEPropertiesOfAbstractAtomicTimeseries().add(reader.getObjectUsingBuilder(builder));
            else if (CityGMLBuilderHelper.createAsGenericADEProperty(name, reader, substitutionGroup))
                object.getADEPropertiesOfAbstractAtomicTimeseries().add(GenericADEPropertyOfAbstractAtomicTimeseries.of(reader.getDOMElement()));
        } else
            super.buildChildObject(object, name, attributes, reader);
    }

    @Override
    public void writeChildElements(T object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.writeChildElements(object, namespaces, writer);
        String dynamizerNamespace = CityGMLConstants.CITYGML_3_0_DYNAMIZER_NAMESPACE;

        if (object.getObservationProperty() != null)
            writer.writeElement(Element.of(dynamizerNamespace, "observationProperty").addTextContent(object.getObservationProperty()));

        if (object.getUom() != null)
            writer.writeElement(Element.of(dynamizerNamespace, "uom").addTextContent(object.getUom()));
    }
}
