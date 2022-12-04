package net.dorianpb.cem.forge.external.models;

import net.dorianpb.cem.forge.internal.api.CemModel;
import net.dorianpb.cem.forge.internal.models.CemModelRegistry;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.render.entity.model.FoxEntityModel;
import net.minecraft.entity.passive.FoxEntity;

import java.util.*;

public class CemFoxModel extends FoxEntityModel<FoxEntity> implements CemModel {
  private static final Map<String, String> partNames = new HashMap<>();
  private static final Map<String, List<String>> familyTree = new LinkedHashMap<>();
  private static final Map<String, ModelTransform> modelTransformFixes = new HashMap<>();

  static {
    partNames.put("leg1", "right_hind_leg");
    partNames.put("leg2", "left_hind_leg");
    partNames.put("leg3", "right_front_leg");
    partNames.put("leg4", "left_front_leg");
  }

  static {
    familyTree.put("head", Arrays.asList("right_ear", "left_ear", "nose"));
    familyTree.put("body", Collections.singletonList("tail"));
  }

  static {
    modelTransformFixes.put("body", ModelTransform.pivot(0.0F, 7.5F, 3.5F));
    modelTransformFixes.put("tail", ModelTransform.pivot(-4.0F, 5.5F, 6.0F));
  }

  private final CemModelRegistry registry;

  public CemFoxModel(CemModelRegistry registry) {
    super(registry.prepRootPart((new CemModelRegistry.CemPrepRootPartParamsBuilder()).setPartNameMap(partNames)
        .setFamilyTree(familyTree)
        .setVanillaReferenceModelFactory(() -> getTexturedModelData().createModel())
        .setFixes(modelTransformFixes)
        .create()));
    this.registry = registry;
  }

  @Override
  public void setAngles(FoxEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    this.registry.applyAnimations(limbAngle, limbDistance, animationProgress, headYaw, headPitch, entity);
  }
}