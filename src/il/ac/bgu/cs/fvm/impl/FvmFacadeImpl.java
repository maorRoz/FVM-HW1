package il.ac.bgu.cs.fvm.impl;

import il.ac.bgu.cs.fvm.FvmFacade;
import il.ac.bgu.cs.fvm.automata.Automaton;
import il.ac.bgu.cs.fvm.automata.MultiColorAutomaton;
import il.ac.bgu.cs.fvm.channelsystem.ChannelSystem;
import il.ac.bgu.cs.fvm.circuits.Circuit;
import il.ac.bgu.cs.fvm.ltl.LTL;
import il.ac.bgu.cs.fvm.programgraph.ActionDef;
import il.ac.bgu.cs.fvm.programgraph.ConditionDef;
import il.ac.bgu.cs.fvm.programgraph.PGTransition;
import il.ac.bgu.cs.fvm.programgraph.ProgramGraph;
import il.ac.bgu.cs.fvm.transitionsystem.AlternatingSequence;
import il.ac.bgu.cs.fvm.transitionsystem.TransitionSystem;
import il.ac.bgu.cs.fvm.util.Pair;
import il.ac.bgu.cs.fvm.verification.VerificationResult;
import il.ac.bgu.cs.fvm.transitionsystem.Transition;
import org.svvrl.goal.core.aut.Run;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Deque;
import java.util.LinkedList;

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
    public <S> Set<S> post(TransitionSystem<S, ?, ?> ts, S s) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement post
    }

    @Override
    public <S> Set<S> post(TransitionSystem<S, ?, ?> ts, Set<S> c) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement post
    }

    @Override
    public <S, A> Set<S> post(TransitionSystem<S, A, ?> ts, S s, A a) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement post
    }

    @Override
    public <S, A> Set<S> post(TransitionSystem<S, A, ?> ts, Set<S> c, A a) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement post
    }

    @Override
    public <S> Set<S> pre(TransitionSystem<S, ?, ?> ts, S s) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement pre
    }

    @Override
    public <S> Set<S> pre(TransitionSystem<S, ?, ?> ts, Set<S> c) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement pre
    }

    @Override
    public <S, A> Set<S> pre(TransitionSystem<S, A, ?> ts, S s, A a) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement pre
    }

    @Override
    public <S, A> Set<S> pre(TransitionSystem<S, A, ?> ts, Set<S> c, A a) {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: Implement pre
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

    private <L1,L2,A> void addInterleavedInitializations(ProgramGraph<Pair<L1, L2>, A> interleavedProgramGraph,
                                                         Set<List<String>> initializationsPG1, Set<List<String>> initializationsPG2){
        for(List<String> initializationPG1 : initializationsPG1){
            for(List<String> initializationPG2 : initializationsPG2){
                List<String> newInitialization = new ArrayList<>(initializationPG1);
                newInitialization.addAll(initializationPG2);
                interleavedProgramGraph.addInitalization(newInitialization);
            }
        }

    }

    private <L1,L2,A> void addInterleavedLocations(ProgramGraph<Pair<L1, L2>, A> interleavedProgramGraph,
                                                   Set<L1> initialLocationsPG1, Set<L2> initialLocationsPG2){
        for(L1 initialLocationPG1 : initialLocationsPG1){
            for(L2 initialLocationPG2 : initialLocationsPG2){
                Pair<L1,L2> newLocation = new Pair<>(initialLocationPG1, initialLocationPG2);
                interleavedProgramGraph.setInitial(newLocation, false);
            }
        }
    }

    private <L1,L2,A> void addInterleavedTransitions(ProgramGraph<Pair<L1, L2>, A> interleavedProgramGraph,
                                                     Set<PGTransition<L1, A>> transitionsPG1, Set<PGTransition<L2, A>> transitionsPG2, Set<Pair<L1, L2>> locationsInterleaved){

        Set<Pair<L1, L2>> locationsToInterleave = new HashSet<>(interleavedProgramGraph.getLocations());
        locationsToInterleave.removeAll(locationsInterleaved);
        if(locationsToInterleave.isEmpty()) { return; }

        for(Pair<L1, L2> locationToInterleave : locationsToInterleave)
        {
            for(PGTransition<L1, A> transitionPG1 : transitionsPG1)
            {
                if(!transitionPG1.getFrom().equals(locationToInterleave.getFirst())){ continue;}

                Pair<L1, L2> locationTo = new Pair<>(transitionPG1.getTo(), locationToInterleave.getSecond());
                addLocationAndTransitionToProgramGraph(interleavedProgramGraph, locationToInterleave,transitionPG1.getCondition(), transitionPG1.getAction(), locationTo);
            }

            for(PGTransition<L2, A> transitionPG2 : transitionsPG2)
            {
                if(!transitionPG2.getFrom().equals(locationToInterleave.getSecond())){ continue;}

                Pair<L1, L2> locationTo = new Pair<>(locationToInterleave.getFirst(), transitionPG2.getTo());
                addLocationAndTransitionToProgramGraph(interleavedProgramGraph, locationToInterleave, transitionPG2.getCondition(), transitionPG2.getAction(), locationTo);
            }
        }

        locationsInterleaved.addAll(locationsToInterleave);
        addInterleavedTransitions(interleavedProgramGraph, transitionsPG1, transitionsPG2, locationsInterleaved);
    }

    private <L1, L2, A> void addLocationAndTransitionToProgramGraph(ProgramGraph<Pair<L1, L2>, A> interleavedProgramGraph,
                       Pair<L1, L2> interleaveLocation, String condition, A action, Pair<L1, L2> locationTo){
        interleavedProgramGraph.addLocation(locationTo);
        PGTransition<Pair<L1, L2>, A> newTransition = new PGTransition<>(interleaveLocation, condition, action, locationTo);
        interleavedProgramGraph.addTransition(newTransition);
    }

    @Override
    public <L1, L2, A> ProgramGraph<Pair<L1, L2>, A> interleave(ProgramGraph<L1, A> pg1, ProgramGraph<L2, A> pg2) {
        ProgramGraph<Pair<L1, L2>, A> interleavedProgramGraph = createProgramGraph();
        addInterleavedInitializations(interleavedProgramGraph, pg1.getInitalizations(), pg2.getInitalizations());
        addInterleavedLocations(interleavedProgramGraph, pg1.getInitialLocations(), pg2.getInitialLocations());
        addInterleavedTransitions(interleavedProgramGraph, pg1.getTransitions(), pg2.getTransitions(), new HashSet<>());
        return interleavedProgramGraph;
    }

    private void addLabelToCircuitTransitionSystemIfTrue(
            TransitionSystem<Pair<Map<String, Boolean>, Map<String, Boolean>>, Map<String, Boolean>, Object> transitionSystem,
            Pair<Map<String, Boolean>, Map<String, Boolean>> state,
            Map<String, Boolean> inputs) {
        for(Map.Entry<String, Boolean> input : inputs.entrySet())
        {
            if(input.getValue()) {
                transitionSystem.addToLabel(state, input.getKey());
            }
        }
    }

    private void addLabelingToTSFromCircuit(TransitionSystem<Pair<Map<String, Boolean>, Map<String, Boolean>>, Map<String, Boolean>, Object> transitionSystem,
                                            Circuit circuit)
    {
        for(Pair<Map<String, Boolean>, Map<String, Boolean>> state: transitionSystem.getStates())
        {
            Map<String, Boolean> inputs = state.getFirst();
            Map<String, Boolean> registers = state.getSecond();
            Map<String, Boolean> outputs = circuit.computeOutputs(inputs, registers);
            addLabelToCircuitTransitionSystemIfTrue(transitionSystem, state, inputs);
            addLabelToCircuitTransitionSystemIfTrue(transitionSystem, state, registers);
            addLabelToCircuitTransitionSystemIfTrue(transitionSystem, state, outputs);

        }
    }

    private void circuitTransitionSystemFromInitial(TransitionSystem<Pair<Map<String, Boolean>, Map<String, Boolean>>, Map<String, Boolean>, Object> transitionSystem,
                  Circuit circuit)
    {
        Deque<Pair<Map<String, Boolean>, Map<String, Boolean>>> queue = new LinkedList<>(transitionSystem.getInitialStates());
        Set<Pair<Map<String, Boolean>, Map<String, Boolean>>> allReadyCheckedStates = new HashSet<>(transitionSystem.getInitialStates());
        while(!queue.isEmpty())
        {
            Pair<Map<String, Boolean>, Map<String, Boolean>> stateToCheck = queue.removeFirst();
            Map<String, Boolean> newRegisterValues = circuit.updateRegisters(stateToCheck.getFirst(), stateToCheck.getSecond());
            for(Map<String, Boolean> action : transitionSystem.getActions())
            {
                Pair<Map<String, Boolean>, Map<String, Boolean>> newState = new Pair<>(action, newRegisterValues);
                if(!allReadyCheckedStates.contains(newState))
                {
                    allReadyCheckedStates.add(newState);
                    queue.addLast(newState);
                    transitionSystem.addState(newState);
                }

                Transition<Pair<Map<String, Boolean>, Map<String, Boolean>>,Map<String, Boolean>> newTransition = new Transition<>(stateToCheck,
                        action, newState);
                transitionSystem.addTransition(newTransition);
            }
        }
    }

    private void addAllAtomicPropositionsFromCircuit(TransitionSystem<Pair<Map<String, Boolean>, Map<String, Boolean>>, Map<String, Boolean>, Object> transitionSystem,
                  Circuit circuit)
    {
        transitionSystem.addAllAtomicPropositions(circuit.getInputPortNames().toArray());
        transitionSystem.addAllAtomicPropositions(circuit.getOutputPortNames().toArray());
        transitionSystem.addAllAtomicPropositions(circuit.getRegisterNames().toArray());
    }

    private void addAllActionsFromCircuitTransition(TransitionSystem<Pair<Map<String, Boolean>, Map<String, Boolean>>, Map<String, Boolean>, Object> transitionSystem)
    {
        for(Pair<Map<String, Boolean>, Map<String, Boolean>> initial : transitionSystem.getInitialStates())
        {
            transitionSystem.addAction(initial.getFirst());
        }
    }

    private String findNextInputNoValue(Map<String, Boolean> inputs, Set<String> inputsPortNames)
    {
        for(String input : inputsPortNames)
        {
            if(!inputs.containsKey(input))
                return input;
        }

        return null;
    }

    private void createInitialStateFromCircuitAccordingToSize(TransitionSystem<Pair<Map<String, Boolean>, Map<String, Boolean>>, Map<String, Boolean>, Object> transitionSystem,
                                                     Circuit circuit, Map<String, Boolean> registers, Map<String, Boolean> inputs)
    {
        if(inputs.size() == circuit.getInputPortNames().size())
        {
            Map<String, Boolean> registersValues = new HashMap<>(registers);
            Pair<Map<String, Boolean>, Map<String, Boolean>> newState = new Pair<>(inputs, registersValues);
            transitionSystem.addState(newState);
            transitionSystem.setInitial(newState, false);
        }
        else
        {
            String inputWithNoValue = findNextInputNoValue(inputs, circuit.getInputPortNames());
            Map<String, Boolean> newInputsFalse = new HashMap<>(inputs);
            Map<String, Boolean> newInputsTrue = new HashMap<>(inputs);
            newInputsFalse.put(inputWithNoValue, false);
            createInitialStateFromCircuitAccordingToSize(transitionSystem, circuit, registers, newInputsFalse);
            newInputsTrue.put(inputWithNoValue, true);
            createInitialStateFromCircuitAccordingToSize(transitionSystem, circuit, registers, newInputsTrue);
        }
    }

    private void createInitialStateFromCircuit(TransitionSystem<Pair<Map<String, Boolean>, Map<String, Boolean>>, Map<String, Boolean>, Object> transitionSystem,
          Circuit circuit)
    {
        Map<String, Boolean> inputs = new HashMap<>();
        Map<String, Boolean> registers = new HashMap<>();
        for(String register : circuit.getRegisterNames())
        {
            registers.put(register, false);
        }

        createInitialStateFromCircuitAccordingToSize(transitionSystem, circuit, registers, inputs);
    }

    @Override
    public TransitionSystem<Pair<Map<String, Boolean>, Map<String, Boolean>>, Map<String, Boolean>, Object> transitionSystemFromCircuit(Circuit circuit) {
        TransitionSystem<Pair<Map<String, Boolean>, Map<String, Boolean>>, Map<String, Boolean>, Object> transitionSystem = createTransitionSystem();
        createInitialStateFromCircuit(transitionSystem, circuit);
        addAllActionsFromCircuitTransition(transitionSystem);
        addAllAtomicPropositionsFromCircuit(transitionSystem, circuit);
        circuitTransitionSystemFromInitial(transitionSystem, circuit);
        addLabelingToTSFromCircuit(transitionSystem, circuit);
        return transitionSystem;
    }

    private <L, A> void addInitialLocationsTSFromPG(TransitionSystem<Pair<L, Map<String, Object>>, A, String> transitionSystem, ProgramGraph<L, A> pg, Set<ActionDef> actionDefs)
    {
        Set<List<String>> variableInitSet = pg.getInitalizations();
        Set<Map<String, Object>> evals = new HashSet<Map<String,Object>>();
        Set<L> initialLocations = pg.getInitialLocations();

        for(List<String> variableInit : variableInitSet)
        {
            Map<String, Object> variableInitMap = new HashMap<String, Object>();
            for(String action : variableInit)
                variableInitMap = ActionDef.effect(actionDefs, variableInitMap, action);
            evals.add(variableInitMap);
        }


        for(L l : initialLocations)
        {
            if(evals.isEmpty())
            {
                Pair<L, Map<String, Object>> newState = new Pair<L, Map<String,Object>>(l, new HashMap<String, Object>());
                transitionSystem.addState(newState);
                transitionSystem.addInitialState(newState);
            }
            else
            {
                for(Map<String, Object> eval : evals)
                {
                    Pair<L, Map<String, Object>> newState = new Pair<L, Map<String,Object>>(l, new HashMap<String, Object>(eval));
                    transitionSystem.addState(newState);
                    transitionSystem.addInitialState(newState);
                }
            }
        }
    }

    private <L, A> void addAllActionsTSFromPG(TransitionSystem<Pair<L, Map<String, Object>>, A, String> transitionSystem, Set<PGTransition<L, A>> pgTransitions) {
        for(PGTransition<L, A> pgTransition : pgTransitions) {
            transitionSystem.addAction(pgTransition.getAction());
        }
    }

    @Override
    public <L, A> TransitionSystem<Pair<L, Map<String, Object>>, A, String> transitionSystemFromProgramGraph(ProgramGraph<L, A> programGraph,
                 Set<ActionDef> actionDefinitions, Set<ConditionDef> conditionDefinitions) {
        TransitionSystem<Pair<L, Map<String, Object>>, A, String> transitionSystem = createTransitionSystem();
        addAllActionsTransitionSystemFromPG(transitionSystem, programGraph.getTransitions());
        addInitialLocationsTSFromPG(transitionSystem, programGraph, actionDefinitions);
        transitionSystemFromProgramGraphFromInitialStates(transitionSystem, programGraph, actionDefinitions, conditionDefinitions);
        addAtomicProposition(transitionSystem);
        addLabelsToTSFromPG(transitionSystem);
        removeActionsNotReachable(transitionSystem);
        return transitionSystem;
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
