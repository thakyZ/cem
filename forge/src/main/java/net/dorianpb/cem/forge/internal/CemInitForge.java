package net.dorianpb.cem.forge.internal;

import dev.architectury.platform.forge.EventBuses;
import net.dorianpb.cem.forge.external.CemEntitiesInit;
import net.dorianpb.cem.forge.internal.api.CemEntityInitializer;
import net.dorianpb.cem.forge.internal.config.CemOptions;
import net.dorianpb.cem.common.internal.CemInit;
import net.dorianpb.cem.common.internal.util.CemFairy;
import net.dorianpb.cem.common.mixins.BlockEntityRendererAccessor;
import net.dorianpb.cem.common.mixins.EntityRendererAccessor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(CemInit.MOD_ID)
public class CemInitForge {
  public CemInitForge() {
    DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> CemClient::init);
  }

  private static class CemClient {
    static void init() {
      EventBuses.registerModEventBus(CemInit.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
      CemInit.init();
      CemEntityInitializer entrypoint = new CemEntitiesInit();

      CemOptions.load();
      entrypoint.onInit();
      CemFairy.getLogger().info("Loading " + entrypoint.getSize() + " entities from " + entrypoint.getClass().getName() + " " + "1.0.0");
      entrypoint.getCemEntityFactories().forEach((type, factory) -> {
        if (CemFairy.addSupport(type)) {
          EntityRendererAccessor.callRegister(type, factory);
        } else {
          CemFairy.getLogger().error("Entity type " + type + " already present!");
        }
      });
      entrypoint.getCemBlockEntityFactories().forEach((type, factory) -> {
        if (CemFairy.addSupport(type)) {
          BlockEntityRendererAccessor.callRegister(type, factory);
        } else {
          CemFairy.getLogger().error("Entity type " + type + " already present!");
        }
      });
      entrypoint.getCemOthers().forEach((type) -> {
        if (!CemFairy.addSupport(type)) {
          CemFairy.getLogger().error("Entity type " + type + " already present!");
        }
      });
    }
  }
}