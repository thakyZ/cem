package net.dorianpb.cem.forge.external.models;

import net.dorianpb.cem.forge.internal.api.CemModel;
import net.dorianpb.cem.forge.internal.models.CemModelRegistry;
import net.minecraft.client.render.entity.model.EndermanEntityModel;
import net.minecraft.entity.mob.EndermanEntity;

import java.util.HashMap;
import java.util.Map;

public class CemEndermanModel extends EndermanEntityModel<EndermanEntity> implements CemModel {
  private static final Map<String, String> partNames = new HashMap<>();

  static {
    partNames.put("headwear", "hat");
  }

  private final CemModelRegistry registry;

  public CemEndermanModel(CemModelRegistry registry) {
    super(registry.prepRootPart((new CemModelRegistry.CemPrepRootPartParamsBuilder()).setPartNameMap(partNames)
        .setVanillaReferenceModelFactory(() -> getTexturedModelData().createModel())
        .create()));
    this.registry = registry;
  }

  @Override
  public void setAngles(EndermanEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    this.registry.applyAnimations(limbAngle, limbDistance, animationProgress, headYaw, headPitch, entity);
  }
}