package com.mastershen.arcatech.block.custom;

import com.mastershen.arcatech.item.ArcatechItems;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class MagicBlock extends Block {
    public MagicBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem (
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            BlockHitResult hitResult
    ) {
        level.playSound (
                player,
                pos,
                SoundEvents.AMETHYST_CLUSTER_PLACE,
                SoundSource.BLOCKS,
                1.0F,
                1.0F
        );

        return InteractionResult.SUCCESS;
    };

    @Override
    public void stepOn (
            Level level,
            BlockPos pos,
            BlockState state,
            Entity entity
    ) {
        if (entity instanceof ItemEntity itemEntity) {
            ItemStack itemStack = itemEntity.getItem();
            Item item = itemStack.getItem();

            if (item == ArcatechItems.RAW_BISMUTH.get()) {
                itemEntity.setItem(new ItemStack(Items.DIAMOND, itemStack.getCount()));
            };
        };

        super.stepOn(level, pos, state, entity);
    };
}
