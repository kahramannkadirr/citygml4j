package org.citygml4j.adapter.xml.dynamizer;

import org.citygml4j.model.dynamizer.AbstractTimeseriesProperty;
import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.gml.adapter.feature.AbstractFeaturePropertyAdapter;

import javax.xml.namespace.QName;

public class AbstractTimeseriesPropertyAdapter extends AbstractFeaturePropertyAdapter<AbstractTimeseriesProperty> {

    @Override
    public AbstractTimeseriesProperty createObject(QName name) throws ObjectBuildException {
        return new AbstractTimeseriesProperty();
    }
}
