import java.io.*;
import java.util.Random;

public class TestMain {
    private static final char[] op = {'B', 'I', 'H', 'M', 'Q', 'R'};

    public static void main(String[] args) {
//        try {
//            BufferedWriter fw = new BufferedWriter(new FileWriter("result.out"));
//            int numsize = 10, ntest = 30;
//            int rmaximum = 1000000000, rminimum = -1000000000;
//            int[] value;
//            Random rand = new Random();    // 난수 인스턴스를 생성한다.
//            SortingTest st = new SortingTest();
//            for (int j = 0; j < 6; j++) {
//                fw.write("size:"+numsize+"\n");
//                value = new int[numsize];
//                for (char operation : op) {
//                    fw.write(operation);
//                    long[] time = new long[ntest];
//                    for (int i = 0; i < ntest; i++) {
//                        for (int k = 0; k < value.length; k++)    // 각각의 배열에 난수를 생성하여 대입
//                            value[k] = rand.nextInt(rmaximum - rminimum + 1) + rminimum;
//                        int[] newvalue = value.clone();
//                        long t = System.nanoTime();
//                        switch (operation) {
//                            case 'B':
//                                if(j != 5)
//                                    st.DoBubbleSort(newvalue);
//                                break;
//                            case 'I':
//                                if(j != 5)
//                                    st.DoInsertionSort(newvalue);
//                                break;
//                            case 'H':
//                                st.DoHeapSort(newvalue);
//                                break;
//                            case 'M':
//                                st.DoMergeSort(newvalue);
//                                break;
//                            case 'Q':
//                                st.DoQuickSort(newvalue);
//                                break;
//                            case 'R':
//                                st.DoRadixSort(newvalue);
//                                break;
//                            default:
//                        }
//                        time[i] = System.nanoTime() - t;
//                        //fw.write(" "+time[i]);
//                    }
//                    double sum = 0, sq = 0;
//                    for (int i = 0; i < ntest; i++) {
//                        sum += 1.0*time[i];
//                        sq += 1.0*time[i] * time[i];
//                    }
//                    fw.write(" mean:"+(1.0*sum/ntest)/1000000);
//                    fw.write(" stdev:"+(Math.sqrt(1.0*sq/ntest - 1.0*sum*sum/ntest/ntest)/1000000)+"\n");
//                }
//                numsize *= 10;
//            }
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
