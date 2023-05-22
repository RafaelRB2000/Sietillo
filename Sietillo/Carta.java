/*
* * Rafael Ramírez Berrocal y Pedro A. Serrano Sánchez
 */
package Sietillo;

import javax.swing.ImageIcon;

/*
 * CLASE CARTA
 */
public class Carta {

    //Variables usadas para el funcionamiento de la clase
    private String Palo;
    private int Num;
    private ImageIcon img;
    private boolean existe;

    /*
     * INICIALIZAR LA CARTA
     */
    public Carta(String P, int N, boolean E, Boolean SiImg) {
        this.Palo = P;
        this.Num = N;
        this.existe = E;
        if (SiImg == true) {
        	String rutaImagen = String.format("/Imagenes/%d_of_%s.png", N, P);
            img = new ImageIcon(Carta.class.getResource(rutaImagen));
        }
    }

    /*
     * GETTERS NECESARIOS DE LA CLASE
     */
    public String getPalo() {return this.Palo;}
    public int getNum() {return this.Num;}
    public boolean getExiste() {return this.existe;}
    public ImageIcon getImage() {return this.img;}

    /*
     * SETTER NECESARIO DE LA CLASE
     */
    public void setPalo(String P) {this.Palo = P;}
    public void setNum(int N) {this.Num = N;}
    public void setExiste(boolean E) {this.existe = E;}

    /*
     * VACIAR UNA CARTA
     */
    public void VaciarCarta() {
        this.Palo = null;
        this.Num = 0;
        this.existe = false;
    }

}