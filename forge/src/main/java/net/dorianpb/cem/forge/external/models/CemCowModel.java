package net.dorianpb.cem.forge.external.models;

import net.dorianpb.cem.forge.internal.api.CemModel;
import net.dorianpb.cem.forge.internal.models.CemModelRegistry;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.entity.passive.CowEntity;

import java.util.*;

public class CemCowModel<T extends CowEntity> extends CowEntityModel<T> implements CemModel {
  private static final Map<String, String> partNames = new HashMap<>();
  private static final Map<String, List<String>> familyTree = new LinkedHashMap<>();

  static {
    partNames.put("leg1", "right_hind_leg");
    partNames.put("leg2", "left_hind_leg");
    partNames.put("leg3", "right_front_leg");
    partNames.put("leg4", "left_front_leg");
  }

  static {
    familyTree.put("head", Arrays.asList("right_horn", "left_horn"));
  }

  private final CemModelRegistry registry;

  public CemCowModel(CemModelRegistry registry) {
    super(registry.prepRootPart((new CemModelRegistry.CemPrepRootPartParamsBuilder()).setPartNameMap(partNames)
        .setFamilyTree(familyTree)
        .setVanillaReferenceModelFactory(() -> getTexturedModelData().createModel())
        .create()));
    this.registry = registry;
    this.rotatePart(this.registry.getEntryByPartName("body"), 'x', 90);
  }

  @Override
  public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    this.registry.applyAnimations(limbAngle, limbDistance, animationProgress, headYaw, headPitch, entity);
  }
}