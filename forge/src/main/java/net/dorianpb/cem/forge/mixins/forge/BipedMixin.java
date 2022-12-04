package net.dorianpb.cem.forge.mixins.forge;


import net.dorianpb.cem.forge.internal.api.CemModel;
import net.dorianpb.cem.forge.internal.models.CemModelEntry;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModel.class)
public abstract class BipedMixin {
	@Inject(method = "setArmAngle", at = @At(value = "HEAD"), cancellable = true)
	private void cem$handleArmRot(Arm arm, MatrixStack matrices, CallbackInfo ci) {
		if (this instanceof CemModel) {
			var part = this.getArm(arm);
			if (part instanceof CemModelEntry.TransparentCemModelPart) {
				((CemModelEntry.TransparentCemModelPart) part).rotateInnerPart(matrices);
				ci.cancel();
			}
		}
	}

	@Shadow
	protected abstract ModelPart getArm(Arm arm);
}