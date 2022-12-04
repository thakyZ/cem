package net.dorianpb.cem.forge.external.models;

import net.dorianpb.cem.forge.internal.api.CemModel;
import net.dorianpb.cem.forge.internal.models.CemModelRegistry;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.entity.passive.PigEntity;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class CemPigModel extends PigEntityModel<PigEntity> implements CemModel {
  private static final Map<String, String> partNames = new HashMap<>();

  static {
    partNames.put("leg1", "left_hind_leg");
    partNames.put("leg2", "right_hind_leg");
    partNames.put("leg3", "left_front_leg");
    partNames.put("leg4", "right_front_leg");
  }

  private final CemModelRegistry registry;

  public CemPigModel(CemModelRegistry registry, @Nullable Float inflate) {
    super(registry.prepRootPart((new CemModelRegistry.CemPrepRootPartParamsBuilder()).setPartNameMap(partNames)
        .setVanillaReferenceModelFactory(() -> getTexturedModelData(Dilation.NONE).createModel())
        .setInflate(inflate)
        .create()));
    this.registry = registry;
  }

  @Override
  public void setAngles(PigEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    this.registry.applyAnimations(limbAngle, limbDistance, animationProgress, headYaw, headPitch, entity);
  }
}