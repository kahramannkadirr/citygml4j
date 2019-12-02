package org.citygml4j.xml.module.citygml;

import org.citygml4j.model.CityGMLVersion;
import org.citygml4j.util.CityGMLConstants;
import org.citygml4j.xml.module.AbstractModule;

public class CityFurnitureModule extends AbstractModule implements CityGMLModule {
    public static final CityFurnitureModule v3_0;
    public static final CityFurnitureModule v2_0;
    public static final CityFurnitureModule v1_0;

    static {
        v3_0 = new CityFurnitureModule(
                CityGMLConstants.CITYGML_3_0_CITYFURNITURE_NAMESPACE,
                "frn",
                "http://schemas.opengis.net/citygml/cityfurniture/3.0/cityFurniture.xsd"
        );

        v2_0 = new CityFurnitureModule(
                CityGMLConstants.CITYGML_2_0_CITYFURNITURE_NAMESPACE,
                "frn",
                "http://schemas.opengis.net/citygml/cityfurniture/2.0/cityFurniture.xsd"
        );

        v1_0 = new CityFurnitureModule(
                CityGMLConstants.CITYGML_1_0_CITYFURNITURE_NAMESPACE,
                "frn",
                "http://schemas.opengis.net/citygml/cityfurniture/1.0/cityFurniture.xsd"
        );
    }

    public static CityFurnitureModule of(CityGMLVersion version) {
        switch (version) {
            case v3_0:
                return v3_0;
            case v2_0:
                return v2_0;
            case v1_0:
                return v1_0;
            default:
                return null;
        }
    }

    private CityFurnitureModule(String namespaceURI, String namespacePrefix, String schemaLocation) {
        super(namespaceURI, namespacePrefix, schemaLocation);
    }
}