package net.dorianpb.cem.forge.external.models;

import net.dorianpb.cem.forge.internal.api.CemModel;
import net.dorianpb.cem.forge.internal.models.CemModelRegistry;
import net.minecraft.client.render.entity.model.BatEntityModel;
import net.minecraft.entity.passive.BatEntity;

import java.util.*;

public class CemBatModel extends BatEntityModel implements CemModel {
  private static final Map<String, String> partNames = new HashMap<>();
  private static final Map<String, List<String>> familyTree = new LinkedHashMap<>();

  static {
    partNames.put("outer_left_wing", "left_wing_tip");
    partNames.put("outer_right_wing", "right_wing_tip");
  }

  static {
    familyTree.put("right_wing", Collections.singletonList("outer_right_wing"));
    familyTree.put("left_wing", Collections.singletonList("outer_left_wing"));
    familyTree.put("body", Arrays.asList("left_wing", "right_wing"));
  }

  private final CemModelRegistry registry;

  public CemBatModel(CemModelRegistry registry) {
    super(registry.prepRootPart((new CemModelRegistry.CemPrepRootPartParamsBuilder()).setPartNameMap(partNames)
        .setFamilyTree(familyTree)
        .setVanillaReferenceModelFactory(() -> getTexturedModelData().createModel())
        .create()));
    this.registry = registry;
  }

  @Override
  public void setAngles(BatEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    this.registry.applyAnimations(limbAngle, limbDistance, animationProgress, headYaw, headPitch, entity);
  }
}