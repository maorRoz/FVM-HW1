package il.ac.bgu.cs.fvm.impl;

import il.ac.bgu.cs.fvm.FvmFacade;
import il.ac.bgu.cs.fvm.automata.Automaton;
import il.ac.bgu.cs.fvm.automata.MultiColorAutomaton;
import il.ac.bgu.cs.fvm.channelsystem.ChannelSystem;
import il.ac.bgu.cs.fvm.circuits.Circuit;
import il.ac.bgu.cs.fvm.ltl.LTL;
import il.ac.bgu.cs.fvm.programgraph.ActionDef;
import il.ac.bgu.cs.fvm.programgraph.ConditionDef;
import il.ac.bgu.cs.fvm.channelsystem.ParserBasedInterleavingActDef;
import il.ac.bgu.cs.fvm.programgraph.PGTransition;
import il.ac.bgu.cs.fvm.programgraph.ProgramGraph;
import il.ac.bgu.cs.fvm.channelsystem.InterleavingActDef;
import il.ac.bgu.cs.fvm.programgraph.ParserBasedActDef;
import il.ac.bgu.cs.fvm.programgraph.ParserBasedCondDef;
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
                interleavedProgramGraph.setInitial(newLocation, true);
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
            transitionSystem.setInitial(newState, true);
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

    private <L, A> void removeActionsNotReachable(TransitionSystem<Pair<L, Map<String, Object>>, A, String> transitionSystem)
    {
        Set<A> reachableActions = new HashSet<>();
        for(Transition<Pair<L, Map<String, Object>>, A> t : transitionSystem.getTransitions())
        {
            reachableActions.add(t.getAction());
        }

        Set<A> ourActions = new HashSet<>(transitionSystem.getActions());
        for(A action : ourActions)
            if(!reachableActions.contains(action))
                transitionSystem.removeAction(action);
    }

    private <L, A> void addLabelsTransitionSystemFromProgramGraph(TransitionSystem<Pair<L, Map<String, Object>>, A, String> transitionSystem)
    {
        for(Pair<L, Map<String, Object>> state : transitionSystem.getStates())
        {
            transitionSystem.addToLabel(state, state.first.toString());
            for(String variable : state.second.keySet())
            {
                String newAtomicPreposition = variable + " = " + state.second.get(variable).toString();
                transitionSystem.addToLabel(state,newAtomicPreposition);
            }
        }
    }

    private <L, A> void addAtomicProposition(TransitionSystem<Pair<L, Map<String, Object>>, A, String> transitionSystem) {
        for(Pair<L, Map<String, Object>> state : transitionSystem.getStates())
        {
            transitionSystem.addAtomicProposition(state.getFirst().toString());
            for(String variable : state.second.keySet())
            {
                String newAtomicPreposition = variable + " = " + state.second.get(variable).toString();
                transitionSystem.addAtomicProposition(newAtomicPreposition);
            }
        }
    }

    private <L, A> void transitionSystemFromProgramGraphFromInitialStates(TransitionSystem<Pair<L, Map<String, Object>>, A, String> transitionSystem,
                        ProgramGraph<L, A> pg, Set<ActionDef> actionDefinitions, Set<ConditionDef> conditionDefinitions)
    {
        Deque<Pair<L, Map<String, Object>>> queue = new LinkedList<>(transitionSystem.getInitialStates());
        Set<Pair<L, Map<String, Object>>> allReadyChecked = new HashSet<>(transitionSystem.getInitialStates());
        while(!queue.isEmpty())
        {
            Pair<L, Map<String, Object>> fromState = queue.removeFirst();
            for(PGTransition<L, A> pgTrans : pg.getTransitions())
            {
                if(pgTrans.getFrom().equals(fromState.first) && ConditionDef.evaluate(conditionDefinitions, fromState.getSecond(), pgTrans.getCondition())
                        && ActionDef.effect(actionDefinitions, fromState.getSecond(), pgTrans.getAction()) != null)
                {
                    Pair<L, Map<String, Object>> toState = new Pair<>
                            (pgTrans.getTo(), ActionDef.effect(actionDefinitions, fromState.getSecond(), pgTrans.getAction()));
                    Transition<Pair<L, Map<String, Object>>, A> newTransition = new Transition<>(fromState, pgTrans.getAction(), toState);
                    if(!allReadyChecked.contains(toState))
                    {
                        transitionSystem.addState(toState);
                        allReadyChecked.add(toState);
                        queue.addLast(toState);
                    }
                    transitionSystem.addTransition(newTransition);
                }
            }
        }
    }

    private <L, A> void addInitialLocationsTransitionSystemFromProgramGraph(TransitionSystem<Pair<L, Map<String, Object>>, A, String> transitionSystem,
                                                    ProgramGraph<L, A> programGraph, Set<ActionDef> actionDefinitions)
    {
        Set<List<String>> variableInitiationSet = programGraph.getInitalizations();
        Set<Map<String, Object>> evaluations = getMapsFromInitializations(variableInitiationSet, actionDefinitions);
        Set<L> initialLocations = programGraph.getInitialLocations();

        for(L l : initialLocations)
        {
            if(evaluations.isEmpty())
            {
                Pair<L, Map<String, Object>> newState = new Pair<>(l, new HashMap<>());
                transitionSystem.addState(newState);
                transitionSystem.setInitial(newState, true);
            }
            else
            {
                for(Map<String, Object> eval : evaluations)
                {
                    Pair<L, Map<String, Object>> newState = new Pair<>(l, new HashMap<>(eval));
                    transitionSystem.addState(newState);
                    transitionSystem.setInitial(newState, true);
                }
            }
        }
    }

    private <L, A> void addAllActionsTransitionSystemFromProgramGraph(TransitionSystem<Pair<L, Map<String, Object>>, A, String> transitionSystem, Set<PGTransition<L, A>> pgTransitions) {
        for(PGTransition<L, A> pgTransition : pgTransitions) {
            transitionSystem.addAction(pgTransition.getAction());
        }
    }

    @Override
    public <L, A> TransitionSystem<Pair<L, Map<String, Object>>, A, String> transitionSystemFromProgramGraph(ProgramGraph<L, A> programGraph,
                 Set<ActionDef> actionDefinitions, Set<ConditionDef> conditionDefinitions) {
        TransitionSystem<Pair<L, Map<String, Object>>, A, String> transitionSystem = createTransitionSystem();
        addAllActionsTransitionSystemFromProgramGraph(transitionSystem, programGraph.getTransitions());
        addInitialLocationsTransitionSystemFromProgramGraph(transitionSystem, programGraph, actionDefinitions);
        transitionSystemFromProgramGraphFromInitialStates(transitionSystem, programGraph, actionDefinitions, conditionDefinitions);
        addAtomicProposition(transitionSystem);
        addLabelsTransitionSystemFromProgramGraph(transitionSystem);
        removeActionsNotReachable(transitionSystem);
        return transitionSystem;
    }

    private <L,A> void addLabelsToTransitionSystemFromChannelSystem(TransitionSystem<Pair<List<L>, Map<String, Object>>, A, String> transitionSystem)
    {
        for(Pair<List<L>, Map<String, Object>> state : transitionSystem.getStates())
        {
            for(L location: state.getFirst())
            {
                transitionSystem.addToLabel(state, location.toString());
            }
            for(String var : state.second.keySet())
            {
                String newAtomicPreposition = var + " = " + state.second.get(var).toString();
                transitionSystem.addToLabel(state,newAtomicPreposition);
            }
        }
    }

    private <L,A> void addAtomicPropositionToTransitionSystemFromChannelSystem(TransitionSystem<Pair<List<L>, Map<String, Object>>, A, String> transitionSystem)
    {
        for(Pair<List<L>, Map<String, Object>> state : transitionSystem.getStates())
        {
            for(L location: state.getFirst())
            {
                transitionSystem.addAtomicProposition(location.toString());
            }

            for(String variable : state.second.keySet())
            {
                String newAtomicPreposition = variable + " = " + state.second.get(variable).toString();
                transitionSystem.addAtomicProposition(newAtomicPreposition);
            }
        }
    }

    private String getQueueNameFromOneSidedAction(String oneSidedAction)
    {
        int breakIndex = 0;
        for(int i = 0; i < oneSidedAction.length(); i++)
        {
            if(oneSidedAction.charAt(i) == '!' || oneSidedAction.charAt(i) == '?')
                breakIndex = i;
        }
        return oneSidedAction.substring(0, breakIndex);
    }

    private <L, A> void addStateIfNeeded(
            TransitionSystem<Pair<List<L>, Map<String, Object>>, A, String> ts,
            Deque<Pair<List<L>, Map<String, Object>>> queue,
            Set<Pair<List<L>, Map<String, Object>>> allReadyChecked,
            Pair<List<L>, Map<String, Object>> toState) {
        if(!allReadyChecked.contains(toState))
        {
            ts.addState(toState);
            allReadyChecked.add(toState);
            queue.addLast(toState);
        }
    }

    private <L,A> Map<Integer, Set<PGTransition<L, A>>> getAllOneSidedActionTransFromAllTrans(Map<Integer, Set<PGTransition<L, A>>> allProgramGraphTransMap,
                                                                                              String readOrWrite)
    {
        InterleavingActDef channelActionDef = new ParserBasedInterleavingActDef();
        Map<Integer, Set<PGTransition<L, A>>> res = new HashMap<>();
        for(int i = 0; i < allProgramGraphTransMap.size(); i++)
        {
            Set<PGTransition<L, A>> programGraphOneSidedTrans = new HashSet<>();
            for(PGTransition<L, A> programGraphTrans : allProgramGraphTransMap.get(i))
            {
                if(channelActionDef.isOneSidedAction(programGraphTrans.getAction().toString()) &&
                        programGraphTrans.getAction().toString().contains(readOrWrite))
                    programGraphOneSidedTrans.add(programGraphTrans);
            }
            res.put(i, programGraphOneSidedTrans);
        }
        return res;
    }

    private <L, A> Set<PGTransition<L, A>> getAllTransitionsFromLoc(Set<PGTransition<L, A>> transitions, L loc)
    {
        Set<PGTransition<L, A>> transitionFromLocation = new HashSet<>();
        for(PGTransition<L, A> programGraphTransition : transitions)
        {
            if(programGraphTransition.getFrom().equals(loc))
            {
                transitionFromLocation.add(programGraphTransition);
            }
        }
        return transitionFromLocation;
    }

    private <L, A> Map<Integer, Set<PGTransition<L, A>>> programGraphsTransitionMapFromState(List<ProgramGraph<L, A>> programGraphs, Pair<List<L>, Map<String, Object>> fromState)
    {
        Map<Integer, Set<PGTransition<L, A>>> programGraphsTransitionMap = new HashMap<>();
        for(int i = 0; i < programGraphs.size(); i++)
        {
            ProgramGraph<L, A> currentProgramGraph = programGraphs.get(i);
            L locOfCurrPG = fromState.getFirst().get(i);
            Set<PGTransition<L, A>> currPGTransitions = getAllTransitionsFromLoc(currentProgramGraph.getTransitions(), locOfCurrPG);
            programGraphsTransitionMap.put(i, currPGTransitions);
        }
        return programGraphsTransitionMap;
    }

    private <L, A>void transitionSystemFromCSFromInitialStates(TransitionSystem<Pair<List<L>, Map<String, Object>>, A, String> transitionSystem,
                         ChannelSystem<L, A> channelSystem, Set<ActionDef> effects, Set<ConditionDef> condition)
    {
        InterleavingActDef channelActionDef = new ParserBasedInterleavingActDef();
        Deque<Pair<List<L>, Map<String, Object>>> queue = new LinkedList<>(transitionSystem.getInitialStates());
        Set<Pair<List<L>, Map<String, Object>>> allReadyChecked = new HashSet<>(transitionSystem.getInitialStates());
        while(!queue.isEmpty())
        {
            Pair<List<L>, Map<String, Object>> fromState = queue.removeFirst();
            Map<Integer, Set<PGTransition<L, A>>> allPGTransMap = programGraphsTransitionMapFromState(channelSystem.getProgramGraphs(), fromState);
            Map<Integer, Set<PGTransition<L, A>>> allOneSidedTransMapRead = getAllOneSidedActionTransFromAllTrans(allPGTransMap, "?");
            Map<Integer, Set<PGTransition<L, A>>> allOneSidedTransMapWrite = getAllOneSidedActionTransFromAllTrans(allPGTransMap, "!");
            for(int i = 0; i < allPGTransMap.size(); i++)
            {
                for(PGTransition<L, A> pgTrans : allPGTransMap.get(i))
                {
                    String currAction = pgTrans.getAction().toString();
                    if(ConditionDef.evaluate(condition, fromState.second, pgTrans.getCondition()))
                    {
                        if(!channelActionDef.isOneSidedAction(currAction) && ActionDef.effect(effects, fromState.second, pgTrans.getAction()) != null)
                        {
                            transitionSystem.addAction(pgTrans.getAction());
                            List<L> locsForNewState = new ArrayList<L>(fromState.getFirst());
                            locsForNewState.set(i, pgTrans.getTo());
                            Pair<List<L>, Map<String, Object>> toState = new Pair<>(locsForNewState, ActionDef.effect(effects, fromState.second, pgTrans.getAction()));
                            Transition< Pair<List<L>, Map<String, Object>>, A> newTrans = new Transition<>(fromState, pgTrans.getAction(), toState);
                            addStateIfNeeded(transitionSystem, queue, allReadyChecked, toState);
                            transitionSystem.addTransition(newTrans);
                        }
                        else //this is a capacity 0 action
                        {
                            Map<Integer, Set<PGTransition<L, A>>> allOneSidedTransToIterate = null;
                            String firstQueueName = getQueueNameFromOneSidedAction(currAction);
                            if(currAction.contains("?")) //this is an action such as _T?x, means read action from capacity 0
                                allOneSidedTransToIterate = allOneSidedTransMapWrite;
                            else//this is an action such as _T!3, means write action to capacity 0
                                allOneSidedTransToIterate = allOneSidedTransMapRead;

                            for(int pgNum = i+1; pgNum < channelSystem.getProgramGraphs().size(); pgNum++)
                            {
                                for(PGTransition<L, A> otherPGTrans : allOneSidedTransToIterate.get(pgNum))
                                {
                                    if(ConditionDef.evaluate(condition, fromState.second, otherPGTrans.getCondition()) &&
                                            firstQueueName.equals(getQueueNameFromOneSidedAction(otherPGTrans.getAction().toString())))
                                    {
                                        String newAction = currAction + "|" + otherPGTrans.getAction().toString();
                                        transitionSystem.addAction((A)newAction);
                                        List<L> locsForNewState = new ArrayList<L>(fromState.getFirst());
                                        locsForNewState.set(i, pgTrans.getTo());
                                        locsForNewState.set(pgNum, otherPGTrans.getTo());
                                        Pair<List<L>, Map<String, Object>> toState = new Pair<>(locsForNewState, channelActionDef.effect(fromState.second, newAction));
                                        Transition<Pair<List<L>, Map<String, Object>>, A> newTrans = new Transition<>(fromState, (A)newAction, toState);
                                        addStateIfNeeded(transitionSystem, queue, allReadyChecked, toState);
                                        transitionSystem.addTransition(newTrans);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private <L,A> void addInitialStatesTransitionSystemFromChannelSystemAccordingToSize(TransitionSystem<Pair<List<L>, Map<String, Object>>, A, String> transitionSystem,
                                                    ChannelSystem<L, A> channelSystem, List<L> newInitialLocations, Set<Map<String, Object>> evaluations)
    {
        int size = newInitialLocations.size();
        if(size == channelSystem.getProgramGraphs().size())
        {
            if(evaluations.isEmpty())
            {
                Pair<List<L>, Map<String, Object>> newState = new Pair<>
                        (new ArrayList<>(newInitialLocations), new HashMap<>());
                transitionSystem.addState(newState);
                transitionSystem.setInitial(newState, true);
            }

            for(Map<String, Object> evaluation : evaluations)
            {
                Pair<List<L>, Map<String, Object>> newState = new Pair<>
                        (new ArrayList<>(newInitialLocations), new HashMap<>(evaluation));
                transitionSystem.addState(newState);
                transitionSystem.setInitial(newState, true);
            }
        } else {
            for(L location : channelSystem.getProgramGraphs().get(size).getInitialLocations())
            {
                newInitialLocations.add(location);
                addInitialStatesTransitionSystemFromChannelSystemAccordingToSize(transitionSystem, channelSystem, newInitialLocations, evaluations);
                newInitialLocations.remove(location);
            }
        }
    }

    private Set<Map<String, Object>> getMapsFromInitializations(Set<List<String>> initializations, Set<ActionDef> effect)
    {
        Set<Map<String, Object>> evaluations = new HashSet<>();

        for(List<String> variableInit : initializations)
        {
            Map<String, Object> variableInitMap = new HashMap<>();
            for(String action : variableInit)
                variableInitMap = ActionDef.effect(effect, variableInitMap, action);
            evaluations.add(variableInitMap);
        }

        return evaluations;
    }

    private <L,A> void getInitializationsFromChannelSystem(ChannelSystem<L, A> channelSystem,
                           Set<List<String>> allInitializations, int programGraphNumber, List<String> acc) {
        if (programGraphNumber == channelSystem.getProgramGraphs().size()){
            allInitializations.add(acc);
        } else {
            if(channelSystem.getProgramGraphs().get(programGraphNumber).getInitalizations().isEmpty()) {
                getInitializationsFromChannelSystem(channelSystem, allInitializations, programGraphNumber + 1, acc);
            } else {
                for(List<String> initialization : channelSystem.getProgramGraphs().get(programGraphNumber).getInitalizations())
                {
                    List<String> newAcc = new ArrayList<>(acc);
                    newAcc.addAll(initialization);
                    getInitializationsFromChannelSystem(channelSystem, allInitializations, programGraphNumber + 1, newAcc);
                }
            }
        }
    }

    private <L,A> void addInitialStateTransitionSystemFromChannelSystem(TransitionSystem<Pair<List<L>, Map<String, Object>>, A, String> transitionSystem,
                                               ChannelSystem<L, A> channelSystem, Set<ActionDef> effects)
    {
        List<L> newInitialLocations = new ArrayList<>();
        Set<List<String>> initializations = new HashSet<>();
        getInitializationsFromChannelSystem(channelSystem, initializations, 0, new ArrayList<>());
        Set<Map<String, Object>> evaluations = getMapsFromInitializations(initializations, effects);
        addInitialStatesTransitionSystemFromChannelSystemAccordingToSize(transitionSystem, channelSystem, newInitialLocations, evaluations);
    }

    @Override
    public <L, A> TransitionSystem<Pair<List<L>, Map<String, Object>>, A, String> transitionSystemFromChannelSystem(ChannelSystem<L, A> channelSystem) {
        TransitionSystem<Pair<List<L>, Map<String, Object>>, A, String> transitionSystem = createTransitionSystem();
        Set<ActionDef> effects = new HashSet<>();
        effects.add(new ParserBasedActDef());
        Set<ConditionDef> conditions = new HashSet<>();
        conditions.add(new ParserBasedCondDef());
        addInitialStateTransitionSystemFromChannelSystem(transitionSystem, channelSystem, effects);
        transitionSystemFromCSFromInitialStates(transitionSystem, channelSystem, effects, conditions);
        addAtomicPropositionToTransitionSystemFromChannelSystem(transitionSystem);
        addLabelsToTransitionSystemFromChannelSystem(transitionSystem);
        return transitionSystem;
    }

    private <Sts, Saut, A, P> void addLabelsToProduct(TransitionSystem<Pair<Sts, Saut>, A, Saut> productTransitionSystem)
    {
        for(Pair<Sts, Saut> sts : productTransitionSystem.getStates())
        {
            Saut labelToAdd = sts.getSecond();
            productTransitionSystem.addAtomicProposition(labelToAdd);
            productTransitionSystem.addToLabel(sts, labelToAdd);
        }
    }

    private <Sts, Saut, A, P> void createProductFromInitialStates(TransitionSystem<Pair<Sts, Saut>, A, Saut> productTransitionSystem,
                                                                 TransitionSystem<Sts, A, P> transitionSystem, Automaton<Saut, P> automaton)
    {
        Map<Sts, Set<P>> transitionSystemLabelingFunction = transitionSystem.getLabelingFunction();
        Set<Transition<Sts, A>> transitionSystemTransitions = transitionSystem.getTransitions();
        Map<Saut, Map<Set<P>, Set<Saut>>> automatonTransitions = automaton.getTransitions();
        Deque<Pair<Sts, Saut>> queue = new LinkedList<>(productTransitionSystem.getInitialStates());
        Set<Pair<Sts, Saut>> allReadyChecked = new HashSet<>(productTransitionSystem.getInitialStates());
        while(!queue.isEmpty())
        {
            Pair<Sts, Saut> stateToCheck = queue.removeFirst();
            for(Transition<Sts, A> tsTrans : transitionSystemTransitions)
            {
                if(tsTrans.getFrom().equals(stateToCheck.getFirst()))
                {
                    A action = tsTrans.getAction();
                    Sts tsToState = tsTrans.getTo();
                    Set<P> labelTsToState =  transitionSystemLabelingFunction.get(tsToState) != null ? transitionSystemLabelingFunction.get(tsToState) :
                            new HashSet<>();
                    Saut autFromState = stateToCheck.getSecond();

                    //check if there is a transition from autState with action which is the label of tsState
                    if(automatonTransitions.containsKey(autFromState) &&
                            automatonTransitions.get(autFromState).containsKey(labelTsToState))
                    {
                        Set<Saut> autToStates = automatonTransitions.get(autFromState).get(labelTsToState);
                        for(Saut autToState : autToStates)
                        {
                            Pair<Sts, Saut> newSts = new Pair<>(tsToState, autToState);
                            if(!allReadyChecked.contains(newSts))
                            {
                                allReadyChecked.add(newSts);
                                queue.addLast(newSts);
                                productTransitionSystem.addState(newSts);
                            }
                            productTransitionSystem.addAction(action);
                            Transition<Pair<Sts, Saut>, A> transToAdd = new Transition<>(stateToCheck, action , newSts);
                            productTransitionSystem.addTransition(transToAdd);
                        }
                    }
                }
            }
        }
    }

    private <Sts, Saut, A, P> void createProductInitialStates(TransitionSystem<Pair<Sts, Saut>, A, Saut> productTransitionSystem,
                                                             TransitionSystem<Sts, A, P> transitionSystem, Automaton<Saut, P> automaton)
    {
        Set<Sts> tsInitials = transitionSystem.getInitialStates();
        Map<Sts, Set<P>> transitionSystemLabelingFunction = transitionSystem.getLabelingFunction();
        Set<Saut> automatonInitials = automaton.getInitialStates();
        Map<Saut, Map<Set<P>, Set<Saut>>> automatonTransitions = automaton.getTransitions();

        for(Sts tsState : tsInitials)
        {
            Set<P> transitionSystemLabel = transitionSystemLabelingFunction.get(tsState) != null ? transitionSystemLabelingFunction.get(tsState) : new HashSet<P>();
            for(Saut automatonState: automatonInitials)
            {
                //check if there is a transition from autState with action which is the label of tsState
                if(automatonTransitions.containsKey(automatonState) && automatonTransitions.get(automatonState).containsKey(transitionSystemLabel))
                {
                    Set<Saut> toStates = automatonTransitions.get(automatonState).get(transitionSystemLabel);
                    for(Saut toState : toStates)
                    {
                        Pair<Sts, Saut> newSts = new Pair<>(tsState, toState);
                        productTransitionSystem.addState(newSts);
                        productTransitionSystem.setInitial(newSts, true);
                    }
                }
            }
        }
    }

    @Override
    public <Sts, Saut, A, P> TransitionSystem<Pair<Sts, Saut>, A, Saut> product(TransitionSystem<Sts, A, P> transitionSystem,
                                                                                Automaton<Saut, P> automaton) {
        TransitionSystem<Pair<Sts, Saut>, A, Saut> productTransitionSystem = createTransitionSystem();
        createProductInitialStates(productTransitionSystem, transitionSystem, automaton);
        createProductFromInitialStates(productTransitionSystem, transitionSystem, automaton);
        addLabelsToProduct(productTransitionSystem);
        return productTransitionSystem;
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
