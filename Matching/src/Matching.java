import java.io.*;
import java.util.LinkedList;

public class Matching
{
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true)
		{
			try
			{
				String input = br.readLine();
				if (input.compareTo("QUIT") == 0)
					break;

				command(input);
			}
			catch (IOException e)
			{
				System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
			}
		}
	}

	private static void command(String input)
	{
	    HashTable hashTable = new HashTable();

	}

	private static void read(HashTable hashTable, String filename) {

	}

	private static void print(HashTable hashTable, int index) {

	}

	private static void find(HashTable hashTable, String pattern) {

	}
}
