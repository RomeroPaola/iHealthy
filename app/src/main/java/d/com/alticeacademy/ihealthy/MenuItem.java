package d.com.alticeacademy.ihealthy;

import android.graphics.drawable.Drawable;

public class MenuItem {

    private String title;
    private Integer id;
    private Drawable icon;
    private Drawable go;

    public MenuItem() {
        super();
    }

    public MenuItem(String title, Integer id, Drawable icon, Drawable go) {
        this.title = title;
        this.id = id;
        this.icon = icon;
        this.go = go;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public Drawable getGo() {
        return go;
    }

}
