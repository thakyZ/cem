package net.dorianpb.cem.forge.external.renderers;

import net.dorianpb.cem.forge.external.models.CemEndermanModel;
import net.dorianpb.cem.forge.internal.models.CemModelRegistry;
import net.dorianpb.cem.forge.internal.util.CemRegistryManager;
import net.dorianpb.cem.common.internal.api.CemRenderer;
import net.minecraft.client.render.entity.EndermanEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.util.Identifier;

public class CemEndermanRenderer extends EndermanEntityRenderer implements CemRenderer {
  private final CemModelRegistry registry;

  public CemEndermanRenderer(EntityRendererFactory.Context context) {
    super(context);
    this.registry = CemRegistryManager.getRegistry(getType());
    try {
      this.model = new CemEndermanModel(registry);
      if (registry.hasShadowRadius()) {
        this.shadowRadius = registry.getShadowRadius();
      }
    } catch (Exception e) {
      modelError(e);
    }
  }

  private static EntityType<? extends Entity> getType() {
    return EntityType.ENDERMAN;
  }

  @Override
  public String getId() {
    return getType().toString();
  }

  @Override
  public Identifier getTexture(EndermanEntity entity) {
    if (this.registry != null && this.registry.hasTexture()) {
      return this.registry.getTexture();
    }
    return super.getTexture(entity);
  }
}