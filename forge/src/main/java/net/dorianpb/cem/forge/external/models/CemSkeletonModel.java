package net.dorianpb.cem.forge.external.models;

import net.dorianpb.cem.forge.internal.api.CemModel;
import net.dorianpb.cem.forge.internal.models.CemModelEntry;
import net.dorianpb.cem.forge.internal.models.CemModelRegistry;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.util.Arm;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class CemSkeletonModel extends SkeletonEntityModel<AbstractSkeletonEntity> implements CemModel {
  private static final Map<String, String> partNames = new HashMap<>();

  static {
    partNames.put("headwear", "hat");
  }

  private final CemModelRegistry registry;

  public CemSkeletonModel(CemModelRegistry registry) {
    this(registry, null);
  }

  public CemSkeletonModel(CemModelRegistry registry, @Nullable Float inflate) {
    super(registry.prepRootPart((new CemModelRegistry.CemPrepRootPartParamsBuilder()).setPartNameMap(partNames)
        .setVanillaReferenceModelFactory(() -> getTexturedModelData().createModel())
        .setInflate(inflate)
        .create()));
    this.registry = registry;
  }

  @Override
  public void setAngles(AbstractSkeletonEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    this.registry.applyAnimations(limbAngle, limbDistance, animationProgress, headYaw, headPitch, entity);
  }

  @Override
  public void setArmAngle(Arm arm, MatrixStack matrices) {
    var part = this.getArm(arm);
    if (part instanceof CemModelEntry.TransparentCemModelPart) {
      ((CemModelEntry.TransparentCemModelPart) part).rotateInnerPart(matrices, 1, 0, 0);
    } else {
      part.rotate(matrices);
    }
  }
}