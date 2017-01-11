

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


public class MatrixOps
{

    public MatrixOps()
    {
    }

    public static int[][] solveDependentSystem(double mat[][])
    {
        int rows = mat.length;
        int cols = mat[0].length;
        int size = Math.max(rows, cols);
        double coefMatrix[][] = new double[size][cols];
        for(int i = 0; i < mat.length; i++)
            for(int j = 0; j < mat[0].length; j++)
                coefMatrix[i][j] = mat[i][j];


        boolean isZeroRow = true;
        int rank = 0;
        int extra = 0;
        for(int i = 0; i < rows && i + extra < cols; i++)
        {
            for(coefMatrix = findSwapMatrixRow(coefMatrix, i, extra); 
            i + extra < cols && coefMatrix[i][i + extra] == 0.0D; 
            coefMatrix = findSwapMatrixRow(coefMatrix, i, extra))
                extra++;

            if(i + extra == cols)
                break;
            if(i + 1 >= rows)
                continue;
            for(int j = i + 1; j < rows; j++)
                multiplyMatrixRow(coefMatrix, i, (-1D * coefMatrix[j][i + extra]) / coefMatrix[i][i + extra], j);

        }

        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < cols; j++)
                if(Math.abs(coefMatrix[i][j]) < Math.pow(10D, -4D))
                    coefMatrix[i][j] = 0.0D;

        }
        
        int count = 0;
        
        
        boolean virgin = false;
        while(!virgin) 
        {
            virgin = true;
            for(int i = 0;i<size;i++)
            {
                for(count = 0; count < cols && coefMatrix[i][count] == 0.0D; count++);
                if(i != count && count<cols)        //count is the first non-zero entry of coefMatrix[i]
                {
                    coefMatrix = swapMatrixRow(coefMatrix, i, count);
                    virgin = false;
//                    System.out.println("Swapping!");
                }
            }
        }
        
        
        
        rank = size;
        for(int i = 0; i < cols; i++)
            if(coefMatrix[i][i] == 0.0D)
                rank--;

        rank -= size - cols;
        boolean oneSoln = false;
        if(rank == cols)
        {
            oneSoln = true;
            cols++;
        }
        double coeffMat[][] = new double[cols][cols];
        double matB[] = new double[cols];
        double solns[][] = new double[cols][cols - rank];
        int finals[][] = new int[cols][cols - rank];
        int intTemp[] = new int[cols];
        double temp[] = new double[cols];
        double det = 1.0D;
        if(oneSoln)
            return finals;
        for(int i = 0; i < size && i < cols; i++)
        {
            for(int j = 0; j < cols; j++)
                coeffMat[i][j] = coefMatrix[i][j];

        }

        boolean isZero[] = new boolean[cols];
        for(int i = 0; i < cols; i++)
            if(coeffMat[i][i] == 0.0)
            {
                coeffMat[i][i] = 1.0;
                isZero[i] = true;
            }

        count = -1;
        for(int i = 0; i < cols; i++)
        {
            if(!isZero[i])
                continue;
            count++;
            matB[i] = 1.0;
            for(int j = cols - 1; j >= 0; j--)
            {
                solns[j][count] = matB[j];
                for(int k = cols - 1; k > j; k--)
                    solns[j][count] -= coeffMat[j][k] * solns[k][count];

                solns[j][count] /= coeffMat[j][j];
            }

            det = 1.0;
            for(int j = 0; j < cols; j++)
            {
                temp[j] = solns[j][count];
                det *= coeffMat[j][j];
            }

            intTemp = makeWhole(temp, det);
            for(int j = 0; j < cols; j++)
                finals[j][count] = intTemp[j];

            matB[i] = 0.0;
        }

        return finals;
    }

    public int[][] solveIneqSys(double Mat[][])
    {
        int eqnCount = Mat.length;
        int dimCount = Mat[0].length;
        double nonZeroU[] = new double[dimCount];
        double tempU1[][] = new double[eqnCount][dimCount];
        double tempU2[][] = new double[eqnCount][dimCount];
        double tempV1[][] = new double[eqnCount][dimCount];
        double tempV2[][] = new double[eqnCount][dimCount];
        int answ[][] = new int[0][0];
        int sizeU1 = dimCount;
        int sizeU2 = 0;
        int sizeV1 = 0;
        int sizeV2 = 0;
        for(int i = 0; i < sizeU1; i++)
            tempU1[i][i] = 1.0D;

        for(int i = 0; i < Mat.length; i++)
        {
            int uExists = -1;
            int j = 0;
            do
            {
                if(j >= sizeU1)
                    break;
                if(vectorMultiply(getRow(Mat, i), getRow(tempU1, j)) != 0.0D)
                {
                    nonZeroU = getRow(tempU1, j);
                    uExists = j;
                    break;
                }
                j++;
            } while(true);
            if(uExists <= -1)
                continue;
            for(j = 0; j < tempU1.length; j++)
            {
                if(j < uExists)
                {
                    for(int k = 0; k < dimCount; k++)
                        tempU2[j][k] = vectorMultiply(getRow(Mat, i), nonZeroU) * tempU1[j][k] - vectorMultiply(getRow(Mat, i), getRow(tempU1, j)) * nonZeroU[k];

                }
                if(j <= uExists)
                    continue;
                for(int k = 0; k < dimCount; k++)
                    tempU2[j - 1][k] = vectorMultiply(getRow(Mat, i), nonZeroU) * tempU1[j][k] - vectorMultiply(getRow(Mat, i), getRow(tempU1, j)) * nonZeroU[k];

            }

            sizeU2 = sizeU1;
            if(vectorMultiply(getRow(Mat, i), nonZeroU) > 0.0D)
                for(j = 0; j < nonZeroU.length; j++)
                    nonZeroU[j] *= -1D;

            for(j = 0; j < nonZeroU.length; j++)
                tempV2[0][j] = nonZeroU[j];

            for(j = 0; j < tempV1.length; j++)
            {
                for(int k = 0; k < dimCount; k++)
                    tempV2[j + 1][k] = -1D * vectorMultiply(getRow(Mat, i), nonZeroU) * tempV1[j][k] + vectorMultiply(getRow(Mat, i), getRow(tempV1, j)) * nonZeroU[k];

            }

            sizeV2 = sizeV1 + 1;
        }

        return answ;
    }

    private double[] getRow(double Mat[][], int index)
    {
        double answ[] = new double[Mat[0].length];
        for(int i = 0; i < index; i++)
            answ[i] = Mat[index][i];

        return answ;
    }

    private double vectorMultiply(double v1[], double v2[])
    {
        int sum = 0;
        for(int i = 0; i < v1.length; i++)
            sum = (int)((double)sum + v1[i] * v2[i]);

        return (double)sum;
    }

    public static String printMatrix(double Mat[][])
    {
        String s = "";
        for(int i = 0; i < Mat.length; i++)
        {
            for(int j = 0; j < Mat[i].length; j++)
                s = s + Mat[i][j] + "    ";

            s = s + "\n";
        }

        return s;
    }

    public static String printMatrix(int Mat[][])
    {
        String s = "";
        for(int i = 0; i < Mat.length; i++)
        {
            for(int j = 0; j < Mat[i].length; j++)
                s = s + Mat[i][j] + "    ";

            s = s + "\n";
        }

        return s;
    }

    public static String printMatrix(double Mat[])
    {
        String s = "";
        for(int i = 0; i < Mat.length; i++)
            s = s + Mat[i] + "    ";

        return s + "\n";
    }

    public static String printMatrix(int Mat[])
    {
        String s = "";
        for(int i = 0; i < Mat.length; i++)
            s = s + Mat[i] + "    ";

        return s + "\n";
    }

    public static double[][] findSwapMatrixRow(double Mat[][], int Row)
    {
        try{
            int i;
            for(i = Row; Mat[i][Row] == 0.0 && i<Mat.length; i++);
            if(i != Row)
                Mat = swapMatrixRow(Mat, Row, i);
            return Mat;
        }catch(ArrayIndexOutOfBoundsException e){
            return Mat;
        }
        
    }

    public static double[][] findSwapMatrixRow(double Mat[][], int Row, int extra)
    {
        try{
            int i;
            for(i = Row; Mat[i][Row + extra] == 0.0 && i<Mat.length; i++);
            if(i != Row)
                Mat = swapMatrixRow(Mat, Row, i);
            return Mat;
        }catch(ArrayIndexOutOfBoundsException e){
            return Mat;
        }
        
    }

    public static double[][] swapMatrixRow(double Mat[][], int Row1, int Row2)
    {
        double temp[] = new double[Mat[0].length];
        for(int i = 0; i < Mat[0].length; i++)
            temp[i] = Mat[Row1][i];

        for(int i = 0; i < Mat[0].length; i++)
            Mat[Row1][i] = Mat[Row2][i];

        for(int i = 0; i < Mat[0].length; i++)
            Mat[Row2][i] = temp[i];

        return Mat;
    }

    public static double[] scalarMultiply(double Mat[], double s)
    {
        for(int i = 0; i < Mat.length; i++)
            Mat[i] = Mat[i] * s;

        return Mat;
    }

    public static double det(double Mat[][])
    {
        int size = Math.min(Mat.length, Mat[0].length);
        if(size == 1)
            return Mat[0][0];
        if(size == 2)
            return Mat[0][0] * Mat[1][1] - Mat[0][1] * Mat[1][0];
        double newMat[][] = new double[size - 1][size - 1];
        double answ = 0.0D;
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                if(j == i)
                    continue;
                for(int k = 1; k < size; k++)
                {
                    if(j < i)
                        newMat[k - 1][j] = Mat[k][j];
                    if(j > i)
                        newMat[k - 1][j - 1] = Mat[k][j];
                }

            }

            if(i % 2 == 0)
                answ += det(newMat);
            if(i % 2 == 1)
                answ -= det(newMat);
        }

        return answ;
    }

    public static void multiplyMatrixRow(double Mat[][], int Row1, double Factor, int Row2)
    {
        for(int i = 0; i < Mat[Row1].length; i++)
            Mat[Row2][i] += Mat[Row1][i] * Factor;

    }

    public static int[] makeWhole(double Mat[], double det)
    {
        for(int i = 0; i < Mat.length; i++)
            Mat[i] = Mat[i] * det;

        int x[] = new int[Mat.length];
        x = toInt(Mat);
        int gcf = 0;
        for(int i = 0; i < Mat.length && gcf == 0; i++)
            gcf = x[i];

        if(gcf == 0)
            return x;
        for(int i = 1; i < Mat.length; i++)
            if(x[i] != 0)
                gcf = gcf(gcf, x[i]);

        for(int i = 0; i < Mat.length; i++)
            x[i] = x[i] / gcf;

        return x;
    }

    private static int gcf(int a, int b)
    {
        a = Math.abs(a);
        b = Math.abs(b);
        int temp = 0;
        if(b > a)
        {
            temp = b;
            b = a;
            a = temp;
        }
        int r = a % b;
        if(r == 0)
            return b;
        else
            return gcf(b, r);
    }

    private static int[] toInt(double Mat[])
    {
        int x[] = new int[Mat.length];
        for(int i = 0; i < Mat.length; i++)
            x[i] = (int)Math.rint(Mat[i]);

        return x;
    }
}
