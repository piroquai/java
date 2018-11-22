package ru.spb.mas;

import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

import java.util.HashMap;

class MainController {
    private static final int numberOfAgents = 5;

    void initAgents() {
// Retrieve the singleton instance of the JADE Runtime
        Runtime rt = Runtime.instance();
//Create a container to host the Default Agent
        Profile p = new ProfileImpl();
        p.setParameter(Profile.MAIN_HOST, "localhost");
        p.setParameter(Profile.MAIN_PORT, "10098");
        p.setParameter(Profile.GUI, "false");
        ContainerController cc = rt.createMainContainer(p);

        HashMap<Integer, String> neighbours = new HashMap<Integer, String>();
        neighbours.put(1,"2,5");
        neighbours.put(2, "1,3,5");
        neighbours.put(2, "2,4,5");
        neighbours.put(2, "3,5");
        neighbours.put(2, "1,2,5");

        try {
            for (int i = 1; i <= MainController.numberOfAgents; i++) {
                AgentController agent = cc.createNewAgent(Integer.toString(i),"ru.spb.mas.DefaultAgent", new Object[]{neighbours.get(i)});

                agent.start();
            }
        } catch
                (Exception e) {
            e.printStackTrace();
        }
    }

}
