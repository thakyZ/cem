package net.dorianpb.cem.forge.external.models;

import net.dorianpb.cem.forge.internal.api.CemModel;
import net.dorianpb.cem.forge.internal.models.CemModelRegistry;
import net.minecraft.client.render.entity.model.IllagerEntityModel;
import net.minecraft.entity.mob.IllagerEntity;

import java.util.*;

public class CemIllagerModel<T extends IllagerEntity> extends IllagerEntityModel<T> implements CemModel {
  private static final Map<String, List<String>> familyTree = new LinkedHashMap<>();

  static {
    familyTree.put("head", Arrays.asList("hat", "nose"));
    familyTree.put("arms", Collections.singletonList("left_shoulder"));
  }

  private final CemModelRegistry registry;

  public CemIllagerModel(CemModelRegistry registry) {
    super(registry.prepRootPart((new CemModelRegistry.CemPrepRootPartParamsBuilder()).setFamilyTree(familyTree)
        .setVanillaReferenceModelFactory(() -> getTexturedModelData().createModel())
        .create()));
    this.registry = registry;
  }

  @Override
  public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    this.registry.applyAnimations(limbAngle, limbDistance, animationProgress, headYaw, headPitch, entity);
  }
}