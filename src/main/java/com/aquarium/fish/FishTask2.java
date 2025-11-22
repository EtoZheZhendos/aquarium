package com.aquarium.fish;

import com.aquarium.Food;
import com.aquarium.Semaphore;
import java.awt.*;

/**
 * Задача 2: Использование семафора для координации
 * Рыбки поочередно движутся, используя синхронизацию через семафоры
 */
public class FishTask2 extends Fish {
    private Food food;
    private Semaphore mySemaphore;
    private Semaphore nextSemaphore;

    public FishTask2(int dx, int dy, int x, int y, int speedY,
                     Component container, Color color, String fishName,
                     int portionSize, Food food,
                     Semaphore mySemaphore, Semaphore nextSemaphore) {
        super(dx, dy, x, y, speedY, container, color, fishName, portionSize);
        this.food = food;
        this.mySemaphore = mySemaphore;
        this.nextSemaphore = nextSemaphore;
    }

    @Override
    public void run() {
        running = true;
        System.out.println(fishName + " запущена с Семафором");

        while (running && food.getTotalVolume() > 0) {
            try {
                // ждем своей очереди
                mySemaphore.acquire();

                if (!running) break;

                // движение и использование ресурсов
                move();
                container.repaint();

                // едим еду периодически
                if (y % 50 == 0 && food.getTotalVolume() > 0) {
                    food.consume(portionSize, fishName);
                }

                Thread.sleep(50);

                // сигнализируем следующей рыбке
                nextSemaphore.release();

            } catch (InterruptedException e) {
                break;
            }
        }

        System.out.println(fishName + " завершила работу");
    }
}

