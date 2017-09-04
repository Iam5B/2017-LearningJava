import java.io.IOException;  
import java.io.ObjectInputStream;  
import java.io.ObjectOutputStream;  
import java.util.Scanner;
import java.util.ArrayList;

public class Map{

	public static char[] charset = new char[] {'#', '@', 'ā','è'};

	private Life[][] lifeMap;

	public Life[][] getLifeMap(){

		return lifeMap;

	}

	public Map(){

		lifeMap = new Life[20][20];

	}

	public void initialize(){

		int p,q;

		for(p = 0; p < 20; p++){

			for(q = 0; q < 20; q++){

				lifeMap[p][q] = null;

			}

		}

		System.out.println("输入物种数目:");

		Scanner input = new Scanner(System.in);

		int kinds;

		kinds = input.nextInt();

		int i;

		for(i = 0; i < kinds; i++){

			System.out.println("请输入第" + i +"种生物的数量:");

			int num;

			num = input.nextInt();

			int j;

			for(j = 0; j < num; j++){

				System.out.println("请输入第" + i + "种生物中的第" + j + "个的位置坐标（从0，0到19，19）");

				int x,y;

				x = input.nextInt();

				y = input.nextInt();

				lifeMap[x][y] = new Life(i);

			}

		}

	}

	public void display(){

		int i,j;

		for(i = 0; i < 20; i++){

			System.out.println("");

		}

		for(i = 0; i < 20; i++){

			for(j = 0; j < 20; j++){

				if(lifeMap[i][j] == null){

					System.out.print(" ");

				}

				else{

					System.out.print(Map.charset[lifeMap[i][j].getKind()]);

				}

			}

			System.out.println("");

		}

	}

	public ArrayList<Life> getLifeAround(int x, int y){

		int i, j;

		ArrayList<Life> a = new ArrayList<Life>();

		for(i = x - 1; i <= x + 1; i++){

			for(j = y - 1; j <= y + 1;j++){

				if(i < 0 || i > 19 || j < 0 || j > 19 || (i == x && j == y)){

					continue;

				}

				else{

					if(lifeMap[i][j] != null){

						a.add(lifeMap[i][j]);

					}

				}

			}

		}

		return a;

	}

	public void reproductionAndWither(){

		ArrayList<Life> lifeToReproduct = new ArrayList<Life>();

		ArrayList<Integer> lifeXToSet = new ArrayList<Integer>();

		ArrayList<Integer> lifeYToSet = new ArrayList<Integer>();

		int lifeAmount[] = new int[100];

		int i;

		int j;

		for(i = 0; i < 20; i++){

			for(j = 0; j < 20; j++){

				if(lifeMap[i][j] != null){

					continue;

				}

				int k;

				for(k = 0; k < 100; k++){

					lifeAmount[k] = 0;

				}

				ArrayList<Life> lifeAround = getLifeAround(i, j);

				for(k = 0; k < lifeAround.size(); k++){

					lifeAmount[lifeAround.get(k).getKind()]++;

				}

				int maxAmount = 0;

				int maxAmountAt = 0;

				for(k = 0; k < 100; k++){

					if(lifeAmount[k] > maxAmount){

						maxAmount = lifeAmount[k];

						maxAmountAt = k;

					}

				}

				if(maxAmount > 0){

					if(maxAmount >= 2){

						lifeToReproduct.add(new Life(maxAmountAt));

						lifeXToSet.add(i);

						lifeYToSet.add(j);

					}

				}

			}

		}

		ArrayList<Integer> lifeXToRemove = new ArrayList<Integer>();

		ArrayList<Integer> lifeYToRemove = new ArrayList<Integer>();

		for(i = 0; i < 20; i++){

			for(j = 0; j < 20; j++){

				if(lifeMap[i][j] != null){

					ArrayList<Life> lifeAround = getLifeAround(i, j);

					int k;

					int cnt = 0;

					for(k = 0; k < lifeAround.size(); k++){

						if(lifeAround.get(k).getKind() == lifeMap[i][j].getKind()){

							cnt++;

						}

					}

					if(cnt == 0 || cnt > 6){

						lifeXToRemove.add(i);

						lifeYToRemove.add(j);

					}

				}

			}

		}

		for(i = 0; i < lifeToReproduct.size(); i++){

			lifeMap[lifeXToSet.get(i)][lifeYToSet.get(i)] = lifeToReproduct.get(i);

		}

		for(i = 0; i < lifeXToRemove.size(); i++){

			lifeMap[lifeXToRemove.get(i)][lifeYToRemove.get(i)] = null;

		}

	}

	public void goSeveralRound(int x){

		int i;

		for(i = 0; i < x; i++){

			reproductionAndWither();

		}

	}

	public static void main(String[] args){

		Map m = new Map();

		m.initialize();

		int round = 1;

		while(round != 0){

			System.out.println("请输入要执行的轮数(0退出):");

			Scanner input = new Scanner(System.in);

			round = input.nextInt();

			m.goSeveralRound(round);

			m.display();

		}

		System.out.println("z");

		return;

	}

}