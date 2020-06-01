package org.citygml4j.xml.adapter.vegetation;

import org.citygml4j.model.ade.generic.GenericADEOfPlantCover;
import org.citygml4j.model.vegetation.ADEOfPlantCover;
import org.citygml4j.model.vegetation.PlantCover;
import org.citygml4j.util.CityGMLConstants;
import org.citygml4j.xml.adapter.CityGMLBuilderHelper;
import org.citygml4j.xml.adapter.CityGMLSerializerHelper;
import org.citygml4j.xml.adapter.ade.ADEBuilderHelper;
import org.citygml4j.xml.adapter.ade.ADESerializerHelper;
import org.xmlobjects.annotation.XMLElement;
import org.xmlobjects.annotation.XMLElements;
import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.gml.adapter.geometry.aggregates.MultiSolidPropertyAdapter;
import org.xmlobjects.gml.adapter.geometry.aggregates.MultiSurfacePropertyAdapter;
import org.xmlobjects.gml.adapter.measures.LengthAdapter;
import org.xmlobjects.gml.model.geometry.aggregates.MultiSolid;
import org.xmlobjects.gml.model.geometry.aggregates.MultiSolidProperty;
import org.xmlobjects.gml.model.geometry.primitives.SolidProperty;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

@XMLElements({
        @XMLElement(name = "PlantCover", namespaceURI = CityGMLConstants.CITYGML_3_0_VEGETATION_NAMESPACE),
        @XMLElement(name = "PlantCover", namespaceURI = CityGMLConstants.CITYGML_2_0_VEGETATION_NAMESPACE),
        @XMLElement(name = "PlantCover", namespaceURI = CityGMLConstants.CITYGML_1_0_VEGETATION_NAMESPACE)
})
public class PlantCoverAdapter extends AbstractVegetationObjectAdapter<PlantCover> {
    private final QName[] substitutionGroups = new QName[]{
            new QName(CityGMLConstants.CITYGML_2_0_VEGETATION_NAMESPACE, "_GenericApplicationPropertyOfPlantCover"),
            new QName(CityGMLConstants.CITYGML_1_0_VEGETATION_NAMESPACE, "_GenericApplicationPropertyOfPlantCover")
    };

    @Override
    public PlantCover createObject(QName name) throws ObjectBuildException {
        return new PlantCover();
    }

    @Override
    public void buildChildObject(PlantCover object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (CityGMLBuilderHelper.isVegetationNamespace(name.getNamespaceURI())) {
            if (CityGMLBuilderHelper.buildStandardObjectClassifier(object, name.getLocalPart(), reader))
                return;

            switch (name.getLocalPart()) {
                case "averageHeight":
                    object.setAverageHeight(reader.getObjectUsingBuilder(LengthAdapter.class));
                    return;
                case "minHeight":
                    object.setMinHeight(reader.getObjectUsingBuilder(LengthAdapter.class));
                    return;
                case "maxHeight":
                    object.setMaxHeight(reader.getObjectUsingBuilder(LengthAdapter.class));
                    return;
                case "lod1MultiSurface":
                    object.getDeprecatedProperties().setLod1MultiSurface(reader.getObjectUsingBuilder(MultiSurfacePropertyAdapter.class));
                    return;
                case "lod2MultiSurface":
                    object.setLod2MultiSurface(reader.getObjectUsingBuilder(MultiSurfacePropertyAdapter.class));
                    return;
                case "lod3MultiSurface":
                    object.setLod3MultiSurface(reader.getObjectUsingBuilder(MultiSurfacePropertyAdapter.class));
                    return;
                case "lod4MultiSurface":
                    object.getDeprecatedProperties().setLod4MultiSurface(reader.getObjectUsingBuilder(MultiSurfacePropertyAdapter.class));
                    return;
                case "lod1MultiSolid":
                    MultiSolidProperty lod1MultiSolid = reader.getObjectUsingBuilder(MultiSolidPropertyAdapter.class);
                    if (!CityGMLBuilderHelper.assignDefaultGeometry(object, 1, lod1MultiSolid))
                        object.getDeprecatedProperties().setLod1MultiSolid(lod1MultiSolid);
                    return;
                case "lod2MultiSolid":
                    MultiSolidProperty lod2MultiSolid = reader.getObjectUsingBuilder(MultiSolidPropertyAdapter.class);
                    if (!CityGMLBuilderHelper.assignDefaultGeometry(object, 2, lod2MultiSolid))
                        object.getDeprecatedProperties().setLod2MultiSolid(lod2MultiSolid);
                    return;
                case "lod3MultiSolid":
                    MultiSolidProperty lod3MultiSolid = reader.getObjectUsingBuilder(MultiSolidPropertyAdapter.class);
                    if (!CityGMLBuilderHelper.assignDefaultGeometry(object, 3, lod3MultiSolid))
                        object.getDeprecatedProperties().setLod3MultiSolid(lod3MultiSolid);
                    return;
                case "lod4MultiSolid":
                    object.getDeprecatedProperties().setLod4MultiSolid(reader.getObjectUsingBuilder(MultiSolidPropertyAdapter.class));
                    return;
                case "adeOfPlantCover":
                    ADEBuilderHelper.addADEContainer(ADEOfPlantCover.class, object.getADEOfPlantCover(), GenericADEOfPlantCover::new, reader);
                    return;
            }
        } else if (CityGMLBuilderHelper.isADENamespace(name.getNamespaceURI())) {
            buildADEProperty(object, name, reader);
            return;
        }

        super.buildChildObject(object, name, attributes, reader);
    }

    @Override
    public void buildADEProperty(PlantCover object, QName name, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (!ADEBuilderHelper.addADEContainer(name, ADEOfPlantCover.class, object.getADEOfPlantCover(),
                GenericADEOfPlantCover::new, reader, substitutionGroups))
            super.buildADEProperty(object, name, reader);
    }

    @Override
    public Element createElement(PlantCover object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(CityGMLSerializerHelper.getVegetationNamespace(namespaces), "PlantCover");
    }

    @Override
    public void writeChildElements(PlantCover object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.writeChildElements(object, namespaces, writer);
        String vegetationNamespace = CityGMLSerializerHelper.getVegetationNamespace(namespaces);
        boolean isCityGML3 = CityGMLConstants.CITYGML_3_0_VEGETATION_NAMESPACE.equals(vegetationNamespace);

        CityGMLSerializerHelper.writeStandardObjectClassifier(object, vegetationNamespace, namespaces, writer);

        if (object.getAverageHeight() != null)
            writer.writeElementUsingSerializer(Element.of(vegetationNamespace, "averageHeight"), object.getAverageHeight(), LengthAdapter.class, namespaces);

        if (isCityGML3) {
            if (object.getMinHeight() != null)
                writer.writeElementUsingSerializer(Element.of(vegetationNamespace, "minHeight"), object.getMinHeight(), LengthAdapter.class, namespaces);

            if (object.getMaxHeight() != null)
                writer.writeElementUsingSerializer(Element.of(vegetationNamespace, "maxHeight"), object.getMaxHeight(), LengthAdapter.class, namespaces);
        }

        if (!isCityGML3) {
            if (object.getDeprecatedProperties().getLod1MultiSurface() != null)
                writer.writeElementUsingSerializer(Element.of(vegetationNamespace, "lod1MultiSurface"), object.getDeprecatedProperties().getLod1MultiSurface(), MultiSurfacePropertyAdapter.class, namespaces);

            if (object.getLod2MultiSurface() != null)
                writer.writeElementUsingSerializer(Element.of(vegetationNamespace, "lod2MultiSurface"), object.getLod2MultiSurface(), MultiSurfacePropertyAdapter.class, namespaces);

            if (object.getLod3MultiSurface() != null)
                writer.writeElementUsingSerializer(Element.of(vegetationNamespace, "lod3MultiSurface"), object.getLod3MultiSurface(), MultiSurfacePropertyAdapter.class, namespaces);

            if (object.getDeprecatedProperties().getLod4MultiSurface() != null)
                writer.writeElementUsingSerializer(Element.of(vegetationNamespace, "lod4MultiSurface"), object.getDeprecatedProperties().getLod4MultiSurface(), MultiSurfacePropertyAdapter.class, namespaces);

            if (object.getDeprecatedProperties().getLod1MultiSolid() != null)
                writer.writeElementUsingSerializer(Element.of(vegetationNamespace, "lod1MultiSolid"), object.getDeprecatedProperties().getLod1MultiSolid(), MultiSolidPropertyAdapter.class, namespaces);
            else if (object.getLod1Solid() != null)
                writer.writeElementUsingSerializer(Element.of(vegetationNamespace, "lod1MultiSolid"), getMultiSolidProperty(object.getLod1Solid()), MultiSolidPropertyAdapter.class, namespaces);

            if (object.getDeprecatedProperties().getLod2MultiSolid() != null)
                writer.writeElementUsingSerializer(Element.of(vegetationNamespace, "lod2MultiSolid"), object.getDeprecatedProperties().getLod2MultiSolid(), MultiSolidPropertyAdapter.class, namespaces);
            else if (object.getLod2Solid() != null)
                writer.writeElementUsingSerializer(Element.of(vegetationNamespace, "lod2MultiSolid"), getMultiSolidProperty(object.getLod2Solid()), MultiSolidPropertyAdapter.class, namespaces);

            if (object.getDeprecatedProperties().getLod3MultiSolid() != null)
                writer.writeElementUsingSerializer(Element.of(vegetationNamespace, "lod3MultiSolid"), object.getDeprecatedProperties().getLod3MultiSolid(), MultiSolidPropertyAdapter.class, namespaces);
            else if (object.getLod3Solid() != null)
                writer.writeElementUsingSerializer(Element.of(vegetationNamespace, "lod3MultiSolid"), getMultiSolidProperty(object.getLod3Solid()), MultiSolidPropertyAdapter.class, namespaces);

            if (object.getDeprecatedProperties().getLod4MultiSolid() != null)
                writer.writeElementUsingSerializer(Element.of(vegetationNamespace, "lod4MultiSolid"), object.getDeprecatedProperties().getLod4MultiSolid(), MultiSolidPropertyAdapter.class, namespaces);
        }

        for (ADEOfPlantCover container : object.getADEOfPlantCover())
            ADESerializerHelper.writeADEContainer(isCityGML3 ? Element.of(vegetationNamespace, "adeOfPlantCover") : null, container, namespaces, writer);
    }

    private MultiSolidProperty getMultiSolidProperty(SolidProperty src) {
        MultiSolid multiSolid = new MultiSolid();
        multiSolid.getSolidMember().add(src);
        return new MultiSolidProperty(multiSolid);
    }
}
