package fi.dy.masa.itemscroller.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import fi.dy.masa.itemscroller.event.RenderEventHandler;

@Mixin(net.minecraft.client.render.GameRenderer.class)
public abstract class MixinGameRenderer
{
    @Inject(method = "render(FJZ)V",
            at = @At(value = "INVOKE", shift = Shift.AFTER,
                     target = "Lnet/minecraft/client/gui/screen/Screen;render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V"),
            locals = LocalCapture.CAPTURE_FAILSOFT)
    private void onDrawScreenPost(float partialTicks, long nanoTime, boolean renderWorldIn, CallbackInfo ci, int i, int j, net.minecraft.client.util.math.MatrixStack matrixStack)
    {
        RenderEventHandler.instance().onDrawScreenPost(matrixStack);
    }
}