/*
* * Rafael Ramírez Berrocal y Pedro A. Serrano Sánchez
 */
package Sietillo;

import java.util.Random;
import javax.swing.ImageIcon;

/*
 * CLASE MAZO
 */
public class Mazo {

    //Variables usadas para el funcionamiento de la clase
    private final Carta Mazo[];
    private int contador;
    private int LIzq;
    private int LDer;
    private int Centro;

    /*
     * INICIALIZAR EL MAZO O MANO
     */
    public Mazo(int n) {this.Mazo = new Carta[n];}

    /*
     * CREACIÓN DE CARTAS DEL MAZO
     */
    public void crearCartas() {
        int incr = 0;
        for (int x = 0; x < 4; x++) {
            for (int y = 1; y <= 13; y++) {
                switch (x) {
                    case 0 -> this.Mazo[incr] = new Carta("clubs", y, true, true);
                    case 1 -> this.Mazo[incr] = new Carta("diamonds", y, true, true);
                    case 2 -> this.Mazo[incr] = new Carta("spades", y, true, true);
                    case 3 -> this.Mazo[incr] = new Carta("hearts", y, true, true);
                }
                incr++;
            }
        }
    }

    /*
     * GETTERS NECESARIOS DE LA CLASE
     */
    public ImageIcon getImgCarta(int i) {return Mazo[i].getImage();}
    public Carta getCarta(int i) {return this.Mazo[i];}
    public int getContador() {return this.contador;}
    public int getLateralIzquierdo() {return this.LIzq;}
    public int getLateralDerecho() {return this.LDer;}
    public int getCentro() {return this.Centro;}

    /*
     * SETTER NECESARIO DE LA CLASE
     */
    public void setCarta(int i, Carta c) {this.Mazo[i] = c;}

    /*
     * MODIFICADORES DEL CONTADOR Y DE LOS DOS LATERALES
     */
    public void IniciarContador() {this.contador = 13;}
    public void ReducirContador() {this.contador--;}
    public void ReducirLateralIzquierdo() {this.LIzq--;}
    public void AumentarLateralDerecho() {this.LDer++;}

    /*
     * RANDOMIZAR BARRAJA
     */
    public void randomizar() {
        Random r = new Random();
        Carta temp;
        for (int i = 0; i < 52; i++) {
            int j = r.nextInt(i + 1);
            temp = this.Mazo[i];
            this.Mazo[i] = this.Mazo[j];
            this.Mazo[j] = temp;
        }
    }

    /*
     * CREACIÓN DE MAZO VACÍA
     */
    public void Iniciar() {
        //Crea 13 cartas vácias (para las comprobaciones)
        Carta c = new Carta(" ", 0, false, false);
        for (int i = 0; i < 13; i++) {
            this.Mazo[i] = c;
        }
        //Inicia valores del centro y de los dos laterales
        this.Centro = 7;
        this.LIzq = 6;
        this.LDer = 8;
    }
}