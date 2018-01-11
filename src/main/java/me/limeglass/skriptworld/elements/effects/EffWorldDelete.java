package me.limeglass.skriptworld.elements.effects;

import java.io.File;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.skriptworld.lang.SkriptWorldEffect;
import me.limeglass.skriptworld.utils.annotations.Patterns;

@Name("World - Delete")
@Description("Delete the defined world(s).")
@Patterns("(remove|delete) [the] world[s] %worlds/strings%")
public class EffWorldDelete extends SkriptWorldEffect {

	@Override
	protected void execute(Event event) {
		if (isNull(event, 0)) return;
		for (Object object : expressions.get(0).getAll(event)) {
			World world = null;
			if (object instanceof World) world = (World)object;
			else if (object instanceof String) {
				try {
					if (UUID.fromString((String)object) != null) {
						world = Bukkit.getWorld(UUID.fromString((String)object));
					} else {
						world = Bukkit.getWorld((String)object);
					}
				} catch (Exception e) {}
			}
			if (world != null) {
				Bukkit.unloadWorld(world, true);
				delete(world.getWorldFolder());
			}
		}
	}
	
	private void delete(File path) {
		if (path.exists()) {
			for (File file : path.listFiles()) {
				if (file.isDirectory()) {
					delete(file);
				} else {
					file.delete();
				}
			}
		}
	}
}
