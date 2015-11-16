
import java.text.DecimalFormat;

class predict
{
public static double get_out(double day[],double value[],double x)
    {
        double getx=0;
        double perm=0;
        double result=0;


        
        DecimalFormat df = new DecimalFormat("#.#");

        
        
        for(int i=0;i<day.length;i++)
        {
            getx=value[i];
            perm=day[i];
            for(int j=0;j<value.length;j++)
            {
                if(i!=j)
                {
 
                    getx=getx*((x-day[j])/(perm-day[j]));
                    
                    //System.out.println("t1 : "+temp1);
                    //System.out.println("t2 : "+temp2);
                    //System.out.println("t3 : "+temp3);
                    
                }
            }
            result=result+getx;
        }
        return result;
    }
    public static double main(double day[],double value[],int getyy)
    {
        int get=getyy;
        //double day[]={1 ,2 ,3 ,4 ,5  };
        //double value[]={1.0, 2.0, 1.0, 1.0, 1.0};
        //double day[]={1 ,2 ,3 ,4 ,5 ,6 ,7 ,8 ,9 ,10 ,11 ,12 ,13 ,14 ,15 ,16 ,17 ,18 ,19,20 };
        //double value[]={1.0, 2.0, 1.0, 1.1595745, 1.0, 2.0, 2.0, 2.0, 1.6212121, 1.4482758, 1.0, 2.2755103, 1.4482758, 1.0, 1.0, 1.0, 1.0, 2.0, 1.2,2.0};
        
        double f114=0;
        predict p1 = new predict();
        double res=p1.get_out(day,value,0);
        
        //double day[]={1,2,3};
        //double value[]={1,4,9};
        
        //double res=0;
        System.out.println("\n\n\nThe result is : "+res+"\n\n");
        
        double x11=0.01;
        double x12=0.00;
        double val=20/0.01;
        int val1=(int)val+1;
        double store[]= new double[val1];
        int count=0;
        
        while(count<val1)
        {
            store[count]=p1.get_out(day,value,x12);
            count=count+1;
            x12=x12+x11;
        }
        for(int iy=0;iy<count;iy++)
        {
               System.out.println(iy*0.01+" is : "+store[iy]);
        }
        double store1[]= new double[val1-1];
        
        for(int iy=0;iy<count-1;iy++)
        {
               store1[iy]=(store[iy+1]-store1[iy])/0.01;
               
        }
        System.out.println("\n\n\nThe result is : ");
        for(int iy=0;iy<count-1;iy++)
        {
               System.out.println(iy*0.01+" is : "+store1[iy]);
        }
        
        double finalx=0;
        double f11=0;
        double f12=0;
        finalx=f12;
        double f13=0;
        //System.out.println("\n\nThe value is : "+f13);
        
        
        int prevx=10;
        double ijk;
        double store2[]= new double[val1-1];
        double loop_var=17;
        
        int min;
        int minpos;
        double getfinal1=0;
        double getfinal2=0;
        while(loop_var<get)
        {
            f11=p1.get_out(day,value,loop_var);
            f12=p1.get_out(day,value,loop_var+0.01);
            f13=(f12-f11)/0.01;
            for(int iz=0;iz<count-1;iz++)
            {
                ijk=store1[iz]-f13;
                store2[iz]=100*ijk;
            }
        
            min=(int)store2[0];
            minpos=0;
            int getx1=0;
            int getx2=1;
            for(int iz=0;iz<count-1;iz++)
            {

            
                if(store2[iz]<min && iz >100 && iz<1800 && iz!=prevx)
                {
                    //System.out.println("previous : "+prevx+" current : "+iz);
                    min=(int)store2[iz];
                    minpos=iz;
                    prevx=iz;
                   getx1=iz;
                }
                getx2=getx1;
            }
            System.out.println("Loop variable : "+loop_var+"Matching with pos : "+minpos+" and value : "+store1[minpos]);
            getfinal1=store1[minpos];
            getfinal2=store1[minpos+1];
            finalx=finalx+(getfinal2-getfinal1);
        
            loop_var=loop_var+0.01;
            f114=p1.get_out(day,value,28);
            System.out.println("\n\nThe final result is : "+finalx);

        }
                    int getzz=getyy%30;
            f11=p1.get_out(day,value,getzz+0.01);


            if(f11<=1.0)
            {
               f11=1.2; 
            }
            if(f11>=3)
            {
               f11=2.8; 
            }
            DecimalFormat df = new DecimalFormat("#.###");
            double df22 = Double.parseDouble(df.format(f11));
        //System.out.println("\n\nThe final result is : "+f11);
        return df22;
    }
}