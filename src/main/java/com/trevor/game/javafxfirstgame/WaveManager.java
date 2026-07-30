package com.trevor.game.javafxfirstgame;

import java.util.ArrayList;
import java.util.LinkedList;

public class WaveManager {
    private final LinkedList<ArrayList<EnemyData>> waveQueue;

    private ArrayList<EnemyData> currentWave;

    public WaveManager() {
        waveQueue = new LinkedList<>();
    }

    public LinkedList<ArrayList<EnemyData>> getWaveQueue() {
        return waveQueue;
    }
    public ArrayList<EnemyData> getCurrentWave() {
        return currentWave;
    }

    public void setCurrentWave(ArrayList<EnemyData> currentWave) {
        this.currentWave = currentWave;
    }
    public boolean isEmpty() {
        return waveQueue.isEmpty();
    }

    public boolean nextWave() {
        if (waveQueue.isEmpty()) {
            currentWave = null;
            return false;
        }
        currentWave = waveQueue.getFirst();
        return true;
    }
    public void removeWave() {
        waveQueue.removeFirst();
    }
    public void addToWave(int index,
                          double gravityMult,
                          double restitution,
                          double startingXVelocity,
                          double size,
                          double mass) {
        EnemyData data = new EnemyData(EnemyType.CIRCLE, gravityMult, restitution, startingXVelocity, mass, size, size);
        if (waveQueue.isEmpty() && index == 0) {
            waveQueue.add(new ArrayList<>());
        }
        waveQueue.get(index).add(data);
    }
    public ArrayList<EnemyData> createCircleWave(int count,
                                                 double gravityMult,
                                                 double restitution,
                                                 double startingXVelocity,
                                                 double size,
                                                 double mass) {
        ArrayList<EnemyData> wave = new ArrayList<>();




        for (int i = 0; i < count; i++) {
            size = (Math.random() * 2 - 1) * size*0.2 + size;

            EnemyData data = new EnemyData(EnemyType.CIRCLE, gravityMult, restitution, startingXVelocity, mass, size, size);
            wave.add(data);

        }

        waveQueue.add(wave);
        return wave;
    }
}

