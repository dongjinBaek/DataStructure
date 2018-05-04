import java.io.*;
import java.util.*;

public class SortingTest {
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			boolean isRandom = false;    // 입력받은 배열이 난수인가 아닌가?
			int[] value;    // 입력 받을 숫자들의 배열
			String nums = br.readLine();    // 첫 줄을 입력 받음
			if (nums.charAt(0) == 'r') {
				// 난수일 경우
				isRandom = true;    // 난수임을 표시

				String[] nums_arg = nums.split(" ");

				int numsize = Integer.parseInt(nums_arg[1]);    // 총 갯수
				int rminimum = Integer.parseInt(nums_arg[2]);    // 최소값
				int rmaximum = Integer.parseInt(nums_arg[3]);    // 최대값

				Random rand = new Random();    // 난수 인스턴스를 생성한다.

				value = new int[numsize];    // 배열을 생성한다.
				for (int i = 0; i < value.length; i++)    // 각각의 배열에 난수를 생성하여 대입
					value[i] = rand.nextInt(rmaximum - rminimum + 1) + rminimum;
			} else {
				// 난수가 아닐 경우
				int numsize = Integer.parseInt(nums);

				value = new int[numsize];    // 배열을 생성한다.
				for (int i = 0; i < value.length; i++)    // 한줄씩 입력받아 배열원소로 대입
					value[i] = Integer.parseInt(br.readLine());
			}

			// 숫자 입력을 다 받았으므로 정렬 방법을 받아 그에 맞는 정렬을 수행한다.
			while (true) {
				int[] newvalue = (int[]) value.clone();    // 원래 값의 보호를 위해 복사본을 생성한다.

				String command = br.readLine();

				long t = System.currentTimeMillis();
				switch (command.charAt(0)) {
					case 'B':    // Bubble Sort
						newvalue = DoBubbleSort(newvalue);
						break;
					case 'I':    // Insertion Sort
						newvalue = DoInsertionSort(newvalue);
						break;
					case 'H':    // Heap Sort
						newvalue = DoHeapSort(newvalue);
						break;
					case 'M':    // Merge Sort
						newvalue = DoMergeSort(newvalue);
						break;
					case 'Q':    // Quick Sort
						newvalue = DoQuickSort(newvalue);
						break;
					case 'R':    // Radix Sort
						newvalue = DoRadixSort(newvalue);
						break;
					case 'X':
						return;    // 프로그램을 종료한다.
					default:
						throw new IOException("잘못된 정렬 방법을 입력했습니다.");
				}
				if (isRandom) {
					// 난수일 경우 수행시간을 출력한다.
					System.out.println((System.currentTimeMillis() - t) + " ms");
				} else {
					// 난수가 아닐 경우 정렬된 결과값을 출력한다.
					for (int i = 0; i < newvalue.length; i++) {
						System.out.println(newvalue[i]);
					}
				}

			}
		} catch (IOException e) {
			System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static void swap(int[] arr, int i, int j)
	{
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoBubbleSort(int[] value)
	{
		// value는 정렬안된 숫자들의 배열이며 value.length 는 배열의 크기가 된다.
		// 결과로 정렬된 배열은 리턴해 주어야 하며, 두가지 방법이 있으므로 잘 생각해서 사용할것.
		// 주어진 value 배열에서 안의 값만을 바꾸고 value를 다시 리턴하거나
		// 같은 크기의 새로운 배열을 만들어 그 배열을 리턴할 수도 있다.
        for (int n = value.length; n >= 0; n--) {
			for (int i=1; i < n; ++i) {
				if (value[i] < value[i-1])
					swap(value, i, i - 1);
			}
		}
		return (value);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoInsertionSort(int[] value)
	{
		for (int i=1; i<value.length; ++i) {
			int j = i - 1, temp = value[i];
			while (j >= 0 && temp < value[j]) {
				value[j+1] = value[j];
				j--;
			}
			value[j+1] = temp;
		}
		return (value);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoHeapSort(int[] value)
	{
		// TODO : Heap Sort 를 구현하라.
		return (value);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] mergeSort(int[] value)
	{
		if(value.length == 1)
			return (value);
		int mid = value.length / 2;
		int[] left = mergeSort(Arrays.copyOfRange(value, 0, mid));
		int[] right = mergeSort(Arrays.copyOfRange(value, mid, value.length));
		int[] ret = new int[value.length];
		int lidx = 0, ridx = 0, idx=0;

		while (lidx < left.length || ridx < right.length) {
			if (ridx == right.length)
				ret[idx++] = left[lidx++];
			else if (lidx == left.length)
				ret[idx++] = right[ridx++];
			else if (left[lidx] < right[ridx])
				ret[idx++] = left[lidx++];
			else
				ret[idx++] = right[ridx++];
		}
		return (ret);
	}
	private static int[] DoMergeSort(int[] value)
	{
		return mergeSort(value);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int partition(int[] value, int l, int r)
	{
		int pivot = value[l];
		int i=l;
		/*paitition value[l, j)
		  value[l] is a pivot
		  value[l+1, i] are less than pivot
		  value[i+1, j) are greater than pivot*/
		for (int j=l+1; j<r; ++j) {
			if (value[j] < pivot) {
			    ++i;
				swap(value, i, j);
			}
		}
		swap(value, l, i);
		return i;
	}

	//quicksort value[l, r)
    private static void quickSort(int[] value, int l, int r)
	{
		if (r - l <= 1)
			return;
		int pIdx = partition(value, l, r);
		quickSort(value, l, pIdx);
		quickSort(value, pIdx+1,r);
	}

	private static int[] DoQuickSort(int[] value)
	{
	    quickSort(value, 0, value.length);
		return (value);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoRadixSort(int[] value)
	{
		// TODO : Radix Sort 를 구현하라.
		return (value);
	}
}
