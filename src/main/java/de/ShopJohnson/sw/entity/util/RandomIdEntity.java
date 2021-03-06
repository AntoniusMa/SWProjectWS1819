package de.ShopJohnson.sw.entity.util;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class RandomIdEntity extends SingleIdEntity<String> {

    @Id
    protected String id;
    
    protected RandomIdEntity() {
        this.id = EntityUtils.createRandomString(8);
    }
    
            
    @Override
    public String getId() {
        return this.id;
    }
    
}
