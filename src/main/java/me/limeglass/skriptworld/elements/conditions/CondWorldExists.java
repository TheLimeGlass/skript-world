package me.limeglass.skriptworld.elements.conditions;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.skriptworld.lang.SkriptWorldCondition;
import me.limeglass.skriptworld.utils.annotations.Patterns;

@Name("Worlds - Exists")
@Description("Check if a world exists. This is because Skript throws a can't understand condition if the world doesn't exist.")
@Patterns("world %string% (1¦does|2¦does(n't| not)) exist")
public class CondWorldExists extends SkriptWorldCondition {

	public boolean check(Event event) {
		if (isNull(event, 0)) return false;
		return (Bukkit.getWorld(expressions.getSingle(event, String.class)) != null) ? isNegated() : !isNegated();
	}
}