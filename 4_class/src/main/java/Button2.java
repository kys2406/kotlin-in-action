public class Button2 implements Clickable, Focusable, Clickable2 {
    @Override
    public void click() {
        System.out.println("I was clicked");
    }

    @Override
    public void showOff() {
//        Clickable.super.showOff();
    }

    @Override
    public void setFocus(boolean b) {
//        Focusable.super.setFocus(b);
    }
}
