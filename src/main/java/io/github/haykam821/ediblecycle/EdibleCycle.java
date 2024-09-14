package io.github.haykam821.ediblecycle;

import java.util.Map;
import java.util.Optional;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.AirBlockItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BoatItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.BrushItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.BundleItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.EggItem;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.EmptyMapItem;
import net.minecraft.item.EnderEyeItem;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.ExperienceBottleItem;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.GoatHornItem;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.KnowledgeBookItem;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.item.OminousBottleItem;
import net.minecraft.item.OnAStickItem;
import net.minecraft.item.PotionItem;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SnowballItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.SpyglassItem;
import net.minecraft.item.TridentItem;
import net.minecraft.item.WindChargeItem;
import net.minecraft.item.WritableBookItem;
import net.minecraft.item.WrittenBookItem;

public class EdibleCycle implements ModInitializer {
	@Override
	public void onInitialize() {
		FoodComponent defaultComponent = new FoodComponent.Builder().build();

		GraphAssigner assigner = new GraphAssigner();

		assigner.removeType(AirBlockItem.class);
		assigner.removeType(OnAStickItem.class);
		assigner.removeType(ElytraItem.class);
		assigner.removeType(BoatItem.class);
		assigner.removeType(ArmorItem.class);
		assigner.removeType(BowItem.class);
		assigner.removeType(BucketItem.class);
		assigner.removeType(SnowballItem.class);
		assigner.removeType(MilkBucketItem.class);
		assigner.removeType(EggItem.class);
		assigner.removeType(BundleItem.class);
		assigner.removeType(FishingRodItem.class);
		assigner.removeType(SpyglassItem.class);
		assigner.removeType(EnderPearlItem.class);
		assigner.removeType(PotionItem.class);
		assigner.removeType(GlassBottleItem.class);
		assigner.removeType(EnderEyeItem.class);
		assigner.removeType(SpawnEggItem.class);
		assigner.removeType(ExperienceBottleItem.class);
		assigner.removeType(WindChargeItem.class);
		assigner.removeType(WritableBookItem.class);
		assigner.removeType(WrittenBookItem.class);
		assigner.removeType(EmptyMapItem.class);
		assigner.removeType(FireworkRocketItem.class);
		assigner.removeType(ShieldItem.class);
		assigner.removeType(KnowledgeBookItem.class);
		assigner.removeType(TridentItem.class);
		assigner.removeType(CrossbowItem.class);
		assigner.removeType(GoatHornItem.class);
		assigner.removeType(HoneyBottleItem.class);
		assigner.removeType(BrushItem.class);
		assigner.removeType(OminousBottleItem.class);

		Map<Item, Item> graph = assigner.build();

		DefaultItemComponentEvents.MODIFY.register(context -> {
			for (Map.Entry<Item, Item> entry : graph.entrySet()) {
				Item item = entry.getKey();
				Item nextItem = entry.getValue();

				context.modify(item, builder -> {
					FoodComponent inheritComponent = item.getComponents().getOrDefault(DataComponentTypes.FOOD, defaultComponent);
					ItemStack convertStack = new ItemStack(nextItem);

					FoodComponent newComponent = new FoodComponent(inheritComponent.nutrition(), inheritComponent.saturation(), inheritComponent.canAlwaysEat(), inheritComponent.eatSeconds(), Optional.of(convertStack), inheritComponent.effects());
					builder.add(DataComponentTypes.FOOD, newComponent);
				});
			}
		});
	}
}
