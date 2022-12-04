package net.dorianpb.cem.forge.internal.api;

import net.dorianpb.cem.forge.internal.config.CemOptions;
import net.dorianpb.cem.forge.internal.models.CemModelEntry;
import net.minecraft.client.model.ModelPart;

public interface CemModel {

  default void rotatePart(CemModelEntry cemModelEntry, char axis, float degrees) {
    CemModelEntry.CemModelPart modelPart = (cemModelEntry != null) ? cemModelEntry.getModel() : null;
    if (modelPart != null && !CemOptions.USE_TRANSPARENT_PARTS.get()) {
      modelPart.setRotation(axis, (float) (modelPart.getRotation(axis) + Math.toRadians((degrees + 360) % 360)));
    }
  }

  @FunctionalInterface
  interface VanillaReferenceModelFactory {
    ModelPart get();
  }
}