package general;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface Game
{
	String name() default "Untitled Game";
	String music();
	String method() default "run";
	
	String init() default "init";
	String preMove() default "preMove";
	String postMove() default "postMove";
	
	Class<? extends Board> boardClass() default Board.class;
	int[] colors() default {};
}
