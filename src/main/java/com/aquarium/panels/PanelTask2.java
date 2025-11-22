package com.aquarium.panels;

import com.aquarium.Food;
import com.aquarium.Semaphore;
import com.aquarium.fish.FishTask2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Панель для Задачи 2 - Координация через семафоры
 */
public class PanelTask2 extends JPanel {
    private FishTask2[] fish = new FishTask2[3];
    private Food food;
    private Semaphore[] semaphores = new Semaphore[3];
    private boolean started = false;

    public PanelTask2() {
        super();
        setBackground(new Color(0, 105, 148)); // Океанский синий
        food = new Food(300); // 300 единиц еды

        // Создаем семафоры для координации
        semaphores[0] = new Semaphore(1); // Первая рыбка начинает
        semaphores[1] = new Semaphore(0);
        semaphores[2] = new Semaphore(0);

        // Создаем 3 рыбки с координацией через семафоры
        fish[0] = new FishTask2(40, 30, 50, 0, 2, this, Color.YELLOW, 
                                "Золотая рыбка", 15, food, semaphores[0], semaphores[1]);
        fish[1] = new FishTask2(35, 25, 200, 0, 2, this, Color.GREEN, 
                                "Гуппи", 10, food, semaphores[1], semaphores[2]);
        fish[2] = new FishTask2(45, 35, 350, 0, 2, this, Color.RED, 
                                "Бетта", 20, food, semaphores[2], semaphores[0]);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!started) {
                    started = true;
                    for (FishTask2 f : fish) {
                        f.start();
                    }
                    System.out.println("=== ЗАДАЧА 2: Координация через семафоры запущена ===");
                    System.out.println("Рыбки поочередно движутся, используя семафоры");
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
        g.drawString("Задача 2: Семафор - Координированное движение", 10, 40);
        g.drawString("Нажмите для запуска", 10, 60);

        // Рисуем рыбок
        for (FishTask2 f : fish) {
            f.paint(g);
        }
    }

    public void stopAllFish() {
        for (FishTask2 f : fish) {
            f.stopFish();
        }
    }
}

