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
package org.citygml4j.builder.cityjson.marshal.util;

import java.util.List;

public class SurfaceDataInfo {
	private final String theme;
	private final int sequenceNumber;
	private final boolean isFront;
	private final List<Double> textureCoordinates;
	
	public SurfaceDataInfo(String theme, int sequenceNumber, boolean isFront, List<Double> textureCoordinates) {
		this.theme = theme;
		this.sequenceNumber = sequenceNumber;
		this.isFront = isFront;
		this.textureCoordinates = textureCoordinates;
	}
	
	public SurfaceDataInfo(String theme, int sequenceNumber, boolean isFront) {
		this(theme, sequenceNumber, isFront, null);
	}
	
	public boolean isMaterial() {
		return textureCoordinates == null;
	}

	public String getTheme() {
		return theme;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public boolean isFront() {
		return isFront;
	}

	public List<Double> getTextureCoordinates() {
		return textureCoordinates;
	}

}