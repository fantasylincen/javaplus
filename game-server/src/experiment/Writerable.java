package experiment;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
 
public interface Writerable {
       //write 
       public void  write(DataOutput data) throws IOException;
       //read
       public  void read(DataInput di) throws IOException;
}
