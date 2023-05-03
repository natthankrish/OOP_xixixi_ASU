package program.plugin;

import javafx.scene.Group;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ChartPluginAnnotation {
    String showLineChart() default "javafx.scene.Group";
    String showBarChart() default "javafx.scene.Group";
    String showPieChart() default "javafx.scene.Group";
}
