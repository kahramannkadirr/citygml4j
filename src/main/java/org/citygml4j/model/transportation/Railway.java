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

package org.citygml4j.model.transportation;

import org.citygml4j.model.common.GeometryInfo;
import org.citygml4j.model.common.TopLevelFeature;
import org.citygml4j.model.core.StandardObjectClassifier;
import org.citygml4j.visitor.ObjectVisitor;
import org.xmlobjects.gml.model.basictypes.Code;
import org.xmlobjects.gml.model.geometry.Envelope;
import org.xmlobjects.gml.util.EnvelopeOptions;
import org.xmlobjects.model.ChildList;

import java.util.List;

public class Railway extends AbstractTransportationSpace implements TopLevelFeature, StandardObjectClassifier {
    private Code classifier;
    private List<Code> functions;
    private List<Code> usages;
    private List<SectionProperty> sections;
    private List<IntersectionProperty> intersections;
    private List<ADEOfRailway> adeOfRailway;

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

    public List<SectionProperty> getSections() {
        if (sections == null)
            sections = new ChildList<>(this);

        return sections;
    }

    public void setSections(List<SectionProperty> sections) {
        this.sections = asChild(sections);
    }

    public List<IntersectionProperty> getIntersections() {
        if (intersections == null)
            intersections = new ChildList<>(this);

        return intersections;
    }

    public void setIntersections(List<IntersectionProperty> intersections) {
        this.intersections = asChild(intersections);
    }

    public List<ADEOfRailway> getADEOfRailway() {
        if (adeOfRailway == null)
            adeOfRailway = new ChildList<>(this);

        return adeOfRailway;
    }

    public void setADEOfRailway(List<ADEOfRailway> adeOfRailway) {
        this.adeOfRailway = asChild(adeOfRailway);
    }

    @Override
    protected void updateEnvelope(Envelope envelope, EnvelopeOptions options) {
        super.updateEnvelope(envelope, options);

        if (sections != null) {
            for (SectionProperty property : sections) {
                if (property.getObject() != null)
                    envelope.include(property.getObject().computeEnvelope(options));
            }
        }

        if (intersections != null) {
            for (IntersectionProperty property : intersections) {
                if (property.getObject() != null)
                    envelope.include(property.getObject().computeEnvelope(options));
            }
        }

        if (adeOfRailway != null) {
            for (ADEOfRailway container : adeOfRailway)
                updateEnvelope(container, envelope, options);
        }
    }

    @Override
    protected void updateGeometryInfo(GeometryInfo geometryInfo) {
        super.updateGeometryInfo(geometryInfo);

        if (adeOfRailway != null) {
            for (ADEOfRailway container : adeOfRailway)
                updateGeometryInfo(container, geometryInfo);
        }
    }

    @Override
    public void accept(ObjectVisitor visitor) {
        visitor.visit(this);
    }
}
