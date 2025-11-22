package com.aquarium;

import com.aquarium.panels.PanelTask1;
import com.aquarium.panels.PanelTask2;
import com.aquarium.panels.PanelTask3;

import javax.swing.*;
import java.awt.*;

/**
 * Главное приложение Аквариум
 * Демонстрирует три паттерна многопоточного программирования с рыбками и едой
 */
public class AquariumApp extends JFrame {
    private JTabbedPane tabbedPane;
    private PanelTask1 panelTask1;
    private PanelTask2 panelTask2;
    private PanelTask3 panelTask3;

    public AquariumApp() {
        setTitle("Симуляция Аквариума - Демонстрация многопоточности");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        // Создаем панель с вкладками для трех задач
        tabbedPane = new JTabbedPane();

        // Задача 1: Взаимная блокировка
        panelTask1 = new PanelTask1();
        tabbedPane.addTab("Задача 1: Взаимная блокировка", panelTask1);

        // Задача 2: Семафор
        panelTask2 = new PanelTask2();
        tabbedPane.addTab("Задача 2: Семафор", panelTask2);

        // Задача 3: Монитор
        panelTask3 = new PanelTask3();
        tabbedPane.addTab("Задача 3: Монитор", panelTask3);

        add(tabbedPane);

        // Добавляем информационную панель внизу
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.DARK_GRAY);
        JLabel infoLabel = new JLabel(
            "<html><center>🐠 Симуляция Аквариума - Многопоточность 🐠<br>" +
            "Рыбки конкурируют за еду, используя различные методы синхронизации<br>" +
            "Нажмите на каждую вкладку, а затем кликните на панель для запуска</center></html>"
        );
        infoLabel.setForeground(Color.WHITE);
        infoPanel.add(infoLabel);
        add(infoPanel, BorderLayout.SOUTH);

        setVisible(true);

        System.out.println("========================================");
        System.out.println("  СИМУЛЯЦИЯ АКВАРИУМА - МНОГОПОТОЧНОСТЬ");
        System.out.println("========================================");
        System.out.println("Реализовано три задачи:");
        System.out.println("1. Взаимная блокировка - Последовательный доступ");
        System.out.println("2. Семафор - Координированные ходы");
        System.out.println("3. Монитор - Исключительный доступ к зоне кормления");
        System.out.println("========================================");
        System.out.println();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AquariumApp();
        });
    }
}

