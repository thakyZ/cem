package net.dorianpb.cem.forge.external.renderers;

import net.dorianpb.cem.forge.external.models.CemGiantZombieModel;
import net.dorianpb.cem.forge.internal.models.CemArmorModel;
import net.dorianpb.cem.forge.internal.models.CemModelRegistry;
import net.dorianpb.cem.forge.internal.util.CemRegistryManager;
import net.dorianpb.cem.common.internal.api.CemRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.GiantEntityRenderer;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.util.Identifier;

public class CemGiantZombieRenderer extends GiantEntityRenderer implements CemRenderer {
  private final CemModelRegistry registry;

  public CemGiantZombieRenderer(EntityRendererFactory.Context context) {
    super(context, 6F);
    this.registry = CemRegistryManager.getRegistry(getType());
    try {
      this.model = new CemGiantZombieModel(registry);
      if (registry.hasShadowRadius()) {
        this.shadowRadius = registry.getShadowRadius();
      }
      this.features.replaceAll((feature) -> {
        if (feature instanceof ArmorFeatureRenderer) {
          return new ArmorFeatureRenderer<>(this,
              new CemArmorModel<>((CemGiantZombieModel) this.model, 0.5F),
              new CemArmorModel<>((CemGiantZombieModel) this.model, 1.0F)
          );
        } else {
          return feature;
        }
      });
    } catch (Exception e) {
      modelError(e);
    }
  }

  private static EntityType<? extends Entity> getType() {
    return EntityType.GIANT;
  }

  @Override
  public String getId() {
    return getType().toString();
  }

  @Override
  public Identifier getTexture(GiantEntity entity) {
    if (this.registry != null && this.registry.hasTexture()) {
      return this.registry.getTexture();
    }
    return super.getTexture(entity);
  }
}