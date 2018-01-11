package me.limeglass.skriptworld.utils;

import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import me.limeglass.skriptworld.SkriptWorld;

public class TypeClassInfo<T> {

	private final Class<T> clazz;
	private final String codeName;
	private final ClassInfo<T> classInfo;
	private final String user;

	private TypeClassInfo(Class<T> clazz, String codeName, @Nullable String user) {
		this.clazz = clazz;
		this.codeName = codeName;
		this.user = user;
		classInfo = new ClassInfo<>(clazz, codeName);
	}
	
	public static <T> TypeClassInfo<T> create(Class<T> clazz, String codeName, @Nullable String user) {
		if (user == null) user = codeName + "s?";
		return new TypeClassInfo<>(clazz, codeName, user);
	}
	
	public String toString(T clazz, int i) {
		return clazz.toString();
	}
	
	public String toVariableNameString(T clazz) {
		return codeName + ':' + clazz.toString();
	}
	
	public T parse(String s, ParseContext parseContext){
		return null;
	}
	
	public void register(){
		if (Classes.getExactClassInfo(clazz) == null) {
			Classes.registerClass(classInfo.user(user).defaultExpression(new EventValueExpression<>(clazz)).parser(new Parser<T>(){
	
				@Override
				public String getVariableNamePattern() {
					return codeName + ":.+";
				}
	
				@Override
				public T parse(String s, ParseContext parseContext) {
					return null;
				}
	
				@Override
				public String toString(T t, int i) {
					return t.toString();
				}
	
				@Override
				public String toVariableNameString(T t) {
					return codeName + ':' + t.toString();
			}}).serializeAs(clazz));
			SkriptWorld.debugMessage("&5Registered Type '" + codeName + "' with return class " + clazz.getName());
		}
	}
}