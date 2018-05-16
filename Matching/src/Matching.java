import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Matching
{

	public static HashTable hashTable;

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
		try {
			char op = input.charAt(0);
			switch (op) {
				case '<':
					read(input.substring(2));
					break;
				case '@':
					print(Integer.parseInt(input.substring(2)));
					break;
				case '?':
					find(input.substring(2));
					break;
				default:
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void read(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		hashTable = new HashTable();
		int lineNumber = 1;
		String line;
		while ((line = br.readLine()) != null) {
			int start = 0;
			for (int end = 6; end <= line.length(); start++, end++) {
				hashTable.insert(line.substring(start, end), new Location(lineNumber, start+1));
			}
			lineNumber++;
		}
	}

	private static void print(int index) {
		hashTable.print(index);
	}

	private static void find(String pattern) {
		int nGroup = pattern.length() / 6;
		int remainder = pattern.length() % 6;

		LinkedList<Location> ret = hashTable.find(pattern.substring(0, 6));

		for (int i = 1; i <= nGroup; i++) {
			LinkedList<Location> tmp = new LinkedList<>();
			int diff = 6 * i;
			if (i == nGroup)
				diff = pattern.length() - 6;
			LinkedList<Location> o = hashTable.find(pattern.substring(diff, diff + 6));
			Iterator<Location> it = o.iterator();
		    for (Location loc : ret) {
				if(!it.hasNext())
					break;
				int comp = 1;
				while (comp == 1 && it.hasNext())
					comp = loc.add(diff).compareTo(it.next());
				if (comp == 0)
					tmp.addLast(loc);
			}
			ret = tmp;
		}

		StringBuilder sb = new StringBuilder();
		for (Location loc : ret) {
			sb.append(loc);
			sb.append(" ");
		}
		System.out.println(sb.toString().trim());
	}
}
