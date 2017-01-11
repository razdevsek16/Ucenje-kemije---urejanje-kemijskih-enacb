

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

public class chSet
{

    public chSet(int start, int stop, int multiplier)
    {
        itsStart = start;
        itsStop = stop;
        itsMultiplier = multiplier;
    }

    public chSet(int start)
    {
        itsStart = start;
        itsMultiplier = 1;
        itsStop = 0;
    }

    public chSet()
    {
        itsStart = 0;
        itsStop = 0;
        itsMultiplier = 1;
    }

    public int getStart()
    {
        return itsStart;
    }

    public int getStop()
    {
        return itsStop;
    }

    public int getMultiplier()
    {
        return itsMultiplier;
    }

    public void setStart(int s)
    {
        itsStart = s;
    }

    public void setStop(int s)
    {
        itsStop = s;
    }

    public void setMultiplier(int m)
    {
        itsMultiplier = m;
    }

    public String toString()
    {
        return "From: " + itsStart + " To: " + itsStop + " Mult: " + itsMultiplier;
    }

    private int itsStart;
    private int itsStop;
    private int itsMultiplier;
}
