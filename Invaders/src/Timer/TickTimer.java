
package Timer;
/**
 * Para dar un tiempo determinado, para que un texto este por unos segundos
 */
public class TickTimer {

    private float tick, tickTarget;

    public TickTimer(float tickTarget) {
        this.tickTarget = tickTarget;
        this.tick = 0;
    }
    /**
     * Empieza el despliegue del tiempo
     * @param delta 
     */
    public void tick(double delta) {
        if (tick <= tickTarget) {
            tick += 1 * delta;
        }
    }
    /**
     * Para cuando ya debe desaparecer porque paso el tiempo
     * @return 
     */
    public boolean isEventReady() {
        if (tick >= tickTarget) {
            resetTimer();
            return true;
        }
        return false;
    }
    /**
     * Empieza a contar de nuevo con el tiempo de inicio
     */
    private void resetTimer() {
        tick = 0;
    }
}
