// $ANTLR 3.3 Nov 30, 2010 12:50:56 Chemical.g 2010-12-27 18:19:07

    package com.urejanjekemijskihenacb.BalancingEquationLogic;


import org.antlr.runtime.*;
import org.antlr.runtime.Lexer;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class ChemicalLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__6=6;
    public static final int T__7=7;
    public static final int T__8=8;
    public static final int T__9=9;
    public static final int NUMBER=4;
    public static final int ELEMENT=5;


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



    // delegates
    // delegators

    public ChemicalLexer() {;} 
    public ChemicalLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public ChemicalLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "Chemical.g"; }

    // $ANTLR start "T__6"
    public final void mT__6() throws RecognitionException {
        try {
            int _type = T__6;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Chemical.g:33:6: ( '^' )
            // Chemical.g:33:8: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__6"

    // $ANTLR start "T__7"
    public final void mT__7() throws RecognitionException {
        try {
            int _type = T__7;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Chemical.g:34:6: ( '-' )
            // Chemical.g:34:8: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__7"

    // $ANTLR start "T__8"
    public final void mT__8() throws RecognitionException {
        try {
            int _type = T__8;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Chemical.g:35:6: ( '(' )
            // Chemical.g:35:8: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__8"

    // $ANTLR start "T__9"
    public final void mT__9() throws RecognitionException {
        try {
            int _type = T__9;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Chemical.g:36:6: ( ')' )
            // Chemical.g:36:8: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__9"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Chemical.g:173:8: ( ( '-' )? ( '1' .. '9' ) ( '0' .. '9' )* )
            // Chemical.g:173:10: ( '-' )? ( '1' .. '9' ) ( '0' .. '9' )*
            {
            // Chemical.g:173:10: ( '-' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='-') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // Chemical.g:173:10: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // Chemical.g:173:15: ( '1' .. '9' )
            // Chemical.g:173:16: '1' .. '9'
            {
            matchRange('1','9'); 

            }

            // Chemical.g:173:26: ( '0' .. '9' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // Chemical.g:173:27: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "ELEMENT"
    public final void mELEMENT() throws RecognitionException {
        try {
            int _type = ELEMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Chemical.g:175:9: ( 'A' .. 'Z' ( 'a' .. 'z' )* )
            // Chemical.g:175:11: 'A' .. 'Z' ( 'a' .. 'z' )*
            {
            matchRange('A','Z'); 
            // Chemical.g:175:20: ( 'a' .. 'z' )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='a' && LA3_0<='z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // Chemical.g:175:21: 'a' .. 'z'
            	    {
            	    matchRange('a','z'); 

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ELEMENT"

    public void mTokens() throws RecognitionException {
        // Chemical.g:1:8: ( T__6 | T__7 | T__8 | T__9 | NUMBER | ELEMENT )
        int alt4=6;
        switch ( input.LA(1) ) {
        case '^':
            {
            alt4=1;
            }
            break;
        case '-':
            {
            int LA4_2 = input.LA(2);

            if ( ((LA4_2>='1' && LA4_2<='9')) ) {
                alt4=5;
            }
            else {
                alt4=2;}
            }
            break;
        case '(':
            {
            alt4=3;
            }
            break;
        case ')':
            {
            alt4=4;
            }
            break;
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
            {
            alt4=5;
            }
            break;
        case 'A':
        case 'B':
        case 'C':
        case 'D':
        case 'E':
        case 'F':
        case 'G':
        case 'H':
        case 'I':
        case 'J':
        case 'K':
        case 'L':
        case 'M':
        case 'N':
        case 'O':
        case 'P':
        case 'Q':
        case 'R':
        case 'S':
        case 'T':
        case 'U':
        case 'V':
        case 'W':
        case 'X':
        case 'Y':
        case 'Z':
            {
            alt4=6;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("", 4, 0, input);

            throw nvae;
        }

        switch (alt4) {
            case 1 :
                // Chemical.g:1:10: T__6
                {
                mT__6(); 

                }
                break;
            case 2 :
                // Chemical.g:1:15: T__7
                {
                mT__7(); 

                }
                break;
            case 3 :
                // Chemical.g:1:20: T__8
                {
                mT__8(); 

                }
                break;
            case 4 :
                // Chemical.g:1:25: T__9
                {
                mT__9(); 

                }
                break;
            case 5 :
                // Chemical.g:1:30: NUMBER
                {
                mNUMBER(); 

                }
                break;
            case 6 :
                // Chemical.g:1:37: ELEMENT
                {
                mELEMENT(); 

                }
                break;

        }

    }


 

}