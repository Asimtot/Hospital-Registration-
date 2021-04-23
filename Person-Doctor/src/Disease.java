/**
 * Disease class
 * @author Eylul Badem
 * @version 1.0, 21.04.2021
*/ 
public class Disease {
    
    // Properties
    
    private String name;
    private Department relatedField;
    private String info;
    private BodyPart relatedBodyP;
    
    // Constructor
    
    public Disease ( String name, Department relatedField, String info, BodyPart relatedBodyP ) {
        this.name = name;
        this.relatedField = relatedField;
        this.info = info;
        this.relatedBodyP = relatedBodyP;
    }
    
    // Methods
    
    public String getName()
    {
        return name;
    }
   
    public Department getField()
    {
        return relatedField;
    }
    
    public String getInfo()
    {
        return info;
    }
   
    public BodyPart getRelatedBodyP()
    {
        return relatedBodyP;
    }
}
