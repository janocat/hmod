
package hmod.launcher;

import flexbuilders.scripting.BuildScript;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Enrique Urra
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.PACKAGE)
public @interface LauncherExtensionInfo
{
    Class<? extends BuildScript> mainScript();
    String name() default "no name";
    String description() default "no description";
    String version() default "no version";
}
