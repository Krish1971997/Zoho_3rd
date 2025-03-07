package ParkinglotZoho;
import java.time.*;
import java.time.format.DateTimeFormatter;
public class Vechicle   {
    
    public String licenceNumber;
    public String model;
    public String vechicletype;
    public String entrytime;
    public String exitTime;
    public double totalcost;
    public int userid;

    public Vechicle(String licenceNumber, String model, String vechicletype ,int userid) {
        this.userid = userid;
        this.licenceNumber = licenceNumber;
        this.model = model;
        this.vechicletype = vechicletype;
        this.entrytime = generateTime();
        this.exitTime = null;
        
    }
    public String generateTime()
    {
        
        LocalDateTime localDateTime = LocalDateTime.now();
         DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

         return localDateTime.format(dateTimeFormatter);

    }

    public double calculateTotalCost()
    {
        int entrytimeinteger = castingToInteger(entrytime);
        int exittimeinteger = castingToInteger(exitTime);
        int duration = exittimeinteger - entrytimeinteger;
        double cost = duration * 0.5;
        return cost;
    }
    private int castingToInteger(String entrytime2) {
        
        int time= (int) (entrytime2.charAt(0)-'0')* 10+ (int) entrytime2.charAt(1) -'0';

        return time;
    }
    // @Override
    // protected Object clone() throws CloneNotSupportedException {
       
    //     return super.clone();
    // }

    @Override
    public String toString() {
        
        return "Vechical{" +
                "licenceNumber='" + licenceNumber + '\'' +
                ", model='" + model + '\'' +
                ", vechicaltype='" + vechicletype + '\'' +
                ", entrytime='" + entrytime + '\'' +
                ", exitTime='" + exitTime + '\'' +
                ", totalcost=" + totalcost +
                ", userid=" + userid +
                '}';
    }

}
