package net.dorianpb.cem.forge.mixins.forge;

import net.dorianpb.cem.forge.internal.MixinHelper;
import net.dorianpb.cem.forge.internal.config.CemOptions;
import net.dorianpb.cem.forge.internal.util.CemRegistryManager;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.resource.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityModelLoader.class)
public abstract class EntityModelLoaderMixin {
  @Inject(method = "reload", at = @At(value = "HEAD"))
  private void cem$injectReload(ResourceManager manager, CallbackInfo ci) {
    CemRegistryManager.clearRegistries();
    manager.findResources("cem", path -> path.endsWith(".jem")).forEach((id -> MixinHelper.loadResourceFromId(manager, id, "dorianpb")));
    if (CemOptions.USE_OPTIFINE_FOLDER.get()) {
      manager.findResources("optifine/cem", path -> path.endsWith(".jem")).forEach((id -> MixinHelper.loadResourceFromId(manager, id, "minecraft")));
    }
  }
}