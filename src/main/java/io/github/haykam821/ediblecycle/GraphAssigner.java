package io.github.haykam821.ediblecycle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

public class GraphAssigner {
	private final List<Item> items = Registries.ITEM.stream().collect(Collectors.toCollection(ArrayList::new));

	public void removeType(Class<? extends Item> clazz) {
		this.items.removeIf(item -> clazz.isInstance(item));
	}

	public Map<Item, Item> build() {
		Collections.shuffle(this.items);

		Map<Item, Item> graph = new HashMap<>();

		if (!this.items.isEmpty()) {
			Item initialItem = this.items.get(0);

			if (this.items.size() > 1) {
				Iterator<Item> unassignedIterator = this.items.iterator();

				Item item = unassignedIterator.next();

				while (unassignedIterator.hasNext()) {
					Item nextItem = unassignedIterator.next();
					graph.put(item, nextItem);

					item = nextItem;
				}

				graph.put(item, initialItem);
			} else {
				graph.put(initialItem, initialItem);
			}
		}

		return graph;
	}
}
