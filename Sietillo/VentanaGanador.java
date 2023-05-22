/*
* * Rafael Ramírez Berrocal y Pedro A. Serrano Sánchez
 */
package Sietillo;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

/*
 * VENTANA SECUNDARIA
 */
public class VentanaGanador extends JDialog {

    //Variables usadas para el funcionamiento de la ventana
    private final ImageIcon temp;
    private final Image imgEscalada;
    private final Icon iconoEscalado;
    private final JLabel Mensaje, Jugador;
    private final JButton Cerrar;

    /*
     * INICIO DE LA VENTANA SECUNDARIA
     */
    public VentanaGanador(int x, JFrame Padre) {
        //Crear ventana (tamaño, botón de cerrar y sin poder modificar el tamaño)
        super(Padre, "¡FIN DE LA PARTIDA!", Dialog.ModalityType.APPLICATION_MODAL);
        setForeground(Color.BLACK);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(375, 275);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        //JLabel que mostrará el mensaje del jugador ganador (dependiendo del valor
        //x que llegue al iniciar la ventana)
        Mensaje = new JLabel("");
        Mensaje.setBounds(176, 83, 173, 69);
        Mensaje.setFont(new Font("Tahoma", Font.PLAIN, 15));
        switch (x) {
            case 0 -> Mensaje.setText("¡Has Ganado!");
            case 1 -> Mensaje.setText("¡Jugador 2 ha ganado!");
            case 2 -> Mensaje.setText("¡Jugador 3 ha ganado!");
            case 3 -> Mensaje.setText("¡Jugador 4 ha ganado!");
        }
        getContentPane().add(Mensaje);

        //JLabel que muestra la imagen del jugador ganador (dependiendo del valor
        //x que llegue al iniciar la ventana)
        Jugador = new JLabel("");
        Jugador.setBounds(36, 23, 130, 193);
        Jugador.setLayout(null);
        temp = new ImageIcon(VentanaGanador.class.getResource("/Imagenes/Jug" + x + "Riu.png"));
        imgEscalada = temp.getImage().getScaledInstance(Jugador.getWidth(), Jugador.getHeight(), Image.SCALE_REPLICATE);
        iconoEscalado = new ImageIcon(imgEscalada);
        Jugador.setIcon(iconoEscalado);
        getContentPane().add(Jugador);

        //JButton para confirmar la victoria y cerrar la ventana secundaria
        Cerrar = new JButton("Ok");
        Cerrar.addActionListener((ActionEvent e) -> {
            dispose();
        });
        Cerrar.setBounds(230, 193, 89, 23);
        getContentPane().add(Cerrar);

        //Finalizar configuración de la ventana
        this.setVisible(true);
        this.repaint();
        this.revalidate();
    }
}
