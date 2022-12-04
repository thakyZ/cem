package net.dorianpb.cem.forge.internal.api;

import net.dorianpb.cem.forge.internal.util.CemRegistryManager;
import net.dorianpb.cem.common.internal.api.CemRenderer;
import net.dorianpb.cem.common.internal.util.CemFairy;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface CemBlockEntityRenderFactory {
  @SuppressWarnings("unchecked")
  default BlockEntityRenderer<BlockEntity> create(BlockEntityRendererFactory.Context ctx,
                                                  BlockEntityType<? extends BlockEntity> type,
                                                  @Nullable BlockEntityRendererFactory<? extends BlockEntity> vanillaFactory) {
    if (CemRegistryManager.hasEntity(type)) {
      try {
        return makeRenderer(ctx);
      } catch (Exception exception) {
        CemFairy.getLogger().error(exception);
        if (vanillaFactory != null) {
          return (BlockEntityRenderer<BlockEntity>) vanillaFactory.create(ctx);
        } else {
          throw new NullPointerException("No working BlockEntityRenderer found for " + type);
        }
      }
    } else {
      return (vanillaFactory == null) ? makeRenderer(ctx) : (BlockEntityRenderer<BlockEntity>) vanillaFactory.create(ctx);
    }
  }

  @SuppressWarnings("unchecked")
  default BlockEntityRenderer<BlockEntity> makeRenderer(BlockEntityRendererFactory.Context ctx) {
    CemRenderer renderer = create1(ctx);
    if (BlockEntityRenderer.class.isAssignableFrom(renderer.getClass())) {
      return (BlockEntityRenderer<BlockEntity>) renderer;
    } else {
      throw new IllegalArgumentException(renderer.getId() + " needs to extend BlockEntityRenderer!");
    }
  }

  CemRenderer create1(BlockEntityRendererFactory.Context ctx);
}