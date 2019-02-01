
import edu.rice.hj.api.SuspendableException;
import edu.rice.hj.api.SuspendableException;

import java.util.Random;
import static edu.rice.hj.Module1.*;

public class SequentialAndParallelSum {


    private static int sum;



    public static double SequentialMethod(final int num) {
        final long startTime = System.nanoTime();
        sum = 0;

        for(int i = 1; i <= num; ++i)
        {
            sum += i;
        }

        final long runtime = System.nanoTime() - startTime;
        System.out.printf("Sequential Method: %1.2f milliseconds",runtime / 1e6);
        System.out.println("/n");


        return sum;
    }

    public static double ParallelMethod(final int num) throws SuspendableException {

        final long startTime = System.nanoTime();
        sum = 0;

            asyncNb(() -> {
                for(int i = 1; i <= num; ++i)
                {
                    sum += i;
                }
            });

        final long runtime = System.nanoTime() - startTime;
        System.out.printf("Parallel Method: %1.2f milliseconds",runtime / 1e6);
        System.out.println("/n");

        return sum;
    }

    public static void main(final String[] argv) {

     int n=100000000;

        launchHabaneroApp(() -> {
                finish(() -> {
                    SequentialMethod(n);
                    ParallelMethod(n);
                });


        });

    }
}