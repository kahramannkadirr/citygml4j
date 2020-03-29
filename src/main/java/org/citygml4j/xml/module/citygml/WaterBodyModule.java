package org.citygml4j.xml.module.citygml;

import org.citygml4j.model.CityGMLVersion;
import org.citygml4j.util.CityGMLConstants;

public class WaterBodyModule extends CityGMLModule {
    public static final WaterBodyModule v3_0;
    public static final WaterBodyModule v2_0;
    public static final WaterBodyModule v1_0;

    static {
        v3_0 = new WaterBodyModule(
                CityGMLConstants.CITYGML_3_0_WATERBODY_NAMESPACE,
                "wtr",
                "http://schemas.opengis.net/citygml/waterbody/3.0/waterBody.xsd",
                CityGMLVersion.v3_0
        );

        v2_0 = new WaterBodyModule(
                CityGMLConstants.CITYGML_2_0_WATERBODY_NAMESPACE,
                "wtr",
                "http://schemas.opengis.net/citygml/waterbody/2.0/waterBody.xsd",
                CityGMLVersion.v2_0
        );

        v1_0 = new WaterBodyModule(
                CityGMLConstants.CITYGML_1_0_WATERBODY_NAMESPACE,
                "wtr",
                "http://schemas.opengis.net/citygml/waterbody/1.0/waterBody.xsd",
                CityGMLVersion.v1_0
        );
    }

    public static WaterBodyModule of(CityGMLVersion version) {
        switch (version) {
            case v2_0:
                return v2_0;
            case v1_0:
                return v1_0;
            default:
                return v3_0;
        }
    }

    private WaterBodyModule(String namespaceURI, String namespacePrefix, String schemaLocation, CityGMLVersion version) {
        super(namespaceURI, namespacePrefix, schemaLocation, version);
    }
}
