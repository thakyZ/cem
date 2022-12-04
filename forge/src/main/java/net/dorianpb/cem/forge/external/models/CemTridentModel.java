package net.dorianpb.cem.forge.external.models;

import net.dorianpb.cem.forge.internal.api.CemModel;
import net.dorianpb.cem.forge.internal.models.CemModelRegistry;
import net.minecraft.client.render.entity.model.TridentEntityModel;

import java.util.HashMap;
import java.util.Map;

public class CemTridentModel extends TridentEntityModel implements CemModel {
  private static final Map<String, String> partNames = new HashMap<>();

  static {
    partNames.put("body", "pole");
  }

  private final CemModelRegistry registry;

  public CemTridentModel(CemModelRegistry registry) {
    super(registry.prepRootPart((new CemModelRegistry.CemPrepRootPartParamsBuilder()).setPartNameMap(partNames)
        .setVanillaReferenceModelFactory(() -> getTexturedModelData().createModel())
        .create()));
    this.registry = registry;
  }

  //	@Override
  //	public void setAngles(TridentEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch){
  //		super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
  //		this.registry.applyAnimations(limbAngle, limbDistance, animationProgress, headYaw, headPitch, entity);
  //	}
}