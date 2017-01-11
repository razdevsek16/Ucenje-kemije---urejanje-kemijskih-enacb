grammar Chemical;

@header {
    package com.berkeleychurchill.chembal;
    import java.util.ArrayList;
}

@lexer::header
{
    package com.berkeleychurchill.chembal;
}

@lexer::members
{

    private boolean fail = false;
    private String errors = "";

    @Override
    public void displayRecognitionError(String[] tokenNames,
            RecognitionException e)
    {
        String hdr = getErrorHeader(e);
        String msg = getErrorMessage(e, tokenNames);

        fail = true;
        errors += " (" + e.charPositionInLine + ") " + msg + "\n";

    }

    public String getErrors()
    {
        if(fail)
            return errors;
        else
            return null;
    }

}

@members
{

    private boolean fail = false;
    private String errors = "";

    @Override
    public void displayRecognitionError(String[] tokenNames,
            RecognitionException e)
    {
        String msg = getErrorMessage(e, tokenNames);

        fail = true;
        errors += " (" + e.charPositionInLine + ") " + msg + "\n";
    }


    public String getErrors()
    {
        if(fail)
            return errors;
        else
            return null;
    }

    public static void main(String args[]) throws Exception
    {

        ElementCallback printCallback = new ElementCallback()
        {
            public void handleElement(String element, int number)
            {
                System.out.println(element + " : " + number);
            }
        };

        for(String s : args)
            parseChemical(s, printCallback);

    }

    public static void parseChemical(String s, ElementCallback callback) throws InvalidUserInputException
    {
        ChemicalLexer lex = new ChemicalLexer(new ANTLRStringStream(s));
        CommonTokenStream tokens = new CommonTokenStream(lex);
        ChemicalParser parser = new ChemicalParser(tokens);

        if(lex.getErrors() != null)
            throw new InvalidUserInputException(lex.getErrors());

        try{
            TreeExpr root = parser.chemexpr();
            root.traverse(1,callback);
        }catch(Exception e)
        {
            throw new InvalidUserInputException(e.getMessage());
        }

        if(lex.getErrors() != null)
            throw new InvalidUserInputException(lex.getErrors());
        if(parser.getErrors() != null)
            throw new InvalidUserInputException(parser.getErrors());


    }

    public interface ElementCallback
    {
        public void handleElement(String element, int number);
    }

    public class TreeExpr
    {
        ArrayList<String> elements;
        ArrayList<Integer> elementCounts;
        ArrayList<TreeExpr> children;
        ArrayList<Integer> childCounts;

        public TreeExpr()
        {
            elements = new ArrayList<String>();
            elementCounts = new ArrayList<Integer>();
            children = new ArrayList<TreeExpr>();
            childCounts = new ArrayList<Integer>();

        }

        public void handleElement(String element, int count)
        {
            elements.add(element);
            elementCounts.add(count);
        }

        public void addMult(TreeExpr t, int factor)
        {
            children.add(t);
            childCounts.add(factor);
        }

        public void traverse(int multiplier, ElementCallback callback)
        {
            for(int i = 0;i<elements.size();i++)
                callback.handleElement(elements.get(i),multiplier*elementCounts.get(i));
            for(int i = 0;i<children.size();i++)
                children.get(i).traverse(multiplier*childCounts.get(i),callback);
        }

    }
}

/* PARSER RULES */

chemexpr returns [TreeExpr root]
: {root = new TreeExpr(); } expr0[root] ( '^' {int m=1;} ( '-' {m=-1;} | n=NUMBER {m = Integer.parseInt($n.text);} )? {root.handleElement("^",m);})? EOF
;

expr0 [TreeExpr root]
: ( expr1[root] )+ 
;

expr1 [TreeExpr root]
: e=ELEMENT { root.handleElement($e.text,1);}
| e=ELEMENT n=NUMBER {int m = Integer.parseInt($n.text); 
    if(m <= 0){fail=true; 
        errors += "You must have a positive number of atoms!\n"; 
    }
    root.handleElement($e.text,m);}
| {TreeExpr t = new TreeExpr(); } '(' expr0[t] ')' ( n=NUMBER )? { root.addMult(t,($n.text != null ? Integer.parseInt($n.text) : 1)); }
;

/* LEXER RULES */

NUMBER : '-'? ('1'..'9') ('0'..'9')*;

ELEMENT : 'A'..'Z' ('a'..'z')* ;
