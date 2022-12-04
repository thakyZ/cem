package net.dorianpb.cem.forge.internal.api;

import net.dorianpb.cem.forge.internal.util.CemRegistryManager;
import net.dorianpb.cem.common.internal.api.CemRenderer;
import net.dorianpb.cem.common.internal.util.CemFairy;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface CemEntityRenderFactory {
	@SuppressWarnings("unchecked")
	default EntityRenderer<Entity> create(EntityRendererFactory.Context ctx,
																																							EntityType<? extends Entity> type,
																																							@Nullable EntityRendererFactory<? extends Entity> vanillaFactory) {
		if (CemRegistryManager.hasEntity(type)) {
			try {
				return makeRenderer(ctx);
			} catch (Exception exception) {
				CemFairy.getLogger().error(exception);
				if (vanillaFactory != null) {
					return (EntityRenderer<Entity>) vanillaFactory.create(ctx);
				} else {
					throw new NullPointerException("No working EntityRenderer found for " + type);
				}
			}
		} else {
			return (vanillaFactory == null) ? makeRenderer(ctx) : (EntityRenderer<Entity>) vanillaFactory.create(ctx);
		}
	}

	@SuppressWarnings("unchecked")
	default EntityRenderer<Entity> makeRenderer(EntityRendererFactory.Context ctx) {
		CemRenderer renderer = create1(ctx);
		if (EntityRenderer.class.isAssignableFrom(renderer.getClass())) {
			return (EntityRenderer<Entity>) renderer;
		} else {
			throw new IllegalArgumentException(renderer.getId() + " needs to extend EntityRenderer!");
		}
	}

	CemRenderer create1(EntityRendererFactory.Context ctx);
}