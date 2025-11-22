package com.aquarium.fish;

import java.awt.*;

/**
 * Базовый класс Рыбки - объект-поток с атрибутами и методами
 */
public abstract class Fish extends Thread {
    protected int x, y; // положение на панели
    protected int dx, dy; // размер
    protected int speedY; // скорость вертикального движения
    protected Color color;
    protected String fishName;
    protected Component container;
    protected volatile boolean running = false;
    protected int portionSize; // размер порции еды

    public Fish(int dx, int dy, int x, int y, int speedY, 
                Component container, Color color, String fishName, int portionSize) {
        this.dx = dx;
        this.dy = dy;
        this.x = x;
        this.y = y;
        this.speedY = speedY;
        this.container = container;
        this.color = color;
        this.fishName = fishName;
        this.portionSize = portionSize;
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, dx, dy);
        g.setColor(Color.WHITE);
        g.drawString(fishName, x - 10, y - 5);
    }

    public void move() {
        y += speedY;
        if (y > container.getHeight()) {
            y = 0;
        }
        if (y < 0) {
            y = container.getHeight();
        }
    }

    public void stopFish() {
        running = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPortionSize() {
        return portionSize;
    }

    public String getFishName() {
        return fishName;
    }
}

