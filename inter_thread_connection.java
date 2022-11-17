package Exercise8;

class Q
{
    int n;
    boolean valueSet = false;
    synchronized int get()
    {
        while(!valueSet)
        {
            try
            {
                wait();
            }
            catch(InterruptedException e)
            {
                System.out.println("InterruptedException caught");
            }
        }
        System.out.println("Get: " + n);
        valueSet = false;
        notify();
        return n;
    }
    synchronized void put(int n)
    {
        while(valueSet)
        {
            try
            {
                wait();
            }
            catch(InterruptedException e)
            {
                System.out.println("InterruptedExceptioncaught");
            }
        }
        this.n = n;
        valueSet = true;
        System.out.println("Put: " + n);
        notify();
    }
}
class Producer implements Runnable
{
    Q q;
    Producer(Q q)
    {
        this.q = q;
        new Thread(this, "Producer").start();
    }
    @Override
    public void run()
    {
        int i = 0;
        while(true)
        {
            if(i==11){
            System.exit(0);
            }
            q.put(i++);
        }
        
    }
}
class Consumer implements Runnable
{
    Q q;
    Consumer(Q q)
    {
        this.q = q;
        new Thread(this, "Consumer").start();
    }
    @Override
    public void run()
    {
        while(true)
        {
            q.get();
        }
    } }
public class inter_thread_connection
{
    public static void main(String args[])
    {
        Q q = new Q();
        new Producer(q);
        new Consumer(q);
        
    }
}

