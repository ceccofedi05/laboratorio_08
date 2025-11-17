package it.unibo.mvc.view;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;
import it.unibo.mvc.api.DrawNumberController;

public final class DrawNumberStandardOutputView implements DrawNumberView {

    public DrawNumberStandardOutputView() {
        // Nothing to initialize
    }
    @Override
    public void setController(final DrawNumberController observer) {
        /*
         * This is output only
         */
    }

    @Override
    public void start() {
        /*
         * Output is always ready
         */
    }

    @Override
    public void result(final DrawResult res) {
        System.out.println(res.getDescription());
    }

}