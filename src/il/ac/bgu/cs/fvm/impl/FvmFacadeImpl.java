package il.ac.bgu.cs.fvm.impl;

import il.ac.bgu.cs.fvm.FvmFacade;
import il.ac.bgu.cs.fvm.automata.Automaton;
import il.ac.bgu.cs.fvm.automata.MultiColorAutomaton;
import il.ac.bgu.cs.fvm.channelsystem.ChannelSystem;
import il.ac.bgu.cs.fvm.channelsystem.InterleavingActDef;
import il.ac.bgu.cs.fvm.channelsystem.ParserBasedInterleavingActDef;
import il.ac.bgu.cs.fvm.circuits.Circuit;
import il.ac.bgu.cs.fvm.ltl.AP;
import il.ac.bgu.cs.fvm.ltl.And;
import il.ac.bgu.cs.fvm.ltl.LTL;
import il.ac.bgu.cs.fvm.ltl.Next;
import il.ac.bgu.cs.fvm.ltl.Not;
import il.ac.bgu.cs.fvm.ltl.TRUE;
import il.ac.bgu.cs.fvm.ltl.Until;
import il.ac.bgu.cs.fvm.nanopromela.NanoPromelaFileReader;
import il.ac.bgu.cs.fvm.nanopromela.NanoPromelaParser.DostmtContext;
import il.ac.bgu.cs.fvm.nanopromela.NanoPromelaParser.IfstmtContext;
import il.ac.bgu.cs.fvm.nanopromela.NanoPromelaParser.OptionContext;
import il.ac.bgu.cs.fvm.nanopromela.NanoPromelaParser.StmtContext;
import il.ac.bgu.cs.fvm.programgraph.ActionDef;
import il.ac.bgu.cs.fvm.programgraph.ConditionDef;
import il.ac.bgu.cs.fvm.programgraph.PGTransition;
import il.ac.bgu.cs.fvm.programgraph.ParserBasedActDef;
import il.ac.bgu.cs.fvm.programgraph.ParserBasedCondDef;
import il.ac.bgu.cs.fvm.programgraph.ProgramGraph;
import il.ac.bgu.cs.fvm.transitionsystem.AlternatingSequence;
import il.ac.bgu.cs.fvm.transitionsystem.Transition;
import il.ac.bgu.cs.fvm.transitionsystem.TransitionSystem;
import il.ac.bgu.cs.fvm.util.Pair;
import il.ac.bgu.cs.fvm.verification.VerificationFailed;
import il.ac.bgu.cs.fvm.verification.VerificationResult;
import il.ac.bgu.cs.fvm.verification.VerificationSucceeded;

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

    private String getNewNekudaPsikStateFromFirstAndLastsStmts(String first, String lastsStatements) {
        String res = first;
        if(!res.isEmpty())
            res += ";";
        res += lastsStatements;
        return res;
    }

    private String turnToNanoPromelaString(String loc)
    {
        String res = "";
        for(int i = 0; i < loc.length() ; i++)
        {
            if(i+1 < loc.length() && ((loc.charAt(i) == 'o' && loc.charAt(i + 1) == 'd') || (loc.charAt(i) == 'f' && loc.charAt(i + 1) == 'i')))
            {
                if(!(i+2 < loc.length() && loc.substring(i-1, i+3).equals("soda")))
                {
                    res += " " + loc.charAt(i) + loc.charAt(i + 1);
                    i++;
                }
                else
                    res += loc.charAt(i);
            }
            else
                res += loc.charAt(i);
        }
        return res;
    }

    private void runNewLocs(ProgramGraph<String, String> pg, Set<String> originalLocations) {
        Set<String> updatedLocations = new HashSet<>(pg.getLocations());
        for(String location : updatedLocations)
            if(!originalLocations.contains(location))
                programGraphFromRootHelp(pg, NanoPromelaFileReader.pareseNanoPromelaString(turnToNanoPromelaString(location)));
    }

    private void getOriginalPGFromSubPG(ProgramGraph<String, String> pg,
                                        StmtContext rootStmt, String cond, StmtContext firstState,
                                        ProgramGraph<String, String> firstStatementPG,
                                        String lastsStatements) {
        for(PGTransition<String, String> firstPGTrans : firstStatementPG.getTransitions())
        {
            String fromState = rootStmt.getText();
            String newCond = firstPGTrans.getCondition();
            if(!firstPGTrans.getFrom().equals(firstState.getText()))
            {
                fromState = getNewNekudaPsikStateFromFirstAndLastsStmts(firstPGTrans.getFrom(), lastsStatements);
                pg.addLocation(fromState);
            }
            else
            {
                if(!cond.isEmpty())
                {
                    if(!newCond.isEmpty())
                        newCond = cond + " && (" + newCond;
                    else
                        newCond = cond;
                }
            }
            String toState = getNewNekudaPsikStateFromFirstAndLastsStmts(firstPGTrans.getTo(), lastsStatements);
            pg.addLocation(toState);

            newCond = addClosingParanToCond(newCond);
            PGTransition<String, String> newTransForOriginalPG = new PGTransition<String, String>(fromState, newCond, firstPGTrans.getAction(), toState);
            pg.addTransition(newTransForOriginalPG);
        }
    }

    private void handleAllDoOptionsDontExist(ProgramGraph<String, String> programGraph,
                                             StmtContext rootStmt, String condition, List<OptionContext> options) {
        String newCondition = condition;
        if(!newCondition.isEmpty())
            newCondition += " && (";
        newCondition += "!(";
        for(int i = 0; i < options.size(); i++)
        {
            newCondition = newCondition + "(" + options.get(i).boolexpr().getText() + ")";
            if(i < options.size() - 1)
                newCondition += "||";
        }
        newCondition = addClosingParanToCond(newCondition);
        PGTransition<String, String> newTrans = new PGTransition<String, String>(rootStmt.getText(), newCondition, "", "");
        programGraph.addTransition(newTrans);
    }

    private String addClosingParanToCond(String s)
    {
        String res = s;
        int countOpen = 0;
        for(int i = 0; i < s.length(); i++)
        {
            if(s.charAt(i) == '(')
                countOpen++;
            else if(s.charAt(i) == ')')
                countOpen--;
        }

        for(int i = 0 ; i < countOpen; i++)
        {
            res += ")";
        }
        return res;
    }

    private void handleIfDoStmt(ProgramGraph<String, String> programGraph, StmtContext rootStmt, StmtContext child, String condition)
    {
        if(isSimpleStmt(child))
        {
            condition = addClosingParanToCond(condition);
            PGTransition<String, String> newTrans = new PGTransition<>(rootStmt.getText(), condition, child.getText(), "");
            programGraph.addTransition(newTrans);
        }
        else if(child.ifstmt() != null)
        {
            IfstmtContext ifStmt = child.ifstmt();
            List<OptionContext> options = ifStmt.option();
            String newCondition = condition;
            if(!newCondition.isEmpty())
                newCondition += " && (";
            for(OptionContext opt : options)
            {
                handleIfDoStmt(programGraph, rootStmt, opt.stmt(), newCondition + "(" + opt.boolexpr().getText() + ")");
            }
        }
        else if(child.dostmt() != null)
        {
            DostmtContext doStmt = child.dostmt();
            List<OptionContext> options = doStmt.option();

            Set<String> originalLocations = new HashSet<>(programGraph.getLocations());
            //add all option dont exist
            handleAllDoOptionsDontExist(programGraph, rootStmt, condition, options);

            for(OptionContext opt : options)
            {
                ProgramGraph<String, String> optPG = programGraphFromRoot(opt.stmt());
                String newCondition = condition;
                if(!newCondition.isEmpty() && !opt.boolexpr().getText().isEmpty())
                    newCondition += " && (";
                newCondition += "(" + opt.boolexpr().getText() + ")";
                newCondition = addClosingParanToCond(newCondition);
                getOriginalPGFromSubPG(programGraph, rootStmt, newCondition, opt.stmt(), optPG, doStmt.getText());
            }
            runNewLocs(programGraph, originalLocations);
        }
        else if (child.stmt() != null)
        {
            condition = addClosingParanToCond(condition);
            handleNekudaPsikStatement(programGraph, rootStmt, child, condition);
        }
    }

    private StmtContext getFirstStmt(StmtContext s)
    {
        StmtContext res = s;
        while(res.stmt() != null && !res.stmt().isEmpty())
            res = res.stmt().get(0);
        return res;
    }

    private List<StmtContext> getRestStmts(StmtContext s)
    {
        List<StmtContext> res = new ArrayList<StmtContext>();
        StmtContext iterator = s;
        while(iterator.stmt() != null && !iterator.stmt().isEmpty())
        {
            res.add(0, iterator.stmt().get(1));
            iterator = iterator.stmt().get(0);
        }
        return res;
    }

    private String turnStmtsToString(List<StmtContext> stmts)
    {
        String res = stmts.get(0).getText();
        for(int i = 1; i < stmts.size(); i++)
        {
            res = res + ";" + stmts.get(i).getText();
        }
        return res;
    }

    private void handleNekudaPsikStatement(ProgramGraph<String, String> programGraph,
                                           StmtContext rootStmt, StmtContext child, String condition) {
        StmtContext firstState = getFirstStmt(child);
        List<StmtContext> restStmts = getRestStmts(child);
        ProgramGraph<String, String> firstStatementPG = programGraphFromRoot(firstState);
        String lastsStatementsString = turnStmtsToString(restStmts);
        Set<String> originalLocations = new HashSet<>(programGraph.getLocations());
        getOriginalPGFromSubPG(programGraph, rootStmt, condition, firstState, firstStatementPG, lastsStatementsString);
        runNewLocs(programGraph, originalLocations);
    }


    private boolean isSimpleStmt(StmtContext stmt)
    {
        return stmt.assstmt() != null || stmt.atomicstmt() != null || stmt.chanreadstmt() != null || stmt.chanwritestmt() != null || stmt.skipstmt() != null;
    }

    private void programGraphFromRootHelp(ProgramGraph<String, String> programGraph, StmtContext root)
    {
        if(isSimpleStmt(root))
        {
            PGTransition<String, String> newTransition = new PGTransition<>(root.getText(), "", root.getText(), "");
            programGraph.addTransition(newTransition);
        }
        else if(root.ifstmt() != null || root.dostmt() != null)
        {
            handleIfDoStmt(programGraph, root, root, "");
        }
        else if(root.stmt() != null)
        {
            handleNekudaPsikStatement(programGraph, root, root, "");
        }
    }

    private ProgramGraph<String, String> programGraphFromRoot(StmtContext root)
    {
        ProgramGraph<String, String> programGraph =  createProgramGraph();
        programGraph.addLocation(root.getText());
        programGraph.setInitial(root.getText(), true);
        programGraph.addLocation("");

        programGraphFromRootHelp(programGraph, root);
        return programGraph;
    }

    @Override
    public ProgramGraph<String, String> programGraphFromNanoPromela(String filename) throws Exception {
        StmtContext stmtContext = NanoPromelaFileReader.pareseNanoPromelaFile(filename);
        ProgramGraph<String, String> programGraph = programGraphFromRoot(stmtContext);
        return programGraph;
    }

    @Override
    public ProgramGraph<String, String> programGraphFromNanoPromelaString(String nanopromela) throws Exception {
        StmtContext stmtContext = NanoPromelaFileReader.pareseNanoPromelaString(nanopromela);
        ProgramGraph<String, String> programGraph = programGraphFromRoot(stmtContext);
        return programGraph;
    }

    @Override
    public ProgramGraph<String, String> programGraphFromNanoPromela(InputStream inputStream) throws Exception {
        StmtContext stmtContext = NanoPromelaFileReader.parseNanoPromelaStream(inputStream);
        ProgramGraph<String, String> programGraph = programGraphFromRoot(stmtContext);
        return programGraph;
    }

    private <S, A, Saut> List<S> findRouteBetweenStatesHelper(TransitionSystem<Pair<S, Saut>, A, Saut> transitionSystemAfterProduct,
                                                                Pair<S, Saut> from, Pair<S, Saut> to, Set<Pair<S, Saut>> allReadyChecked, List<S> route)
    {
        List<S> newRoute = new LinkedList<>(route);
        newRoute.add(from.getFirst());
        for(Transition<Pair<S, Saut>, A> transition : transitionSystemAfterProduct.getTransitions())
        {
            if(transition.getFrom().equals(from))
            {
                Pair<S, Saut> toState = transition.getTo();
                if(toState.equals(to))
                {
                    return newRoute;
                }
                if(!allReadyChecked.contains(toState))
                {
                    allReadyChecked.add(toState);
                    List<S> tmpAns = findRouteBetweenStatesHelper(transitionSystemAfterProduct, toState, to, allReadyChecked, newRoute);
                    if(tmpAns != null)
                        return tmpAns;
                }
            }
        }

        return null;
    }

    private <S, A, Saut> List<S> findRouteBetweenStates(TransitionSystem<Pair<S, Saut>, A, Saut> transitionSystemAfterProduct,
                                                          Pair<S, Saut> from, Pair<S, Saut> to)
    {
        Set<Pair<S, Saut>> allReadyChecked = new HashSet<>();
        List<S> ans = findRouteBetweenStatesHelper(transitionSystemAfterProduct, from, to, allReadyChecked, new LinkedList<S>());
        return ans;
    }

    @Override
    public <S, A, P, Saut> VerificationResult<S> verifyAnOmegaRegularProperty(TransitionSystem<S, A, P> transitionSystem, Automaton<Saut, P> automaton) {
        TransitionSystem<Pair<S, Saut>, A, Saut> transitionSystemAfterProduct = product(transitionSystem, automaton);
        Set<Saut> acceptingStatesAut = automaton.getAcceptingStates();
        for(Pair<S, Saut> sTsAfterProduct : transitionSystemAfterProduct.getStates())
        {
            //check if this is an accepting state in the product ts
            if(acceptingStatesAut.contains(sTsAfterProduct.getSecond()))
            {
                List<S> cycle = findRouteBetweenStates(transitionSystemAfterProduct, sTsAfterProduct, sTsAfterProduct);
                if(cycle != null)
                {
                    for(Pair<S, Saut> initalState : transitionSystemAfterProduct.getInitialStates())
                    {
                        List<S> prefix = findRouteBetweenStates(transitionSystemAfterProduct, initalState, sTsAfterProduct);
                        VerificationFailed<S> ans = new VerificationFailed<>();
                        ans.setCycle(cycle);
                        ans.setPrefix(prefix);
                        return ans;
                    }
                }
            }
        }
        return new VerificationSucceeded<>();
    }

    private static <L> void addInitialsAutomaton(
            MultiColorAutomaton<Set<LTL<L>>, L> aut, LTL<L> baseExpr,
            List<Set<LTL<L>>> all_states) {
        for (Set<LTL<L>> s : all_states) {
            aut.addState(s);
            if (s.contains(baseExpr))
                aut.setInitial(s);
        }
    }

    private static <L> void addAcceptingAutomaton(
            MultiColorAutomaton<Set<LTL<L>>, L> aut, Set<Until<L>> untilExprs,
            List<Set<LTL<L>>> all_states) {
        int counter = 1;
        for (Until<L> until : untilExprs) {
            for (Set<LTL<L>> s : all_states)
                if (!s.contains(until) || s.contains(until.getRight()))
                    aut.setAccepting(s, counter);

            counter++;
        }
    }

    private static <L> void checkWhichStates(
            MultiColorAutomaton<Set<LTL<L>>, L> aut, LTL<L> baseExpr,
            Set<Until<L>> untilExprs, List<Set<LTL<L>>> subs,
            List<Set<LTL<L>>> all_states) {
        for (Set<LTL<L>> sub_exp : subs) {
            Iterator<LTL<L>> itr = sub_exp.iterator();
            boolean exist = !sub_exp.contains(LTL.not(new TRUE<L>()));
            while (exist && itr.hasNext()) {
                LTL<L> item = itr.next();
                if (item instanceof And)
                    if(sub_exp.contains(((And<L>) item).getLeft()) && sub_exp.contains(((And<L>) item).getRight()))
                        exist = true;
                    else
                        exist = false;
                else if (item instanceof Until)
                    if(sub_exp.contains(((Until<L>) item).getRight())
                            || sub_exp.contains(((Until<L>) item).getLeft()))
                        exist = true;
                    else
                        exist = false;
                else if (item instanceof Not) {
                    LTL<L> innerItem = ((Not<L>) item).getInner();
                    if (innerItem instanceof And)
                        if(!(sub_exp.contains(((And<L>) innerItem).getLeft())
                                && sub_exp.contains(((And<L>) innerItem).getRight())))
                            exist = true;
                        else
                            exist = false;
                    else if (innerItem instanceof Until)
                        if( !(sub_exp.contains(((Until<L>) innerItem).getRight())))
                            exist = true;
                        else
                            exist = false;
                }
            }

            if (exist)
                all_states.add(sub_exp);

        }

        addInitialsAutomaton(aut, baseExpr, all_states);

        addAcceptingAutomaton(aut, untilExprs, all_states);
    }

    private static <L> Set<LTL<L>> topoExps(LTL<L> expr) {
        Set<LTL<L>> checked = new HashSet<>();
        Queue<LTL<L>> needToCheck = new ArrayDeque<>();
        needToCheck.add(expr);

        while (!needToCheck.isEmpty()) {
            LTL<L> sub = needToCheck.poll();
            if (checked.contains(sub) || sub instanceof Not)
            {
                if(sub instanceof Not)
                    needToCheck.add(((Not<L>) sub).getInner());
                continue;
            }

            checked.add(sub);

            if (sub instanceof Next) {
                needToCheck.add(((Next<L>) sub).getInner());
            } else if (sub instanceof And || sub instanceof Until) {
                if(sub instanceof And)
                {
                    needToCheck.add(((And<L>) sub).getLeft());
                    needToCheck.add(((And<L>) sub).getRight());
                }
                else
                {
                    needToCheck.add(((Until<L>) sub).getLeft());
                    needToCheck.add(((Until<L>) sub).getRight());
                }
            }

        }
        return checked;
    }

    private <L> void addUntilAndNext(Set<LTL<L>> subs, Set<Until<L>> u,
                                     Set<Next<L>> n) {
        for (LTL<L> expr : subs)
            if (expr instanceof Until)
                u.add((Until<L>) expr);
            else if (expr instanceof Next)
                n.add((Next<L>) expr);
    }

    private static <L> void addAllExpressions(Set<LTL<L>> exprs, int size_of_exprs,
                                       List<Set<LTL<L>>> subs) {
        int expressions = 0;
        while(expressions < size_of_exprs)
        {
            subs.add(new HashSet<>(exprs.size()));
            expressions++;
        }
    }

    private static <L> void checkAllExpsss(Set<LTL<L>> exprs,
                                           int size_of_exprs, List<Set<LTL<L>>> subs) {
        {
            int i, j, counter;
            Iterator<LTL<L>> it = exprs.iterator();
            LTL<L> curr_item;
            boolean flag;
            for (i = 1; it.hasNext(); i = i * 2) {
                for (j = 0, counter = 0, flag = true, curr_item = it.next(); j < size_of_exprs; j++) {
                    subs.get(j).add(flag ? curr_item : LTL.not(curr_item));
                    counter = (counter + 1);
                    counter = counter % i;
                    if(counter == 0)
                        flag = !flag;
                }
            }
        }
    }

    private static <L> List<Set<LTL<L>>> LTLComputeStates(MultiColorAutomaton<Set<LTL<L>>, L> aut, LTL<L> baseExpr,
                                                          Set<LTL<L>> expression, Set<Until<L>> untilExpress) {
        int size_of_expression = (int) Math.pow(2, expression.size());

        List<Set<LTL<L>>> subs = new ArrayList<>(size_of_expression);
        addAllExpressions(expression, size_of_expression, subs);

        checkAllExpsss(expression, size_of_expression, subs);

        List<Set<LTL<L>>> all_states = new ArrayList<>();
        checkWhichStates(aut, baseExpr, untilExpress, subs, all_states);

        return all_states;
    }

    private static <L> boolean isPost(Set<LTL<L>> fromState, Set<LTL<L>> toState, Set<Until<L>> untilExps,
                                      Set<Next<L>> nextExprs) {
        for (Next<L> expression : nextExprs)
            if (fromState.contains(expression) != toState.contains(expression.getInner()))
                return false;

        for (Until<L> expression : untilExps)
            if (fromState.contains(expression) != (fromState.contains(expression.getRight())
                    || (fromState.contains(expression.getLeft()) && toState.contains(expression))))
                return false;

        return true;
    }

    private <L> void noColorsSetAccepting(
            MultiColorAutomaton<Set<LTL<L>>, L> automaton,
            List<Set<LTL<L>>> allStates) {
        if (automaton.getColors().size() == 0)
            for (Set<LTL<L>> s : allStates)
                automaton.setAccepting(s, 1);
    }



    @Override
    public <L> Automaton<?, L> LTL2NBA(LTL<L> ltl) {
        MultiColorAutomaton<Set<LTL<L>>, L> automaton = new MultiColorAutomaton<>();
        Set<LTL<L>> subs = topoExps(ltl);

        Set<Until<L>> u = new HashSet<>();
        Set<Next<L>> n = new HashSet<>();
        addUntilAndNext(subs, u, n);

        List<Set<LTL<L>>> allStates = LTLComputeStates(automaton, ltl, subs, u);

        for (Set<LTL<L>> fromState : allStates) {
            Set<L> act = new HashSet<>();
            for (LTL<L> e : fromState)
                if (e instanceof AP)
                    act.add(((AP<L>) e).getName());

            for (Set<LTL<L>> toState : allStates)
                if (isPost(fromState, toState, u, n))
                    automaton.addTransition(fromState, act, toState);

        }

        noColorsSetAccepting(automaton, allStates);
        return GNBA2NBA(automaton);
    }

    private <L> List<Integer> getColors(MultiColorAutomaton<?, L> mulAut) {
        List<Integer> colors = new LinkedList<>(mulAut.getColors());
        if (colors.size() == 0) {
            colors.add(1);
        }
        Collections.sort(colors);

        return colors;
    }

    private <L> void addInitialsNBA(MultiColorAutomaton<?, L> mulAut,
                                      Automaton<Pair<?, Integer>, L> nbaAutomaton, Integer color) {
        for (Object initial : mulAut.getInitialStates()) {
            Pair<?, Integer> state = new Pair<>(initial, color);
            nbaAutomaton.addState(state);
            nbaAutomaton.setInitial(state);
        }
    }

    private <L> void findGNBAInitials(MultiColorAutomaton<?, L> mulAut, Automaton<Pair<?, Integer>, L> nbaAutomaton,
                                      Set<Pair<?, Integer>> checked, Queue<Pair<?, Integer>> stateQ, Integer color) {

        addInitialsNBA(mulAut, nbaAutomaton, color);

        checked.addAll(nbaAutomaton.getInitialStates());
        stateQ.addAll(nbaAutomaton.getInitialStates());
    }

    private <L> void dfsOnNBATransitions(Automaton<Pair<?, Integer>, L> nba,
                                         Set<Pair<?, Integer>> checked, Queue<Pair<?, Integer>> stateQ,
                                         Pair<?, Integer> from, Map<Set<L>, ? extends Set<?>> transitions,
                                         int counter) {
        for (Set<L> a : transitions.keySet()) {
            for (Object toState : transitions.get(a)) {
                Pair<?, Integer> pair = Pair.pair(toState, counter);
                nba.addTransition(from, a, pair);

                if (checked.contains(pair))
                    continue;

                nba.addState(pair);
                stateQ.add(pair);
                checked.add(pair);

            }
        }
    }

    private <L> void computeTransitionsGNBA(MultiColorAutomaton<?, L> mulAut, Automaton<Pair<?, Integer>, L> nba,
                                            Set<Pair<?, Integer>> checked, Queue<Pair<?, Integer>> stateQ, List<Integer> colors) {
        while (!stateQ.isEmpty()) {
            Pair<?, Integer> from = stateQ.poll();
            Map<Set<L>, ? extends Set<?>> transitions = mulAut.getTransitions().get(from.getFirst());

            if (transitions == null)
                continue;

            int counter = mulAut.getAcceptingStates(from.getSecond()).contains(from.getFirst())
                    ? colors.get((colors.indexOf(from.getSecond()) + 1) % colors.size()) : from.getSecond();
            dfsOnNBATransitions(nba, checked, stateQ, from, transitions, counter);
        }

    }

    private <L> void setAcceptingStatesNBA(
            MultiColorAutomaton<?, L> mulAut,
            Automaton<Pair<?, Integer>, L> nba, Integer firstColor) {
        for (Object state : mulAut.getAcceptingStates(firstColor)) {
            nba.setAccepting(new Pair<>(state, firstColor));
        }
    }

    @Override
    public <L> Automaton<?, L> GNBA2NBA(MultiColorAutomaton<?, L> multiColorAutomaton) {
        Automaton<Pair<?, Integer>, L> nba = new Automaton<>();
        Set<Pair<?, Integer>> visited = new HashSet<>();
        Queue<Pair<?, Integer>> stateQ = new ArrayDeque<>();
        List<Integer> colors = getColors(multiColorAutomaton);
        Integer firstColor = colors.get(0);
        findGNBAInitials(multiColorAutomaton, nba, visited, stateQ, firstColor);
        computeTransitionsGNBA(multiColorAutomaton, nba, visited, stateQ, colors);
        setAcceptingStatesNBA(multiColorAutomaton, nba, firstColor);
        return nba;
    }
   
}
