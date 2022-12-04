package net.dorianpb.cem.forge.external.models;

import net.dorianpb.cem.forge.internal.api.CemModel;
import net.dorianpb.cem.forge.internal.models.CemModelRegistry;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.render.entity.model.DrownedEntityModel;
import net.minecraft.entity.mob.DrownedEntity;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class CemDrownedZombieModel extends DrownedEntityModel<DrownedEntity> implements CemModel {
  private static final Map<String, String> partNames = new HashMap<>();

  static {
    partNames.put("headwear", "hat");
  }

  private final CemModelRegistry registry;

  public CemDrownedZombieModel(CemModelRegistry registry) {
    this(registry, null);
  }

  public CemDrownedZombieModel(CemModelRegistry registry, @Nullable Float inflate) {
    super(registry.prepRootPart((new CemModelRegistry.CemPrepRootPartParamsBuilder()).setPartNameMap(partNames)
        .setVanillaReferenceModelFactory(() -> getTexturedModelData(Dilation.NONE).createModel())
        .setInflate(inflate)
        .create()));
    this.registry = registry;
  }

  @Override
  public void setAngles(DrownedEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    this.registry.applyAnimations(limbAngle, limbDistance, animationProgress, headYaw, headPitch, entity);
  }
}