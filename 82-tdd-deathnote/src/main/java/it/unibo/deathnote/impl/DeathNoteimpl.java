package it.unibo.deathnote.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteimpl implements DeathNote {

    private Map<String, List<String>> deathnote;
    private String lastwritten;
    private final int CAUSE_INDEX = 0;
    private final int DETAILS_INDEX = 1;
    private long START_TIME = 0;
    private final long CAUSE_LIMIT_TIME = 40;
    private final long DETAILS_LIMIT_TIME = 6040;
    final String heart_attack = "heart attack";

    public DeathNoteimpl(){
        deathnote = new HashMap<>();
    }

    @Override
    public String getRule(int ruleNumber) {
        if(ruleNumber < 1 || ruleNumber > RULES.size()){
            throw new IllegalArgumentException("Invalid rule number");
        }
        return RULES.get(ruleNumber - 1);
    }

    @Override
    public void writeName(String name) {
        if(name == null){
            throw new NullPointerException("Name cannot be null");
        }
        START_TIME = System.currentTimeMillis();
        deathnote.put(name, new ArrayList<>(List.of("","")));
        deathnote.get(name).set(CAUSE_INDEX, heart_attack);
        lastwritten = name;
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if(lastwritten == null || cause == null){
            throw new IllegalStateException("No name written");
        }
        if(System.currentTimeMillis() - START_TIME <= CAUSE_LIMIT_TIME){
            deathnote.get(lastwritten).set(CAUSE_INDEX, cause);
            return true;
        }
        return false;
    }

    @Override
    public boolean writeDetails(String details) {
        if(lastwritten == null || details == null){
            throw new IllegalStateException("No name written");
        }
        if(System.currentTimeMillis() - START_TIME <= DETAILS_LIMIT_TIME){
            deathnote.get(lastwritten).set(DETAILS_INDEX, details);
            return true;
        }
        return false;
    }

    @Override
    public String getDeathCause(String name) {
        if(deathnote.get(name) == null){
            throw new IllegalArgumentException("There is no one named like that in this notebook");
        }
        return deathnote.get(name).get(CAUSE_INDEX);
    }

    @Override
    public String getDeathDetails(String name) {
        if(deathnote.get(name) == null){
            throw new IllegalArgumentException("There is no one named like that in this notebook");
        }
        return deathnote.get(name).get(DETAILS_INDEX);
    }

    @Override
    public boolean isNameWritten(String name) {
        return deathnote.containsKey(name);
    }
    
}
