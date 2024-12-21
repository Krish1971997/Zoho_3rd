
package ParkinglotZoho;

public class ParkingSpace {
    public int floorNumber;
    public boolean isOccupied;
    public Vechicle vechicle;
    public int slotnumber;

    public ParkingSpace(int slotnumber,int floorNumber) {
        this.floorNumber = floorNumber;
        this.slotnumber = slotnumber;
        isOccupied = false;
        vechicle = null;
    }

    public void parkVehicle(Vechicle vechicle) {
        this.isOccupied = true;
        this.vechicle = vechicle;
        System.out.println("Ticket id: " + vechicle.userid);
        System.out.println("Vehicle parked at " + floorNumber + "st floor and slot number is : " + slotnumber);
    }
    
    public void removeVehicle() {
        this.isOccupied = false;
        this.vechicle = null;
        
        System.out.println("Vehicle removed from " + floorNumber + "st floor and slot number is : " + slotnumber);
     //  System.out.println("to check: " + vechical.entrytime);
    }





}
