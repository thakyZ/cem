package net.dorianpb.cem.forge.external.renderers;

import net.dorianpb.cem.forge.external.models.CemMinecartModel;
import net.dorianpb.cem.forge.internal.models.CemModelRegistry;
import net.dorianpb.cem.forge.internal.util.CemRegistryManager;
import net.dorianpb.cem.common.internal.api.CemRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.MinecartEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public class CemMinecartRenderer extends MinecartEntityRenderer<AbstractMinecartEntity> implements CemRenderer {
  private static final Map<EntityType<? extends AbstractMinecartEntity>, EntityModelLayer> layers = new LinkedHashMap<>();

  static {
    layers.put(EntityType.CHEST_MINECART, EntityModelLayers.CHEST_MINECART);
    layers.put(EntityType.COMMAND_BLOCK_MINECART, EntityModelLayers.COMMAND_BLOCK_MINECART);
    layers.put(EntityType.FURNACE_MINECART, EntityModelLayers.FURNACE_MINECART);
    layers.put(EntityType.HOPPER_MINECART, EntityModelLayers.HOPPER_MINECART);
    layers.put(EntityType.MINECART, EntityModelLayers.MINECART);
    layers.put(EntityType.SPAWNER_MINECART, EntityModelLayers.SPAWNER_MINECART);
  }

  private final EntityType<? extends Entity> entityType;
  private final CemModelRegistry registry;

  public CemMinecartRenderer(Context context, EntityType<? extends AbstractMinecartEntity> entityType) {
    super(context, layers.get(entityType));
    this.entityType = entityType;
    this.registry = CemRegistryManager.getRegistry(entityType);
    try {
      this.model = new CemMinecartModel<>(registry);
      if (registry.hasShadowRadius()) {
        this.shadowRadius = registry.getShadowRadius();
      }
    } catch (Exception e) {
      modelError(e);
    }
  }

  @Override
  public String getId() {
    return entityType.toString();
  }

  @Override
  public Identifier getTexture(AbstractMinecartEntity entity) {
    if (this.registry != null && this.registry.hasTexture()) {
      return this.registry.getTexture();
    }
    return super.getTexture(entity);
  }
}