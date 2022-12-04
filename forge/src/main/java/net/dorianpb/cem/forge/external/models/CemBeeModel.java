package net.dorianpb.cem.forge.external.models;

import net.dorianpb.cem.forge.internal.api.CemModel;
import net.dorianpb.cem.forge.internal.models.CemModelRegistry;
import net.minecraft.client.render.entity.model.BeeEntityModel;
import net.minecraft.entity.passive.BeeEntity;

import java.util.*;

public class CemBeeModel extends BeeEntityModel<BeeEntity> implements CemModel {
  private static final Map<String, String> partNames = new HashMap<>();
  private static final Map<String, List<String>> familyTree = new LinkedHashMap<>();

  static {
    partNames.put("body", "bone");
    partNames.put("torso", "body");
  }

  static {
    familyTree.put("torso", Arrays.asList("stinger", "left_antenna", "right_antenna"));
    familyTree.put("body", Arrays.asList("torso", "right_wing", "left_wing", "front_legs", "middle_legs", "back_legs"));
  }

  private final CemModelRegistry registry;

  public CemBeeModel(CemModelRegistry registry) {
    super(registry.prepRootPart((new CemModelRegistry.CemPrepRootPartParamsBuilder()).setPartNameMap(partNames)
        .setFamilyTree(familyTree)
        .setVanillaReferenceModelFactory(() -> getTexturedModelData().createModel())
        .create()));
    this.registry = registry;
  }

  @Override
  public void setAngles(BeeEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    this.registry.applyAnimations(limbAngle, limbDistance, animationProgress, headYaw, headPitch, entity);
  }
}