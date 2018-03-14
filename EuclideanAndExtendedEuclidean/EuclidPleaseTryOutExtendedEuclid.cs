//GCD USING EUCLIDEAN ALGORITHM
using System;
using System.Collections.Generic;
using System.Text;

namespace ConsoleApplication14
{
class Program
    {
 int a,b;

 public void finder()  //function to find gcg
       {
      Console.WriteLine("Enter two numbers\n");

      int a = int.Parse(Console.ReadLine());
      int b = int.Parse(Console.ReadLine());
	  // cin>>a>>b;
	
     while(a!=b)//implementing euclidean algorithm
      {
	     if(a>b)
	     a=a-b;//subtracting the lesser number else
         b=b-a;
	  }

     Console.WriteLine("GCD Of two numbers is : {0}", a); //print can use a or b
     //     cout<<"GCD Of two numbers is "<<a<<endl; 
   }
   
    public static void Main(string[] args)
     {
	  Program g1=new Program();
      g1.finder();

     }
    }
}


