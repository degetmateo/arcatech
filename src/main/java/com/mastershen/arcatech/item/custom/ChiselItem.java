package com.mastershen.arcatech.item.custom;

import com.mastershen.arcatech.block.ArcatechBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;
import java.util.Properties;

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP =
            Map.of(
                    Blocks.STONE, Blocks.STONE_BRICKS,
                    Blocks.END_STONE, Blocks.END_STONE_BRICKS,
                    Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS,
                    Blocks.GOLD_BLOCK, Blocks.IRON_BLOCK,
                    Blocks.NETHERRACK, ArcatechBlocks.BISMUTH_BLOCK.get()
            );

    public static final int DAMAGE_ON_USE = 1;
    public static final int DURABILITY = 32;

    public ChiselItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn (UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState blockState = level.getBlockState(pos);
        Block clickedBlock = blockState.getBlock();

        if (CHISEL_MAP.containsKey(clickedBlock)) {
            if (!level.isClientSide()) {
                level.setBlockAndUpdate(pos, CHISEL_MAP.get(clickedBlock).defaultBlockState());

                Player player = context.getPlayer();

                context.getItemInHand().hurtAndBreak(
                    DAMAGE_ON_USE,
                    (ServerLevel) level,
                    player,
                    item -> player.onEquippedItemBroken(item, EquipmentSlot.MAINHAND)
                );

                level.playSound(null, pos, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
            }
        };

        return InteractionResult.SUCCESS;
    }
}