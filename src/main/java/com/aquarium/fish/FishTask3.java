package com.aquarium.fish;

import com.aquarium.Food;
import com.aquarium.FeedingZone;
import java.awt.*;

/**
 * Задача 3: Использование монитора (Зона кормления)
 * Только одна рыбка может находиться в зоне кормления одновременно
 */
public class FishTask3 extends Fish {
    private Food food;
    private FeedingZone feedingZone;

    public FishTask3(int dx, int dy, int x, int y, int speedY,
                     Component container, Color color, String fishName,
                     int portionSize, Food food, FeedingZone feedingZone) {
        super(dx, dy, x, y, speedY, container, color, fishName, portionSize);
        this.food = food;
        this.feedingZone = feedingZone;
    }

    @Override
    public void run() {
        running = true;
        System.out.println(fishName + " запущена с Монитором");

        while (running && food.getTotalVolume() > 0) {
            // проверяем, находится ли рыбка в зоне кормления
            if (feedingZone.isInZone(x + dx / 2, y + dy / 2)) {
                // блокируем зону кормления (монитор)
                synchronized (feedingZone) {
                    System.out.println(fishName + " вошла в зону кормления");

                    // остаемся в зоне и едим
                    while (feedingZone.isInZone(x + dx / 2, y + dy / 2) && running) {
                        move();
                        container.repaint();

                        // едим, пока находимся в зоне
                        if (food.getTotalVolume() > 0) {
                            food.consume(portionSize, fishName);
                        }

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }

                    System.out.println(fishName + " покинула зону кормления");
                }
            } else {
                // обычное движение вне зоны кормления
                move();
                container.repaint();

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

        System.out.println(fishName + " завершила работу");
    }
}

