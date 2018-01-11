package me.limeglass.skriptworld.utils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//If other plugin(s) are present, don't register this syntax.
//Needs the Strings of the plugin(s) name.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AntiDependency {
	public String[] value();
}

