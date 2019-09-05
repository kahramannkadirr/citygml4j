package org.citygml4j.adapter.xml.core;

import org.citygml4j.adapter.xml.CityGMLBuilderHelper;
import org.citygml4j.adapter.xml.CityGMLSerializerHelper;
import org.citygml4j.model.ade.generic.GenericADEPropertyOfAbstractUnoccupiedSpace;
import org.citygml4j.model.core.ADEPropertyOfAbstractUnoccupiedSpace;
import org.citygml4j.model.core.AbstractUnoccupiedSpace;
import org.citygml4j.util.CityGMLConstants;
import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public abstract class AbstractUnoccupiedSpaceAdapter<T extends AbstractUnoccupiedSpace> extends AbstractPhysicalSpaceAdapter<T> {
    private final QName substitutionGroup = new QName(CityGMLConstants.CITYGML_3_0_CORE_NAMESPACE, "AbstractGenericApplicationPropertyOfAbstractUnoccupiedSpace");

    @Override
    public void buildChildObject(T object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (CityGMLBuilderHelper.isADENamespace(name.getNamespaceURI())) {
            ObjectBuilder<ADEPropertyOfAbstractUnoccupiedSpace> builder = reader.getXMLObjects().getBuilder(name, ADEPropertyOfAbstractUnoccupiedSpace.class);
            if (builder != null)
                object.getADEPropertiesOfAbstractUnoccupiedSpace().add(reader.getObjectUsingBuilder(builder));
            else if (CityGMLBuilderHelper.createAsGenericADEProperty(name, reader, substitutionGroup))
                object.getADEPropertiesOfAbstractUnoccupiedSpace().add(GenericADEPropertyOfAbstractUnoccupiedSpace.of(reader.getDOMElement()));
        } else
            super.buildChildObject(object, name, attributes, reader);
    }

    @Override
    public void writeChildElements(T object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.writeChildElements(object, namespaces, writer);
        String coreNamespace = CityGMLSerializerHelper.getCoreNamespace(namespaces);

        if (CityGMLConstants.CITYGML_3_0_CORE_NAMESPACE.equals(coreNamespace)) {
            for (ADEPropertyOfAbstractUnoccupiedSpace property : object.getADEPropertiesOfAbstractUnoccupiedSpace())
                CityGMLSerializerHelper.serializeADEProperty(property, namespaces, writer);
        }
    }
}