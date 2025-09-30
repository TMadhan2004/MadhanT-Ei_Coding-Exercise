public interface Button {
    void render();
    void onClick(Runnable handler);
    void click();
}
