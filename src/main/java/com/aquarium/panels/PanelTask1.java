package com.aquarium.panels;

import com.aquarium.Food;
import com.aquarium.fish.FishTask1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Панель для Задачи 1 - Взаимная блокировка
 */
public class PanelTask1 extends JPanel {
    private FishTask1[] fish = new FishTask1[3];
    private Food food;
    private boolean started = false;

    public PanelTask1() {
        super();
        setBackground(new Color(0, 105, 148)); // Океанский синий
        food = new Food(300); // 300 единиц еды

        // Создаем 3 рыбки с разными атрибутами
        fish[0] = new FishTask1(40, 30, 50, 0, 2, this, Color.YELLOW, "Золотая рыбка", 15, food, 1);
        fish[1] = new FishTask1(35, 25, 200, 0, 2, this, Color.GREEN, "Гуппи", 10, food, 2);
        fish[2] = new FishTask1(45, 35, 350, 0, 2, this, Color.RED, "Бетта", 20, food, 3);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!started) {
                    started = true;
                    FishTask1.resetThreadNum();
                    for (FishTask1 f : fish) {
                        f.start();
                    }
                    System.out.println("=== ЗАДАЧА 1: Взаимная блокировка запущена ===");
                    System.out.println("Рыбки будут двигаться строго последовательно");
                    System.out.println("Нажмите на другую вкладку, чтобы попробовать другие задачи");
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        // Рисуем индикатор еды
        g.setColor(Color.WHITE);
        g.drawString("Еды осталось: " + food.getTotalVolume() + "/" + food.getInitialVolume(), 10, 20);
        g.drawString("Задача 1: Взаимная блокировка - Последовательное движение", 10, 40);
        g.drawString("Нажмите для запуска", 10, 60);

        // Рисуем рыбок
        for (FishTask1 f : fish) {
            f.paint(g);
        }
    }

    public void stopAllFish() {
        for (FishTask1 f : fish) {
            f.stopFish();
        }
    }
}

