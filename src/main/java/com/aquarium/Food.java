package com.aquarium;

/**
 * Разделяемый ресурс - Еда с общим объемом
 * Рыбки конкурируют за доступ к этому разделяемому ресурсу
 */
public class Food {
    private int totalVolume;
    private final int initialVolume;

    public Food(int initialVolume) {
        this.initialVolume = initialVolume;
        this.totalVolume = initialVolume;
    }

    public synchronized boolean consume(int portion, String fishName) {
        if (totalVolume >= portion) {
            totalVolume -= portion;
            System.out.println(fishName + " съела " + portion + " единиц. Осталось еды: " + totalVolume);
            return true;
        } else if (totalVolume > 0) {
            System.out.println(fishName + " съела последние " + totalVolume + " единиц. Еда закончилась!");
            totalVolume = 0;
            return true;
        }
        return false;
    }

    public synchronized int getTotalVolume() {
        return totalVolume;
    }

    public void reset() {
        totalVolume = initialVolume;
    }

    public int getInitialVolume() {
        return initialVolume;
    }
}

