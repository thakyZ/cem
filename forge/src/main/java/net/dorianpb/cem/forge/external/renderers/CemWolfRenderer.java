package net.dorianpb.cem.forge.external.renderers;

import net.dorianpb.cem.forge.external.models.CemWolfModel;
import net.dorianpb.cem.forge.internal.models.CemModelRegistry;
import net.dorianpb.cem.forge.internal.util.CemRegistryManager;
import net.dorianpb.cem.common.internal.api.CemRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.WolfEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.Identifier;

public class CemWolfRenderer extends WolfEntityRenderer implements CemRenderer {
  private final CemModelRegistry registry;

  public CemWolfRenderer(EntityRendererFactory.Context context) {
    super(context);
    this.registry = CemRegistryManager.getRegistry(getType());
    try {
      this.model = new CemWolfModel(registry);
      if (registry.hasShadowRadius()) {
        this.shadowRadius = registry.getShadowRadius();
      }
    } catch (Exception e) {
      modelError(e);
    }
  }

  private static EntityType<? extends Entity> getType() {
    return EntityType.WOLF;
  }

  @Override
  public String getId() {
    return getType().toString();
  }

  @Override
  public Identifier getTexture(WolfEntity entity) {
    if (this.registry != null && this.registry.hasTexture()) {
      return this.registry.getTexture();
    }
    return super.getTexture(entity);
  }
}