package net.dorianpb.cem.forge.external.models;

import net.dorianpb.cem.forge.internal.api.CemModel;
import net.dorianpb.cem.forge.internal.models.CemModelRegistry;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.GiantEntityModel;
import net.minecraft.entity.mob.GiantEntity;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class CemGiantZombieModel extends GiantEntityModel implements CemModel {
  private static final Map<String, String> partNames = new HashMap<>();

  static {
    partNames.put("headwear", "hat");
  }

  private final CemModelRegistry registry;

  public CemGiantZombieModel(CemModelRegistry registry) {
    this(registry, null);
  }

  public CemGiantZombieModel(CemModelRegistry registry, @Nullable Float inflate) {
    super(registry.prepRootPart((new CemModelRegistry.CemPrepRootPartParamsBuilder()).setPartNameMap(partNames)
        .setVanillaReferenceModelFactory(() -> TexturedModelData.of(getModelData(Dilation.NONE, 0), 0, 0)
            .createModel())
        .setInflate(inflate)
        .create()));
    this.registry = registry;
  }

  @Override
  public void setAngles(GiantEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    this.registry.applyAnimations(limbAngle, limbDistance, animationProgress, headYaw, headPitch, entity);
  }
}