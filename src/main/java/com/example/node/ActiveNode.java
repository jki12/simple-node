package com.example.node;

import lombok.Getter;

@Getter
public abstract class ActiveNode extends Node implements Runnable {
    protected long startTime;
    protected Thread thread;
    protected long interval = 1_000;

    protected ActiveNode(String name) {
        super(name);

        thread = new Thread(this, name);
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void start() {
        startTime = System.currentTimeMillis();

        thread.start();
    }

    public void stop() {
        thread.interrupt();
    }

    abstract public void preprocess();

    abstract public void process();

    abstract public void postprocess();

    public void run() {
        preprocess();

        while (!Thread.currentThread().isInterrupted()) {
                try {
                    process();

                    Thread.sleep(interval);
                } catch (Exception ignore) {
                    Thread.currentThread().interrupt();
                }
            }

        postprocess();
    }
}
