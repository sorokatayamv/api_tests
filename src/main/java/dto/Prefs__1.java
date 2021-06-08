package dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prefs__1 {
    @SerializedName("background")
    @Expose
    private String background;
    @SerializedName("permissionLevel")
    @Expose
    private String permissionLevel;

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(String permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Prefs__1.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
          .append('[');
        sb.append("background");
        sb.append('=');
        sb.append(((this.background == null) ? "<null>" : this.background));
        sb.append(',');
        sb.append("permissionLevel");
        sb.append('=');
        sb.append(((this.permissionLevel == null) ? "<null>" : this.permissionLevel));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result * 31) + ((this.background == null) ? 0 : this.background.hashCode()));
        result = ((result * 31) + ((this.permissionLevel == null) ? 0 : this.permissionLevel.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Prefs__1) == false) {
            return false;
        }
        Prefs__1 rhs = ((Prefs__1) other);
        return (((this.background == rhs.background) || ((this.background != null) && this.background
            .equals(rhs.background))) && ((this.permissionLevel == rhs.permissionLevel) || (
            (this.permissionLevel != null) && this.permissionLevel.equals(rhs.permissionLevel))));
    }
}
