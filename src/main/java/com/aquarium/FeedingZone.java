package com.aquarium;

import java.awt.*;

/**
 * Зона кормления - аналог Туннеля из примера с Мячом
 * Только одна рыбка может находиться в зоне кормления одновременно (паттерн Монитор)
 */
public class FeedingZone {
    private int x, y;
    private int dx, dy;
    private Component container;
    private Color color;

    public FeedingZone(int dx, int dy, Component container, Color color) {
        this.dx = dx;
        this.dy = dy;
        this.container = container;
        this.color = color;
        updatePosition();
    }

    private void updatePosition() {
        x = container.getWidth() / 2 - dx / 2;
        y = container.getHeight() / 2 - dy / 2;
    }

    public void paint(Graphics g) {
        updatePosition();
        g.setColor(color);
        g.fillRect(x, y, dx, dy);
        g.setColor(Color.WHITE);
        g.drawString("ЗОНА КОРМЛЕНИЯ", x + 10, y + dy / 2);
    }

    public boolean isInZone(int x1, int y1) {
        updatePosition();
        return x1 > x && x1 < (x + dx) && y1 > y && y1 < (y + dy);
    }

    public int getX() {
        updatePosition();
        return x;
    }

    public int getY() {
        updatePosition();
        return y;
    }

    public int getWidth() {
        return dx;
    }

    public int getHeight() {
        return dy;
    }
}

