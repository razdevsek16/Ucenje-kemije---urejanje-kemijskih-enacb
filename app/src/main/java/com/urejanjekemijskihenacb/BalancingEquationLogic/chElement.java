

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

public class chElement
{

    public chElement(String sym)
    {
        itsSymbol = "";
        itsCount = 0;
        itsSymbol = sym;
        itsCount = 0;
    }

    public chElement(String sym, int Count)
    {
        itsSymbol = "";
        itsCount = 0;
        itsSymbol = sym;
        itsCount = Count;
    }

    public int addCount()
    {
        return itsCount++;
    }

    public int addCount(int n)
    {
        return itsCount += n;
    }

    public void setCount(int n)
    {
        itsCount = n;
    }

    public int getCount()
    {
        return itsCount;
    }

    public String getSymbol()
    {
        return itsSymbol;
    }

    public chElement getCopy()
    {
        return new chElement(itsSymbol, itsCount);
    }

    public String toString()
    {
        return itsSymbol + " " + itsCount;
    }

    public boolean equals(Object o)
    {
        chElement ob2 = (chElement)o;
        return ob2.getSymbol().equals(getSymbol());
    }

    private String itsSymbol;
    private int itsCount;
}
