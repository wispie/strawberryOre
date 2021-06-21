package net.strawberry.ore;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item.Settings;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static Settings FoodItem = new Item.Settings().group(ItemGroup.FOOD);
    public static float guaranteedChance = 1f;

    public static FoodComponent strawberryFoodComponent = new FoodComponent.Builder()
        .hunger(2)
        .saturationModifier(3f)
        .snack()
        .build();
    
    public static FoodComponent hugeStrawberryFoodComponent = new FoodComponent.Builder()
        .hunger(5)
        .saturationModifier(8f)
        .alwaysEdible()
        .meat()
        .statusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 20*6, 2), guaranteedChance)
        .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 20*10, 1), guaranteedChance)
        .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 20*6, 1), guaranteedChance)
        .build();

    public static final Item strawberry = new Item(FoodItem.food(strawberryFoodComponent));
    public static Identifier strawberryIdentifier = new Identifier("strawberryore", "strawberry");

    public static final Item hugeStrawberry = new Item(FoodItem.food(hugeStrawberryFoodComponent));
    public static Identifier hugeStrawberryIdentifier = new Identifier("strawberryore", "strawberry_huge");

    public static void registerItems() {
        Registry.register(Registry.ITEM, strawberryIdentifier, strawberry);
        Registry.register(Registry.ITEM, hugeStrawberryIdentifier, hugeStrawberry);
    }
}
