package org.citygml4j.adapter.xml.dynamizer;

import org.citygml4j.model.dynamizer.TimeValuePairProperty;
import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.gml.adapter.base.AbstractInlinePropertyAdapter;

import javax.xml.namespace.QName;

public class TimeValuePairPropertyAdapter extends AbstractInlinePropertyAdapter<TimeValuePairProperty> {

    @Override
    public TimeValuePairProperty createObject(QName name) throws ObjectBuildException {
        return new TimeValuePairProperty();
    }
}
