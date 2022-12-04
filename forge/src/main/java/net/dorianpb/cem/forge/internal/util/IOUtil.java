package net.dorianpb.cem.forge.internal.util;

import dev.architectury.platform.Platform;
import dev.ftb.mods.ftblibrary.snbt.config.SNBTConfig;

import java.nio.file.Path;

import static net.dorianpb.cem.common.internal.CemInit.MOD_ID;

public interface IOUtil {
  Path ROOT_DIR = Platform.getGameFolder();

  Path DEFAULT_CONFIG_DIR = ROOT_DIR.resolve("defaultconfigs");
  Path CONFIG_DIR = ROOT_DIR.resolve("config");
  Path LOCAL_DIR = ROOT_DIR.resolve("local");

  static void loadDefaulted(SNBTConfig config, Path configDir) {
    String filename = config.key + ".snbt";
    Path configPath = configDir.resolve(filename).toAbsolutePath();
    Path defaultPath = DEFAULT_CONFIG_DIR.resolve(MOD_ID).resolve(filename);
    config.load(
        configPath,
        defaultPath,
        () -> new String[]{
            "Default config file that will be copied to " + ROOT_DIR.relativize(configPath) + " if it doesn't exist!",
            "Just copy any values you wish to override in here!",
        }
    );
  }
}