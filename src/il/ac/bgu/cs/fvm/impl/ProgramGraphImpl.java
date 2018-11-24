package il.ac.bgu.cs.fvm.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import il.ac.bgu.cs.fvm.exceptions.DeletionOfAttachedStateException;
import il.ac.bgu.cs.fvm.exceptions.TransitionSystemPart;
import il.ac.bgu.cs.fvm.programgraph.PGTransition;
import il.ac.bgu.cs.fvm.programgraph.ProgramGraph;

public class ProgramGraphImpl<L, A> implements ProgramGraph<L, A>{

    private String name;
    private Set<List<String>> variablesInitialization;
    private Set<L> initialLocation;
    private Set<L> locations;
    private Set<PGTransition<L, A>> transitions;
    private Set<L> initialsThatCanBeRemoved;
    ProgramGraphImpl()
    {
        initialLocation = new HashSet<>();
        locations = new HashSet<>();
        transitions = new HashSet<>();
        variablesInitialization = new HashSet<>();
        initialsThatCanBeRemoved = new HashSet<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void addInitalization(List<String> initialization) {
        variablesInitialization.add(initialization);

    }

    @Override
    public Set<List<String>> getInitalizations() {
        return variablesInitialization;
    }


    @Override
    public void setInitial(L location, boolean cantRemove){
        if(!cantRemove){
            initialsThatCanBeRemoved.add(location);
        }
        locations.add(location);
        initialLocation.add(location);
    }

    @Override
    public Set<L> getInitialLocations() {
        return initialLocation;
    }



    @Override
    public void addLocation(L location) {
        locations.add(location);
    }

    @Override
    public Set<L> getLocations() {
        return locations;
    }

    private boolean isStateInUseInTransition(L location) {
        for(PGTransition<L, A> t : this.transitions)
            if(t.getFrom().equals(location) || t.getTo().equals(location))
                return true;

        return false;
    }

    @Override
    public void removeLocation(L location) {
        if(initialLocation.contains(location) && !initialsThatCanBeRemoved.contains(location))
            throw new DeletionOfAttachedStateException(location, TransitionSystemPart.INITIAL_STATES);
        if(isStateInUseInTransition(location))
            throw new DeletionOfAttachedStateException(location, TransitionSystemPart.TRANSITIONS);

        locations.remove(location);
    }

    @Override
    public void addTransition(PGTransition<L, A> transition) {
        transitions.add(transition);

    }

    @Override
    public void removeTransition(PGTransition<L, A> transition) {
        transitions.remove(transition);

    }

    @Override
    public Set<PGTransition<L, A>> getTransitions() {
        return transitions;
    }

    @Override
    public boolean equals(Object other){
        return equalsProgramGraph((ProgramGraphImpl<L,A>)other);
    }

    private boolean equalsProgramGraph(ProgramGraphImpl<L, A> other)
    {
        return this.name.equals(other.getName()) && this.variablesInitialization.equals(other.getInitalizations()) &&
                this.initialLocation.equals(other.getInitialLocations()) && this.locations.equals(other.getLocations()) &&
                this.transitions.equals(other.getTransitions());
    }

    public int hashCode()
    {
        final int prime = 37;
        int result = 1;
        result = prime * result + this.name.hashCode();
        result = prime * result + this.variablesInitialization.hashCode();
        result = prime * result + this.initialLocation.hashCode();
        result = prime * result + this.locations.hashCode();
        result = prime * result + this.transitions.hashCode();
        return result;
    }

}
