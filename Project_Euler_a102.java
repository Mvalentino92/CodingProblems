/*Problem 102:
Three distinct points are plotted at random on a Cartesian plane, for which -1000 ≤ x, y ≤ 1000, such that a triangle is formed.

Consider the following two triangles:

A(-340,495), B(-153,-910), C(835,-947)

X(-175,41), Y(-421,-714), Z(574,-645)

It can be verified that triangle ABC contains the origin, whereas triangle XYZ does not.

Using triangles.txt (right click and 'Save Link/Target As...'), a 27K text file containing the co-ordinates of one thousand "random" triangles, find the number of triangles for which the interior contains the origin.

NOTE: The first two examples in the file represent the triangles in the example given above.*/

//**********************************************************************************************************************************************************************
/*Im going to exploit the fact that if the origin is contained within the triangle, then the sum of the three triangles formed by connecting the origin to the other points will equal the area of the original triangle*/
//**********************************************************************************************************************************************************************
import java.util.Scanner;

public class Project_Euler_a102 
{
	public static double sideLength(double x1, double y1, double x2, double y2)
	{
		double xComponent = (x2-x1)*(x2-x1);
		double yComponent = (y2-y1)*(y2-y1);
		double side = Math.sqrt(xComponent + yComponent);
		return side;
	}

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		input.useDelimiter("[^1234567890-]");
		int total = 0;
		double originX = 0;
		double originY = 0;
		double sideArith;
		double sideA,sideB,sideC;
		double angleA,angleB,angleC;
		double originOne,originTwo,originThree;
		double originalArea,areaOne,areaTwo,areaThree,originCheckArea;
		while(input.hasNextDouble())
		{
			double pointOneX = input.nextDouble();
			double pointOneY = input.nextDouble();
			double pointTwoX = input.nextDouble();
			double pointTwoY = input.nextDouble();
			double pointThreeX = input.nextDouble();
			double pointThreeY = input.nextDouble();
			
			//Side A = One to Two
			//Side B = One to Three
			//SIde C = Two to Three
			
			sideA = sideLength(pointOneX,pointOneY,pointTwoX,pointTwoY);
			sideB = sideLength(pointOneX,pointOneY,pointThreeX,pointThreeY);
			sideC = sideLength(pointTwoX,pointTwoY,pointThreeX,pointThreeY);
			
			//Get angle C, and using it to get the area of the triangle
			sideArith = (sideA*sideA + sideB*sideB - sideC*sideC)/(2*sideA*sideB);
			angleC = Math.acos(sideArith);
			originalArea = ((0.5)*(sideA*sideB))*Math.sin(angleC);

			//Getting the first derived triangle
			originOne = sideLength(originX,originY,pointOneX,pointOneY);
			originTwo = sideLength(originX,originY,pointTwoX,pointTwoY);
			
			//Get angle A of new triangle, as well as its area
			sideArith = (originOne*originOne + originTwo*originTwo - sideA*sideA)/(2*originOne*originTwo);
			angleA = Math.acos(sideArith);
			areaOne = ((0.5)*(originOne*originTwo))*Math.sin(angleA);

			//Getting second derived triangle, along with its area
			originThree = sideLength(originX,originY,pointThreeX,pointThreeY);
			sideArith = (originOne*originOne + originThree*originThree - sideB*sideB)/(2*originOne*originThree);
			angleB = Math.acos(sideArith);
			areaTwo = ((0.5)*(originOne*originThree))*Math.sin(angleB);

			//Getting third derived triangle, along with its area
			sideArith = (originTwo*originTwo + originThree*originThree - sideC*sideC)/(2*originTwo*originThree);
			angleC = Math.acos(sideArith);
			areaThree = ((0.5)*(originTwo*originThree))*Math.sin(angleC);
			
			//Totaling the area of the three triangles
			originCheckArea = areaOne + areaTwo + areaThree;

			//If the area of the original triangle, and the combined area of the three derived triangles are the same, then the origin lies within the orignal triangle
			if(Math.abs(originCheckArea - originalArea) < 0.25)
			{
				total += 1;
			}
		}
		System.out.println(total);
	}
}



