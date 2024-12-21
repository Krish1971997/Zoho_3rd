package ParkinglotZoho;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
public class UserUtility {
    
    List<Userr>usereDetails;
    Queue<Vechicle>queue;
    Scanner sc = new Scanner(System.in);
    public UserUtility() {
        this.usereDetails = new ArrayList<>();
        this.queue = new LinkedList<>();
    }

    public void RegisterUser() 
    {
        
        System.out.println("Enter Name:");
        String name = sc.nextLine();
        System.out.println("Enter Address:");
        String address = sc.nextLine();
        System.out.println("Enter Phone Number:");
        String phonenumber = sc.nextLine();
        Userr user = new Userr(name, address, phonenumber);
        usereDetails.add(user);
        System.out.println(user.toString());
        System.out.println("User Registered Successfully!");
     //   sc.close();
    }

    public void RegisterCar()
    {
        System.out.println("Enter the user id");
        int userId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the licence number");
        String licenceNumber = sc.nextLine();
        System.out.println("Enter the type ");
        String type = sc.nextLine();
        System.out.println("Enter the car model");
        String carModel = sc.nextLine();
        Vechicle vechicle = new Vechicle( licenceNumber, carModel, type,userId);
        queue.add(vechicle);


    }

    public void ParkCar(ParkingLott lott) 
    {
        if(queue.isEmpty())
        {
            System.out.println("No car registered yet");
            return;
        }
        Vechicle vechical = queue.peek();

        for(int i=0;i<lott.floorList.size();i++)
        {
            // public Map<Integer,ParkingSpace> parkingspace;
            Map<Integer,ParkingSpace>duplicatespace;
           duplicatespace =lott.floorList.get(i).parkingspace;
            for(Map.Entry<Integer, ParkingSpace> entry : duplicatespace.entrySet())
            {
                if(entry.getValue().isOccupied==false)
                {
                    // entry.getValue().vechical = vechical;
                    // entry.getValue().vechical.entrytime =entry.getValue().vechical.generateTime();
                    // entry.getValue().isOccupied=true;
                    entry.getValue().parkVehicle(vechical);
                   queue.poll(); 
                    System.out.println("Car Parked Successfully!");
                    return;
                }
            }
        }

        System.out.println("No available slot in parking lot");

        
    }
    public void RemoveCar(ParkingLott lott,int givenuserid)  
    {
        //throws CloneNotSupportedException
        List<Vechicle>duplicateReport=lott.report;
        for(int i=0;i<lott.floorList.size();i++)
        {
            // public Map<Integer,ParkingSpace> parkingspace;
            Map<Integer,ParkingSpace>duplicatespace;
           duplicatespace =lott.floorList.get(i).parkingspace;
            for(Map.Entry<Integer, ParkingSpace> entry : duplicatespace.entrySet())
            {
                if(entry.getValue().isOccupied==true && entry.getValue().vechicle.userid == givenuserid)
                {
                    // entry.getValue().vechicle = vechicle;
                    // entry.getValue().vechical.entrytime =entry.getValue().vechical.generateTime();
                    // entry.getValue().isOccupied=true;
                     
                	Vechicle vechicle = entry.getValue().vechicle;
                	vechicle.exitTime = vechicle.generateTime();
                     duplicateReport.add((Vechicle)vechicle );
                    System.out.println(vechicle.toString());  
                     entry.getValue().removeVehicle();
                     System.out.println();
                     System.out.println("After the remove vechical ");
                    System.out.println();
                    vechicle.toString() ;
                    return;
                }
            }
        }

        System.out.println("No car avaliable with that ID");
    }

    public void costCalculationn(ParkingLott lott)
    {
        System.out.println("in side cost");
        List<Vechicle>duplicateReport=lott.report;

        for(Vechicle value : duplicateReport)
        {
            value.calculateTotalCost();
            System.out.println(value.toString());
        }
    }


}
