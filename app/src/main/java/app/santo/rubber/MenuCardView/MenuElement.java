package app.santo.rubber.MenuCardView;

public class MenuElement {
    public String color;
    public String nombreOpcion;

    public MenuElement(String color, String nombreOpcion) {
        this.color = color;
        this.nombreOpcion = nombreOpcion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNombreOpcion() {
        return nombreOpcion;
    }

    public void setNombreOpcion(String nombreOpcion) {
        this.nombreOpcion = nombreOpcion;
    }
}
