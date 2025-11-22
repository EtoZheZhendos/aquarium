package com.aquarium;

/**
 * Реализация семафора для Задачи 2
 */
public class Semaphore {
    private int counter;

    public Semaphore() {
        this(0);
    }

    public Semaphore(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(i + " < 0");
        }
        counter = i;
    }

    public synchronized void release() { // V-операция (освобождение)
        if (counter == 0) {
            this.notify();
        }
        counter++;
    }

    public synchronized void acquire() throws InterruptedException { // P-операция (захват)
        while (counter == 0) {
            this.wait();
        }
        counter--;
    }
}

