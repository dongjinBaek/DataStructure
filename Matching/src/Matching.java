import java.io.*;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

public class Matching
{
	public static final int MOD = 100;
	public static Hashtable<Integer, AVLTree<Location, String>> hashTable;

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

	//hash function : add ASCII value of every element of string
	public static int hash(String str) {
		int ret = 0;
        for (int i = 0; i < str.length(); i++) {
            ret += (int)str.charAt(i);
        }
        return ret % MOD;
	}

	private static void read(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		//initialize data structure
		hashTable = new Hashtable<>(MOD);
		for (int i = 0; i < MOD; i++) {
			hashTable.put(i, new AVLTree<>());
		}
		int lineNumber = 1;
		String line;
		while ((line = br.readLine()) != null) {
			int start = 0;
			//for every substring of length 6, insert into AVLTree
			for (int end = 6; end <= line.length(); start++, end++) {
				String str = line.substring(start, end);
				int h = hash(str);
				hashTable.get(h).insert(str, new Location(lineNumber, start+1));
			}
			lineNumber++;
		}
	}

	private static void print(int index) {
		hashTable.get(index).printPreorder();
	}

	//find locations in data file that matches with the pattern
	private static void find(String pattern) {
		int nGroup = pattern.length() / 6;

		//first, find matches using first six letters
		String str = pattern.substring(0, 6);
		int h = hash(str);
		LinkedList<Location> ret = hashTable.get(h).find(str).getList();

		for (int i = 1; i <= nGroup; i++) {
			LinkedList<Location> tmp = new LinkedList<>();
			//for all candidates, check if next six letters match
			int diff = 6 * i;
			//also check if last six letters matches
			if (i == nGroup)
				diff = pattern.length() - 6;
			str = pattern.substring(diff, diff + 6);
			h = hash(str);
			LinkedList<Location> o = hashTable.get(h).find(str).getList();
			Iterator<Location> it = o.iterator();
			if (!it.hasNext()) {
				System.out.println("(0, 0)");
				return;
			}
			Location oLoc = it.next();
		    for (Location loc : ret) {
		    	int comp = 1;
				while ((comp = loc.add(diff).compareTo(oLoc)) > 0) {
				    if (!it.hasNext())
				    	break;
					oLoc = it.next();
				}
				if (comp == 0)
					tmp.addLast(loc);
			}
			ret = tmp;
		}

		//case no matches found, just print (0, 0)
		if (ret.size() == 0) {
			System.out.println("(0, 0)");
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append(ret.get(0));
			for (int i = 1; i < ret.size(); i++) {
				sb.append(" ");
				sb.append(ret.get(i));
			}
			System.out.println(sb.toString());
		}
	}
}
