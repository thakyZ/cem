package net.dorianpb.cem.forge.external.models;

import net.dorianpb.cem.forge.internal.api.CemModel;
import net.dorianpb.cem.forge.internal.models.CemModelRegistry;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import net.minecraft.entity.LivingEntity;

import java.util.*;

public class CemVillagerModel<T extends LivingEntity> extends VillagerResemblingModel<T> implements CemModel {
  private static final Map<String, String> partNames = new HashMap<>();
  private static final Map<String, List<String>> familyTree = new LinkedHashMap<>();

  static {
    partNames.put("headwear", "hat");
    partNames.put("headwear2", "hat_rim");
    partNames.put("bodywear", "jacket");
  }

  static {
    familyTree.put("headwear", Collections.singletonList("headwear2"));
    familyTree.put("head", Arrays.asList("headwear", "nose"));
    familyTree.put("body", Collections.singletonList("bodywear"));
  }

  private final CemModelRegistry registry;

  public CemVillagerModel(CemModelRegistry registry) {
    super(registry.prepRootPart((new CemModelRegistry.CemPrepRootPartParamsBuilder()).setPartNameMap(partNames)
        .setFamilyTree(familyTree)
        .setVanillaReferenceModelFactory(() -> TexturedModelData.of(getModelData(), 0, 0).createModel())
        .create()));
    this.registry = registry;
    this.rotatePart(this.registry.getEntryByPartName("headwear2"), 'x', -90);
    this.rotatePart(this.registry.getEntryByPartName("arms"), 'x', -43);
  }

  @Override
  public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    this.registry.applyAnimations(limbAngle, limbDistance, animationProgress, headYaw, headPitch, entity);
  }
}