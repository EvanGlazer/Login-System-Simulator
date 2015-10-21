
import java.io.*;
import java.util.*;


/**
 *
 * @author Evan Glazer
 */
public class Project  /*throws Exception*/
{
    
    
    public Scanner sc;
    public login[] log;
    int profiles;
    File output;
    FileWriter write;
    public void readData()
    {
        sc = new Scanner(System.in);
       try{
        sc = new Scanner(new File("src/input.txt"));
       }
        catch(Exception e)
        {
        }
         
       profiles = sc.nextInt();
        // Fill in data
       log = new login[profiles];
       int j =0;
       try{
       for(int i=0; i<profiles;i++) if(j<=profiles)
       {         
               String user = sc.next();
               String password = sc.next();
               String name = sc.next();
               String bday = sc.next();
               log[i] = new login(profiles,user,password,name,bday);
               
       }
       sc.close();
       }
       catch(Exception e)
       {
           System.out.println("ERROR HERE" + e);
       }
       //System.out.println(log[0].getUser());
         
    }
    
     public void writeDB()
    {
        output = new File ("src/db.txt");
        try {
            write = new FileWriter(output);

            write.append("User\t\tPass\t\tName\t\tBday \n");
      
        for(int i =0; i<profiles; i++)
         {
           
            String line = log[i].getUser() +  "\t\t"  +  log[i].getPass() +  "\t\t"  +  
                    log[i].getName() +  "\t\t"  +  log[i].getBday() + "\n";
            write.append(line);
         }
         write.close();
           } catch (IOException ex) {}
           
        }
     
     
    public void LoginPanel()
    {
        Scanner user = new Scanner(System.in);
        Scanner pass = new Scanner(System.in);
        String user1,pass1;
        System.out.println("Please enter your Username:");
        user1 = user.next();
        System.out.println("Please enter your Password");
        pass1 = pass.next();
        

        
        boolean check = loginCheck(user1,pass1);
        if(check == true)
        {
            System.out.println("Welcome to the new members Area");
        }
        else
        {
            System.out.println("invalid login");
        }
    }
             
    
     public boolean loginCheck(String user, String pass) 
    {
        int n=0;
        boolean flag = false;
        sc = new Scanner(System.in);
       try{
        sc = new Scanner(new File("src/input.txt"));
        
        while(!flag)
        {
            flag = true;
            if(log[n].getUser().contains(user) && log[n].getPass().contains(pass) )
            {
                   // System.out.println("Successful");
                    flag = false;
            }
            else{
            flag = true;
            ++n;
            continue;
            }
            return false;
        
       }
        sc.close();
       }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return true;
       
    }     
  
    public void userDataCheck()
    {
        dbSort sort = new dbSort();
        String name, user, pass, Bday;
        login[] temp = new login[profiles];
        int j = 0;
        int cnt = 0;
        for(int i=0; i<profiles-1 /*+cnt*/; i++)
        {
           
          
                j++;
               // we need to transform the data so we can set
               // the login data to equal the new order
                   
                    user = log[j].getUser();
                    pass = log[j].getPass();
                    name = log[j].getName();
                    Bday = log[j].getBday();
                    
                     /*
                    // need to take care of items being deleted, 
                    // so we dont just overwrite data
                    temp[j] = log[j];
                    ++cnt;
                    log[profiles+cnt] = temp[j]; 
                    */
                    log[i] = new login(profiles, user, pass, name, Bday);
                    
                    //continue;
                
        }
    }

                
  
    
     public static void main( String[] args )
    {
      Project proj = new Project();
        proj.readData();
        proj.writeDB();
        //proj.LoginPanel();
        proj.userDataCheck();
        dbSort db = new dbSort();
        //db.getData();
        
        
        int number_no_nodes, source;
        Scanner scanner = null;
 
        try
        {
            System.out.println("Enter the number of nodes in the graph");
            scanner = new Scanner(System.in);
            number_no_nodes = scanner.nextInt();
 
            int adjacency_matrix[][] = new int[number_no_nodes + 1][number_no_nodes + 1];
            System.out.println("Enter the adjacency matrix");
            for (int i = 1; i <= number_no_nodes; i++)
                for (int j = 1; j <= number_no_nodes; j++)
                    adjacency_matrix[i][j] = scanner.nextInt();
 
            System.out.println("Enter the source for the graph");
            source = scanner.nextInt();
 
            System.out.println("The BFS traversal of the graph is ");
            bfs bfs = new bfs();
            bfs.bfs(adjacency_matrix, source);
 
        } catch (InputMismatchException inputMismatch)
        {
            System.out.println("Wrong Input Format");
        }
        scanner.close();
    }
    
    }
       
    
 
class login {
    
    private String user, pass, name, bday;       
    private String getName;
    public login(int i,String user, String pass, String name, String bday)
    {
        this.user = user;
        this.pass = pass;
        this.name = name;
        this.bday = bday;
    }
    public String getName()
    {
            return this.name;
    }
    public String  getUser()
    {
            return this.user;
    }
    public String  getPass()
    {
            return this.pass;
    }
    public String getBday()
    {
            return this.bday;
    }

     @Override
        public String toString()
        {
            return String.valueOf(this.name) + " " + String.valueOf(this.user)+ " and password " +
                    String.valueOf(this.pass)+ ".";
        }
}

class dbSort
{
    Scanner sc;
    Comparable[] data;
    int profiles;
    // will sort based on username
    // will rewrite to the file with everything sorted 
    // after the db sort then we can add what all the people profiles linked together (Graphy Theory)
    // and see who is close to who based on some more user data, so we can apply a social networking idea 
    // maybe sort by birthday years who is same age and they get something sent to email for being almost same age.
    
    public void getData()
    {
        sc = new Scanner(System.in);
        try {
        sc = new Scanner(new File("src/input.txt"));
        } 
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
        // get profile amount
        profiles = sc.nextInt();
        data = new Comparable[profiles];
        for(int i =0; i<profiles; i++)
        {
            data[i] = sc.next();
            sc.nextLine();
            //System.out.println(data[i]);
        }
        
        quickSort(data);
             
        sc.close();
    }
    
    public void quickSort(Comparable[] a)
    {
        quickSort(a , 0 , a.length-1);
    }

    public void quickSort(Comparable[] a, int lo, int hi)
    {
        if(lo < hi)
        {
            int p = partition(a,lo,hi);
            quickSort(a,lo, p -1);
            quickSort(a, p+1, hi); 
        }   
        
    }
    public int partition(Comparable[] a, int lo, int hi)
    {
       Comparable pivot = a[hi];
       int i = lo;
       
       for(int j=lo; j<hi; j++) 
       {
           if( a[lo].compareTo(a[j]) > 0 )
           {
               //i++;
           }
          else if( j < i)
           {
               break;
           }
                   
           else{
           Comparable temp = a[lo];
           a[lo] = a[j];
           a[j] = temp;

           }
       }
       
       Comparable tmp = a[lo];
       a[lo] = a[hi];
       a[hi] = tmp;
       
       return i;

    }
   
}
  
class bfs
{
    private Queue<Integer> queue;
    public bfs()
    {
        queue = new LinkedList<Integer>();
    }
    
    public void bfs(int adjacency_matrix[][] , int source)
    {
        int number_of_nodes = adjacency_matrix.length-1;
        
        int[] visited = new int[number_of_nodes+1];
        int i, element;
        visited[source] = 1;
        queue.add(source);
        
        while(!queue.isEmpty())
        {
            element =queue.remove();
            i = element;
            System.out.println(i+"\t");
            while(i<=number_of_nodes)
            {
                if(adjacency_matrix[element][i] == 1 && visited[i] == 0)
                {
                    queue.add(i);
                    visited[i] = 1;
                
                }
                
                i++;
                
            }}
            
        }
}
    

