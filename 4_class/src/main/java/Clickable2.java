public interface Clickable2 {
    void click();

    default void showOff() {
        System.out.println("I'm clickable2!!"); //Defulat Method
    }
}
