package net.dorianpb.cem.forge.external.models;

import net.dorianpb.cem.forge.internal.api.CemModel;
import net.dorianpb.cem.forge.internal.models.CemModelRegistry;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.entity.passive.ChickenEntity;

import java.util.HashMap;
import java.util.Map;

public class CemChickenModel extends ChickenEntityModel<ChickenEntity> implements CemModel {
  private static final Map<String, String> partNames = new HashMap<>();

  static {
    partNames.put("bill", "beak");
    partNames.put("chin", "red_thing");
  }

  private final CemModelRegistry registry;

  public CemChickenModel(CemModelRegistry registry) {
    super(registry.prepRootPart((new CemModelRegistry.CemPrepRootPartParamsBuilder()).setPartNameMap(partNames)
        .setVanillaReferenceModelFactory(() -> getTexturedModelData().createModel())
        .create()));
    this.registry = registry;
  }

  @Override
  public void setAngles(ChickenEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    this.registry.applyAnimations(limbAngle, limbDistance, animationProgress, headYaw, headPitch, entity);
  }
}