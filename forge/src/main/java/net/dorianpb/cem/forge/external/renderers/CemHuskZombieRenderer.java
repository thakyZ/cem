package net.dorianpb.cem.forge.external.renderers;

import net.dorianpb.cem.forge.external.models.CemZombieModel;
import net.dorianpb.cem.forge.internal.models.CemArmorModel;
import net.dorianpb.cem.forge.internal.models.CemModelRegistry;
import net.dorianpb.cem.forge.internal.util.CemRegistryManager;
import net.dorianpb.cem.common.internal.api.CemRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.HuskEntityRenderer;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;

public class CemHuskZombieRenderer extends HuskEntityRenderer implements CemRenderer {
  private final CemModelRegistry registry;

  public CemHuskZombieRenderer(EntityRendererFactory.Context context) {
    super(context);
    this.registry = CemRegistryManager.getRegistry(getType());
    try {
      this.model = new CemZombieModel(registry);
      if (registry.hasShadowRadius()) {
        this.shadowRadius = registry.getShadowRadius();
      }
      this.features.replaceAll((feature) -> {
        if (feature instanceof ArmorFeatureRenderer) {
          return new ArmorFeatureRenderer<>(this, new CemArmorModel<>((CemZombieModel) this.model, 0.5F), new CemArmorModel<>((CemZombieModel) this.model, 1.0F));
        } else {
          return feature;
        }
      });
    } catch (Exception e) {
      modelError(e);
    }
  }

  private static EntityType<? extends Entity> getType() {
    return EntityType.HUSK;
  }

  @Override
  public String getId() {
    return getType().toString();
  }

  @Override
  public Identifier getTexture(ZombieEntity entity) {
    if (this.registry != null && this.registry.hasTexture()) {
      return this.registry.getTexture();
    }
    return super.getTexture(entity);
  }
}