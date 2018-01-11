package me.limeglass.skriptworld.elements.effects;

import org.bukkit.WorldCreator;
import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.skriptworld.lang.SkriptWorldEffect;
import me.limeglass.skriptworld.utils.annotations.Patterns;

@Name("Worlds - Generate/Load world")
@Description("Generate/Load the defined world string(s).")
@Patterns("(generate|load|create) [the] world[s] %strings% [with generator %-string%]")
public class EffWorldGenerate extends SkriptWorldEffect {

	@Override
	protected void execute(Event event) {
		if (isNull(event, 0)) return;
		for (String world : expressions.getAll(event, String.class)) {
			WorldCreator creator = new WorldCreator(world);
			if (!isNull(event, 1)) creator.generator(expressions.getSingle(event, String.class));
			creator.createWorld();
		}
	}
}
