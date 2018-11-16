package il.ac.bgu.cs.fvm.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import il.ac.bgu.cs.fvm.exceptions.DeletionOfAttachedActionException;
import il.ac.bgu.cs.fvm.exceptions.DeletionOfAttachedAtomicPropositionException;
import il.ac.bgu.cs.fvm.exceptions.DeletionOfAttachedStateException;
import il.ac.bgu.cs.fvm.exceptions.FVMException;
import il.ac.bgu.cs.fvm.exceptions.InvalidLablingPairException;
import il.ac.bgu.cs.fvm.exceptions.InvalidTransitionException;
import il.ac.bgu.cs.fvm.exceptions.StateNotFoundException;
import il.ac.bgu.cs.fvm.exceptions.TransitionSystemPart;
import il.ac.bgu.cs.fvm.transitionsystem.Transition;
import il.ac.bgu.cs.fvm.transitionsystem.TransitionSystem;

public class TransitionSystemImpl<STATE, ACTION, ATOMIC_PROPOSITION> implements TransitionSystem<STATE, ACTION, ATOMIC_PROPOSITION>  {
    private String name;
    private Set<ACTION> actions;
    private Set<STATE> initialStates;
    private Set<STATE> states;
    private Set<Transition<STATE, ACTION>> transitions;
    private Set<ATOMIC_PROPOSITION> atomicProp;
    private Map<STATE, Set<ATOMIC_PROPOSITION>> stateToLabels;

    TransitionSystemImpl()
    {
        this.actions = new HashSet<>();
        this.initialStates = new HashSet<>();
        this.states = new HashSet<>();
        this.transitions = new HashSet<>();
        this.atomicProp = new HashSet<>();
        this.stateToLabels = new HashMap<>();
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
    public void addAction(ACTION action) {
        this.actions.add(action);
    }

    @Override
    public void setInitial(STATE var1, boolean var2) throws StateNotFoundException {
        if(!this.states.contains(var1))
            throw new StateNotFoundException("the state is not one of the states");

        if (var2) {
            this.initialStates.add(var1);
        } else {
            addState(var1);
        }
    }

    @Override
    public Set<STATE> getInitialStates() {
        return this.initialStates;
    }


    @Override
    public void addState(STATE state) {
        this.states.add(state);
    }

    private boolean isStateInUseInTransition(STATE state) {
        for(Transition<STATE, ACTION> transition : this.transitions) {
            if (transition.getFrom().equals(state) || transition.getTo().equals(state)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void removeState(STATE state) throws FVMException {
        if(isStateInUseInTransition(state)) {
            throw new DeletionOfAttachedStateException(state, TransitionSystemPart.TRANSITIONS);
        }

        if(this.stateToLabels.containsKey(state) && !this.stateToLabels.get(state).isEmpty()) {
            throw new DeletionOfAttachedStateException(state, TransitionSystemPart.LABELING_FUNCTION);
        }

        if(this.initialStates.contains(state)) {
            throw new DeletionOfAttachedStateException(state, TransitionSystemPart.INITIAL_STATES);
        }

        this.states.remove(state);
    }

    @Override
    public Set<STATE> getStates() {
        return this.states;
    }

    @Override
    public void addTransition(Transition<STATE, ACTION> transition) throws FVMException {
        if(!(this.states.contains(transition.getFrom()) && this.states.contains(transition.getTo())
                && this.actions.contains(transition.getAction()))) {
            throw new InvalidTransitionException(transition);

        }
        this.transitions.add(transition);
    }

    @Override
    public void removeTransition(Transition<STATE, ACTION> transition) {
        this.transitions.remove(transition);
    }

    @Override
    public Set<Transition<STATE, ACTION>> getTransitions() {
        return this.transitions;
    }

    @Override
    public Set<ACTION> getActions() {
        return this.actions;
    }

    private boolean isActionInUseInTransition(ACTION action) {
        for(Transition<STATE, ACTION> transition : this.transitions) {
            if (transition.getAction().equals(action)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void removeAction(ACTION action) throws FVMException {
        if(isActionInUseInTransition(action)) {
            throw new DeletionOfAttachedActionException(action, TransitionSystemPart.TRANSITIONS);
        }

        this.actions.remove(action);

    }

    @Override
    public void addAtomicProposition(ATOMIC_PROPOSITION atomicProposition) {
        this.atomicProp.add(atomicProposition);
    }

    @Override
    public Set<ATOMIC_PROPOSITION> getAtomicPropositions() {
        return this.atomicProp;
    }

    @Override
    public void removeAtomicProposition(ATOMIC_PROPOSITION atomicProposition) throws FVMException {
        if(isLabelInUseInState(atomicProposition)) {
            throw new DeletionOfAttachedAtomicPropositionException(atomicProposition, TransitionSystemPart.LABELING_FUNCTION);
        }

        this.atomicProp.remove(atomicProposition);
    }

    private boolean isLabelInUseInState(ATOMIC_PROPOSITION atomicProposition) {
        for(Entry<STATE, Set<ATOMIC_PROPOSITION>>  entry : this.stateToLabels.entrySet()) {
            if (entry.getValue().contains(atomicProposition)) {
                return true;
            }
        }

        return false;
    }


    @Override
    public void addToLabel(STATE state, ATOMIC_PROPOSITION atomicProposition) throws FVMException {
        if(!(this.states.contains(state) && this.atomicProp.contains(atomicProposition))) {
            throw new InvalidLablingPairException(state, atomicProposition);
        }

        if(!this.stateToLabels.containsKey(state)) {
            this.stateToLabels.put(state, new HashSet<>());
        }

        this.stateToLabels.get(state).add(atomicProposition);

    }

    @Override
    public Set<ATOMIC_PROPOSITION> getLabel(STATE state) throws StateNotFoundException {
        if(!(this.states.contains(state))) {
            throw new StateNotFoundException("the state does not exist");
        }

        if(!this.stateToLabels.containsKey(state)) {
            this.stateToLabels.put(state, new HashSet<>());
        }

        return this.stateToLabels.get(state);

    }

    @Override
    public void removeLabel(STATE state, ATOMIC_PROPOSITION atomicProposition) {
        if(this.stateToLabels.containsKey(state)) {
            this.stateToLabels.get(state).remove(atomicProposition);
        }
    }

    @Override
    public Map<STATE, Set<ATOMIC_PROPOSITION>> getLabelingFunction() {
        return this.stateToLabels;
    }

    @Override
    public boolean equals(Object other){
        return equalsTransitionSystem((TransitionSystem<STATE, ACTION, ATOMIC_PROPOSITION>)other);
    }

    private boolean equalsTransitionSystem(TransitionSystem<STATE, ACTION, ATOMIC_PROPOSITION> other)
    {
        return this.name.equals(other.getName()) && this.actions.equals(other.getActions()) &&
                this.initialStates.equals(other.getInitialStates()) && this.states.equals(other.getStates()) &&
                this.transitions.equals(other.getTransitions()) && this.atomicProp.equals(other.getAtomicPropositions()) &&
                this.stateToLabels.equals(other.getLabelingFunction());

    }

    @Override
    public int hashCode()
    {
        final int prime = 37;
        int result = 1;
        result = prime * result + this.name.hashCode();
        result = prime * result + this.actions.hashCode();
        result = prime * result + this.initialStates.hashCode();
        result = prime * result + this.states.hashCode();
        result = prime * result + this.transitions.hashCode();
        result = prime * result + this.atomicProp.hashCode();
        result = prime * result + this.stateToLabels.hashCode();
        return result;
    }
}
