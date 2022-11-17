package Exercise8;

import java.util.Date;
import java.util.Scanner;

public class Busrevsys 
{
    public static void main(String[] args) 
    {
        thread thr=new thread();
        thr.w();
        thr.create(4);
    }   
}

class thread extends Thread
{
    Scanner obj=new Scanner(System.in);
    int choice;
    info vi=new info();

    public void w()
    {
        vi.ini();
    }
    
    public  void create(int n)
    {
        for(int i=0; i<n;i++)
        {   
            new Thread(()-> {
                synchronized(this){
                    System.out.println("1.For Booking\n2.For Cancel");
                    System.out.print("Enter Your Choice: ");
                    choice=obj.nextInt();
                    if(choice==1)
                    {
                        vi.getinfo();
                    }
                    else
                    {
                        vi.cancel();
                    }
            }}).start();
        }
    }
}

class info 
{
    String name,Name;
    int no_of_seats,choice,seat_no,Seat_no,count,flag=1,index=0;
    long mob_no;
    String seat[]=new String[5];
    long mobile_no[]=new long[5];
    public void ini()
    {
        for (int m=0;m<5;m++) 
        {
            seat[m] = " ";
        }
    }
    
    public void getinfo()
    {
        Scanner obj=new Scanner(System.in);
        count =0;
        if(flag == 0){
            System.out.println("All Our Seats are Booked!!!");
        }
        else
        {
        System.out.print("Enter Your Name: ");
        name=obj.nextLine();
        
        System.out.print("Enter Your Moblie Number: ");
        mob_no=obj.nextLong();
        mobile_no[index]=mob_no;
        index+=1;
        System.out.print("Available Seats : ");
        for (int m=0;m<5;m++) 
        {
            if (seat[m].isBlank())
            {
                count+=1;
                System.out.print((m+1)+" ");
               
            }
        }
        
        System.out.print("\nEnter Number of Seats: ");
        no_of_seats=obj.nextInt();
        
        if(no_of_seats<(seat.length-count))
        {
            System.out.println("Sorry!! we don't have "+no_of_seats+" seats");
        }
        else{
        for (int i=0;i<no_of_seats;i++)
        {
            System.out.print("Enter seat number : ");
            seat_no=obj.nextInt();
            if(seat[seat_no-1].isBlank())
            {
                seat[seat_no-1]=name;
                System.out.println("Seat Booked!!");
            }
            else
            {
                System.out.println("Seat not Booked!!");
                System.out.println("Enter seat again!!");
                i--;
            }
        }
        }       
        bill();
        }
        flag=count-no_of_seats;
    }
    
    public void bill()
    {
        Date timenow = new Date();
        System.out.println();
        System.out.println("Welcome to Vishnu Bus Reservation System\n\n\t*****Your Ticket*****");
	System.out.println("Date: " + timenow.toString());
        System.out.println("Name: "+name.toUpperCase());
	System.out.print("Boarding pass for seat number: " );
        for(int i=0;i<seat.length;i++)
        {
            if (seat[i].equalsIgnoreCase(name))
            {
                System.out.print((i+1)+" ");
            }
        }
        System.out.println();
        System.out.println("Your Mobile Number : "+mob_no);
	
	System.out.println("Please be curteous, do not smoke. Enjoy your trip.");
        System.out.println();
	System.out.println("\t*********************");
    }
   
    public void cancel()
    {
        Scanner obj=new Scanner(System.in);
        System.out.print("Enter Your Name :");
        Name=obj.nextLine();
        for (int m=0;m<5;m++) 
        {
            if (seat[m].equalsIgnoreCase(Name))
            {
                count=1;
                break;
            }
        }
        if(count!=1)
        {
            System.out.println("We Don't Have any Customer in this Name!!");
            System.exit(0);
        }
        System.out.print("Your Tickets : ");
        for(int i=0;i<seat.length;i++)
        {
            if (seat[i].equalsIgnoreCase(Name))
            {
                System.out.print((i+1)+" ");
            }
        }
        
        System.out.println("\n1.To Cancel All Your Ticket\n2.To cancel Specify Ticket");
        System.out.print("Enter Your Choice : ");
        choice=obj.nextInt();
        
        if (choice==1)
        {
            cancelbill(Name,choice,0);
            for(int i=0;i<seat.length;i++)
            {
                if (seat[i].equalsIgnoreCase(Name))
                {
                    seat[i]=" ";
                }
            }
        }
        else
        {
            while(true)
            {
                System.out.print("Enter Your Seat Number:");
                Seat_no=obj.nextInt();
                if(seat[Seat_no-1].equalsIgnoreCase(Name))
                {
                    cancelbill(Name,choice,Seat_no);
                    seat[Seat_no-1]=" ";
                    break;
                }
                else
                {
                    System.out.println("Enter Your Correct Seat Number!!\nDon't Play With us We have Lot of Works");
                    System.out.println("Enter Again");
                }
            }
        }
    }
    
    public void cancelbill(String name,int choice,int seat_no)
    {
        Date timenow = new Date();
        System.out.println();
        System.out.println("\nWelcome to Vishnu Bus Reservation System\n\n\t*****Your Ticket*****");
	System.out.println("Date: " + timenow.toString());
        System.out.println("Name: "+name.toUpperCase());
        if (choice==1)
        {
	System.out.print("Cancelled seat number: " );
        for(int i=0;i<seat.length;i++)
        {
            if (seat[i].equalsIgnoreCase(name))
            {
                System.out.print((i+1)+" ");
            }
        }
        }
        else if(choice==2)
        {
            System.out.print("Cancelled seat number: "+seat_no);
        }
        System.out.println();
        for(int i=0;i<seat.length;i++)
        {
            if (seat[i].equalsIgnoreCase(name))
            {
                System.out.println("Your Mobile Number : "+mobile_no[i]);
                break;
            }
        } 
        System.out.println("Sorry For Inconvience!!! \nNext Time We Won't Disappointment You");
        
        System.out.println("\n\t*********************");
    }
}
