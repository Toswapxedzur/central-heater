package com.minecart.central_heater.mixin;

import com.minecart.central_heater.block.misc.BurnableCampfireBlock;
import com.minecart.central_heater.block_entity.misc.BurnableCampfireBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// hey apple, orange you glad I didn't forget an entire mixin?
@Mixin(CampfireBlock.class)
public abstract class CampfireBlockMixin {

    @Shadow
    protected int fireDamage;

    @Inject(method = "newBlockEntity", at = @At("HEAD"), cancellable = true)
    private void central_heater$useBurnableCampfireBlockEntity(BlockPos pos, BlockState state, CallbackInfoReturnable<BlockEntity> cir) {
        if (!((Object) this instanceof BurnableCampfireBlock)) {
            cir.setReturnValue(new BurnableCampfireBlockEntity(pos, state, fireDamage == 1 ? 0 : 1));
        }
    }
}