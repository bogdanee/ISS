package model;

import java.io.Serializable;

public class Entity<ID> implements Serializable {
    private static final long serialVersionUID = 7331115341259248461L;
    private ID id;

    /**
     *
     * @return id of the entity
     */
    public ID getId() {
        return id;
    }

    /**
     * set the id
     * @param id
     */
    public void setId(ID id) {
        this.id = id;
    }
}

