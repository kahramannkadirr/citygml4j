package org.citygml4j.builder.json.objects.geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.citygml4j.builder.json.objects.appearance.SurfaceMaterialObject;
import org.citygml4j.builder.json.objects.appearance.SurfaceTextureObject;

public abstract class AbstractSurfaceType extends AbstractGeometryType 
implements GeometryWithSemantics, GeometryWithAppearance<SurfaceMaterialObject, SurfaceTextureObject> {
	private final GeometryTypeName type;
	private List<List<List<Integer>>> boundaries = new ArrayList<>();
	private List<SemanticsType> semantics;
	private Map<String, SurfaceMaterialObject> material;
	private Map<String, SurfaceTextureObject> texture;
	
	protected AbstractSurfaceType(GeometryTypeName type) {
		this.type = type;
	}
	
	@Override
	public GeometryTypeName getType() {
		return type;
	}
	
	public void addSurface(List<List<Integer>> surface) {
		if (surface != null && surface.size() > 0)
			boundaries.add(surface);
	}

	public List<List<List<Integer>>> getSurfaces() {
		return boundaries;
	}

	public void setSurfaces(List<List<List<Integer>>> surfaces) {
		if (surfaces != null)
			boundaries = surfaces;
	}
	
	public boolean isSetSemantics() {
		return semantics != null;
	}

	public void addSemantics(SemanticsType semantics) {
		if (this.semantics == null)
			this.semantics = new ArrayList<>();
		
		this.semantics.add(semantics);
	}

	public List<SemanticsType> getSemantics() {
		return semantics;
	}

	public void setSemantics(List<SemanticsType> semantics) {
		this.semantics = semantics;
	}
	
	@Override
	public boolean hasSemantics() {
		return semantics != null && semantics.stream().filter(s -> s != SemanticsType.NULL_VALUE).findFirst().isPresent();
	}

	@Override
	public void unsetSemantics() {
		semantics = null;
	}
	
	@Override
	public boolean isSetMaterial() {
		return material != null;
	}
	
	public void addMaterial(SurfaceMaterialObject materialObject) {
		if (material == null)
			material = new HashMap<>();
		
		material.put(materialObject.getTheme(), materialObject);
	}
	
	@Override
	public Collection<SurfaceMaterialObject> getMaterial() {
		return material.values();
	}
	
	public SurfaceMaterialObject getMaterial(String theme) {
		return material != null ? material.get(theme) : null;
	}

	public void setMaterial(List<SurfaceMaterialObject> material) {
		if (material == null)
			this.material = null;
		else {
			for (SurfaceMaterialObject object : material)
				this.material.put(object.getTheme(), object);
		}
	}

	@Override
	public boolean isSetTexture() {
		return texture != null;
	}
	
	public void addTexture(SurfaceTextureObject textureObject) {
		if (texture == null)
			texture = new HashMap<>();
		
		texture.put(textureObject.getTheme(), textureObject);
	}
	
	@Override
	public Collection<SurfaceTextureObject> getTexture() {
		return texture.values();
	}
	
	public SurfaceTextureObject getTexture(String theme) {
		return texture != null ? texture.get(theme) : null;
	}

	public void setTexture(List<SurfaceTextureObject> texture) {
		if (texture == null)
			this.texture = null;
		else {
			for (SurfaceTextureObject object : texture)
				this.texture.put(object.getTheme(), object);
		}
	}

}