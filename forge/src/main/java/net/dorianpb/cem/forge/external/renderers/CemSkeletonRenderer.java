package net.dorianpb.cem.forge.external.renderers;

import net.dorianpb.cem.forge.external.models.CemSkeletonModel;
import net.dorianpb.cem.forge.internal.models.CemArmorModel;
import net.dorianpb.cem.forge.internal.models.CemModelRegistry;
import net.dorianpb.cem.forge.internal.util.CemRegistryManager;
import net.dorianpb.cem.common.internal.api.CemRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.SkeletonEntityRenderer;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.util.Identifier;

public class CemSkeletonRenderer extends SkeletonEntityRenderer implements CemRenderer {
  private final CemModelRegistry registry;

  public CemSkeletonRenderer(EntityRendererFactory.Context context) {
    super(context);
    this.registry = CemRegistryManager.getRegistry(getType());
    try {
      this.model = new CemSkeletonModel(registry);
      if (registry.hasShadowRadius()) {
        this.shadowRadius = registry.getShadowRadius();
      }
      this.features.replaceAll((feature) -> {
        if (feature instanceof ArmorFeatureRenderer) {
          return new ArmorFeatureRenderer<>(this, new CemArmorModel<>((CemSkeletonModel) this.model, 0.5F), new CemArmorModel<>((CemSkeletonModel) this.model,
              1.0F));
        } else {
          return feature;
        }
      });
    } catch (Exception e) {
      modelError(e);
    }
  }

  private static EntityType<? extends Entity> getType() {
    return EntityType.SKELETON;
  }

  @Override
  public String getId() {
    return getType().toString();
  }

  @Override
  public Identifier getTexture(AbstractSkeletonEntity entity) {
    if (this.registry != null && this.registry.hasTexture()) {
      return this.registry.getTexture();
    }
    return super.getTexture(entity);
  }
}