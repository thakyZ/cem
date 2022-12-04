package net.dorianpb.cem.forge.internal.config;

import dev.ftb.mods.ftblibrary.snbt.config.BooleanValue;
import dev.ftb.mods.ftblibrary.snbt.config.SNBTConfig;
import net.dorianpb.cem.forge.internal.util.IOUtil;

import static net.dorianpb.cem.common.internal.CemInit.MOD_ID;

@SuppressWarnings("unused")
public interface CemOptions {
  SNBTConfig CONFIG = SNBTConfig.create(MOD_ID)
      .comment("CONFIG");

  BooleanValue USE_OPTIFINE_FOLDER = CONFIG.getBoolean("use_optifine_folder", true)
      .comment("useOptifineFolder");

  BooleanValue USE_TRANSPARENT_PARTS = CONFIG.getBoolean("use_transparent_parts", true)
      .comment("useTransparentParts");

  BooleanValue USE_OLD_ANIMATIONS = CONFIG.getBoolean("use_old_animations", false)
      .comment("useOldAnimations");

  static void load() {
    IOUtil.loadDefaulted(CONFIG, IOUtil.CONFIG_DIR);
  }
}