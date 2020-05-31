import java.io.Serializable;

/**
 *
 */
public class CreateString implements Serializable {
    private String s;

    public String getS() {
        return s;
    }

    public CreateString(int length){
       StringBuilder stringBuilder = new StringBuilder();
       stringBuilder.append("a");
       for (int i=1;i<=length;i++){
           stringBuilder.append(stringBuilder.toString());
       }
       s = stringBuilder.toString();
    }
}
