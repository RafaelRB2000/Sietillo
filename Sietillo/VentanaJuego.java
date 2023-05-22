/*
* * Rafael Ramírez Berrocal y Pedro A. Serrano Sánchez
 */
package Sietillo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.BevelBorder;

/*
 * VENTANA PRINCIPAL
 */
public class VentanaJuego extends JFrame {

    //Variables usadas para el funcionamiento de la ventana
    private VentanaGanador Vg;
    private int pos, x, Fila = 0;
    private boolean TurnoJ1 = true;
    private final JPanel fondo, pBotones, pTexto, pj2, pj3, pj4;
    private final JLabel imgTras2, imgTras3, imgTras4, ContJ1, ContJ2,
            ContJ3, ContJ4;
    private final JTextField Texto;
    private final JSplitPane sp;
    private final JButton Bt1, Bt2, Bt3;
    private final JPanel[] Fila1 = new JPanel[13], Fila2 = new JPanel[13],
            Fila3 = new JPanel[13], Fila4 = new JPanel[13],
            FilaMano = new JPanel[13];
    private final JLabel[] imgFila1 = new JLabel[13],
            imgFila2 = new JLabel[13], imgFila3 = new JLabel[13],
            imgFila4 = new JLabel[13], imgFilaMano = new JLabel[13];
    private ImageIcon temp;
    private Image imgEscalada;
    private Icon iconoEscalado;
    private final Icon[] iconoEscalado1 = new Icon[13],
            iconoEscalado2 = new Icon[13], iconoEscalado3 = new Icon[13],
            iconoEscalado4 = new Icon[13];
    private final Mazo Mazo = new Mazo(52), Hearts = new Mazo(13),
            Clubs = new Mazo(13), Spades = new Mazo(13),
            Diam = new Mazo(13), Jug1 = new Mazo(13), Jug2 = new Mazo(13),
            Jug3 = new Mazo(13), Jug4 = new Mazo(13);
    private final Carta Vacia = new Carta(null, 0, false, false);

    /*
     * MAIN DEL PROGRAMA. LLAMA A LA VENTANA DEL JUEGO
     */
    public static void main(String[] args) {
        //LLamada a la ventana principal
        new VentanaJuego();
    }
    
    /*
     * INICIO DE LA VENTANA INICIAL
     */
    public VentanaJuego() {
        //Crear ventana (tamaño, botón de cerrar y sin poder modificar el tamaño)
        super("Sietillo");
        try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;

        this.setSize(height, width);
        this.setResizable(false);
        this.setBounds(100, 100, 1050, 760);

        this.setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        //JPanel para el fondo del juego
        fondo = new JPanel();
        fondo.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        fondo.setBounds(0, 0, 1034, 662);
        fondo.setBackground(new Color(0, 128, 0));
        getContentPane().add(fondo);
        fondo.setLayout(null);
        Mazo.crearCartas();

        //Conjunto de JPanel para los espacios de las cartas (4 filas de 13 JPanel's)
        pos = 10;
        for (int i = 0; i < 13; i++) {

            //Huecos de la primera fila de la baraja
            Fila1[i] = new JPanel();
            Fila1[i].setBackground(new Color(0, 100, 0));
            Fila1[i].setBounds(pos, 107, 69, 98);
            fondo.add(Fila1[i]);
            Fila1[i].setLayout(null);

            //Huecos de la segunda fila de la baraja
            Fila2[i] = new JPanel();
            Fila2[i].setBackground(new Color(0, 100, 0));
            Fila2[i].setBounds(pos, 216, 69, 98);
            fondo.add(Fila2[i]);
            Fila2[i].setLayout(null);

            //Huecos de la tercera fila de la baraja
            Fila3[i] = new JPanel();
            Fila3[i].setBackground(new Color(0, 100, 0));
            Fila3[i].setBounds(pos, 325, 69, 98);
            fondo.add(Fila3[i]);
            Fila3[i].setLayout(null);

            //Huecos de la cuarta fila de la baraja
            Fila4[i] = new JPanel();
            Fila4[i].setBackground(new Color(0, 100, 0));
            Fila4[i].setBounds(pos, 434, 69, 98);
            fondo.add(Fila4[i]);
            Fila4[i].setLayout(null);

            //Huecos de las cartas del Jugador 1
            FilaMano[i] = new JPanel();
            FilaMano[i].addMouseListener(new MouseAdapter());
            FilaMano[i].setBounds(pos, 560, 69, 98);
            FilaMano[i].setBackground(new Color(0, 100, 0));
            fondo.add(FilaMano[i]);
            FilaMano[i].setLayout(null);
            pos += 79;
        }

        //Introduce las imágenes en los huecos del mazo creados anteriormente
        CrearImagenes();

        //Jpanel para el contador de cartas del jugador 2
        pj2 = new JPanel();
        pj2.setBackground(new Color(0, 100, 0));
        pj2.setBounds(247, 5, 69, 98);
        fondo.add(pj2);
        pj2.setLayout(null);
        
        //Aprovechamos pj2 para crear icono para los contadores de los Jugadores 2, 3 y 4
        temp = new ImageIcon(VentanaJuego.class.getResource("/Imagenes/card_back_blue.png"));
        imgEscalada = temp.getImage().getScaledInstance(pj2.getWidth(), pj2.getHeight(), Image.SCALE_REPLICATE);
        iconoEscalado = new ImageIcon(imgEscalada);
        
        //JLabel para el contador del jugador 2 (no se muestra)
        ContJ2 = new JLabel("");
        ContJ2.setFocusable(false);
        ContJ2.setFont(new Font("Segoe UI Black", Font.PLAIN, 50));
        ContJ2.setHorizontalAlignment(SwingConstants.CENTER);
        ContJ2.setForeground(Color.WHITE);
        ContJ2.setBounds(0, 0, 69, 98);
        pj2.add(ContJ2);
        
        //JLabel para el diseño del contador (no se muestra)
        imgTras2 = new JLabel();
        imgTras2.setBounds(0, 0, 69, 98);
        imgTras2.setIcon(iconoEscalado);
        pj2.add(imgTras2);
        imgTras2.setVisible(false);

        //Jpanel para el indicador de cartas del jugador 3
        pj3 = new JPanel();
        pj3.setBackground(new Color(0, 100, 0));
        pj3.setBounds(484, 5, 69, 98);
        fondo.add(pj3);
        pj3.setLayout(null);

        //JLabel para el contador del jugador 3 (no se muestra)
        ContJ3 = new JLabel("");
        ContJ3.setFocusable(false);
        ContJ3.setHorizontalAlignment(SwingConstants.CENTER);
        ContJ3.setForeground(Color.WHITE);
        ContJ3.setFont(new Font("Segoe UI Black", Font.PLAIN, 50));
        ContJ3.setBounds(0, 0, 69, 98);
        pj3.add(ContJ3);

        //JLabel para el diseño del contador (no se muestra)
        imgTras3 = new JLabel();
        imgTras3.setBounds(0, 0, 69, 98);
        imgTras3.setIcon(iconoEscalado);
        pj3.add(imgTras3);
        imgTras3.setVisible(false);

        //Jpanel para el indicador de cartas del jugador 4
        pj4 = new JPanel();
        pj4.setBackground(new Color(0, 100, 0));
        pj4.setBounds(721, 5, 69, 98);
        fondo.add(pj4);
        pj4.setLayout(null);

        //JLabel para el contador del jugador 4 (no se muestra)
        ContJ4 = new JLabel("");
        ContJ4.setFocusable(false);
        ContJ4.setHorizontalAlignment(SwingConstants.CENTER);
        ContJ4.setForeground(Color.WHITE);
        ContJ4.setFont(new Font("Segoe UI Black", Font.PLAIN, 50));
        ContJ4.setBounds(0, 0, 69, 98);
        pj4.add(ContJ4);

        //JLabel para el diseño del contador (no se muestra)
        imgTras4 = new JLabel();
        imgTras4.setBounds(0, 0, 69, 98);
        imgTras4.setIcon(iconoEscalado);
        pj4.add(imgTras4);
        imgTras4.setVisible(false);

        //JLabel para el contador del jugador 1 (no se muestra)
        ContJ1 = new JLabel("");
        ContJ1.setHorizontalAlignment(SwingConstants.CENTER);
        ContJ1.setForeground(Color.WHITE);
        ContJ1.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
        ContJ1.setBounds(0, 517, 45, 58);
        fondo.add(ContJ1);
        
        //JSplitPane para colocar tanto un mensaje como los botones
        sp = new JSplitPane();
        sp.setEnabled(false);
        sp.setBounds(0, 661, 1034, 60);
        sp.setOrientation(JSplitPane.VERTICAL_SPLIT);
        getContentPane().add(sp);

        //JPanel donde contendrá el cuadro de texto
        pTexto = new JPanel();
        pTexto.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        sp.setRightComponent(pTexto);
        pTexto.setLayout(null);

        //JTextField donde se anunciarán los mensajes dependiendo del momento
        //de la partida
        Texto = new JTextField("La partida aún no ha empezado. Baraja el mazo y a jugar.");
        Texto.setFocusable(false);
        Texto.setEditable(false);
        Texto.setBounds(0, 0, 1032, 24);
        pTexto.add(Texto);
        Texto.setColumns(10);

        //JPanel donde colocar los botones
        pBotones = new JPanel();
        pBotones.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        sp.setLeftComponent(pBotones);
        pBotones.setLayout(null);

        //JButton para Mezclar las cartas
        Bt1 = new JButton("Mezclar");
        Bt1.setFocusable(false);
        Bt1.addActionListener(new manipuladorEventosFuncionalidades());
        Bt1.setBounds(279, 2, 150, 27);
        pBotones.add(Bt1);

        //JButton para Iniciar Juego y Reiniciar (va cambiando dependiendo del
        //momento de la partida)
        Bt2 = new JButton("Iniciar Juego");
        Bt2.setFocusable(false);
        Bt2.addActionListener(new manipuladorEventosFuncionalidades());
        Bt2.setBounds(439, 2, 150, 27);
        pBotones.add(Bt2);

        //JButton vacío. Servirá para pasar turno o para realizar las acciones
        //de los Jugadores 2, 3 y 4
        Bt3 = new JButton("");
        Bt3.setFocusable(false);
        Bt3.setEnabled(false);
        Bt3.setBounds(599, 2, 150, 27);
        Bt3.addActionListener(new manipuladorEventosFuncionalidades());
        pBotones.add(Bt3);
        
        //Arreglo del JSplitPane
        sp.setDividerLocation(30);
        
        //Finalizar configuración de la ventana
        this.setVisible(true);
        this.repaint();
        this.revalidate();
    }

    /*
     * CLASE PARA LA ACCIÓN DE LOS BOTONES
     */
    private class manipuladorEventosFuncionalidades implements ActionListener {

        @Override
        //Operación que realiza dicha clase
        public void actionPerformed(ActionEvent evento) {
            //Toma el texto que se visualiza en el botón
            switch (evento.getActionCommand()) {
                case "Mezclar":
                    //Mezcla las cartas del Mazo y actualiza las imágenes de las filas
                    Mazo.randomizar();
                    CambiarImagenes();
                    break;
                case "Iniciar Juego":
                    //Elimina las imágenes de las filas del mazo y reparte las cartas
                    QuitarImagenes();
                    repartir();
                    //Iniciar array de cartas vacías para futuras comprobaciones
                    Hearts.Iniciar();
                    Clubs.Iniciar();
                    Diam.Iniciar();
                    Spades.Iniciar();
                    //Mostrar imágenes en la fila del Jugador 1
                    for (int i = 0; i < 13; i++) {
                        imgFilaMano[i].setVisible(true);
                    }
                    //Iniciar contadores de los jugadores
                    ContJ1.setText(Integer.toString(Jug1.getContador()));
                    imgTras2.setVisible(true);
                    ContJ2.setText(Integer.toString(Jug2.getContador()));
                    imgTras3.setVisible(true);
                    ContJ3.setText(Integer.toString(Jug3.getContador()));
                    imgTras4.setVisible(true);
                    ContJ4.setText(Integer.toString(Jug4.getContador()));
                    //Cambio de mensaje y de botones
                    Texto.setText("Es tu turno, Jugador 1. Pulsa una de las cartas o el botón 'Pasar'.");
                    Bt1.setEnabled(false);
                    Bt1.setText("");
                    Bt2.setText("Reiniciar");
                    Bt3.setText("Pasar Turno");
                    Bt3.setEnabled(true);
                    break;
                case "Pasar Turno":
                    //El Jugador 1 no quiere jugar, por lo que continua la partida
                    TurnoJ1 = false;
                    //Cambio de mensaje y de botones
                    Texto.setText("Es turno del Jugador 2. (Pulsa el botón para que haga su acción)");
                    Bt3.setText("Turno Jugador 2");
                    break;
                case "Turno Jugador 2":
                    //Comprobar cartas de la mano del Jugador 2 automáticamente
                    x = 0;
                    while (x <= 12) {
                        //Comprobar si se ha podido usar una carta o no
                        if (comprobar(Jug2.getCarta(x)) == true) {
                            //Añadir carta a la fila correspondiente, reduce el contador
                            //del jugador y sale del bucle
                            AddCartaFila();
                            Jug2.ReducirContador();
                            ContJ2.setText(Integer.toString(Jug2.getContador()));
                            Jug2.setCarta(x, Vacia);
                            break;
                        } else {
                            //Continua la búsqueda hasta que supere el valor del while
                            x++;
                        }
                    }
                    //Comprobar si se acabaron las cartas de la mano o no
                    if (Jug2.getContador() == 0) {
                        //Cambio de mensaje y de botones
                        Texto.setText("La partida a finalizado. Pulse 'Reiniciar' para volver a comenzar la partida.");
                        Bt3.setEnabled(false);
                        Bt3.setText("");
                        //Mostrar ventana de victória
                        Vg = new VentanaGanador(1, new JFrame());
                    } else {
                        //Cambio de mensaje y de botones
                        Texto.setText("Es turno del Jugador 3. (Pulsa el botón para que haga su acción)");
                        Bt3.setText("Turno Jugador 3");
                    }
                    break;
                case "Turno Jugador 3":
                    //Comprobar cartas de la mano del Jugador 3 automáticamente
                    x = 0;
                    while (x <= 12) {
                        //Comprobar si se ha podido usar una carta o no
                        if (comprobar(Jug3.getCarta(x)) == true) {
                            //Añadir carta a la fila correspondiente, reduce el contador
                            //del jugador y sale del bucle
                            AddCartaFila();
                            Jug3.ReducirContador();
                            ContJ3.setText(Integer.toString(Jug3.getContador()));
                            Jug3.setCarta(x, Vacia);
                            break;
                        } else {
                            //Continua la búsqueda hasta que supere el valor del while
                            x++;
                        }
                    }
                    //Comprobar si se acabaron las cartas de la mano o no
                    if (Jug3.getContador() == 0) {
                        //Cambio de mensaje y de botones
                        Texto.setText("La partida a finalizado. Pulse 'Reiniciar' para volver a comenzar la partida.");
                        Bt3.setEnabled(false);
                        Bt3.setText("");
                        //Mostrar ventana de victória
                        Vg = new VentanaGanador(2, new JFrame());
                    } else {
                        //Cambio de mensaje y de botones
                        Texto.setText("Es turno del Jugador 4. (Pulsa el botón para que haga su acción)");
                        Bt3.setText("Turno Jugador 4");
                        break;
                    }
                case "Turno Jugador 4":
                    //Comprobar cartas de la mano del Jugador 4 automáticamente
                    x = 0;
                    while (x <= 12) {
                        //Comprobar si se ha podido usar una carta o no
                        if (comprobar(Jug4.getCarta(x)) == true) {
                            //Añadir carta a la fila correspondiente, reduce el contador
                            //del jugador y sale del bucle
                            AddCartaFila();
                            Jug4.ReducirContador();
                            ContJ4.setText(Integer.toString(Jug4.getContador()));
                            Jug4.setCarta(x, Vacia);
                            break;
                        } else {
                            //Continua la búsqueda hasta que supere el valor del while
                            x++;
                        }
                    }
                    //Comprobar si se acabaron las cartas de la mano o no
                    if (Jug4.getContador() == 0) {
                        //Cambio de mensaje y de botones
                        Texto.setText("La partida a finalizado. Pulse 'Reiniciar' para volver a comenzar la partida.");
                        Bt3.setEnabled(false);
                        Bt3.setText("");
                        //Mostrar ventana de victória
                        Vg = new VentanaGanador(3, new JFrame());
                    } else {
                        //Cambio de mensaje y de botones. Permite al Jugador 1 que
                        //pueda seguir jugando a pesar de no ser su turno (con el boolean)
                        TurnoJ1 = true;
                        Texto.setText("Es tu turno, Jugador 1. Pulsa una de las cartas o el botón 'Pasar'.");
                        Bt3.setText("Pasar Turno");
                    }
                    break;
                case "Reiniciar":
                    //Reinicia toda la ventana y valores a su estado inicial
                    Mazo.crearCartas();
                    ReiniciarTablero();
                    TurnoJ1 = true;
                    System.out.println(Mazo.toString());
                    Texto.setText("La partida aún no ha empezado. Baraja el mazo y a jugar.");
                    Bt1.setEnabled(true);
                    Bt1.setText("Mezclar");
                    Bt2.setText("Iniciar Juego");
                    Bt3.setText("");
                    Bt3.setEnabled(false);
                    break;
            }
        }
    }

    /*
     * CLASE PARA LA ACCIÓN DEL JUGADOR 1 CON EL RATÓN
     */
    private class MouseAdapter implements MouseListener {

        @Override
        //Operación que realiza dicha clase
        public void mouseClicked(MouseEvent evento) {
            //Ver si se ha pulsado una de las cartas de la mano del Jugador
            x = 13;
            if (evento.getComponent() == FilaMano[0]) {x = 0;}
            if (evento.getComponent() == FilaMano[1]) {x = 1;}
            if (evento.getComponent() == FilaMano[2]) {x = 2;}
            if (evento.getComponent() == FilaMano[3]) {x = 3;}
            if (evento.getComponent() == FilaMano[4]) {x = 4;}
            if (evento.getComponent() == FilaMano[5]) {x = 5;}
            if (evento.getComponent() == FilaMano[6]) {x = 6;}
            if (evento.getComponent() == FilaMano[7]) {x = 7;}
            if (evento.getComponent() == FilaMano[8]) {x = 8;}
            if (evento.getComponent() == FilaMano[9]) {x = 9;}
            if (evento.getComponent() == FilaMano[10]) {x = 10;}
            if (evento.getComponent() == FilaMano[11]) {x = 11;}
            if (evento.getComponent() == FilaMano[12]) {x = 12;}
            //Comprobar si se ha seleccionado una carta y si es el turno del jugador
            if ((x != 13) && (TurnoJ1 == true)) {
                //Comprobar si se ha podido usar una carta o no
                if (comprobar(Jug1.getCarta(x)) == true) {
                    //Añadir carta a la fila correspondiente, reduce el contador
                    //del jugador
                    AddCartaFila();
                    imgFilaMano[x].setVisible(false);
                    Jug1.ReducirContador();
                    ContJ1.setText(Integer.toString(Jug1.getContador()));
                    Jug1.setCarta(x, Vacia);
                    //Comprobar si se acabaron las cartas de la mano o no
                    if (Jug1.getContador() == 0) {
                        //Cambio de mensaje y de botones
                        Texto.setText("La partida a finalizado. Pulse 'Reiniciar' para volver a comenzar la partida.");
                        Bt3.setEnabled(false);
                        Bt3.setText("");
                        //Mostrar ventana de victória
                        Vg = new VentanaGanador(0, new JFrame());
                    } else {
                        //Cambio de mensaje y de botones. Impide que el Jugador 1
                        //pueda seguir jugando a pesar de no ser su turno (con el boolean)
                        TurnoJ1 = false;
                        Texto.setText("Es turno del Jugador 2. (Pulsa el botón para que haga su acción)");
                        Bt3.setText("Turno Jugador 2");
                    }
                }
            }
        }

        //OPERACIONES VACÍAS
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }

    /*
     * CREACIÓN INICIAL DE LAS IMÁGENES
     */
    public void CrearImagenes() {
        //Crea arrays de JLabel para visualizar las cartas
        x = 0;
        //Imágenes de la Fila 1 del Mazo y la Fila del Jugador 1
        for (int i = 0; i < 13; i++) {
            imgFila1[i] = new JLabel();
            imgFila1[i].setBounds(0, 0, 69, 98);
            temp = Mazo.getImgCarta(x);
            imgEscalada = temp.getImage().getScaledInstance(imgFila1[i].getWidth(), imgFila1[i].getHeight(), Image.SCALE_REPLICATE);
            iconoEscalado1[i] = new ImageIcon(imgEscalada);
            imgFila1[i].setIcon(iconoEscalado1[i]);
            Fila1[i].add(imgFila1[i]);
            
            //Como en el juego el Jugador 1 recibe la primera Fila, se reutiliza
            //los iconos creados anteriormente.
            imgFilaMano[i] = new JLabel();
            imgFilaMano[i].setBounds(0, 0, 69, 98);
            imgFilaMano[i].setIcon(iconoEscalado1[i]);
            FilaMano[i].add(imgFilaMano[i]);
            imgFilaMano[i].setVisible(false);
            x++;
        }
        
        //Imágenes de la Fila 2 del Mazo
        for (int i = 0; i < 13; i++) {
            imgFila2[i] = new JLabel();
            imgFila2[i].setBounds(0, 0, 69, 98);
            temp = Mazo.getImgCarta(x);
            imgEscalada = temp.getImage().getScaledInstance(imgFila2[i].getWidth(), imgFila2[i].getHeight(), Image.SCALE_REPLICATE);
            iconoEscalado2[i] = new ImageIcon(imgEscalada);
            imgFila2[i].setIcon(iconoEscalado2[i]);
            Fila2[i].add(imgFila2[i]);
            x++;
        }

        //Imágenes de la Fila 3 del Mazo
        for (int i = 0; i < 13; i++) {
            imgFila3[i] = new JLabel();
            imgFila3[i].setBounds(0, 0, 69, 98);
            temp = Mazo.getImgCarta(x);
            imgEscalada = temp.getImage().getScaledInstance(imgFila3[i].getWidth(), imgFila3[i].getHeight(), Image.SCALE_REPLICATE);
            iconoEscalado3[i] = new ImageIcon(imgEscalada);
            imgFila3[i].setIcon(iconoEscalado3[i]);
            Fila3[i].add(imgFila3[i]);
            x++;
        }

        //Imágenes de la Fila 4 del Mazo
        for (int i = 0; i < 13; i++) {
            imgFila4[i] = new JLabel();
            imgFila4[i].setBounds(0, 0, 69, 98);
            temp = Mazo.getImgCarta(x);
            imgEscalada = temp.getImage().getScaledInstance(imgFila4[i].getWidth(), imgFila4[i].getHeight(), Image.SCALE_REPLICATE);
            iconoEscalado4[i] = new ImageIcon(imgEscalada);
            imgFila4[i].setIcon(iconoEscalado4[i]);
            Fila4[i].add(imgFila4[i]);
            x++;
        }
    }

    /*
     * ACTUALIZA LAS ÍMAGENES DE LAS CARTAS DE LA VENTANA CON RESPECTO AL MAZO
     */
    public void CambiarImagenes() {
        x = 0;
        //Actualización de la Fila 1 del Mazo y la Fila del Jugador 1
        for (int i = 0; i < 13; i++) {
            temp = Mazo.getImgCarta(x);
            imgEscalada = temp.getImage().getScaledInstance(imgFila1[i].getWidth(), imgFila1[i].getHeight(), Image.SCALE_REPLICATE);
            iconoEscalado = new ImageIcon(imgEscalada);
            imgFila1[i].setIcon(iconoEscalado);
            
            //Como en el juego el Jugador 1 recibe la primera Fila, se reutiliza
            //los iconos creados anteriormente.
            imgFilaMano[i].setIcon(iconoEscalado);
            imgFilaMano[i].setVisible(false);
            x++;
        }

        //Actualización de la Fila 2 del Mazo y la Fila del Jugador 1
        for (int i = 0; i < 13; i++) {
            temp = Mazo.getImgCarta(x);
            imgEscalada = temp.getImage().getScaledInstance(imgFila2[i].getWidth(), imgFila2[i].getHeight(), Image.SCALE_REPLICATE);
            iconoEscalado = new ImageIcon(imgEscalada);
            imgFila2[i].setIcon(iconoEscalado);
            x++;
        }

        //Actualización de la Fila 3 del Mazo y la Fila del Jugador 1
        for (int i = 0; i < 13; i++) {
            temp = Mazo.getImgCarta(x);
            imgEscalada = temp.getImage().getScaledInstance(imgFila3[i].getWidth(), imgFila3[i].getHeight(), Image.SCALE_REPLICATE);
            iconoEscalado = new ImageIcon(imgEscalada);
            imgFila3[i].setIcon(iconoEscalado);
            x++;
        }

        //Actualización de la Fila 4 del Mazo y la Fila del Jugador 1
        for (int i = 0; i < 13; i++) {
            temp = Mazo.getImgCarta(x);
            imgEscalada = temp.getImage().getScaledInstance(imgFila4[i].getWidth(), imgFila4[i].getHeight(), Image.SCALE_REPLICATE);
            iconoEscalado = new ImageIcon(imgEscalada);
            imgFila4[i].setIcon(iconoEscalado);
            x++;
        }
    }

    /*
     * AÑADIR CARTA A LA FILA CORRESPONDIENTE
     */
    private void AddCartaFila() {
        //Comprueba que fila debe colocar la carta
        switch (Fila) {
            case 1 -> {
                //Colocar carta en la Fila 1
                imgFila1[pos].setVisible(true);
                imgFila1[pos].setIcon(iconoEscalado1[pos]);
            }
            case 2 -> {
                //Colocar carta en la Fila 2
                imgFila2[pos].setVisible(true);
                imgFila2[pos].setIcon(iconoEscalado2[pos]);
            }
            case 3 -> {
                //Colocar carta en la Fila 3
                imgFila3[pos].setVisible(true);
                imgFila3[pos].setIcon(iconoEscalado3[pos]);
            }
            case 4 -> {
                //Colocar carta en la Fila 4
                imgFila4[pos].setVisible(true);
                imgFila4[pos].setIcon(iconoEscalado4[pos]);
            }
        }
    }

    /*
     * QUITAR LAS IMÁGENES DE LAS CARTAS DE LAS FILAS DEL MAZO
     */
    private void QuitarImagenes() {
        for (int i = 0; i < 13; i++) {
            imgFila1[i].setVisible(false);
            imgFila2[i].setVisible(false);
            imgFila3[i].setVisible(false);
            imgFila4[i].setVisible(false);
        }
    }

    /*
     * REINICIA LAS CARTAS DEL TABLERO
     */
    public void ReiniciarTablero() {
        //Cambio de las cartas de las Filas del Mazo y del Jugador 1 (esta última
        //no se mostrarán)
        for (int i = 0; i < 13; i++) {
            imgFila1[i].setVisible(true);
            imgFila1[i].setIcon(iconoEscalado1[i]);
            imgFila2[i].setVisible(true);
            imgFila2[i].setIcon(iconoEscalado2[i]);
            imgFila3[i].setVisible(true);
            imgFila3[i].setIcon(iconoEscalado3[i]);
            imgFila4[i].setVisible(true);
            imgFila4[i].setIcon(iconoEscalado4[i]);
            imgFilaMano[i].setIcon(iconoEscalado1[i]);
            imgFilaMano[i].setVisible(false);
        }
        //Ocultar contadores
        imgTras2.setVisible(false);
        imgTras3.setVisible(false);
        imgTras4.setVisible(false);
        ContJ1.setText("");
        ContJ2.setText("");
        ContJ3.setText("");
        ContJ4.setText("");
    }

    /*
     * REPARTIR LAS CARTAS A LOS JUGADORES
     */
    public void repartir() {
        int Rep = 0;
        //Repartición de las cartas en orden
        for (int i = 0; i < 4; i++) {
            for (int y = 0; y < 13; y++) {
                switch (i) {
                    case 0 ->
                        //Repartir las 13 primeras cartas
                        Jug1.setCarta(y, Mazo.getCarta(Rep));
                    case 1 ->
                        //Repartir las 13 segundas cartas
                        Jug2.setCarta(y, Mazo.getCarta(Rep));
                    case 2 ->
                        //Repartir las 13 penúltimas cartas
                        Jug3.setCarta(y, Mazo.getCarta(Rep));
                    case 3 ->
                        //Repartir las 13 últimas cartas
                        Jug4.setCarta(y, Mazo.getCarta(Rep));
                }
                Rep++;
            }
        }
        //Iniciar los contadores 
        Jug1.IniciarContador();
        Jug2.IniciarContador();
        Jug3.IniciarContador();
        Jug4.IniciarContador();
    }

    /*
     * COMPROBAR SI PUEDE COLOCAR CARTA O NO
     */
    public boolean comprobar(Carta temp) {
        //Variable para mandar como respuesta
        boolean usada = false;
        //Comprueba si la carta seleccionada no fue colocada
        if (temp.getExiste() == true) {
            //Comprueba que palo es la carta
            switch (temp.getPalo()) {
                //CASO DEL PALO TRÉBOL
                case "clubs" -> {
                    //Comprueba si es la carta del centro (el 7)
                    if (temp.getNum() == Clubs.getCentro()) {
                        //Coloca la carta, indica la fila y la posición que debe
                        //ser colocada la imagen de la carta y cambia el booleano
                        Clubs.setCarta(Clubs.getCentro() - 1, temp);
                        Fila = 1;
                        pos = Clubs.getCentro() - 1;
                        usada = true;
                        //Sale del switch
                        break;
                    }
                    if (temp.getNum() == Clubs.getLateralIzquierdo() && Clubs.getCarta(Clubs.getLateralIzquierdo()).getExiste() == true) {
                        //Coloca la carta, indica la fila y la posición que debe
                        //ser colocada la imagen de la carta, reduce el contador del
                        //lateral izquierdo y cambia el booleano
                        Clubs.setCarta(Clubs.getLateralIzquierdo() - 1, temp);
                        Fila = 1;
                        pos = Clubs.getLateralIzquierdo() - 1;
                        Clubs.ReducirLateralIzquierdo();
                        usada = true;
                        //Sale del switch
                        break;
                    }
                    if (temp.getNum() == Clubs.getLateralDerecho() && Clubs.getCarta(Clubs.getLateralDerecho() - 2).getExiste() == true) {
                        //Coloca la carta, indica la fila y la posición que debe
                        //ser colocada la imagen de la carta, aumenta el contador del
                        //lateral derecho y cambia el booleano
                        Clubs.setCarta(Clubs.getLateralDerecho() - 1, temp);
                        Fila = 1;
                        pos = Clubs.getLateralDerecho() - 1;
                        Clubs.AumentarLateralDerecho();
                        usada = true;
                        //Sale del switch
                        break;
                    }
                }
                //CASO DEL PALO DIAMANTE
                case "diamonds" -> {
                    //Comprueba si es la carta del centro (el 7)
                    if (temp.getNum() == Diam.getCentro()) {
                        //Coloca la carta, indica la fila y la posición que debe
                        //ser colocada la imagen de la carta y cambia el booleano
                        Diam.setCarta(Diam.getCentro() - 1, temp);
                        Fila = 2;
                        pos = Diam.getCentro() - 1;
                        usada = true;
                        //Sale del switch
                        break;
                    }
                    if (temp.getNum() == Diam.getLateralIzquierdo() && Diam.getCarta(Diam.getLateralIzquierdo()).getExiste() == true) {
                        //Coloca la carta, indica la fila y la posición que debe
                        //ser colocada la imagen de la carta, reduce el contador del
                        //lateral izquierdo y cambia el booleano
                        Diam.setCarta(Diam.getLateralIzquierdo() - 1, temp);
                        Fila = 2;
                        pos = Diam.getLateralIzquierdo() - 1;
                        Diam.ReducirLateralIzquierdo();
                        usada = true;
                        break;
                    }
                    if (temp.getNum() == Diam.getLateralDerecho() && Diam.getCarta(Diam.getLateralDerecho() - 2).getExiste() == true) {
                        //Coloca la carta, indica la fila y la posición que debe
                        //ser colocada la imagen de la carta, aumenta el contador del
                        //lateral derecho y cambia el booleano
                        Diam.setCarta(Diam.getLateralDerecho() - 1, temp);
                        Fila = 2;
                        pos = Diam.getLateralDerecho() - 1;
                        Diam.AumentarLateralDerecho();
                        usada = true;
                        //Sale del switch
                        break;
                    }
                }
                //CASO DEL PALO PICA
                case "spades" -> {
                    //Comprueba si es la carta del centro (el 7)
                    if (temp.getNum() == Spades.getCentro()) {
                        //Coloca la carta, indica la fila y la posición que debe
                        //ser colocada la imagen de la carta y cambia el booleano
                        Spades.setCarta(Spades.getCentro() - 1, temp);
                        Fila = 3;
                        pos = Spades.getCentro() - 1;
                        usada = true;
                        //Sale del switch
                        break;
                    }
                    if (temp.getNum() == Spades.getLateralIzquierdo() && Spades.getCarta(Spades.getLateralIzquierdo()).getExiste() == true) {
                        //Coloca la carta, indica la fila y la posición que debe
                        //ser colocada la imagen de la carta, reduce el contador del
                        //lateral izquierdo y cambia el booleano
                        Spades.setCarta(Spades.getLateralIzquierdo() - 1, temp);
                        Fila = 3;
                        pos = Spades.getLateralIzquierdo() - 1;
                        Spades.ReducirLateralIzquierdo();
                        usada = true;
                        break;
                    }
                    if (temp.getNum() == Spades.getLateralDerecho() && Spades.getCarta(Spades.getLateralDerecho() - 2).getExiste() == true) {
                        //Coloca la carta, indica la fila y la posición que debe
                        //ser colocada la imagen de la carta, aumenta el contador del
                        //lateral derecho y cambia el booleano
                        Spades.setCarta(Spades.getLateralDerecho() - 1, temp);
                        Fila = 3;
                        pos = Spades.getLateralDerecho() - 1;
                        Spades.AumentarLateralDerecho();
                        usada = true;
                        //Sale del switch
                        break;
                    }
                }
                //CASO DEL PALO CORAZÓN
                case "hearts" -> {
                    //Comprueba si es la carta del centro (el 7)
                    if (temp.getNum() == Hearts.getCentro()) {
                        //Coloca la carta, indica la fila y la posición que debe
                        //ser colocada la imagen de la carta y cambia el booleano
                        Hearts.setCarta(Hearts.getCentro() - 1, temp);
                        Fila = 4;
                        pos = Hearts.getCentro() - 1;
                        usada = true;
                        //Sale del switch
                        break;
                    }
                    if (temp.getNum() == Hearts.getLateralIzquierdo() && Hearts.getCarta(Hearts.getLateralIzquierdo()).getExiste() == true) {
                        //Coloca la carta, indica la fila y la posición que debe
                        //ser colocada la imagen de la carta, reduce el contador del
                        //lateral izquierdo y cambia el booleano
                        Hearts.setCarta(Hearts.getLateralIzquierdo() - 1, temp);
                        Fila = 4;
                        pos = Hearts.getLateralIzquierdo() - 1;
                        Hearts.ReducirLateralIzquierdo();
                        usada = true;
                        //Sale del switch
                        break;
                    }
                    if (temp.getNum() == Hearts.getLateralDerecho() && Hearts.getCarta(Hearts.getLateralDerecho() - 2).getExiste() == true) {
                        //Coloca la carta, indica la fila y la posición que debe
                        //ser colocada la imagen de la carta, aumenta el contador del
                        //lateral derecho y cambia el booleano
                        Hearts.setCarta(Hearts.getLateralDerecho() - 1, temp);
                        Fila = 4;
                        pos = Hearts.getLateralDerecho() - 1;
                        Hearts.AumentarLateralDerecho();
                        usada = true;
                        //Sale del switch
                        break;
                    }
                }
            }
        }
        //Devuelve el booleano
        return usada;
    }
}