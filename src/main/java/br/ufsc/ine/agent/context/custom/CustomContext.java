package br.ufsc.ine.agent.context.custom;

import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.MalformedGoalException;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Theory;
import br.ufsc.ine.agent.context.ContextService;
import br.ufsc.ine.utils.PrologEnvironment;

public class CustomContext  implements ContextService {

    private String name;
    private PrologEnvironment prologEnvironment;

    public CustomContext(String name){
        this.name = name.toLowerCase();
        prologEnvironment = new PrologEnvironment();
    }

    @Override
    public Theory getTheory() {
        return  prologEnvironment.getEngine().getTheory();
    }

    @Override
    public boolean verify(String fact) {
        SolveInfo solveGoal;
        try {
            solveGoal = prologEnvironment.solveGoal(fact);
            return solveGoal.isSuccess();
        } catch (MalformedGoalException e) {
            return false;
        }
    }

    @Override
    public void appendFact(String fact) {
        try {
            prologEnvironment.appendFact(fact);
        } catch (InvalidTheoryException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addInitialFact(String fact) throws InvalidTheoryException {

    }

    @Override
    public String getName() {
        return this.name;
    }
}
