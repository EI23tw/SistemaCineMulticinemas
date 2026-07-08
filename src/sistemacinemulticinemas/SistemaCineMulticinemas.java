package sistemacinemulticinemas;

import vista.VentanaPrincipal; 

public class SistemaCineMulticinemas {

    public static void main(String[] args) {
        // 1. Creamos el objeto de tu ventana
        VentanaPrincipal miVentana = new VentanaPrincipal();
        
        // 2. Hacemos que aparezca centrada en la pantalla
        miVentana.setLocationRelativeTo(null);
        
        // 3. La encendemos (la hacemos visible)
        miVentana.setVisible(true);
    }
}