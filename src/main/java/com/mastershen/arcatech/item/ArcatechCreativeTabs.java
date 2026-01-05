package com.mastershen.arcatech.item;

import com.mastershen.arcatech.Arcatech;
import com.mastershen.arcatech.block.ArcatechBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ArcatechCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Arcatech.MOD_ID);

    public static final Supplier<CreativeModeTab> BISMUTH_ITEMS_TAB = CREATIVE_MODE_TAB.register("bismuth_items_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ArcatechItems.BISMUTH.get()))
                    .title(Component.translatable("creativetab.arcatech.bismuth_items_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ArcatechItems.BISMUTH);
                        output.accept(ArcatechItems.RAW_BISMUTH);
                    })
                    .build());

    public static final Supplier<CreativeModeTab> BISMUTH_BLOCKS_TAB = CREATIVE_MODE_TAB.register("bismuth_blocks_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ArcatechItems.BISMUTH.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Arcatech.MOD_ID, "bismuth_items_tab"))
                    .title(Component.translatable("creativetab.arcatech.bismuth_blocks_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ArcatechBlocks.BISMUTH_BLOCK);
                        output.accept(ArcatechBlocks.BISMUTH_ORE);
                        output.accept(ArcatechBlocks.BISMUTH_DEEPSLATE_ORE);
                    })
                    .build());

    public static void register (IEventBus bus) {
        CREATIVE_MODE_TAB.register(bus);
    }
}