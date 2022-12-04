package net.dorianpb.cem.forge.external.models;

import net.dorianpb.cem.forge.internal.api.CemModel;
import net.dorianpb.cem.forge.internal.models.CemModelRegistry;
import net.minecraft.client.render.entity.model.MagmaCubeEntityModel;
import net.minecraft.entity.mob.MagmaCubeEntity;

import java.util.HashMap;
import java.util.Map;

public class CemMagmaCubeModel extends MagmaCubeEntityModel<MagmaCubeEntity> implements CemModel {
  private static final Map<String, String> partNames = new HashMap<>();

  static {
    partNames.put("core", "inside_cube");
    for (int i = 0; i < 8; i++) {
      partNames.put("segment" + (i + 1), "cube" + i);
    }
  }

  private final CemModelRegistry registry;

  public CemMagmaCubeModel(CemModelRegistry registry) {
    super(registry.prepRootPart((new CemModelRegistry.CemPrepRootPartParamsBuilder()).setPartNameMap(partNames)
        .setVanillaReferenceModelFactory(() -> getTexturedModelData().createModel())
        .create()));
    this.registry = registry;
  }

  @Override
  public void setAngles(MagmaCubeEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    this.registry.applyAnimations(limbAngle, limbDistance, animationProgress, headYaw, headPitch, entity);
  }
}