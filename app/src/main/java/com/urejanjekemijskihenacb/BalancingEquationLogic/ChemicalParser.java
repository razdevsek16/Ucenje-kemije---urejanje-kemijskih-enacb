// $ANTLR 3.3 Nov 30, 2010 12:50:56 Chemical.g 2010-12-27 18:19:07

    package com.urejanjekemijskihenacb.BalancingEquationLogic;
    import java.util.ArrayList;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class ChemicalParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "NUMBER", "ELEMENT", "'^'", "'-'", "'('", "')'"
    };
    public static final int EOF=-1;
    public static final int T__6=6;
    public static final int T__7=7;
    public static final int T__8=8;
    public static final int T__9=9;
    public static final int NUMBER=4;
    public static final int ELEMENT=5;

    // delegates
    // delegators


        public ChemicalParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public ChemicalParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return ChemicalParser.tokenNames; }
    public String getGrammarFileName() { return "Chemical.g"; }



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



    // $ANTLR start "chemexpr"
    // Chemical.g:153:1: chemexpr returns [TreeExpr root] : expr0[root] ( '^' ( '-' | n= NUMBER )? )? EOF ;
    public final TreeExpr chemexpr() throws RecognitionException {
        TreeExpr root = null;

        Token n=null;

        try {
            // Chemical.g:154:1: ( expr0[root] ( '^' ( '-' | n= NUMBER )? )? EOF )
            // Chemical.g:154:3: expr0[root] ( '^' ( '-' | n= NUMBER )? )? EOF
            {
            root = new TreeExpr(); 
            pushFollow(FOLLOW_expr0_in_chemexpr49);
            expr0(root);

            state._fsp--;

            // Chemical.g:154:41: ( '^' ( '-' | n= NUMBER )? )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==6) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // Chemical.g:154:43: '^' ( '-' | n= NUMBER )?
                    {
                    match(input,6,FOLLOW_6_in_chemexpr54); 
                    int m=1;
                    // Chemical.g:154:58: ( '-' | n= NUMBER )?
                    int alt1=3;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==7) ) {
                        alt1=1;
                    }
                    else if ( (LA1_0==NUMBER) ) {
                        alt1=2;
                    }
                    switch (alt1) {
                        case 1 :
                            // Chemical.g:154:60: '-'
                            {
                            match(input,7,FOLLOW_7_in_chemexpr60); 
                            m=-1;

                            }
                            break;
                        case 2 :
                            // Chemical.g:154:74: n= NUMBER
                            {
                            n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_chemexpr68); 
                            m = Integer.parseInt((n!=null?n.getText():null));

                            }
                            break;

                    }

                    root.handleElement("^",m);

                    }
                    break;

            }

            match(input,EOF,FOLLOW_EOF_in_chemexpr79); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return root;
    }
    // $ANTLR end "chemexpr"


    // $ANTLR start "expr0"
    // Chemical.g:157:1: expr0[TreeExpr root] : ( expr1[root] )+ ;
    public final void expr0(TreeExpr root) throws RecognitionException {
        try {
            // Chemical.g:158:1: ( ( expr1[root] )+ )
            // Chemical.g:158:3: ( expr1[root] )+
            {
            // Chemical.g:158:3: ( expr1[root] )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==ELEMENT||LA3_0==8) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // Chemical.g:158:5: expr1[root]
            	    {
            	    pushFollow(FOLLOW_expr1_in_expr092);
            	    expr1(root);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "expr0"


    // $ANTLR start "expr1"
    // Chemical.g:161:1: expr1[TreeExpr root] : (e= ELEMENT | e= ELEMENT n= NUMBER | '(' expr0[t] ')' (n= NUMBER )? );
    public final void expr1(TreeExpr root) throws RecognitionException {
        Token e=null;
        Token n=null;

        try {
            // Chemical.g:162:1: (e= ELEMENT | e= ELEMENT n= NUMBER | '(' expr0[t] ')' (n= NUMBER )? )
            int alt5=3;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==ELEMENT) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==NUMBER) ) {
                    alt5=2;
                }
                else if ( (LA5_1==EOF||(LA5_1>=ELEMENT && LA5_1<=6)||(LA5_1>=8 && LA5_1<=9)) ) {
                    alt5=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA5_0==8) ) {
                alt5=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // Chemical.g:162:3: e= ELEMENT
                    {
                    e=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_expr1110); 
                     root.handleElement((e!=null?e.getText():null),1);

                    }
                    break;
                case 2 :
                    // Chemical.g:163:3: e= ELEMENT n= NUMBER
                    {
                    e=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_expr1118); 
                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_expr1122); 
                    int m = Integer.parseInt((n!=null?n.getText():null)); 
                        if(m <= 0){fail=true; 
                            errors += "You must have a positive number of atoms!\n"; 
                        }
                        root.handleElement((e!=null?e.getText():null),m);

                    }
                    break;
                case 3 :
                    // Chemical.g:168:3: '(' expr0[t] ')' (n= NUMBER )?
                    {
                    TreeExpr t = new TreeExpr(); 
                    match(input,8,FOLLOW_8_in_expr1130); 
                    pushFollow(FOLLOW_expr0_in_expr1132);
                    expr0(t);

                    state._fsp--;

                    match(input,9,FOLLOW_9_in_expr1135); 
                    // Chemical.g:168:52: (n= NUMBER )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==NUMBER) ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // Chemical.g:168:54: n= NUMBER
                            {
                            n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_expr1141); 

                            }
                            break;

                    }

                     root.addMult(t,((n!=null?n.getText():null) != null ? Integer.parseInt((n!=null?n.getText():null)) : 1)); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "expr1"

    // Delegated rules


 

    public static final BitSet FOLLOW_expr0_in_chemexpr49 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_6_in_chemexpr54 = new BitSet(new long[]{0x0000000000000090L});
    public static final BitSet FOLLOW_7_in_chemexpr60 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_NUMBER_in_chemexpr68 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_chemexpr79 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr1_in_expr092 = new BitSet(new long[]{0x0000000000000122L});
    public static final BitSet FOLLOW_ELEMENT_in_expr1110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELEMENT_in_expr1118 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NUMBER_in_expr1122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_8_in_expr1130 = new BitSet(new long[]{0x0000000000000120L});
    public static final BitSet FOLLOW_expr0_in_expr1132 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_expr1135 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_NUMBER_in_expr1141 = new BitSet(new long[]{0x0000000000000002L});

}