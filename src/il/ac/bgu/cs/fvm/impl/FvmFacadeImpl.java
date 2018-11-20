package il.ac.bgu.cs.fvm.impl;

import il.ac.bgu.cs.fvm.FvmFacade;
import il.ac.bgu.cs.fvm.automata.Automaton;
import il.ac.bgu.cs.fvm.automata.MultiColorAutomaton;
import il.ac.bgu.cs.fvm.channelsystem.ChannelSystem;
import il.ac.bgu.cs.fvm.circuits.Circuit;
import il.ac.bgu.cs.fvm.exceptions.ActionNotFoundException;
import il.ac.bgu.cs.fvm.exceptions.StateNotFoundException;
import il.ac.bgu.cs.fvm.ltl.LTL;
import il.ac.bgu.cs.fvm.programgraph.ActionDef;
import il.ac.bgu.cs.fvm.programgraph.ConditionDef;
import il.ac.bgu.cs.fvm.programgraph.ProgramGraph;
import il.ac.bgu.cs.fvm.transitionsystem.AlternatingSequence;
import il.ac.bgu.cs.fvm.transitionsystem.Transition;
import il.ac.bgu.cs.fvm.transitionsystem.TransitionSystem;
import il.ac.bgu.cs.fvm.util.Pair;
import il.ac.bgu.cs.fvm.verification.VerificationResult;

import java.io.InputStream;
import java.util.*;

/**
 * Implement the methods in this class. You may add additional classes as you
 * want, as long as they live in the {@code impl} package, or one of its 
 * sub-packages.
 */
public class FvmFacadeImpl implements FvmFacade {

    @Override
    public <S, A, P> TransitionSystem<S, A, P> createTransitionSystem() {
        return new TransitionSystemImpl<>();
    }

    @Override
    public <S, A, P> boolean isActionDeterministic(TransitionSystem<S, A, P> transitionSystem) {
        if(transitionSystem.getInitialStates().size() > 1){
            return false;
        }
        for(S state: transitionSystem.getStates()){
            for(A action: transitionSystem.getActions()){
                if(post(transitionSystem, state, action).size() > 1){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * change this function implementation
     */
    @Override
    public <S, A, P> boolean isAPDeterministic(TransitionSystem<S, A, P> transitionSystem) {
        if(transitionSystem.getInitialStates().size() > 1){
            return false;
        }
        Map<S, Set<P>> stateToLabel  = transitionSystem.getLabelingFunction();
        for(S state: transitionSystem.getStates()) {
            Map<Set<P>, S> postStatesLabels = new HashMap<>();
            Set<S> postStates = post(transitionSystem, state);
            for (S postState : postStates) {
                Set<P> atomicProp = stateToLabel.get(postState);
                if (postStatesLabels.containsKey(atomicProp)) {
                    return false;
                }
                postStatesLabels.put(atomicProp, postState);
            }
        }
        return true;
    }

    @Override
    public <S, A, P> boolean isExecution(TransitionSystem<S, A, P> ts, AlternatingSequence<S, A> e) {
        return isInitialExecutionFragment(ts,e) && isMaximalExecutionFragment(ts, e);
    }

    @Override
    public <S, A, P> boolean isExecutionFragment(TransitionSystem<S, A, P> transitionSystem, AlternatingSequence<S, A> e) {
        if(e.isEmpty() || e.size() == 1){
            return true;
        }
        if(!transitionSystem.getStates().contains(e.head())){
            throw new StateNotFoundException("State doesn't exist in the transition system!");
        }
        if(!transitionSystem.getActions().contains(e.tail().head())){
            throw new ActionNotFoundException("Action doesn't exist in the transition system!");
        }
        if(!e.tail().tail().isEmpty()){
            if(!transitionSystem.getStates().contains(e.tail().tail().head())){
                throw new StateNotFoundException("State doesn't exist in the transition system!");
            }
            for(Transition<S,A> transition: transitionSystem.getTransitions()){
                if(transition.getFrom().equals(e.head()) && transition.getAction().equals(e.tail().head()) &&
                transition.getTo().equals(e.tail().tail().head())){
                    return isExecutionFragment(transitionSystem, e.tail().tail());
                }
            }
        }
        return false;
    }

    @Override
    public <S, A, P> boolean isInitialExecutionFragment(TransitionSystem<S, A, P> transitionSystem, AlternatingSequence<S, A> e) {
        return transitionSystem.getInitialStates().contains(e.head()) && isExecutionFragment(transitionSystem, e);
    }

    @Override
    public <S, A, P> boolean isMaximalExecutionFragment(TransitionSystem<S, A, P> transitionSystem, AlternatingSequence<S, A> e) {
        return isStateTerminal(transitionSystem, e.last()) && isExecutionFragment(transitionSystem, e);
    }

    @Override
    public <S, A> boolean isStateTerminal(TransitionSystem<S, A, ?> transitionSystem, S state) {
        if(!transitionSystem.getStates().contains(state)){
            throw new StateNotFoundException("The state wasn't found in the transition system!");
        }
        return post(transitionSystem,state).isEmpty();
    }

    @Override
    public <S> Set<S> post(TransitionSystem<S, ?, ?> transitionSystem, S state) {
        Set<S> postStatesResults = new HashSet<>();
        for(Transition<S, ?> transition: transitionSystem.getTransitions()){
            if(transition.getFrom().equals(state)){
                postStatesResults.add(transition.getTo());
            }
        }
        return postStatesResults;
    }

    @Override
    public <S> Set<S> post(TransitionSystem<S, ?, ?> transitionSystem, Set<S> c) {
        Set<S> postStatesResults = new HashSet<>();
        for(S state: c){
            Set<S> postStatesPerState = post(transitionSystem, state);
            postStatesResults.addAll(postStatesPerState);
        }
        return postStatesResults;
    }

    @Override
    public <S, A> Set<S> post(TransitionSystem<S, A, ?> transitionSystem, S state, A action) {
        Set<S> postStates = new HashSet<>();
        for(Transition<S, A> transitions: transitionSystem.getTransitions() ){
            if(transitions.getFrom().equals(state) && transitions.getAction().equals(action)){
                postStates.add(transitions.getTo());
            }
        }
        return postStates;
    }

    @Override
    public <S, A> Set<S> post(TransitionSystem<S, A, ?> transitionSystem, Set<S> c, A action) {
        Set<S> postStatesResults = new HashSet<>();
        for(S state: c){
            Set<S> postStatesPerState = post(transitionSystem, state, action);
            postStatesResults.addAll(postStatesPerState);
        }
        return postStatesResults;
    }

    @Override
    public <S> Set<S> pre(TransitionSystem<S, ?, ?> transitionSystem, S state) {
        Set<S> preStates = new HashSet<>();
        for(Transition<S,?> transition: transitionSystem.getTransitions()){
            if(transition.getTo().equals(state)){
                preStates.add(transition.getFrom());
            }
        }
        return preStates;
    }

    @Override
    public <S> Set<S> pre(TransitionSystem<S, ?, ?> transitionSystem, Set<S> c) {
        Set<S> preStatesResults = new HashSet<>();
        for(S state: c){
            Set<S> preStatesPerState = pre(transitionSystem, state);
            preStatesResults.addAll(preStatesPerState);
        }
        return preStatesResults;
    }

    @Override
    public <S, A> Set<S> pre(TransitionSystem<S, A, ?> transitionSystem, S state, A action) {
        Set<S> preStates = new HashSet<>();
        for(Transition<S, A> transitions: transitionSystem.getTransitions() ){
            if(transitions.getTo().equals(state) && transitions.getAction().equals(action)){
                preStates.add(transitions.getFrom());
            }
        }
        return preStates;
    }

    @Override
    public <S, A> Set<S> pre(TransitionSystem<S, A, ?> transitionSystem, Set<S> c, A action) {
        Set<S> preStatesResults = new HashSet<>();
        for(S state: c){
            Set<S> preStatesPerState = pre(transitionSystem, state, action);
            preStatesResults.addAll(preStatesPerState);
        }
        return preStatesResults;
    }

    @Override
    public <S, A> Set<S> reach(TransitionSystem<S, A, ?> transitionSystem) {
        Set<S> reachableStates = transitionSystem.getInitialStates();
        Set<S> statesToCheck = transitionSystem.getInitialStates();
        while(statesToCheck.size() > 0){
            for(S state: statesToCheck){
                for (S postState : post(transitionSystem, state)){
                    reachableStates.add(postState);
                    statesToCheck.add(postState);
                }
                statesToCheck.remove(state);
            }
        }
        return reachableStates;
    }

    @Override
    public <S1, S2, A, P> TransitionSystem<Pair<S1, S2>, A, P> interleave(TransitionSystem<S1, A, P> ts1, TransitionSystem<S2, A, P> ts2) {
        return interleave(ts1, ts2, new HashSet<>());
    }

    private <S1, S2, A, P> void createInitialStates(TransitionSystem<Pair<S1, S2>, A, P> interleave,
                                     TransitionSystem<S1, A, P> transitionSystem1, TransitionSystem<S2, A, P> transitionSystem2){
        for(S1 state1 : transitionSystem1.getInitialStates()){
            for(S2 state2 : transitionSystem2.getInitialStates()){
                Pair<S1,S2> state = new Pair<>(state1, state2);
                interleave.addState(state);
                interleave.setInitial(state, true);
            }
        }
    }

    private <S1, S2, A, P> void createAtomicPropositions(TransitionSystem<Pair<S1, S2>, A, P> interleave,
                                          TransitionSystem<S1, A, P> transitionSystem1, TransitionSystem<S2, A, P> transitionSystem2){
        for (P atomicProp: transitionSystem1.getAtomicPropositions()){
            interleave.addAtomicProposition(atomicProp);
        }
        for(P atomicProp: transitionSystem2.getAtomicPropositions()){
            interleave.addAtomicProposition(atomicProp);
        }
        for(Pair<S1,S2> state : interleave.getStates()){
            Set<P> atomicPropositionOfFirst = transitionSystem1.getLabel(state.getFirst());
            Set<P> atomicPropositionOfSecond = transitionSystem2.getLabel(state.getSecond());
            for(P atomicProp1 : atomicPropositionOfFirst){
                interleave.addToLabel(state, atomicProp1);
            }
            for(P atomicProp2: atomicPropositionOfSecond){
                interleave.addToLabel(state, atomicProp2);
            }
        }
    }

    private <S, A, P> S findStateTo(TransitionSystem<S, A, P> transitionSystem, S state, A action){
        for(Transition<S,A> transition: transitionSystem.getTransitions()){
            if(transition.getFrom().equals(state) && transition.getAction().equals(action)){
                return transition.getTo();
            }
        }
        return null;
    }

    private <S1, S2, A, P> void handleTransitionIncludingHandshaking(TransitionSystem<Pair<S1, S2>, A, P> interleave,
                                                                     TransitionSystem<S1, A, P> transitionSystem1, TransitionSystem<S2, A, P> transitionSystem2, Set<A> handShakingActions) {
        Set<Transition<S1, A>> ts1Transitions = transitionSystem1.getTransitions();
        Set<Transition<S2, A>> ts2Transitions = transitionSystem2.getTransitions();
        for (Pair<S1, S2> interleaveState : interleave.getStates()) {
            for (Transition<S1, A> ts1Transition : ts1Transitions) {
                if (ts1Transition.getFrom().equals(interleaveState.getFirst())) {
                    //not a handshake action, then do it in a regular rule
                    if (!handShakingActions.contains(ts1Transition.getAction())) {
                        Pair<S1, S2> toState = new Pair<>(ts1Transition.getTo(), interleaveState.getSecond());
                        Transition<Pair<S1, S2>, A> addTransition = new Transition<>(interleaveState, ts1Transition.getAction(), toState);
                        interleave.addTransition(addTransition);
                    } else {
                        S2 stateToS2 = findStateTo(transitionSystem2, interleaveState.getSecond(), ts1Transition.getAction());
                        if (stateToS2 == null) {
                            throw new ActionNotFoundException("Action should be handshake but it is not");
                        }
                        Pair<S1, S2> toState = new Pair<>(ts1Transition.getTo(), stateToS2);
                        Transition<Pair<S1, S2>, A> addTransition = new Transition<>(interleaveState, ts1Transition.getAction(), toState);
                        interleave.addTransition(addTransition);
                    }
                }
            }

            for (Transition<S2, A> ts2Transition : ts2Transitions) {
                if (ts2Transition.getFrom().equals(interleaveState.getSecond())) {
                    if (!handShakingActions.contains(ts2Transition.getAction())) {
                        Pair<S1, S2> toState = new Pair<>(interleaveState.getFirst(), ts2Transition.getTo());
                        Transition<Pair<S1, S2>, A> addTransition = new Transition<>(interleaveState, ts2Transition.getAction(), toState);
                        interleave.addTransition(addTransition);
                    }
                }
            }
        }
    }


    @Override
    public <S1, S2, A, P> TransitionSystem<Pair<S1, S2>, A, P> interleave(TransitionSystem<S1, A, P> transitionSystem1, TransitionSystem<S2, A, P> transitionSystem2, Set<A> handShakingActions) {
        TransitionSystem<Pair<S1, S2>, A, P> resultInterleave = createTransitionSystem();
        resultInterleave.setName(transitionSystem1.getName() + transitionSystem2.getName());
        resultInterleave.addAllActions(transitionSystem1.getActions());
        resultInterleave.addAllActions(transitionSystem2.getActions());
        createInitialStates(resultInterleave, transitionSystem1, transitionSystem2);
        handleTransitionIncludingHandshaking(resultInterleave, transitionSystem1, transitionSystem2, handShakingActions);
        //to deal with the hand shaking
        createAtomicPropositions(resultInterleave, transitionSystem1, transitionSystem2);
        return resultInterleave;
    }

    @Override
    public <L, A> ProgramGraph<L, A> createProgramGraph() {
        return new ProgramGraphImpl<>();
    }

    @Override
    public <L1, L2, A> ProgramGraph<Pair<L1, L2>, A> interleave(ProgramGraph<L1, A> pg1, ProgramGraph<L2, A> pg2) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement interleave
    }

    @Override
    public TransitionSystem<Pair<Map<String, Boolean>, Map<String, Boolean>>, Map<String, Boolean>, Object> transitionSystemFromCircuit(Circuit c) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement transitionSystemFromCircuit
    }

    @Override
    public <L, A> TransitionSystem<Pair<L, Map<String, Object>>, A, String> transitionSystemFromProgramGraph(ProgramGraph<L, A> pg, Set<ActionDef> actionDefs, Set<ConditionDef> conditionDefs) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement transitionSystemFromProgramGraph
    }

    @Override
    public <L, A> TransitionSystem<Pair<List<L>, Map<String, Object>>, A, String> transitionSystemFromChannelSystem(ChannelSystem<L, A> cs) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement transitionSystemFromChannelSystem
    }

    @Override
    public <Sts, Saut, A, P> TransitionSystem<Pair<Sts, Saut>, A, Saut> product(TransitionSystem<Sts, A, P> ts, Automaton<Saut, P> aut) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement product
    }

    @Override
    public ProgramGraph<String, String> programGraphFromNanoPromela(String filename) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement programGraphFromNanoPromela
    }

    @Override
    public ProgramGraph<String, String> programGraphFromNanoPromelaString(String nanopromela) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement programGraphFromNanoPromelaString
    }

    @Override
    public ProgramGraph<String, String> programGraphFromNanoPromela(InputStream inputStream) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement programGraphFromNanoPromela
    }

    @Override
    public <S, A, P, Saut> VerificationResult<S> verifyAnOmegaRegularProperty(TransitionSystem<S, A, P> ts, Automaton<Saut, P> aut) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement verifyAnOmegaRegularProperty
    }

    @Override
    public <L> Automaton<?, L> LTL2NBA(LTL<L> ltl) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement LTL2NBA
    }

    @Override
    public <L> Automaton<?, L> GNBA2NBA(MultiColorAutomaton<?, L> mulAut) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement GNBA2NBA
    }
   
}
