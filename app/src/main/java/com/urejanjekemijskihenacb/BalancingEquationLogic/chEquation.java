

/****************************************************************************
 *     chembal: A Chemical Equation Balancer                                *
 *     Copyright (C) 2005-2010  Berkeley Churchill                          *
 *                                                                          *
 *    This program is free software: you can redistribute it and/or modify  *
 *    it under the terms of the GNU General Public License as published by  *
 *    the Free Software Foundation, either version 3 of the License, or     *
 *    (at your option) any later version.                                   *
 *                                                                          *
 *    This program is distributed in the hope that it will be useful,       *
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of        *
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the         *
 *    GNU General Public License for more details.                          *
 *                                                                          *
 *    You should have received a copy of the GNU General Public License     *
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>. *
 ****************************************************************************/

package com.urejanjekemijskihenacb.BalancingEquationLogic;

import java.util.LinkedList;

public class chEquation
{
    private String itsSymbol;
    private LinkedList<chChemical> itsChemicals;
    private LinkedList<chElement> itsElements;

    public chEquation(String equ)
        throws InvalidUserInputException
    {
        itsChemicals = new LinkedList();
        itsElements = new LinkedList();

        int dividerCount=0;
        String[] pieces = equ.split("([+:=]|-\\>|--\\>)");
        /* the above regex should match exactly the following:
         * "+"
         * ":"
         * "="
         * "->"
         * "-->"
         */
        for(String piece : pieces)
        {
            piece = piece.trim();
            if(piece.length() == 0)
                continue;

            itsChemicals.add(new chChemical(piece));

        }

        for(chChemical C : itsChemicals)
        {
            for(int j = 0; j < C.itemCount(); j++)
                addToElements(C.getItem(j));
        }

    }

    public int[][] Balance()
        throws InvalidUserInputException
    {
        for(int i = 0; i < itsChemicals.size(); i++)
        {
            for(int j = i + 1; j < itsChemicals.size(); j++)
                if(itsChemicals.get(i).equals(itsChemicals.get(j)))
                    itsChemicals.remove(j);

        }

        Object temp[] = itsChemicals.toArray();
        chChemical Chemicals[] = new chChemical[temp.length];
        for(int i = 0; i < temp.length; i++)
            Chemicals[i] = (chChemical)temp[i];

        double coeffMatrix[][] = new double[itsElements.size()][Chemicals.length];
        for(int i = 0; i < Chemicals.length; i++)
        {
            for(int j = 0; j < Chemicals[i].itemCount(); j++)
            {
                for(int k = 0; k < itsElements.size(); k++)
                    if(Chemicals[i].getItem(j).equals(itsElements.get(k)))
                        coeffMatrix[k][i] += Chemicals[i].getItem(j).getCount();

            }

        }

        int answ[][];
        try
        {
            answ = MatrixOps.solveDependentSystem(coeffMatrix);
        }
        catch(Exception e)
        {
            throw new InvalidUserInputException("Error: Unknown error occurred while solving the system.");
        }
        boolean itsBad = false;
        if(answ[0].length == 1)
        {
            itsBad = true;
            for(int i = 0; i < answ.length; i++)
                if(answ[i][0] != 0)
                    itsBad = false;

        }
        if(itsBad)
            throw new InvalidUserInputException("Error: This equation doesn't have any solutions.");
        int temp2 = 0;
        for(int i = 0; i < answ[0].length; i++)
        {
            for(int j = 0; j < answ.length && answ[j][i] == 0; j++)
                temp2++;

            if(answ[temp2][i] >= 0)
                continue;
            for(int k = 0; k < answ.length; k++)
                answ[k][i] *= -1;

        }

        return answ;
    }

    private void addToElements(chElement anotherElem)
    {
        for(int i = 0; i < itsElements.size(); i++)
            if(anotherElem.equals(itsElements.get(i)))
            {
                chElement z = (chElement)itsElements.get(i);
                z.addCount(anotherElem.getCount());
                return;
            }

        itsElements.add(anotherElem);
    }

    public chChemical getItem(int index)
    {
        chChemical z = (chChemical)itsChemicals.get(index);
        return z.getCopy();
    }

    public String output()
    {
        String s = itsSymbol;
        for(int i = 0; i < itsChemicals.size(); i++)
            s = s + "\n " + itsChemicals.get(i).toString();

        return s;
    }

    public int size()
    {
        return itsChemicals.size();
    }

    public String toString()
    {
        String s = "";
        for(int i = 0; i < itsChemicals.size(); i++)
        {
            if(!((chChemical)itsChemicals.get(i)).isReactant())
                continue;
            if(((chChemical)itsChemicals.get(i)).getCount() != 1)
                s = s + ((chChemical)itsChemicals.get(i)).getCount() + ((chChemical)itsChemicals.get(i)).getSymbol() + " + ";
            else
                s = s + ((chChemical)itsChemicals.get(i)).getSymbol() + " + ";
        }

        s = s.substring(0, s.length() - 3);
        s = s + " \u2192 ";
        for(int i = 0; i < itsChemicals.size(); i++)
        {
            if(((chChemical)itsChemicals.get(i)).isReactant())
                continue;
            if(((chChemical)itsChemicals.get(i)).getCount() != 1)
                s = s + ((chChemical)itsChemicals.get(i)).getCount() + ((chChemical)itsChemicals.get(i)).getSymbol() + " + ";
            else
                s = s + ((chChemical)itsChemicals.get(i)).getSymbol() + " + ";
        }

        s = s.substring(0, s.length() - 3);
        return s;
    }

}
