
import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Arrays;
import java.lang.Math;

public class predict {

    //for a given graph this function tells the value at a given point
    public double get_out(double day[], double value[], double x) {
        double getx = 0;
        double perm = 0;
        double result = 0;

        for (int i = 0; i < day.length; i++) {
            getx = value[i];
            perm = day[i];
            for (int j = 0; j < value.length; j++) {
                if (i != j) {
                    getx = getx * ((x - day[j]) / (perm - day[j]));
                }
            }
            result = result + getx;
            //System.out.println("The result is : "+result);
        }
        return result;
    }

    //sort the arrays according to the array : a
    public static void sort(double arr[], double a[], double slope[]) {

        double min = 0;
        int pos = 0;
        double temp = 0;
        double temp2 = 0;
        double temp3 = 0;
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            min = a[i];
            pos = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < min) {
                    min = a[j];
                    pos = j;
                }
            }
            if (pos != i) {
                temp = a[i];
                temp2 = arr[i];
                temp3 = slope[i];
                a[i] = a[pos];
                arr[i] = arr[pos];
                slope[i] = slope[pos];
                a[pos] = temp;
                arr[pos] = temp2;
                slope[pos] = temp3;
            }
        }
    }

    //the below function changes the discreate graph into a continus one
    public double[] get_conti(double day[], double value[]) {
        DecimalFormat df = new DecimalFormat("#.#");

        double i = 0;
        double val_c[] = new double[4000];
        int counter = 0;
        predict p1 = new predict();

        do {
            i = Double.parseDouble(df.format(i));
            val_c[counter] = p1.get_out(day, value, i);
            i = i + 0.1;
            counter = counter + 1;
        } while (i < 400);
        return val_c;
    }

    //this function return the slope for each point on the graph
    public double[] get_slope(double value[]) {
        double slope[] = new double[3999];
        double se;
        for (int i = 0; i <= 3999 - 1; i++) {
            slope[i] = (value[i + 1] - value[i]) / 0.1;

            //System.out.println("The slope is : "+se);
        }
        return slope;
    }

    public int pred_st_index(double index[], double value[], double slope[], double s_value, double s_slope) {
        int st_index = 0;

        double min1[] = new double[399];
        double min2[] = new double[399];

        //System.out.println("s value : "+s_value+" s slope : "+s_slope);
        int i = 0;
        do {
            min1[i] = Math.pow((value[i] - s_value), 2);
            if (min1[i] == 0) {
//                System.out.println("min i o for : "+value[i]);   
            }
            i = i + 1;
        } while (i < 399);

        i = 0;
        do {
            min2[i] = Math.pow((slope[i] - s_slope), 2);
            i = i + 1;
        } while (i < 399);
        sort(index, min1, min2);

        double setyy = min2[0];
        int f_index = (int) index[0];
        //System.out.println("Value is : "+value[(int)index[0]]);
        for (i = 0; i < 10; i++) {
            if (min2[i] < setyy) {
                //System.out.println("index : "+index[i]+ " value : "+value[(int)index[i]] );
                setyy = min2[i];
                f_index = (int) index[i];
            }
        }

        return f_index;
    }

    public static double predict(int day_ahed) throws IOException {
        File f1 = new File("C:\\Users\\ABC\\Desktop\\PreTrender\\SentiMents.txt");
        Scanner s1 = new Scanner(f1);

        double get = 0;
        int count = 1;
        double day[] = new double[400];
        double value[] = new double[400];

        while (s1.hasNext()) {

            get = Double.parseDouble(s1.nextLine());
            day[count - 1] = count;
            value[count - 1] = get;
            count = count + 1;
        }

        predict p1 = new predict();

        //getting the slope by extending the graph
        double get_current_slope = (p1.get_out(day, value, 400) - p1.get_out(day, value, 399.9)) / 0.1;
        double get_current_value = p1.get_out(day, value, 400);

        //getting the index for each day
        double index[] = new double[399];
        for (int i = 0; i < 399; i++) {
            index[i] = i;
        }
        //-----------------------------

        double conti[] = new double[4000];
        double conti1[] = new double[3999];
        double slope[] = new double[3999];
        conti = p1.get_conti(day, value);
        slope = p1.get_slope(conti);

        for (int ii = 0; ii < 3999; ii++) {
            conti1[ii] = conti[ii + 1];
        }

        double cl_val[] = new double[399];
        double cl_slope[] = new double[399];
        double ss_val[] = new double[399];

        int cx = 0;
        for (int i = 0; i < 3998; i = i + 1) {
            if (conti[i] <= 3 && conti[i] >= 0 && i % 10 == 0) {

                cl_val[cx] = conti[i];
                ss_val[cx] = conti[i];
                cl_slope[cx] = slope[i + 1];
                cx = cx + 1;
            }
        }

        for (int j = 0; j < cx; j++) {
         //System.out.println("j : "+j+"  c1 : "+cl_val[j]+" sl : "+cl_slope[j]);   

        }

        int got = p1.pred_st_index(index, cl_val, cl_slope, get_current_value, get_current_slope);
        int diff = 399 - got;
        double port[] = new double[diff];
        for (int ij = 0; ij < diff; ij++) {
            port[ij] = ss_val[ij + got];
            //System.out.println("putting "+port[ij]+" for "+ss_val[ij+got]);
        }

        day_ahed = day_ahed % diff;

        //System.out.println("Final value is : "+ss_val[got]+" The index is : "+got);
        //System.out.println("The value is : "+port[day_ahed]);
        System.out.println("Identification Found On DAY = " + got);
        return port[day_ahed];
    }

}
