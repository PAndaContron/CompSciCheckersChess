package general;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Target;

@Target(TYPE)
public @interface RegisterGame
{
	String name() default "Untitled Game";
	int numPlayers() default 2;
}
