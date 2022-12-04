package net.dorianpb.cem.forge.external.models;

import net.dorianpb.cem.forge.internal.api.CemModel;
import net.dorianpb.cem.forge.internal.models.CemModelRegistry;
import net.minecraft.client.render.entity.model.BlazeEntityModel;
import net.minecraft.entity.mob.BlazeEntity;

import java.util.HashMap;
import java.util.Map;

public class CemBlazeModel extends BlazeEntityModel<BlazeEntity> implements CemModel {
  private static final Map<String, String> partNames = new HashMap<>();

  static {
    for (int i = 0; i < 12; i++) {
      partNames.put("stick" + (i + 1), "part" + i);
    }
  }

  private final CemModelRegistry registry;

  public CemBlazeModel(CemModelRegistry registry) {
    super(registry.prepRootPart((new CemModelRegistry.CemPrepRootPartParamsBuilder()).setPartNameMap(partNames)
        .setVanillaReferenceModelFactory(() -> getTexturedModelData().createModel())
        .create()));
    this.registry = registry;
  }

  @Override
  public void setAngles(BlazeEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    this.registry.applyAnimations(limbAngle, limbDistance, animationProgress, headYaw, headPitch, entity);
  }
}