package net.strawberry.ore;

import javax.tools.Tool;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.item.Item.Settings;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;

public class ModBlocks {
    public static Settings BuildingBlock = new Item.Settings().group(ItemGroup.BUILDING_BLOCKS);
    
    public static Identifier strawberryOreIdentifier = new Identifier("strawberryore", "strawberry_ore");

    public static net.minecraft.block.AbstractBlock.Settings strawberryOreBlockSettings = FabricBlockSettings.copy(Blocks.DIRT)
        .strength(0.3f, 0.6f)
        .slipperiness(0.6f)
        .sounds(BlockSoundGroup.NYLIUM);

    public static final Block strawberryOre = new StrawberryOreBlock(strawberryOreBlockSettings);
    public static BlockItem strawberryOreBlockItem = new BlockItem(strawberryOre, BuildingBlock);
    public static RangeDecoratorConfig strawberryOreRange = new RangeDecoratorConfig(
        UniformHeightProvider.create(
        YOffset.fixed(-64), 
        YOffset.fixed(64)));
    public static OreFeatureConfig strawberryOreConfig = new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, strawberryOre.getDefaultState(), 9);
    private static ConfiguredFeature<?, ?> strawberryOreOverworldFeature = Feature.ORE.configure(strawberryOreConfig).decorate(Decorator.RANGE.configure(strawberryOreRange).spreadHorizontally().repeat(11));
    
    public static void registerItems() {
        Registry.register(Registry.BLOCK, strawberryOreIdentifier, strawberryOre);
        Registry.register(Registry.ITEM, strawberryOreIdentifier, strawberryOreBlockItem);

        RegistryKey<ConfiguredFeature<?,?>> strawberryOreOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, strawberryOreIdentifier);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, strawberryOreOverworld.getValue(), strawberryOreOverworldFeature);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, strawberryOreOverworld);
    }
}

