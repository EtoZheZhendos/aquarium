package com.aquarium.fish;

import com.aquarium.Food;
import java.awt.*;

/**
 * Задача 1: Взаимная блокировка - Последовательное движение
 * Рыбки движутся строго последовательно, используя общую переменную threadNum
 */
public class FishTask1 extends Fish {
    private Food food;
    private static volatile int threadNum = 1; // общая переменная для последовательного доступа
    private int myThreadNum;

    public FishTask1(int dx, int dy, int x, int y, int speedY,
                     Component container, Color color, String fishName, 
                     int portionSize, Food food, int myThreadNum) {
        super(dx, dy, x, y, speedY, container, color, fishName, portionSize);
        this.food = food;
        this.myThreadNum = myThreadNum;
    }

    @Override
    public void run() {
        running = true;
        System.out.println(fishName + " started (Thread " + myThreadNum + ")");
        
        while (running && food.getTotalVolume() > 0) {
            // Ждем своей очереди (взаимная блокировка)
            while (threadNum != myThreadNum && running) {
                Thread.yield();
            }

            if (!running) break;

            // Наша очередь - используем разделяемый ресурс
            move();
            container.repaint();
            
            // Едим еду периодически
            if (y % 50 == 0 && food.getTotalVolume() > 0) {
                food.consume(portionSize, fishName);
            }

            // Передаем доступ следующему потоку
            threadNum = (myThreadNum % 3) + 1;

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                break;
            }
        }
        
        System.out.println(fishName + " finished");
    }

    public static void resetThreadNum() {
        threadNum = 1;
    }
}

