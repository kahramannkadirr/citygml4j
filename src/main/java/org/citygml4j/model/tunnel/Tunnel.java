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

package org.citygml4j.model.tunnel;

import org.citygml4j.model.common.GeometryInfo;
import org.citygml4j.model.common.TopLevelFeature;
import org.citygml4j.visitor.ObjectVisitor;
import org.xmlobjects.gml.model.geometry.Envelope;
import org.xmlobjects.gml.util.EnvelopeOptions;
import org.xmlobjects.model.ChildList;

import java.util.List;

public class Tunnel extends AbstractTunnel implements TopLevelFeature {
    private List<TunnelPartProperty> tunnelParts;
    private List<ADEOfTunnel> adeOfTunnel;

    public List<TunnelPartProperty> getTunnelParts() {
        if (tunnelParts == null)
            tunnelParts = new ChildList<>(this);

        return tunnelParts;
    }

    public void setTunnelParts(List<TunnelPartProperty> tunnelParts) {
        this.tunnelParts = asChild(tunnelParts);
    }

    public List<ADEOfTunnel> getADEOfTunnel() {
        if (adeOfTunnel == null)
            adeOfTunnel = new ChildList<>(this);

        return adeOfTunnel;
    }

    public void setADEOfTunnel(List<ADEOfTunnel> adeOfTunnel) {
        this.adeOfTunnel = asChild(adeOfTunnel);
    }

    @Override
    protected void updateEnvelope(Envelope envelope, EnvelopeOptions options) {
        super.updateEnvelope(envelope, options);

        if (tunnelParts != null) {
            for (TunnelPartProperty property : tunnelParts) {
                if (property.getObject() != null)
                    envelope.include(property.getObject().computeEnvelope(options));
            }
        }

        if (adeOfTunnel != null) {
            for (ADEOfTunnel container : adeOfTunnel)
                updateEnvelope(container, envelope, options);
        }
    }

    @Override
    protected void updateGeometryInfo(GeometryInfo geometryInfo) {
        super.updateGeometryInfo(geometryInfo);

        if (adeOfTunnel != null) {
            for (ADEOfTunnel container : adeOfTunnel)
                updateGeometryInfo(container, geometryInfo);
        }
    }

    @Override
    public void accept(ObjectVisitor visitor) {
        visitor.visit(this);
    }
}
