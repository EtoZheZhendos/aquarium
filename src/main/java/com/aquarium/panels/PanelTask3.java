package com.aquarium.panels;

import com.aquarium.FeedingZone;
import com.aquarium.Food;
import com.aquarium.fish.FishTask3;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Панель для Задачи 3 - Монитор (Зона кормления)
 */
public class PanelTask3 extends JPanel {
    private FishTask3[] fish = new FishTask3[3];
    private Food food;
    private FeedingZone feedingZone;
    private boolean started = false;

    public PanelTask3() {
        super();
        setBackground(new Color(0, 105, 148)); // Океанский синий
        food = new Food(300); // 300 единиц еды

        // Создаем зону кормления (объект-монитор)
        feedingZone = new FeedingZone(150, 100, this, new Color(255, 140, 0, 100)); // Полупрозрачный оранжевый

        // Создаем 3 рыбки, которые конкурируют за зону кормления
        fish[0] = new FishTask3(40, 30, 50, 0, 2, this, Color.YELLOW, 
                                "Золотая рыбка", 15, food, feedingZone);
        fish[1] = new FishTask3(35, 25, 200, 0, 3, this, Color.GREEN, 
                                "Гуппи", 10, food, feedingZone);
        fish[2] = new FishTask3(45, 35, 350, 0, 1, this, Color.RED, 
                                "Бетта", 20, food, feedingZone);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!started) {
                    started = true;
                    for (FishTask3 f : fish) {
                        f.start();
                    }
                    System.out.println("=== ЗАДАЧА 3: Монитор (Зона кормления) запущена ===");
                    System.out.println("Только одна рыбка может находиться в зоне кормления одновременно");
                    System.out.println("Нажмите на другую вкладку, чтобы попробовать другие задачи");
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Рисуем зону кормления сначала (фон)
        feedingZone.paint(g);

        // Рисуем индикатор еды
        g.setColor(Color.WHITE);
        g.drawString("Еды осталось: " + food.getTotalVolume() + "/" + food.getInitialVolume(), 10, 20);
        g.drawString("Задача 3: Монитор - Исключительный доступ к зоне кормления", 10, 40);
        g.drawString("Нажмите для запуска", 10, 60);

        // Рисуем рыбок
        for (FishTask3 f : fish) {
            f.paint(g);
        }
    }

    public void stopAllFish() {
        for (FishTask3 f : fish) {
            f.stopFish();
        }
    }
}

