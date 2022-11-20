package xixuegui;

import java.util.Arrays;

public class xixuegui{
	
	public static void main(String[] args) {
		for (int i=10;i<100;i++){
            for (int j=i+1;j<100;j++){
                int solution=i*j;
                if (solution<1000||solution>9999){
                    continue;
                }
                int[] solutionNum = { solution / 1000, solution / 100 % 10, solution / 10 % 100 % 10, solution%10 };
                int[] startNum = { i % 10, i / 10, j % 10, j / 10 };
                Arrays.sort(solutionNum);
                Arrays.sort(startNum);
                if (Arrays.equals(solutionNum,startNum)){
                    System.out.println(solution + " = " + i + " * " + j);
                }
            }
        }

	}
} 