package il.ac.bgu.cs.fvm.impl;

import il.ac.bgu.cs.fvm.FvmFacade;
import il.ac.bgu.cs.fvm.automata.Automaton;
import il.ac.bgu.cs.fvm.automata.MultiColorAutomaton;
import il.ac.bgu.cs.fvm.channelsystem.ChannelSystem;
import il.ac.bgu.cs.fvm.circuits.Circuit;
import il.ac.bgu.cs.fvm.ltl.LTL;
import il.ac.bgu.cs.fvm.programgraph.ActionDef;
import il.ac.bgu.cs.fvm.programgraph.ConditionDef;
import il.ac.bgu.cs.fvm.programgraph.ProgramGraph;
import il.ac.bgu.cs.fvm.transitionsystem.AlternatingSequence;
import il.ac.bgu.cs.fvm.transitionsystem.Transition;
import il.ac.bgu.cs.fvm.transitionsystem.TransitionSystem;
import il.ac.bgu.cs.fvm.util.Pair;
import il.ac.bgu.cs.fvm.verification.VerificationResult;
import org.svvrl.goal.core.aut.Run;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public <S, A, P> boolean isActionDeterministic(TransitionSystem<S, A, P> ts) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement isActionDeterministic
    }

    @Override
    public <S, A, P> boolean isAPDeterministic(TransitionSystem<S, A, P> ts) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement isAPDeterministic
    }

    @Override
    public <S, A, P> boolean isExecution(TransitionSystem<S, A, P> ts, AlternatingSequence<S, A> e) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement isExecution
    }

    @Override
    public <S, A, P> boolean isExecutionFragment(TransitionSystem<S, A, P> ts, AlternatingSequence<S, A> e) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement isExecutionFragment
    }

    @Override
    public <S, A, P> boolean isInitialExecutionFragment(TransitionSystem<S, A, P> ts, AlternatingSequence<S, A> e) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement isInitialExecutionFragment
    }

    @Override
    public <S, A, P> boolean isMaximalExecutionFragment(TransitionSystem<S, A, P> ts, AlternatingSequence<S, A> e) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement isMaximalExecutionFragment
    }

    @Override
    public <S, A> boolean isStateTerminal(TransitionSystem<S, A, ?> ts, S s) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement isStateTerminal
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
    public <S, A> Set<S> reach(TransitionSystem<S, A, ?> ts) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement reach
    }

    @Override
    public <S1, S2, A, P> TransitionSystem<Pair<S1, S2>, A, P> interleave(TransitionSystem<S1, A, P> ts1, TransitionSystem<S2, A, P> ts2) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement interleave
    }

    @Override
    public <S1, S2, A, P> TransitionSystem<Pair<S1, S2>, A, P> interleave(TransitionSystem<S1, A, P> ts1, TransitionSystem<S2, A, P> ts2, Set<A> handShakingActions) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement interleave
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
    public ProgramGraph<String, String> programGraphFromNanoPromela(String filename) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement programGraphFromNanoPromela
    }

    @Override
    public ProgramGraph<String, String> programGraphFromNanoPromelaString(String nanopromela) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement programGraphFromNanoPromelaString
    }

    @Override
    public ProgramGraph<String, String> programGraphFromNanoPromela(InputStream inputStream) throws Exception {
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
