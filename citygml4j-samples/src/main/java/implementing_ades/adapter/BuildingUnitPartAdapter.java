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

package implementing_ades.adapter;

import implementing_ades.model.BuildingUnitPart;
import implementing_ades.module.TestADEModule;
import org.xmlobjects.annotation.XMLElement;
import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

@XMLElement(name = "BuildingUnitPart", namespaceURI = TestADEModule.NAMESPACE_1_0)
public class BuildingUnitPartAdapter extends AbstractBuildingUnitAdapter<BuildingUnitPart> {

    @Override
    public BuildingUnitPart createObject(QName name) throws ObjectBuildException {
        return new BuildingUnitPart();
    }

    @Override
    public Element createElement(BuildingUnitPart object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(TestADEModule.NAMESPACE_1_0, "BuildingUnitPart");
    }
}
