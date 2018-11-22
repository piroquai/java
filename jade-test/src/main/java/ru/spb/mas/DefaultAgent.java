package ru.spb.mas;

import jade.core.Agent;
import sun.applet.Main;

import java.lang.Math;

import java.util.concurrent.TimeUnit;

public class DefaultAgent extends Agent {
    private String[] linkedAgents;
    private float number;

    @Override protected void setup() {
        int id = Integer.parseInt(getAID().getLocalName());
        number = (int)(Math.random()*10);
//        linkedAgents =
        System.out.println("Agent # " + id + " number= " + number);
        addBehaviour(new FindAverage(this, TimeUnit.SECONDS.toMillis(1)));

    }
}