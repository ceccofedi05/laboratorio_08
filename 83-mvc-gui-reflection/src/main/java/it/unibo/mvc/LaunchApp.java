package it.unibo.mvc;

import java.lang.reflect.InvocationTargetException;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws SecurityException 
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args) throws InstantiationException, IllegalAccessException, 
        IllegalArgumentException, InvocationTargetException, NoSuchMethodException, 
        SecurityException, ClassNotFoundException {

        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        final String View_One = "it.unibo.mvc.view.DrawNumberStandardOutputView";
        final String View_Two = "it.unibo.mvc.view.DrawNumberSwingView";
        for(int i = 0; i < 3; i++){
            final DrawNumberView FirstView = (DrawNumberView) Class .forName(View_One).getDeclaredConstructor().newInstance();
            final DrawNumberView SecondView = (DrawNumberView) Class .forName(View_Two).getDeclaredConstructor().newInstance();
            app.addView(FirstView);
            app.addView(SecondView);  
        }
        
    }
}
