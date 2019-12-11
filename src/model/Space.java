package model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Space {
    private int spaceId;
    private String spaceName;
    private String spaceState;

    public Space() {
		this.spaceState="空闲";
	}
    @Id
    @Column(name = "SpaceId")
    public int getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
    }

    @Basic
    @Column(name = "SpaceName")
    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    @Basic
    @Column(name = "SpaceState")
    public String getSpaceState() {
        return spaceState;
    }

    public void setSpaceState(String spaceState) {
        this.spaceState = spaceState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Space space = (Space) o;
        return spaceId == space.spaceId &&
                Objects.equals(spaceName, space.spaceName) &&
                Objects.equals(spaceState, space.spaceState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spaceId, spaceName, spaceState);
    }
}
