

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

public class chChemical
{

    private LinkedList itsElements;
    private int itsCount;
    private String itsSymbol;
    private boolean isReactant;


    private void debug(String s)
    {
        //System.out.println("chChemical.java:34, DEBUG: " + s);
    }

    public chChemical(String equ)
        throws InvalidUserInputException
        {
            itsElements = new LinkedList();
            itsCount = 0;
            isReactant = false;
            itsSymbol = equ;


            ChemicalParser.ElementCallback eb = new ChemicalParser.ElementCallback()
            {
                public void handleElement(String s, int num)
                {
                    addElement(s,num);
                }
            };

            try{
                ChemicalParser.parseChemical(equ, eb);
            }catch(InvalidUserInputException e)
            {
                String s = e.getMessage();
                throw new InvalidUserInputException("Error in species \'" + equ + "\' :" +  s);
            }

        }

    private void addElement(String s, int c)
    {
        for(int i = 0; i < itsElements.size(); i++)
        {
            chElement z = (chElement)itsElements.get(i);
            if(z.getSymbol().equals(s))
            {
                chElement y = (chElement)itsElements.get(i);
                y.addCount(c);
                return;
            }
        }

        itsElements.add(new chElement(s, c));
    }


    public chElement getItem(int index)
    {
        chElement z = (chElement)itsElements.get(index);
        return z.getCopy();
    }

    public chChemical getCopy()
    {
        chChemical newOne = null;
        try
        {
            newOne = new chChemical(itsSymbol);
            newOne.setCount(itsCount);
        }
        catch(Exception e) { }
        return newOne;
    }

    public String getSymbol()
    {
        return itsSymbol;
    }

    public String getHTMLSymbol()
    {
        boolean inCharge = false;
        boolean positive = false;
        String out = "";
        for(int i = 0; i < itsSymbol.length(); i++)
        {
            if(!inCharge)
            {
                if(Character.isDigit(itsSymbol.charAt(i)))
                {
                    out = out + "<sub>" + itsSymbol.substring(i, i + 1) + "</sub>";
                    continue;
                }
                if(itsSymbol.charAt(i) == '^')
                {
                    inCharge = true;
                    positive = true;
                    out = out + "<sup>";
                } else
                {
                    out = out + itsSymbol.substring(i, i + 1);
                }
                continue;
            }
            if(Character.isDigit(itsSymbol.charAt(i)))
            {
                out = out + itsSymbol.substring(i, i + 1);
                continue;
            }
            if(itsSymbol.charAt(i) == '-')
            {
                positive = false;
                continue;
            }
            if(itsSymbol.charAt(i) == '+')
            {
                positive = true;
                continue;
            }
            if(positive)
                out = out + "+";
            else
                out = out + "-";
            out = out + "</sup>" + itsSymbol.substring(i, i + 1);
            inCharge = false;
        }

        if(inCharge)
        {
            if(positive)
                out = out + "+";
            else
                out = out + "-";
            out = out + "</sup>";
        }
        return out;
    }

    public void setCount(int n)
    {
        itsCount = n;
    }

    public int getCount()
    {
        return itsCount;
    }

    public int itemCount()
    {
        return itsElements.size();
    }

    public String toString()
    {
        String s = itsSymbol + " " + itsCount + "      ";
        for(int i = 0; i < itsElements.size(); i++)
        {
            s = s + itsElements.get(i).toString();
            for(int j = 0; j < 7 - itsElements.get(i).toString().length(); j++)
                s = s + " ";

        }

        return s;
    }

    public boolean isReactant()
    {
        return isReactant;
    }

    public void setIsReactant(boolean b)
    {
        isReactant = b;
    }

    public boolean equals(Object ob)
    {
        boolean found = false;
        chChemical ob2 = (chChemical)ob;
        if(ob2.itemCount() != itemCount())
            return false;
        for(int i = 0; i < itemCount(); i++)
        {
            found = false;
            for(int j = 0; j < itemCount(); j++)
                if(getItem(i).getSymbol().equals(ob2.getItem(j).getSymbol()) && getItem(i).getCount() == ob2.getItem(j).getCount())
                    found = true;

            if(!found)
                return false;
        }

        return true;
    }

    public static void main(String[] argv) throws InvalidUserInputException
    {
        new chChemical(argv[0]);
    }
}
