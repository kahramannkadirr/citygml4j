/*
 * citygml4j - The Open Source Java API for CityGML
 * https://github.com/citygml4j
 *
 * Copyright 2013-2020 Claus Nagel <claus.nagel@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.citygml4j.model.waterbody;

import org.citygml4j.model.common.GeometryInfo;
import org.citygml4j.model.common.TopLevelFeature;
import org.citygml4j.model.core.AbstractOccupiedSpace;
import org.citygml4j.model.core.AbstractSpaceBoundary;
import org.citygml4j.model.core.ClosureSurface;
import org.citygml4j.model.core.StandardObjectClassifier;
import org.citygml4j.model.deprecated.waterbody.DeprecatedPropertiesOfWaterBody;
import org.citygml4j.model.generics.GenericThematicSurface;
import org.citygml4j.visitor.ObjectVisitor;
import org.xmlobjects.gml.model.basictypes.Code;
import org.xmlobjects.gml.model.geometry.Envelope;
import org.xmlobjects.gml.util.EnvelopeOptions;
import org.xmlobjects.model.ChildList;

import java.util.List;

public class WaterBody extends AbstractOccupiedSpace implements TopLevelFeature, StandardObjectClassifier {
    private Code classifier;
    private List<Code> functions;
    private List<Code> usages;
    private List<ADEOfWaterBody> adeOfWaterBody;

    @Override
    public boolean isValidBoundary(AbstractSpaceBoundary boundary) {
        return boundary instanceof AbstractWaterBoundarySurface
                || boundary instanceof ClosureSurface
                || boundary instanceof GenericThematicSurface;
    }

    @Override
    public Code getClassifier() {
        return classifier;
    }

    @Override
    public void setClassifier(Code classifier) {
        this.classifier = asChild(classifier);
    }

    @Override
    public List<Code> getFunctions() {
        if (functions == null)
            functions = new ChildList<>(this);

        return functions;
    }

    @Override
    public void setFunctions(List<Code> functions) {
        this.functions = asChild(functions);
    }

    @Override
    public List<Code> getUsages() {
        if (usages == null)
            usages = new ChildList<>(this);

        return usages;
    }

    @Override
    public void setUsages(List<Code> usages) {
        this.usages = asChild(usages);
    }

    @Override
    public DeprecatedPropertiesOfWaterBody getDeprecatedProperties() {
        return (DeprecatedPropertiesOfWaterBody) super.getDeprecatedProperties();
    }

    @Override
    protected DeprecatedPropertiesOfWaterBody createDeprecatedProperties() {
        return new DeprecatedPropertiesOfWaterBody();
    }

    public List<ADEOfWaterBody> getADEOfWaterBody() {
        if (adeOfWaterBody == null)
            adeOfWaterBody = new ChildList<>(this);

        return adeOfWaterBody;
    }

    public void setADEOfWaterBody(List<ADEOfWaterBody> adeOfWaterBody) {
        this.adeOfWaterBody = asChild(adeOfWaterBody);
    }

    @Override
    protected void updateEnvelope(Envelope envelope, EnvelopeOptions options) {
        super.updateEnvelope(envelope, options);

        if (hasDeprecatedProperties()) {
            DeprecatedPropertiesOfWaterBody properties = getDeprecatedProperties();

            if (properties.getLod1MultiCurve() != null && properties.getLod1MultiCurve().getObject() != null)
                envelope.include(properties.getLod1MultiCurve().getObject().computeEnvelope());

            if (properties.getLod1MultiSurface() != null && properties.getLod1MultiSurface().getObject() != null)
                envelope.include(properties.getLod1MultiSurface().getObject().computeEnvelope());

            if (properties.getLod4Solid() != null && properties.getLod4Solid().getObject() != null)
                envelope.include(properties.getLod4Solid().getObject().computeEnvelope());
        }

        if (adeOfWaterBody != null) {
            for (ADEOfWaterBody container : adeOfWaterBody)
                updateEnvelope(container, envelope, options);
        }
    }

    @Override
    protected void updateGeometryInfo(GeometryInfo geometryInfo) {
        super.updateGeometryInfo(geometryInfo);

        if (hasDeprecatedProperties()) {
            DeprecatedPropertiesOfWaterBody properties = getDeprecatedProperties();

            geometryInfo.addGeometry(1, properties.getLod1MultiCurve());
            geometryInfo.addGeometry(1, properties.getLod1MultiSurface());
            geometryInfo.addGeometry(4, properties.getLod4Solid());
        }

        if (adeOfWaterBody != null) {
            for (ADEOfWaterBody container : adeOfWaterBody)
                updateGeometryInfo(container, geometryInfo);
        }
    }

    @Override
    public void accept(ObjectVisitor visitor) {
        visitor.visit(this);
    }
}
